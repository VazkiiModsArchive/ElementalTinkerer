/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.handler;

import java.io.File;

import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.BlockNames;
import vazkii.tinkerer.reference.ConfigurationNodes;
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.PotionReference;
import vazkii.tinkerer.reference.ResearchReference;

/**
 * ConfigurationHelper
 *
 * This class handles the configuration of Elemental Tinkerer.
 *
 * @author Vazkii
 */
public final class ConfigurationHandler {

	private static Configuration config;

	private static ConfigCategory categoryGraphics = new ConfigCategory(ConfigurationNodes.CATEGORY_GRAPHICS),
								  categoryPotions = new ConfigCategory(ConfigurationNodes.CATEGORY_POTION_IDS);

	/** Config nodes **/
	public static boolean elementiumGemAnimate = ConfigurationNodes.DEFAULT_ELEMENTIUM_GEM_ANIMATE,
						  elementiumGemSpectrum = ConfigurationNodes.DEFAULT_ELEMENTIUM_GEM_SPECTRUM,
						  elementiumOreColored = ConfigurationNodes.DEFAULT_ELEMENTIUM_ORE_COLORED,
						  wandFlicker = ConfigurationNodes.DEFAULT_WAND_FLICKER,
						  elementiumIngotAnimate = ConfigurationNodes.DEFAULT_ELEMENTIUM_INGOT_ANIMATE,
						  vignetteLowHealth = true,
						  vignetteFrozen = true,
						  vignettePoison = true,
						  safeElementiumOre = false,
						  researchEasyMode = false;

	/** Player to hold the shared research, this player will keep the
	 * research data for the entire server and all the players in it,
	 * this is used, for example, for coop play, where only a small
	 * handful of players are in the people and don't want to have to
	 * research multiple times. Setting this to the default (wildcard)
	 * will have the research not be shared, and rather, individual, per
	 * player.
	 */
	public static String sharedResearch = "*";

