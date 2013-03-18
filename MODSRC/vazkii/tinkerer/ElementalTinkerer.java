/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Dec 2012
package vazkii.tinkerer;

import java.util.logging.Logger;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import vazkii.tinkerer.block.ElementalTinkererBlocks;
import vazkii.tinkerer.block.voidStorage.VoidMap;
import vazkii.tinkerer.command.CommandResearch;
import vazkii.tinkerer.compat.Thaumcraft3Compat;
import vazkii.tinkerer.core.CommonProxy;
import vazkii.tinkerer.entity.ElementalTinkererEntities;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.handler.ElementiumDustDropsHandler;
import vazkii.tinkerer.handler.FrozenEntityHandler;
import vazkii.tinkerer.handler.GuiHandler;
import vazkii.tinkerer.handler.InteractionAccessHandler;
import vazkii.tinkerer.handler.ItemCraftingHandler;
import vazkii.tinkerer.handler.PlayerSpellUpdateHandler;
import vazkii.tinkerer.handler.PlayerTrackingHandler;
import vazkii.tinkerer.handler.WorldGenerationHandler;
import vazkii.tinkerer.helper.ElementiumOreGenerationHelper;
import vazkii.tinkerer.helper.IOHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.item.ElementalTinkererItems;
import vazkii.tinkerer.magic.SpellLibrary;
import vazkii.tinkerer.network.PacketHandler;
import vazkii.tinkerer.potion.ElementalTinkererPotions;
import vazkii.tinkerer.reference.AnnotationConstants;
import vazkii.tinkerer.reference.NetworkReference;
import vazkii.tinkerer.research.ResearchLibrary;
import vazkii.tinkerer.research.trigger.CraftingRecipesTrigger;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.Mod.ServerStopped;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

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

@NetworkMod(clientSideRequired = true,
			channels = { NetworkReference.CHANNEL_NAME },
			packetHandler = PacketHandler.class)
public class ElementalTinkerer {

	public static Logger logger;

	@Instance(AnnotationConstants.MOD_ID)
	public static ElementalTinkerer instance;

	@SidedProxy(clientSide = AnnotationConstants.CLIENT_PROXY_CLASS,
			    serverSide = AnnotationConstants.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@PreInit
	public void onPreInit(FMLPreInitializationEvent event) {
		// Get the logger
		logger = event.getModLog();

		// Init the config, passing in the configuration file FML suggests for this mod
		ConfigurationHandler.initConfig(event.getSuggestedConfigurationFile());

		// Init the Spell Data
		SpellLibrary.initSpells();
	}

	@Init
	public void onInit(FMLInitializationEvent event) {
		// Proxy: Register the tick handler
		proxy.registerTickHandler();

		// Register the Gui Handler
		NetworkRegistry.instance().registerGuiHandler(instance, GuiHandler.INSTANCE);

		// Register the Player Tracker
		GameRegistry.registerPlayerTracker(PlayerTrackingHandler.INSTANCE);

		// Register the Research Crafting Handler
		GameRegistry.registerCraftingHandler(CraftingRecipesTrigger.INSTANCE);

		// Register the Item Handler
		GameRegistry.registerCraftingHandler(ItemCraftingHandler.INSTANCE);

		// Register, in the Event Bus, the Interaction Access Handler
		MinecraftForge.EVENT_BUS.register(InteractionAccessHandler.INSTANCE);

		// Register, in the Event Bus, the Frozen Entity Handler
		MinecraftForge.EVENT_BUS.register(FrozenEntityHandler.INSTANCE);

		// Register, in the Event Bus, the Player Spell Update Handler
		MinecraftForge.EVENT_BUS.register(PlayerSpellUpdateHandler.INSTANCE);

		// Register, in the Event Bus, the Elementium Dust Drop Handler
		MinecraftForge.EVENT_BUS.register(ElementiumDustDropsHandler.INSTANCE);

		// Init the mod's Potion Effects
		ElementalTinkererPotions.initPotions();

		// Proxy: Register the Client Handlers
		proxy.registerClientHandlers();

		// Init the mod's Items
		ElementalTinkererItems.init();

		// Init the mod's Blocks
		ElementalTinkererBlocks.init();

		// Init the mod's Entities
		ElementalTinkererEntities.init();

		// Init the research data
		ResearchLibrary.initResearch();

		// Proxy: Read the research descriptions
		proxy.readResearchDescriptions();

		// Register the World Generation
		GameRegistry.registerWorldGenerator(WorldGenerationHandler.INSTANCE);

		// Init the Item Recipes
		ElementalTinkererItems.initItemRecipes();

		// Init the Block Recipes
		ElementalTinkererBlocks.initBlockRecipes();

		// Init the Tinkering Altar Recipes
		ResearchLibrary.initTinkeringRecipes();

		// Proxy: Register the mod's Tile Entities
		proxy.registerTileEntities();

		// Proxy: Map the Entity Renders
		proxy.mapEntityRenderers();

		// Proxy: Register the Block Renders
		proxy.registerBlockRenders();

		// Proxy: Init the Mod's Custom Spritesheets
		//proxy.initCustomSpritesheets();

		// Proxy: Register the mod's packets
		proxy.registerPackets();
	}

	@PostInit
	public void onPostInit(FMLPostInitializationEvent event) {
		// Register the Thaumcraft 3 compatibility stuff
	    Thaumcraft3Compat.init();
	}

	@ServerStarting
	public void onServerStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(CommandResearch.INSTANCE);
	}

	@ServerStarted
	public void onServerStarted(FMLServerStartedEvent event) {
		VoidMap.theMap = new VoidMap();
		NBTTagCompound worldCmp = IOHelper.getWorldCache(MiscHelper.getServer().worldServers[0]);
		if(worldCmp.hasKey("voidStorage"))
			VoidMap.theMap.readFromNBT(worldCmp.getCompoundTag("voidStorage"));
		ElementiumOreGenerationHelper.readFromNBT();
	}


	@ServerStopped
	public void onServerStopped(FMLServerStoppedEvent event) {
		VoidMap.theMap = null;
		ElementiumOreGenerationHelper.veins.clear();
	}
}