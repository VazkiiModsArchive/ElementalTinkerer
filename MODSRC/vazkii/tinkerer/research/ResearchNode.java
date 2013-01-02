/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Dec 2012
package vazkii.tinkerer.research;

/**
 * ResearchNode
 *
 * A Node of research. This stores the name, and some other data regarding it,
 * it also allows to check if the node has been researched.
 *
 * @author Vazkii
 */
public class ResearchNode {

	public String spritesheet, label, displayName;
	public int spriteIndex;

	public ResearchNode(String spritesheet, String label, String displayName, int spriteIndex) {
		this.spritesheet = spritesheet;
		this.label = label;
		this.displayName = displayName;
		this.spriteIndex = spriteIndex;
	}

}
