/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 5 Jan 2013
package vazkii.tinkerer.research;

/**
 * ResearchType
 *
 * The various types of research.
 *
 * @author Vazkii
 */
public enum ResearchType {

	KNOWLEDGE("Knowledge"),
	ITEM("Item"),
	SPELL("Spell"),
	PASSIVE("Passive");

	private ResearchType(String displayName) {
		this.displayName = displayName;
	}

	private String displayName;

	public String getDisplayName() {
		return displayName;
	}
}
