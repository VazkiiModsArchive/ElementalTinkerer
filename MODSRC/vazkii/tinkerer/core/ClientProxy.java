/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.core;

import java.awt.Color;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.client.handler.KeybindHandler;
import vazkii.tinkerer.client.handler.LightningRenderHandler;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.client.hud.HudElementSpellTooltip;
import vazkii.tinkerer.client.hud.HudElementVignette;
import vazkii.tinkerer.client.particle.EntityFXColoredPortal;
import vazkii.tinkerer.client.particle.EntityFXGaseousGlowstone;
import vazkii.tinkerer.client.particle.EntityFXSteam;
import vazkii.tinkerer.client.render.RenderBoulder;
import vazkii.tinkerer.client.render.RenderElementiumGuardian;
import vazkii.tinkerer.client.render.RenderElementiumLocator;
import vazkii.tinkerer.client.render.RenderProjectile;
import vazkii.tinkerer.client.tilerender.TileEntityRenderAttuner;
import vazkii.tinkerer.client.tilerender.TileEntityRenderCatalystCapsule;
import vazkii.tinkerer.client.tilerender.TileEntityRenderElementalDesk;
import vazkii.tinkerer.client.tilerender.TileEntityRenderRotatingCubes;
import vazkii.tinkerer.client.tilerender.TileEntityRenderVoidGateway;
import vazkii.tinkerer.entity.EntityBoulder;
import vazkii.tinkerer.entity.EntityElementiumGuardian;
import vazkii.tinkerer.entity.EntityElementiumLocator;
import vazkii.tinkerer.entity.EntityFireball;
import vazkii.tinkerer.entity.EntityFrostBolt;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.magic.PlayerSpellData;
import vazkii.tinkerer.network.packet.PacketLightning;
import vazkii.tinkerer.network.packet.PacketResearchData;
import vazkii.tinkerer.network.packet.PacketSpells;
import vazkii.tinkerer.network.packet.PacketVerification;
import vazkii.tinkerer.network.packet.PacketVoidStorage;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.RenderIDs;
import vazkii.tinkerer.tile.TileEntityAttuner;
import vazkii.tinkerer.tile.TileEntityCatalystCapsule;
import vazkii.tinkerer.tile.TileEntityElementalDesk;
import vazkii.tinkerer.tile.TileEntityRotatingCubes;
import vazkii.tinkerer.tile.TileEntityVoidGateway;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ClientProxy
 *
 * Extension of CommonProxy, providing client side specific functionality.
 *
 * @author Vazkii
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void registerTileEntities() {
		super.registerTileEntities();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElementalDesk.class, TileEntityRenderElementalDesk.INSTANCE);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCatalystCapsule.class, TileEntityRenderCatalystCapsule.INSTANCE);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAttuner.class, TileEntityRenderAttuner.INSTANCE);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidGateway.class, TileEntityRenderVoidGateway.INSTANCE);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRotatingCubes.class, TileEntityRenderRotatingCubes.INSTANCE);
	}

	@Override
	public void registerPackets() {
		super.registerPackets();
		PacketHelper.packetHandlers.add(PacketVerification.INSTANCE);
		PacketHelper.packetHandlers.add(PacketResearchData.RECIEVER_INSTANCE);
		PacketHelper.packetHandlers.add(PacketLightning.RECIEVER_INSTANCE);
		PacketHelper.packetHandlers.add(PacketSpells.RECIEVER_INSTANCE);
		PacketHelper.packetHandlers.add(PacketVoidStorage.RECIEVER_INSTANCE);
	}

	@Override
	public void registerTickHandler() {
		super.registerTickHandler();
		TickRegistry.registerTickHandler(ClientTickHandler.INSTANCE, Side.CLIENT);
	}

	@Override
	public void initCustomSpritesheets() {
		IconHelper.initCustonSpritesheets();
	}

	@Override
	public void registerClientHandlers() {
		// Register the Keybind Handler
		KeybindHandler.INSTANCE = KeybindHandler.createInstance();
		KeyBindingRegistry.registerKeyBinding(KeybindHandler.INSTANCE);

		// Register, in the Event Bus, the Lightning Render Handler
		MinecraftForge.EVENT_BUS.register(LightningRenderHandler.INSTANCE);

		// Register the vignettes, not static as it requires a potion effect initialized in the mod
		HudElementVignette.registerViginettes();
	}

	@Override
	public void mapEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityElementiumGuardian.class,
														 new RenderElementiumGuardian());
		RenderingRegistry.registerEntityRenderingHandler(EntityFireball.class,
														 new RenderProjectile(new ItemStack(Item.fireballCharge), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostBolt.class,
														 new RenderProjectile(new ItemStack(Item.snowball), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBoulder.class,
														 new RenderBoulder());
		RenderingRegistry.registerEntityRenderingHandler(EntityElementiumLocator.class,
														 new RenderElementiumLocator());
	}

	@Override
	public void registerBlockRenders() {
		RenderIDs.elementalDesk = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.catalystContainer = RenderingRegistry.getNextAvailableRenderId();
		RenderIDs.rotatingBlocks = RenderingRegistry.getNextAvailableRenderId();

		MinecraftForgeClient.registerItemRenderer(BlockIDs.elementalDesk, TileEntityRenderElementalDesk.INSTANCE);
		MinecraftForgeClient.registerItemRenderer(BlockIDs.catalystCapsule, TileEntityRenderCatalystCapsule.INSTANCE);
		MinecraftForgeClient.registerItemRenderer(BlockIDs.waveInputter, TileEntityRenderRotatingCubes.INSTANCE);
		MinecraftForgeClient.registerItemRenderer(BlockIDs.dislocator, TileEntityRenderRotatingCubes.INSTANCE);
		MinecraftForgeClient.registerItemRenderer(BlockIDs.scavenger, TileEntityRenderRotatingCubes.INSTANCE);
		MinecraftForgeClient.registerItemRenderer(BlockIDs.incinerator, TileEntityRenderRotatingCubes.INSTANCE);
	}

	@Override
	public void readResearchDescriptions() {
		ResearchHelper.readResearchDescriptions();
	}

	@Override
	public void initClientSpells() {
		if(SpellHelper.clientSpells == null) {
			SpellHelper.clientSpells = new PlayerSpellData(MiscHelper.getClientPlayer().username);
			SpellHelper.clientSpells.flagClientSided();
		}
	}

	@Override
	public void setItemOnScreenTooltip(String tooltip) {
		HudElementSpellTooltip.INSTANCE.setTooltip(tooltip);
	}

	@Override
	public long getGameTicksElapsed() {
		return ClientTickHandler.elapsedClientTicks;
	}

	@Override
	public boolean isServerPVP() {
		return false;
	}

	@Override
	public void spawnColoredPortalParticle(Color color, World world, double x, double y, double z, double motionX, double motionY, double motionZ, boolean staticMotion) {
		EntityFXColoredPortal.spawn(color, world, x, y, z, motionX, motionY, motionZ, staticMotion);
	}

	@Override
	public void spawnSteamParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		EntityFXSteam.spawn(world, x, y, z, motionX, motionY, motionZ);
	}

	@Override
	public void spawnGlowstoneAirParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		EntityFXGaseousGlowstone.spawn(world, x, y, z, motionX, motionY, motionZ);
	}
}