/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.core;

import java.awt.Color;

import net.minecraft.world.World;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.network.packet.PacketCompleteResearch;
import vazkii.tinkerer.network.packet.PacketElementalDeskSync;
import vazkii.tinkerer.network.packet.PacketElementalistTinkeringAltarStartRecipe;
import vazkii.tinkerer.network.packet.PacketElementalistTinkeringAltarSync;
import vazkii.tinkerer.reference.AnnotationConstants;
import vazkii.tinkerer.reference.BlockNames;
import vazkii.tinkerer.tile.TileEntityCatalystCapsule;
import vazkii.tinkerer.tile.TileEntityElementalDesk;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * CommonProxy
 *
 * Common proxy, used between the client and the server.
 * The client and server proxies extend this class to
 * add functionality only available on the specific side.
 *
 * @author Vazkii
 */
public class CommonProxy {

	/** Registers the tile entities, used by the client to
	 * also register the renderers **/
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityElementalDesk.class, AnnotationConstants.MOD_NAME + "_" + BlockNames.ELEMENTAL_DESK_NAME);
		GameRegistry.registerTileEntity(TileEntityCatalystCapsule.class, AnnotationConstants.MOD_NAME + "_" + BlockNames.CATALYST_CAPSULE_NAME);
		GameRegistry.registerTileEntity(TileEntityElementalTinkeringAltar.class, AnnotationConstants.MOD_NAME + "_" + BlockNames.ELEMENTALIST_TINKERING_ALTAR_NAME);
	}

	/** Registers the mod's packets. Some packets use different
	 * instances per side. **/
	public void registerPackets() {
		PacketHelper.packetHandlers.add(PacketElementalDeskSync.RECIEVER_INSTANCE);
		PacketHelper.packetHandlers.add(PacketElementalistTinkeringAltarSync.RECIEVER_INSTANCE);
		PacketHelper.packetHandlers.add(PacketElementalistTinkeringAltarStartRecipe.RECIEVER_INSTANCE);
		PacketHelper.packetHandlers.add(PacketCompleteResearch.RECIEVER_INSTANCE);
	}

	/** Used by the client to map entity classes to their adequate
	 *  render instances. NO-OP in server side. **/
	public void mapEntityRenderers() {
		// NO-OP
	}

	/** Registers various block Renders. NO-OP in server side **/
	public void registerBlockRenders() {
		//NO-OP
	}

	/** Preloads the textures, to avoid render glitches, NO-OP
	 * in server side **/
	public void preloadTextures() {
		// NO-OP
	}

	/** Reads the research descriptions, used to show the text
	 * for the researches in the Elementalist's Lexicon GUI,
	 * NO-OP in server side. **/
	public void readResearchDescriptions() {
		// NO-OP
	}

	/** Used by the client to spawn a colored portal particle at
	 * the location passed in, with the color and motion passed
	 * in. NO-OP in server side. **/
	public void spawnColoredPortalParticle(Color color, World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		// NO-OP
	}
}
