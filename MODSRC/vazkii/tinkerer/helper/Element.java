/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Dec 2012
package vazkii.tinkerer.helper;

import vazkii.tinkerer.reference.ResearchReference;

/**
 * Element
 *
 * The classical elements.
 *
 * @author Vazkii
 */
public enum Element {

	WATER(ResearchReference.CATEGORY_NAME_WATER, "Tides", 244),
	AIR(ResearchReference.CATEGORY_NAME_AIR, "Winds", 154),
	EARTH(ResearchReference.CATEGORY_NAME_EARTH, "Terra", 32),
	FIRE(ResearchReference.CATEGORY_NAME_FIRE, "Flame", 0);

	private Element(String elementName, String suffix, int hue) {
		this.elementName = elementName;
		this.suffix = suffix;
		this.hue = hue;
	}

	private String elementName,
				   suffix;

	private int hue;

	/** The classic name of the element **/
	public String getName() {
		return elementName;
	}

	/** A suffix representing the element, e.g. Fire -> Flame **/
	public String getSuffix() {
		return suffix;
	}

	/** Gets the Hue of this element, used to colorize wands **/
	public int getHue() {
		return hue;
	}

	/** Gets the suffix correspondent to the ordinal value passed
	 * in, or "N/A" if the value is too high. **/
	public static String getSuffix(int ordinal) {
		Element[] elements = Element.class.getEnumConstants();
		if(ordinal >= elements.length)
			return "N/A";
		return elements[ordinal].getSuffix();
	}

	/** Gets the name correspondent to the ordinal value passed
	 * in, or "N/A" if the value is too high. **/
	public static String getName(int ordinal) {
		Element[] elements = Element.class.getEnumConstants();
		if(ordinal >= elements.length)
			return "N/A";
		return elements[ordinal].getName();
	}

}
