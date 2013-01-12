/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Dec 2012
package vazkii.tinkerer.research;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ResearchCategory
 *
 * A category for the research. This stores research nodes and some other
 * configuration
 *
 * @author Vazkii
 */
public class ResearchCategory {

	public int index;

	Set<ResearchNode> nodes = new LinkedHashSet();

	public ResearchCategory(int index) {
		this.index = index;
	}

	public ResearchNode addNode(ResearchNode node) {
		nodes.add(node);
		return node;
	}

	public boolean hasNode(ResearchNode node) {
		return nodes.contains(node);
	}

	public Set<ResearchNode> getNodes() {
		return nodes;
	}
}
