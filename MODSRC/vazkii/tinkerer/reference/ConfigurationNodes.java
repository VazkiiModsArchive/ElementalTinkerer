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

	/** Config nodes for the graphics category **/
	public static final String NODE_ELEMENTIUM_GEM_SPECTRUM = ItemNames.ELEMENTIUM_GEM_NAME + ".spectrum",
							   NODE_ELEMENTIUM_GEM_ANIMATED = ItemNames.ELEMENTIUM_GEM_NAME + ".animate",
							   NODE_ELEMENTIUM_ORE_COLORED = BlockNames.ELEMENTIUM_ORE_NAME + ".variousColors";

	/** Config nodes for the general catergory **/
	public static final String NODE_RESEARCH_SHARE = "research.share",
							   COMMENT_RESEARCH_SHARE = "Set to the username of a player to have that player hold all of the research for the server (shared research data), set to '" + ResearchReference.CONFIG_SHARE_WILDCARD + "' to don't have the research being shared, but rather, per player.";

	/** Default nodes for the graphics category **/
	public static final boolean DEFAULT_ELEMENTIUM_GEM_SPECTRUM = true,
								DEFAULT_ELEMENTIUM_GEM_ANIMATE = true,
								DEFAULT_ELEMENTIUM_ORE_COLORED = true;

}
