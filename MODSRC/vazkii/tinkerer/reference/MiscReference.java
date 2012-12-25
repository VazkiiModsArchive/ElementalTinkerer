/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Dec 2012
package vazkii.tinkerer.reference;

/**
 * MiscReference
 *
 * Miscellaneous Constants.
 *
 * @author Vazkii
 */
public final class MiscReference {

	/** Simple and dirty debug mode flag. **/ // VAZ_TODO Set to false for potential releases
	public static final boolean DEBUG_MODE = true;
	
	/** The amount of game ticks in a second **/
	public static final byte TICKS_IN_SECOND = 20;
	
	/** The Lenght of a square chunk **/
	public static final byte CHUNK_LENGHT = 16;
	
	/** The index that item IDs are shifted by **/
	public static final int ITEM_INDEX_SHIFT = 256;

	/** The Character for formatting codes in the Font Renderer **/
	public static final char FORMATTING_CODE_CHAR = '\u00a7';
}
