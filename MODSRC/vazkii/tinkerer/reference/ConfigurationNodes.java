/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.reference;

/**
 * ConfigurationNodes
 *
 * This class holds names for the config.
 *
 * @author Vazkii
 */
public final class ConfigurationNodes {

	/** The graphics category for the config **/
	public static final String CATEGORY_GRAPHICS = "graphics";

	/** The potion id category for the config **/
	public static final String CATEGORY_POTION_IDS = "potions";

	/** Config nodes for the graphics category **/
	public static final String NODE_ELEMENTIUM_GEM_SPECTRUM = ItemNames.ELEMENTIUM_GEM_NAME + ".spectrum",
							   NODE_ELEMENTIUM_GEM_ANIMATED = ItemNames.ELEMENTIUM_GEM_NAME + ".animate",
							   NODE_ELEMENTIUM_ORE_COLORED = BlockNames.ELEMENTIUM_ORE_NAME + ".variousColors",
							   NODE_WAND_FLICKER = ItemNames.WAND_NAME + ".flicker",
							   NODE_ELEMENTIUM_INGOT_ANIMATED = ItemNames.ELEMENTIUM_INGOT_NAME + ".animate",
							   NODE_VIGNETTE_LOW_HEALTH = "vignette.lowHealth",
							   NODE_VIGNETTE_FROZEN = "vignette.frozen",
							   NODE_VIGNETTE_POISON = "vignette.poison";

	/** Config nodes for the general catergory **/
	public static final String NODE_RESEARCH_SHARE = "research.share",
							   COMMENT_RESEARCH_SHARE = "Set to the username of a player to have that player hold all of the research for the server (shared research data), set to '" + ResearchReference.CONFIG_SHARE_WILDCARD + "' to don't have the research being shared, but rather, per player.",
							   NODE_SAFE_ELEMENTIUM = BlockNames.ELEMENTIUM_ORE_NAME + ".safe",
							   COMMENT_SAFE_ELEMENTIUM = "Set to true for Elementium Ore mining to be safe. (Spawning no mobs at all)",
							   NODE_EASY_RESEARCH = "research.easyMode",
							   COMMENT_EASY_RESEARCH = "(For Oldschool research only) Set to true to change the research game to be a swap puzzle rather than a slide puzzle. Significantly easier!",
							   NODE_OLD_RESEARCH = "research.oldschool",
							   COMMENT_OLD_RESEARCH = "Set to true to change the research puzzle into the Oldschool slide puzzle.";

	/** Default nodes for the graphics category **/
	public static final boolean DEFAULT_ELEMENTIUM_GEM_SPECTRUM = true,
								DEFAULT_ELEMENTIUM_GEM_ANIMATE = true,
								DEFAULT_ELEMENTIUM_ORE_COLORED = true,
								DEFAULT_WAND_FLICKER = true,
								DEFAULT_ELEMENTIUM_INGOT_ANIMATE = true;

}
