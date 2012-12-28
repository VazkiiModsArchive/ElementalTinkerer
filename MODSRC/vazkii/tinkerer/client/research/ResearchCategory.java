/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Dec 2012
package vazkii.tinkerer.client.research;

/**
 * ResearchCategory
 *
 * A category for the research. This stores research nodes and some other
 * configuration 
 *
 * @author Vazkii
 */
public class ResearchCategory {
	
	private String label, displayName;
	
	public ResearchCategory(String label, String displayName) {
		this.label = label;
		this.displayName = displayName;
	}

}
