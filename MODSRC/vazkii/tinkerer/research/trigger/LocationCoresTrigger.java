/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 15 Mar 2013
package vazkii.tinkerer.research.trigger;

import net.minecraftforge.event.ForgeSubscribe;
import vazkii.tinkerer.core.event.ResearchCompleteEvent;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.ResearchReference;

/**
 * LocationCoresTrigger
 *
 * Trigger that checks for location core
 * research completes and gives the next
 * one if it's available.
 *
 * @author Vazkii
 */
public class LocationCoresTrigger {

	public static final LocationCoresTrigger INSTANCE = new LocationCoresTrigger();

	private LocationCoresTrigger() { }

	@ForgeSubscribe
	public void onResearchComplete(ResearchCompleteEvent event) {
		short give = -1;
		if(event.research == ResearchReference.ID_BASIC_CORE_LOCATION)
			give = ResearchReference.ID_ADV_CORE_LOCATION;

		if(event.research == ResearchReference.ID_ADV_CORE_LOCATION && event.researchData.isResearchCompleted(ResearchReference.ID_ORICHALCUM))
			give = ResearchReference.ID_ULTIMATE_CORE_LOCATION;

		if(event.research == ResearchReference.ID_ORICHALCUM && event.researchData.isResearchCompleted(ResearchReference.ID_ADV_CORE_LOCATION))
			give = ResearchReference.ID_ULTIMATE_CORE_LOCATION;

		if(give != -1)
			ResearchHelper.formulateResearchNode(give, MiscHelper.getServer().getConfigurationManager().getPlayerForUsername(event.researchData.playerLinkedTo), ResearchReference.CATEGORY_NAME_PURE);
	}

}
