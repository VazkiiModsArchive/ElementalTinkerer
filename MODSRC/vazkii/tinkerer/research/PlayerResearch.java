/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Jan 2013
package vazkii.tinkerer.research;

import java.util.TreeSet;

import net.minecraft.world.World;
import vazkii.tinkerer.helper.ResearchHelper;

/**
 * PlayerResearch
 *
 * The Research Data of a Player. Holds what researches
 * have been done.
 *
 * @author Vazkii
 */
public class PlayerResearch {

	// This one actually needs to be a TreeSet
	public TreeSet<Short> researchesDone = new TreeSet();

	public String playerLinkedTo;

	public PlayerResearch(String player) {
		playerLinkedTo = player;
	}

	/** Checks if a research is done, or if it's enabled by default; if the latter
	 * is the case, adds it to the list of the researches done **/
	public boolean isResearchDone(short i) {
		boolean has = researchesDone.contains(i);
		if(!has && ResearchLibrary.allNodes.get(i).isDefaultEnabled()) {
			researchesDone.add(i);
			return isResearchDone(i);
		}
		return has;
	}

	/** Enables a research, passing in the ID of the research, and if it's supposed to save
	 * to NBT. The world parameter can be null if save is false.**/
	public void research(short i, boolean save, World world) {
		if(i >= 0) {
			researchesDone.add(i);
			if(save)
				ResearchHelper.updateResearch(world, this);
		}
	}

}
