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
 * OrichalcumTrigger
 *
 * Trigger for Orichalcum research, checks if all the ender
 * catalysts are done.
 *
 * @author Vazkii
 */
public class OrichalcumTrigger {

	public static final OrichalcumTrigger INSTANCE = new OrichalcumTrigger();

	private OrichalcumTrigger() { }

	@ForgeSubscribe
	public void onResearchComplete(ResearchCompleteEvent event) {
		if(event.research == ResearchReference.ID_CATALYST_START + 12
			|| event.research == ResearchReference.ID_CATALYST_START + 13
			|| event.research == ResearchReference.ID_CATALYST_START + 14
			|| event.research == ResearchReference.ID_CATALYST_START + 15) {
			boolean water = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 12)) || event.research == ResearchReference.ID_CATALYST_START + 12;
			boolean air = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 13)) || event.research == ResearchReference.ID_CATALYST_START + 13;
			boolean earth = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 14)) || event.research == ResearchReference.ID_CATALYST_START + 14;
			boolean fire = event.researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 15)) || event.research == ResearchReference.ID_CATALYST_START + 15;


			if(water && air && earth && fire)
				ResearchHelper.formulateResearchNode(ResearchReference.ID_ORICHALCUM, MiscHelper.getServer().getConfigurationManager().getPlayerForUsername(event.researchData.playerLinkedTo), ResearchReference.CATEGORY_NAME_PURE);
		}
	}
}
