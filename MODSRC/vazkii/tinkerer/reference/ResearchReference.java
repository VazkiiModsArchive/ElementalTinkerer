/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Jan 2013
package vazkii.tinkerer.reference;

// Import required for the a javadoc
import vazkii.tinkerer.handler.ConfigurationHandler;

/**
 * ResearchReference
 *
 * Reference for research. Stores values like research IDs, names, etc.
 *
 * @author Vazkii
 */
public final class ResearchReference {

	/** The Wildcard value to have the research values.
	 *
	 * @see {@link ConfigurationHandler#sharedResearch}
	 */
	public static final String CONFIG_SHARE_WILDCARD = "*";

	/** Reseach IDs **/
	public static final short ID_ELEMENTIUM_ORE = 0,
							  ID_ELEMENTIUM_GEM = 1,
							  ID_ELEMENTAL_DESK = 2,
							  ID_RESEARCH_BOOKS = 3,
							  ID_ELEMENTALIST_LEXICON = 4,
							  ID_CATALYST_START = 5,
							  ID_ELEMENTAL_BARK = 21,
							  ID_ELEMENTIUM_DUST = 22,
							  ID_ELEMENTIUM_INGOT = 23,
							  ID_WAND_START = 24,
							  ID_ELEMENTAL_TINKERING = 28,
							  ID_CATALYST_CAPSULE = 29,
							  ID_ATTUNER = 30;

	/** Research Labels **/
	public static final String LABEL_ELEMENTIUM_ORE = BlockNames.ELEMENTIUM_ORE_NAME,
							   LABEL_ELEMENTIUM_GEM = ItemNames.ELEMENTIUM_GEM_NAME,
							   LABEL_ELEMENTAL_DESK = BlockNames.ELEMENTAL_DESK_NAME,
							   LABEL_RESEARCH_BOOKS = "researchBooks",
							   LABEL_ELEMENTALIST_LEXICON = ItemNames.ELEMENTALIST_LEXICON_NAME,
							   LABEL_CATALSYT = ItemNames.CATALYST_ITEM_NAME + "%s",
							   LABEL_ELEMENTAL_BARK = ItemNames.ELEMENTAL_BARK_ITEM_NAME,
							   LABEL_ELEMENTIUM_DUST = ItemNames.ELEMENTIUM_DUST_NAME,
							   LABEL_ELEMENTIUM_INGOT = ItemNames.ELEMENTIUM_INGOT_NAME,
							   LABEL_WAND = ItemNames.WAND_NAME + "%s",
							   LABEL_ELEMENTAL_TINKERING = "elementalTinkering",
							   LABEL_CATALYST_CAPSULE = BlockNames.CATALYST_CAPSULE_NAME,
							   LABEL_ATTUNER = BlockNames.ATTUNER_NAME;

	/** Display Names **/
	public static final String DISPLAY_NAME_ELEMENTIUM_ORE = BlockNames.ELEMENTIUM_ORE_DISPLAY_NAME,
							   DISPLAY_NAME_ELEMENTIUM_GEM = ItemNames.ELEMENTIUM_GEM_DISPLAY_NAME,
							   DISPLAY_NAME_ELEMENTAL_DESK = BlockNames.ELEMENTAL_DESK_DISPLAY_NAME,
							   DISPLAY_NAME_RESEARCH_BOOKS = "Ancient Magic Books",
							   DISPLAY_NAME_ELEMENTALIST_LEXICON = ItemNames.ELEMENTALIST_LEXICON_DISPLAY_NAME,
							   DISPLAY_NAME_ELEMENTAL_BARK = ItemNames.ELEMENTAL_BARK_ITEM_DISPLAY_NAME,
							   DISPLAY_NAME_ELEMENTIUM_DUST = ItemNames.ELEMENTIUM_DUST_DISPLAY_NAME,
							   DISPLAY_NAME_ELEMENTIUM_INGOT = ItemNames.ELEMENTIUM_INGOT_DISPLAY_NAME,
							   DISPLAY_NAME_ELEMENTAL_TINKERING = "Elemental Tinkering",
							   DISPLAY_NAME_CATALYST_CAPSULE = BlockNames.CATALYST_CAPSULE_DISPLAY_NAME,
							   DISPLAY_NAME_ATTUNER = "Magical Attuning";

	/** The Compound Tag name of the Research Data **/
	public static final String COMPOUND_TAG_NAME = "researchData";

	/** The names of the research categories **/
	public static final String CATEGORY_NAME_GENERAL = "General",
							   CATEGORY_NAME_PURE = "Pure",
							   CATEGORY_NAME_WATER = "Water",
							   CATEGORY_NAME_AIR = "Air",
							   CATEGORY_NAME_EARTH = "Earth",
							   CATEGORY_NAME_FIRE = "Fire";
}