	public static void initConfig(File configFile) {
		config = new Configuration(configFile);

		// Load the Config
		config.load();

		// Load Block IDs
		BlockIDs.elementiumOre = config.getBlock(BlockNames.ELEMENTIUM_ORE_NAME, BlockIDs.DEFAULT_ELEMENTIUM_ORE).getInt(BlockIDs.DEFAULT_ELEMENTIUM_ORE);
		BlockIDs.elementiumOreSpawner = config.getBlock(BlockNames.ELEMENTIUM_ORE_SPAWNER_NAME, BlockIDs.DEFAULT_ELEMENTIUM_ORE_SPAWNER).getInt(BlockIDs.DEFAULT_ELEMENTIUM_ORE_SPAWNER);
		BlockIDs.elementalDesk = config.getBlock(BlockNames.ELEMENTAL_DESK_NAME, BlockIDs.DEFAULT_ELEMENTAL_DESK).getInt(BlockIDs.DEFAULT_ELEMENTAL_DESK);
		BlockIDs.elementiumGemBlock = config.getBlock(BlockNames.ELEMENTIUM_GEM_BLOCK_NAME, BlockIDs.DEFAULT_ELEMENTIUM_GEM_BLOCK).getInt(BlockIDs.DEFAULT_ELEMENTIUM_GEM_BLOCK);
		BlockIDs.elementalistTinkeringAltar = config.getBlock(BlockNames.ELEMENTALIST_TINKERING_ALTAR_NAME, BlockIDs.DEFAULT_ELEMENTALIST_TINKERING_ALTAR).getInt(BlockIDs.DEFAULT_ELEMENTALIST_TINKERING_ALTAR);
		BlockIDs.catalystCapsule = config.getBlock(BlockNames.CATALYST_CAPSULE_NAME, BlockIDs.DEFAULT_CATALYST_CAPSULE).getInt(BlockIDs.DEFAULT_CATALYST_CAPSULE);
		BlockIDs.attuner = config.getBlock(BlockNames.ATTUNER_NAME, BlockIDs.DEFAULT_ATTUNER).getInt(BlockIDs.DEFAULT_ATTUNER);
		BlockIDs.voidGateway = config.getBlock(BlockNames.VOID_GATEWAY_NAME, BlockIDs.DEFAULT_VOID_GATEWAY).getInt(BlockIDs.DEFAULT_VOID_GATEWAY);
		BlockIDs.voidNetwork = config.getBlock(BlockNames.VOID_NETWORK_NAME, BlockIDs.DEFAULT_VOID_NETWORK).getInt(BlockIDs.DEFAULT_VOID_NETWORK);
		// RESERVED
		// RESERVED
		BlockIDs.glowstoneAir = config.getBlock(BlockNames.GLOWSTONE_AIR_NAME, BlockIDs.DEFAULT_GLOWSTONE_AIR).getInt(BlockIDs.DEFAULT_GLOWSTONE_AIR);
		BlockIDs.waveInputter = config.getBlock(BlockNames.WAVE_INPUTTER_NAME, BlockIDs.DEFAULT_WAVE_INPUTTER).getInt(BlockIDs.DEFAULT_WAVE_INPUTTER);
		BlockIDs.dislocator = config.getBlock(BlockNames.DISLOCATOR_NAME, BlockIDs.DEFAULT_DISLOCATOR).getInt(BlockIDs.DEFAULT_DISLOCATOR);
		BlockIDs.scavenger = config.getBlock(BlockNames.SCAVENGER_NAME, BlockIDs.DEFAULT_SCAVENGER).getInt(BlockIDs.DEFAULT_SCAVENGER);
		BlockIDs.incinerator = config.getBlock(BlockNames.INCINERATOR_NAME, BlockIDs.DEFAULT_INCINERATOR).getInt(BlockIDs.DEFAULT_INCINERATOR);
		BlockIDs.darkQuartz = config.getBlock(BlockNames.DARK_QUARTZ_NAME, BlockIDs.DEFAULT_DARK_QUARTZ).getInt(BlockIDs.DEFAULT_DARK_QUARTZ);
		BlockIDs.darkQuartzStairs = config.getBlock(BlockNames.DARK_QUARTZ_STAIRS_NAME, BlockIDs.DEFAULT_DARK_QUARTZ_STAIRS).getInt(BlockIDs.DEFAULT_DARK_QUARTZ_STAIRS);
		BlockIDs.darkQuartzSlab = config.getBlock(BlockNames.DARK_QUARTZ_SLAB_NAME, BlockIDs.DEFAULT_DARK_QUARTZ_SLAB).getInt(BlockIDs.DEFAULT_DARK_QUARTZ_SLAB);
		BlockIDs.darkQuartzFullSlab = config.getBlock(BlockNames.DARK_QUARTZ_FULL_SLAB_NAME, BlockIDs.DEFAULT_DARK_QUARTZ_FULL_SLAB).getInt(BlockIDs.DEFAULT_DARK_QUARTZ_SLAB);

		// Load Item IDs
		ItemIDs.elementiumGem = config.getItem(ItemNames.ELEMENTIUM_GEM_NAME, ItemIDs.DEFAULT_ELEMENTIUM_GEM).getInt(ItemIDs.DEFAULT_ELEMENTIUM_GEM);
		ItemIDs.elementalBook = config.getItem(ItemNames.ELEMENT_BOOK_NAME, ItemIDs.DEFAULT_ELEMENTAL_BOOK).getInt(ItemIDs.DEFAULT_ELEMENTAL_BOOK);
		ItemIDs.elementalistLexicon = config.getItem(ItemNames.ELEMENTALIST_LEXICON_NAME, ItemIDs.DEFAULT_ELEMENTALIST_LEXICON).getInt(ItemIDs.DEFAULT_ELEMENTALIST_LEXICON);
		ItemIDs.catalyst = config.getItem(ItemNames.CATALYST_ITEM_NAME, ItemIDs.DEFAULT_CATALYST).getInt(ItemIDs.DEFAULT_CATALYST);
		ItemIDs.elementalBark = config.getItem(ItemNames.ELEMENTAL_BARK_ITEM_NAME, ItemIDs.DEFAULT_ELEMENTAL_BARK).getInt(ItemIDs.DEFAULT_ELEMENTAL_BARK);
		ItemIDs.wand = config.getItem(ItemNames.WAND_NAME, ItemIDs.DEFAULT_WAND).getInt(ItemIDs.DEFAULT_WAND);
		ItemIDs.elementiumIngot = config.getItem(ItemNames.ELEMENTIUM_INGOT_NAME, ItemIDs.DEFAULT_ELEMENTIUM_INGOT).getInt(ItemIDs.DEFAULT_ELEMENTIUM_INGOT);
		ItemIDs.elementiumDust = config.getItem(ItemNames.ELEMENTIUM_DUST_NAME, ItemIDs.DEFAULT_ELEMENTIUM_DUST).getInt(ItemIDs.DEFAULT_ELEMENTIUM_DUST);
		ItemIDs.elementiumDetector = config.getItem(ItemNames.ELEMENTIUM_DETECTOR_NAME, ItemIDs.DEFAULT_ELEMENTIUM_DETECTOR).getInt(ItemIDs.DEFAULT_ELEMENTIUM_DETECTOR);
		ItemIDs.oddClaw = config.getItem(ItemNames.ODD_CLAW_NAME, ItemIDs.DEFAULT_ODD_CLAW).getInt(ItemIDs.DEFAULT_ODD_CLAW);
		ItemIDs.locationGem = config.getItem(ItemNames.LOCATION_GEM_NAME, ItemIDs.DEFAULT_LOCATION_GEM).getInt(ItemIDs.DEFAULT_LOCATION_GEM);
		ItemIDs.gem = config.getItem(ItemNames.GEM_NAME, ItemIDs.DEFAULT_GEM).getInt(ItemIDs.DEFAULT_GEM);
		ItemIDs.enderParticle = config.getItem(ItemNames.ENDER_PARTICLE_NAME, ItemIDs.DEFAULT_ENDER_PARTICLE).getInt(ItemIDs.DEFAULT_ENDER_PARTICLE);
		ItemIDs.gaseousGlowstone = config.getItem(ItemNames.GASEOUS_GLOWSTONE_NAME, ItemIDs.DEFAULT_GASEOUS_GLOWSTONE).getInt(ItemIDs.DEFAULT_GASEOUS_GLOWSTONE);
		ItemIDs.orichalcum = config.getItem(ItemNames.ORICHALCUM_NAME, ItemIDs.DEFAULT_ORICHALCUM).getInt(ItemIDs.DEFAULT_ORICHALCUM);
		ItemIDs.orichalcumBlade = config.getItem(ItemNames.ORICHALCUM_BLADE_NAME, ItemIDs.DEFAULT_ORICHALCUM_BLADE).getInt(ItemIDs.DEFAULT_ORICHALCUM_BLADE);
		ItemIDs.orichalcumSpade = config.getItem(ItemNames.ORICHALCUM_SPADE_NAME, ItemIDs.DEFAULT_ORICHALCUM_SPADE).getInt(ItemIDs.DEFAULT_ORICHALCUM_SPADE);
		ItemIDs.orichalcumPick = config.getItem(ItemNames.ORICHALCUM_PICK_NAME, ItemIDs.DEFAULT_ORICHALCUM_PICK).getInt(ItemIDs.DEFAULT_ORICHALCUM_PICK);
		ItemIDs.orichalcumHatchet = config.getItem(ItemNames.ORICHALCUM_HATCHET_NAME, ItemIDs.DEFAULT_ORICHALCUM_HATCHET).getInt(ItemIDs.DEFAULT_ORICHALCUM_HATCHET);
		ItemIDs.orichalcumHelmet = config.getItem(ItemNames.ORICHALCUM_HELMET_NAME, ItemIDs.DEFAULT_ORICHALCUM_HELMET).getInt(ItemIDs.DEFAULT_ORICHALCUM_HELMET);
		ItemIDs.orichalcumChestplate = config.getItem(ItemNames.ORICHALCUM_CHESTPLATE_NAME, ItemIDs.DEFAULT_ORICHALCUM_CHESTPLATE).getInt(ItemIDs.DEFAULT_ORICHALCUM_CHESTPLATE);
		ItemIDs.orichalcumPlatelegs = config.getItem(ItemNames.ORICHALCUM_PLATELEGS_NAME, ItemIDs.DEFAULT_ORICHALCUM_PLATELEGS).getInt(ItemIDs.DEFAULT_ORICHALCUM_PLATELEGS);
		ItemIDs.orichalcumBoots = config.getItem(ItemNames.ORICHALCUM_BOOTS_NAME, ItemIDs.DEFAULT_ORICHALCUM_BOOTS).getInt(ItemIDs.DEFAULT_ORICHALCUM_BOOTS);
		ItemIDs.locatingCore = config.getItem(ItemNames.LOCATING_CORE_NAME, ItemIDs.DEFAULT_LOCATING_CORE).getInt(ItemIDs.DEFAULT_LOCATING_CORE);
		ItemIDs.unboundBook = config.getItem(ItemNames.UNBOUND_BOOK_NAME, ItemIDs.DEFAULT_UNBOUND_BOOK).getInt(ItemIDs.DEFAULT_UNBOUND_BOOK);
		ItemIDs.spellbindCloth = config.getItem(ItemNames.SPELLBIND_CLOTH_NAME, ItemIDs.DEFAULT_SPELLBIND_CLOTH).getInt(ItemIDs.DEFAULT_SPELLBIND_CLOTH);
		ItemIDs.mysticalTalisman = config.getItem(ItemNames.MYSTICAL_TALISMAN_NAME, ItemIDs.DEFAULT_MYSTICAL_TALISMAN).getInt(ItemIDs.DEFAULT_MYSTICAL_TALISMAN);
		ItemIDs.attunedTome = config.getItem(ItemNames.ATTUNED_TOME_NAME, ItemIDs.DEFAULT_ATTUNED_TOME).getInt(ItemIDs.DEFAULT_ATTUNED_TOME);
		ItemIDs.roastedCarrot = config.getItem(ItemNames.ROASTED_CARROT_NAME, ItemIDs.DEFAULT_ROASTED_CARROT).getInt(ItemIDs.DEFAULT_ROASTED_CARROT);
		ItemIDs.caramel = config.getItem(ItemNames.CARAMEL_NAME, ItemIDs.DEFAULT_CARAMEL).getInt(ItemIDs.DEFAULT_CARAMEL);
		ItemIDs.darkQuartz = config.getItem(ItemNames.DARK_QUARTZ_NAME, ItemIDs.DEFAULT_DARK_QUARTZ).getInt(ItemIDs.DEFAULT_DARK_QUARTZ);

		// Load Potion IDs
		PotionReference.idFrozen = config.get(ConfigurationNodes.CATEGORY_POTION_IDS, PotionReference.NAME_FROZEN, PotionReference.ID_DEFAULT_FROZEN).getInt(PotionReference.ID_DEFAULT_FROZEN);

		// Load Graphics Nodes
		elementiumGemAnimate = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_ELEMENTIUM_GEM_ANIMATED, elementiumGemAnimate).getBoolean(elementiumGemAnimate);
		elementiumGemSpectrum = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_ELEMENTIUM_GEM_SPECTRUM, elementiumGemSpectrum).getBoolean(elementiumGemSpectrum);
		elementiumOreColored = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_ELEMENTIUM_ORE_COLORED, elementiumOreColored).getBoolean(elementiumOreColored);
		wandFlicker = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_WAND_FLICKER, wandFlicker).getBoolean(wandFlicker);
		elementiumIngotAnimate = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_ELEMENTIUM_INGOT_ANIMATED, elementiumIngotAnimate).getBoolean(elementiumIngotAnimate);
		vignetteLowHealth = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_VIGNETTE_LOW_HEALTH, vignetteLowHealth).getBoolean(vignetteLowHealth);
		vignetteFrozen = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_VIGNETTE_FROZEN, vignetteFrozen).getBoolean(vignetteFrozen);
		vignettePoison = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_VIGNETTE_POISON, vignettePoison).getBoolean(vignettePoison);

		// Load, and comment, if necessary the research share
		Property researchShareProp = config.get(Configuration.CATEGORY_GENERAL, ConfigurationNodes.NODE_RESEARCH_SHARE, ResearchReference.CONFIG_SHARE_WILDCARD);
		researchShareProp.comment = ConfigurationNodes.COMMENT_RESEARCH_SHARE;
		sharedResearch = researchShareProp.getString();

		// Load, and comment the Safe Elementium node
		// (if on, no mobs will be spawned when mining elementium)
		Property safeElementiumProp = config.get(Configuration.CATEGORY_GENERAL, ConfigurationNodes.NODE_SAFE_ELEMENTIUM, false);
		safeElementiumProp.comment = ConfigurationNodes.COMMENT_SAFE_ELEMENTIUM;
		safeElementiumOre = safeElementiumProp.getBoolean(false);

		// Load, and comment the Easy Mode Research node
		Property researchEasyProp = config.get(Configuration.CATEGORY_GENERAL, ConfigurationNodes.NODE_EASY_RESEARCH, false);
		researchEasyProp.comment = ConfigurationNodes.COMMENT_EASY_RESEARCH;
		researchEasyMode = researchEasyProp.getBoolean(false);

		// Save the config if anything went wrong and happened to need changing
		config.save();
	}
}
