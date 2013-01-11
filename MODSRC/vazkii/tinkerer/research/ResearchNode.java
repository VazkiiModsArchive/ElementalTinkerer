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
public class ResearchNode implements Comparable<ResearchNode> {

	public String spritesheet, label, displayName;
	public int spriteIndex;
	public short index;
	public ResearchType type;

	/** If this is set to true, then it will be instantly added
	 * to the list of acquired researches **/
	private boolean isDefaultEnabled = false;

	public ResearchNode(short index, String spritesheet, String label, String displayName, int spriteIndex, ResearchType type) {
		this.spritesheet = spritesheet;
		this.label = label;
		this.displayName = displayName;
		this.spriteIndex = spriteIndex;
		this.index = index;
		this.type = type;
	}

	public ResearchNode setDefaultEnabled() {
		isDefaultEnabled = true;
		return this;
	}

	public ResearchNode addToCategory(ResearchCategory category) {
		return category.addNode(this);
	}

	@Override
	public int compareTo(ResearchNode o) {
		return Integer.compare(index, o.index);
	}

	public boolean isDefaultEnabled() {
		return isDefaultEnabled;
	}
}
