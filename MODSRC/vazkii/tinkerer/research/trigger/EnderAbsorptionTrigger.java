/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 19 Feb 2013
package vazkii.tinkerer.research.trigger;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.ResearchReference;

/**
 * EnderAbsorptionTrigger
 *
 * A Class that looks for enderman deaths in the end and triggers the ender
 * absorption research if a player killed it.
 *
 * @author Vazkii
 */
public class EnderAbsorptionTrigger {

	public static final EnderAbsorptionTrigger INSTANCE = new EnderAbsorptionTrigger();

	private EnderAbsorptionTrigger() { }

	@ForgeSubscribe
	public void onEntityDeath(LivingDeathEvent event) {
		if(event.entityLiving instanceof EntityEnderman && event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer && !event.entityLiving.worldObj.isRemote) {
			EntityPlayer player = (EntityPlayer)event.source.getEntity();
			if(player.dimension != 1)
				return;

			ResearchHelper.getResearchDataForPlayer(player.username);
			ResearchHelper.formulateResearchNode(ResearchReference.ID_ENDER_ABSORPTION, player, ResearchReference.CATEGORY_NAME_PURE);
		}
	}

}
