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
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.BlockNames;
import vazkii.tinkerer.reference.ConfigurationNodes;
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.reference.ItemNames;

/**
 * ConfigurationHelper
 *
 * This class handles the configuration of Elemental Tinkerer.
 *
 * @author Vazkii
 */
public final class ConfigurationHandler {

	private static Configuration config;

	private static ConfigCategory categoryGraphics = new ConfigCategory(ConfigurationNodes.CATEGORY_GRAPHICS);

	/** Config nodes **/
	public static boolean elementiumGemAnimate = ConfigurationNodes.DEFAULT_ELEMENTIUM_GEM_ANIMATE,
						  elementiumGemSpectrum = ConfigurationNodes.DEFAULT_ELEMENTIUM_GEM_SPECTRUM,
						  elementiumOreColored = ConfigurationNodes.DEFAULT_ELEMENTIUM_ORE_COLORED;

	public static void initConfig(File configFile) {
		config = new Configuration(configFile);

		// Load the Config
		config.load();

		// Load Block IDs
		BlockIDs.elementiumOre = config.getBlock(BlockNames.ELEMENTIUM_ORE_NAME, BlockIDs.DEFAULT_ELEMENTIUM_ORE).getInt(BlockIDs.DEFAULT_ELEMENTIUM_ORE);
		BlockIDs.elementiumOreSpawner = config.getBlock(BlockNames.ELEMENTIUM_ORE_SPAWNER_NAME, BlockIDs.DEFAULT_ELEMENTIUM_ORE_SPAWNER).getInt(BlockIDs.DEFAULT_ELEMENTIUM_ORE_SPAWNER);
		BlockIDs.elementalDesk = config.getBlock(BlockNames.ELEMENTAL_DESK_NAME, BlockIDs.DEFAULT_ELEMENTAL_DESK).getInt(BlockIDs.DEFAULT_ELEMENTAL_DESK);

		// Load Item IDs
		ItemIDs.elementiumGem = config.getItem(ItemNames.ELEMENTIUM_GEM_NAME, ItemIDs.DEFAULT_ELEMENTIUM_GEM).getInt(ItemIDs.DEFAULT_ELEMENTIUM_GEM);
		ItemIDs.elementalBook = config.getItem(ItemNames.ELEMENT_BOOK_NAME, ItemIDs.DEFAULT_ELEMENTAL_BOOK).getInt(ItemIDs.DEFAULT_ELEMENTAL_BOOK);
		
		// Load Graphics Nodes
		elementiumGemAnimate = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_ELEMENTIUM_GEM_ANIMATED, elementiumGemAnimate).getBoolean(elementiumGemAnimate);
		elementiumGemSpectrum = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_ELEMENTIUM_GEM_SPECTRUM, elementiumGemSpectrum).getBoolean(elementiumGemSpectrum);
		elementiumOreColored = config.get(ConfigurationNodes.CATEGORY_GRAPHICS, ConfigurationNodes.NODE_ELEMENTIUM_ORE_COLORED, elementiumOreColored).getBoolean(elementiumOreColored);

		// Save the config if anything went wrong and happened to need changing
		config.save();
	}


}
