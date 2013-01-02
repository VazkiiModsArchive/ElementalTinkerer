/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.core;

import java.awt.Color;

import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import vazkii.tinkerer.client.particle.EntityFXColoredPortal;
import vazkii.tinkerer.client.render.RenderElementiumGuardian;
import vazkii.tinkerer.client.tilerender.TileEntityRenderElementalDesk;
import vazkii.tinkerer.entity.EntityElementiumGuardian;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.network.packet.PacketVerification;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.RenderIDs;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.TileEntityElementalDesk;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
	}

	@Override
	public void registerPackets() {
		super.registerPackets();
		PacketHelper.packetHandlers.add(PacketVerification.RECIEVER_INSTANCE);
	}

	@Override
	public void mapEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityElementiumGuardian.class,
														 new RenderElementiumGuardian());
	}

	@Override
	public void registerBlockRenders() {
		RenderIDs.elementalDesk = RenderingRegistry.getNextAvailableRenderId();
		MinecraftForgeClient.registerItemRenderer(BlockIDs.elementalDesk, TileEntityRenderElementalDesk.INSTANCE);
	}

	@Override
	public void preloadTextures() {
		MinecraftForgeClient.preloadTexture(ResourcesReference.BLOCKS_SPRITESHEET);
		MinecraftForgeClient.preloadTexture(ResourcesReference.ITEMS_32_SPRITESHEET);
	}

	@Override
	public void spawnColoredPortalParticle(Color color, World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		EntityFXColoredPortal.spawn(color, world, x, y, z, motionX, motionY, motionZ);
	}
}
