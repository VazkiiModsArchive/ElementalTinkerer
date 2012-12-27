/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Dec 2012
package vazkii.tinkerer;

import vazkii.tinkerer.block.ElementalTinkererBlocks;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.core.CommonProxy;
import vazkii.tinkerer.entity.ElementalTinkererEntities;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.handler.GuiHandler;
import vazkii.tinkerer.handler.WorldGenerationHandler;
import vazkii.tinkerer.item.ElementalTinkererItems;
import vazkii.tinkerer.reference.AnnotationConstants;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * ElementalTinkerer
 *
 * The Main Mod class for Elemental Tinkerer.
 *
 * @author Vazkii
 */
@Mod(modid = AnnotationConstants.MOD_ID,
     name = AnnotationConstants.MOD_NAME,
     version = AnnotationConstants.VERSION)

@NetworkMod()
public class ElementalTinkerer {

	@Instance(AnnotationConstants.MOD_ID)
	public static ElementalTinkerer instance;

	@SidedProxy(clientSide = AnnotationConstants.CLIENT_PROXY_CLASS,
			    serverSide = AnnotationConstants.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@PreInit
	public void onPreInit(FMLPreInitializationEvent event) {
		// Init the config, passing in the configuration file FML suggests for this mod
		ConfigurationHandler.initConfig(event.getSuggestedConfigurationFile());

		// Preloads the textures, in order to avoid possible visual anomalies
		proxy.preloadTextures();
	}

	@Init
	public void onInit(FMLInitializationEvent event) {
		// Register the client tick handler
		TickRegistry.registerTickHandler(ClientTickHandler.INSTANCE, Side.CLIENT);

		// Register the Gui Handler
		NetworkRegistry.instance().registerGuiHandler(instance, GuiHandler.INSTANCE);

		// Init the mod's Items
		ElementalTinkererItems.init();

		// Init the mod's Blocks
		ElementalTinkererBlocks.init();

		// Init the mod's Entities
		ElementalTinkererEntities.init();

		// Register the World Generation
		GameRegistry.registerWorldGenerator(WorldGenerationHandler.INSTANCE);

		// Proxy: Register the mod's Tile Entities
		proxy.registerTileEntities();

		// Proxy: Map the Entity Renders
		proxy.mapEntityRenderers();

		// Proxy: Register the Block Renders
		proxy.registerBlockRenders();
	}

	@PostInit
	public void onPostInit(FMLPostInitializationEvent event) {
		//NO-OP for now
	}

}
