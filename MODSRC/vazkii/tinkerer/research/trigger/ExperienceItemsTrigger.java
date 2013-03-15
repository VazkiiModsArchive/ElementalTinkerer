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
 * ExperienceItemsTrigger
 *
 * Trigger for Experience items (spellbinding cloth and
 * withhold talisman)
 *
 * @author Vazkii
 */
public class ExperienceItemsTrigger {

	public static final ExperienceItemsTrigger INSTANCE = new ExperienceItemsTrigger();

	private ExperienceItemsTrigger() { }

	@ForgeSubscribe
	public void onResearchComplete(ResearchCompleteEvent event) {
		if(event.research == ResearchReference.ID_CATALYST_START + 4
			|| event.research == ResearchReference.ID_CATALYST_START + 5
			|| event.research == ResearchReference.ID_CATALYST_START + 6
			|| event.research == ResearchReference.ID_CATALYST_START + 7) {
			boolean water = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 4)) || event.research == ResearchReference.ID_CATALYST_START + 4;
			boolean air = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 5)) || event.research == ResearchReference.ID_CATALYST_START + 5;
			boolean earth = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 6)) || event.research == ResearchReference.ID_CATALYST_START + 6;
			boolean fire = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 7)) || event.research == ResearchReference.ID_CATALYST_START + 7;


			if(water && air && earth && fire)
				ResearchHelper.formulateResearchNode(ResearchReference.ID_SPELLBIND_CLOTH, MiscHelper.getServer().getConfigurationManager().getPlayerForUsername(event.researchData.playerLinkedTo), ResearchReference.CATEGORY_NAME_PURE);
		}

		if(event.research == ResearchReference.ID_CATALYST_START + 8
				|| event.research == ResearchReference.ID_CATALYST_START + 9
				|| event.research == ResearchReference.ID_CATALYST_START + 10
				|| event.research == ResearchReference.ID_CATALYST_START + 11) {
				boolean water = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 8)) || event.research == ResearchReference.ID_CATALYST_START + 8;
				boolean air = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 9)) || event.research == ResearchReference.ID_CATALYST_START + 9;
				boolean earth = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 10)) || event.research == ResearchReference.ID_CATALYST_START + 10;
				boolean fire = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 11)) || event.research == ResearchReference.ID_CATALYST_START + 11;


				if(water && air && earth && fire)
					ResearchHelper.formulateResearchNode(ResearchReference.ID_WITHHOLD_TALISMAN, MiscHelper.getServer().getConfigurationManager().getPlayerForUsername(event.researchData.playerLinkedTo), ResearchReference.CATEGORY_NAME_PURE);
			}
	}

}
