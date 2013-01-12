/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Jan 2013
package vazkii.tinkerer.research;

import java.util.Map;
import java.util.TreeMap;

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

	/** Map containing the researches done, or completed **/
	public Map<Short, Byte> researchesDone = new TreeMap();

	public String playerLinkedTo;

	public PlayerResearch(String player) {
		playerLinkedTo = player;
	}

	/** Checks if a research is done, or if it's enabled by default; if the latter
	 * is the case, adds it to the list of the researches done **/
	public boolean isResearchDone(short i) {
		boolean has = researchesDone.containsKey(i);
		if(!has && ResearchLibrary.allNodes.get(i).isDefaultEnabled()) {
			researchesDone.put(i, (byte) 2);
			return isResearchDone(i);
		}
		return has;
	}

	/** Checks if a research is completed **/
	public boolean isResearchCompleted(short i) {
		return isResearchDone(i) && researchesDone.get(i) == (byte) 2;
	}

	/** Enables a research, passing in the ID of the research, and if it's supposed to save
	 * to NBT. The world parameter can be null if save is false.**/
	public void research(short i, boolean save, World world) {
		if(i >= 0 && (!researchesDone.containsKey(i) || researchesDone.get(i) != 2)) {
			researchesDone.put(i, (byte) 1);
			if(save)
				ResearchHelper.updateResearch(world, this);
		}
	}

	/** Completes the research entirely, passing in the ID of the research, and
	 * if it's supposed to save to NBT. The world parameter can be null if save is false. **/
	public void completeResearch(short i, boolean save, World world) {
		if(i >= 0 && researchesDone.containsKey(i) && researchesDone.get(i) == 1) {
			researchesDone.put(i, (byte) 2);
			if(save)
				ResearchHelper.updateResearch(world, this);
		}
	}

}
