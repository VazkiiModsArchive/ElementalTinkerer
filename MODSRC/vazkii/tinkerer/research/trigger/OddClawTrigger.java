/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Feb 2013
package vazkii.tinkerer.research.trigger;

import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.research.PlayerResearch;

/**
 * OddClawTrigger
 *
 * A Class that looks for ocelot deaths and triggers the odd claw
 * research if a player killed it.
 *
 * @author Vazkii
 */
public class OddClawTrigger {

	public static final OddClawTrigger INSTANCE = new OddClawTrigger();

	private OddClawTrigger() { }

	@ForgeSubscribe
	public void onEntityDeath(LivingDeathEvent event) {
		if(event.entityLiving instanceof EntityOcelot && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.source.getEntity();
			PlayerResearch researchData = ResearchHelper.getResearchDataForPlayer(player.username);

			if(researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 5)) &&
			researchData.isResearchCompleted((short) (ResearchReference.ID_CATALYST_START + 6)) &&
			researchData.isResearchCompleted(ResearchReference.ID_CATALYST_CAPSULE))
				ResearchHelper.formulateResearchNode(ResearchReference.ID_ODD_CLAW, player, ResearchReference.CATEGORY_NAME_PURE);
		}
	}

}
