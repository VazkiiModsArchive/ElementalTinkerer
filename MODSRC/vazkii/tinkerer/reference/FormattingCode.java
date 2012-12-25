/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.reference;

/**
 * FormattingCode
 *
 * String formatting codes, read more:
 * http://www.minecraftwiki.net/wiki/Formatting_codes
 *
 * @author Vazkii
 */
public enum FormattingCode {
	
	BLACK('0'),
	DARK_BLUE('1'),
	DARK_GREEN('2'),
	DARK_AQUA('3'),
	DARK_RED('4'),
	DARK_PURPLE('5'),
	GOLD('6'),
	GRAY('7'),
	DARK_GRAY('8'),
	BLUE('9'),
	GREEN('a'),
	AQUA('b'),
	RED('c'),
	PINK('d'),
	YELLOW('e'),
	WHITE('f'),
	RANDOM('f'),
	BOLD('l'),
	STRIKE('m'),
	UNDERLINE('n'),
	ITALICS('o'),
	RESET('r');
	
	private FormattingCode(char c) {
		this.c = c;
	}
	
	private char c;
	
	public String toString() {
		return MiscReference.FORMATTING_CODE_CHAR + "" + c;
	}

}
