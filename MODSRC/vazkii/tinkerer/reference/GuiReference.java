/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Jan 2013
package vazkii.tinkerer.reference;

import java.awt.Color;

/**
 * GuiReference
 *
 * Reference for GUI based constants.
 *
 * @author Vazkii
 */
public final class GuiReference {

	/** The default color for tooltips (the one used in vanilla minecraft) **/
	public static final int TOOLTIP_DEFAULT_COLOR = 1347420415,
							TOOLTIP_DEFAULT_COLOR_BG = -267386864;

	/** The color for error tooltips (Note: these are in ARGB, not RGB) **/
	public static final int TOOLTIP_ERROR_COLOR = new Color(160, 0, 0, 255).getRGB(),
							TOOLTIP_ERROR_COLOR_BG = new Color(32, 0, 0, 0xF0).getRGB();

	/** The color for container items tooltips (Note: these are in ARGB, not RGB) **/
	public static final int TOOLTIP_CONTAINER_ITEM_COLOR = new Color(0, 160, 0, 255).getRGB(),
							TOOLTIP_CONTAINER_ITEM_COLOR_BG = new Color(0, 32, 0, 0xF0).getRGB();

	/** The amount of lines in the elementalist's lexicon index
	 * pages **/
	public static final int LINES_IN_ELEMENTALIST_LEXICON = 12;
}
