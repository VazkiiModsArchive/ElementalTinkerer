/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Feb 2013
package vazkii.tinkerer.research.trigger;

import net.minecraftforge.event.ForgeSubscribe;
import vazkii.tinkerer.core.event.ResearchCompleteEvent;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.ResearchReference;

/**
 * VoidGatewayTrigger
 *
 * This class looks for completed researches of Greater Earth Catalysts
 * and Ender Air Catalysts. Once both are known, it triggers the
 * Void Gateway research.
 *
 * @author Vazkii
 */
public class VoidGatewayTrigger {

	public static final VoidGatewayTrigger INSTANCE = new VoidGatewayTrigger();

	private VoidGatewayTrigger() { }

	@ForgeSubscribe
	public void onResearchComplete(ResearchCompleteEvent event) {
		short lookFor;
		switch(event.research) {
			case ResearchReference.ID_CATALYST_START + 10 :
				lookFor = ResearchReference.ID_CATALYST_START + 13;
				break;
			case ResearchReference.ID_CATALYST_START + 13 :
				lookFor = ResearchReference.ID_CATALYST_START + 10;
				break;
			default : lookFor = -1;
		}

		if(lookFor != -1 && event.researchData.isResearchCompleted(lookFor))
			ResearchHelper.formulateResearchNode(ResearchReference.ID_VOID_GATEWAY, MiscHelper.getServer().getConfigurationManager().getPlayerForUsername(event.researchData.playerLinkedTo), ResearchReference.CATEGORY_NAME_PURE);
	}
}
