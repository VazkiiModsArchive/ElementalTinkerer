/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 14 Mar 2013
package vazkii.tinkerer.research.trigger;

import net.minecraftforge.event.ForgeSubscribe;
import vazkii.tinkerer.core.event.ResearchCompleteEvent;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.ResearchReference;

/**
 * GaseousGlowstoneTrigger
 *
 * This class looks for completed researches of
 * Elemental Fire Catalysts and gives the Gaseous
 * Glowstone research.
 *
 * @author Vazkii
 */
public class GaseousGlowstoneTrigger {

	public static final GaseousGlowstoneTrigger INSTANCE = new GaseousGlowstoneTrigger();

	private GaseousGlowstoneTrigger() { }

	@ForgeSubscribe
	public void onResearchComplete(ResearchCompleteEvent event) {
		if(event.research == ResearchReference.ID_CATALYST_START + 7)
			ResearchHelper.formulateResearchNode(ResearchReference.ID_GASEOUS_GLOWSTONE, MiscHelper.getServer().getConfigurationManager().getPlayerForUsername(event.researchData.playerLinkedTo), ResearchReference.CATEGORY_NAME_PURE);
	}

}
