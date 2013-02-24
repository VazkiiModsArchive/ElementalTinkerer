/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Feb 2013
package vazkii.tinkerer.core.event;

import net.minecraftforge.event.Event;
import vazkii.tinkerer.research.PlayerResearch;

/**
 * ResearchCompleteEvent
 *
 * A forge event fired when a research is completed.
 *
 * @author Vazkii
 */
public class ResearchCompleteEvent extends Event {

	public PlayerResearch researchData;
	public short research;

	public ResearchCompleteEvent(PlayerResearch researchData, short research) {
		this.researchData = researchData;
		this.research = research;
	}

}
