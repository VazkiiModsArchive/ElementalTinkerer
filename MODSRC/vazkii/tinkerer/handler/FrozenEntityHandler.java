/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 30 Jan 2013
package vazkii.tinkerer.handler;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.tinkerer.reference.PotionReference;

/**
 * FrozenEntityHandler
 *
 * Handler for Frozen entities. Frozen entities have their movement nullified.
 * When damaged, the frozen debuff gets removed.
 *
 * @author Vazkii
 */
public final class FrozenEntityHandler {

	public static final FrozenEntityHandler INSTANCE = new FrozenEntityHandler();
	
	private FrozenEntityHandler() { }
	
	@ForgeSubscribe(priority = EventPriority.HIGHEST)
	public void onEntityUpdate(LivingUpdateEvent event) {
		if(isEntityFrozen(event.entityLiving)) {
			if(event.entityLiving instanceof EntityPlayer && ((EntityPlayer)event.entityLiving).capabilities.isCreativeMode)
				return; // Ignore creative mode
			
			event.entityLiving.landMovementFactor = 0F;
			event.entityLiving.jumpMovementFactor = 0F;
			event.entityLiving.setJumping(false);
			event.entityLiving.motionY = 0;
		} else {
			event.entityLiving.landMovementFactor = 0.1F;
			event.entityLiving.jumpMovementFactor = 0.02F;
			// This gets calibrated after, but for some entities (e.g. slimes)
			// it doesn't get calibrated automatically, so they stay
			// in the same place.
		}
	}
	
	@ForgeSubscribe
	public void onEntityDamage(LivingHurtEvent event) {
		if(isEntityFrozen(event.entityLiving))
			event.entityLiving.removePotionEffect(PotionReference.idFrozen);
	}
	
	public static boolean isEntityFrozen(EntityLiving entity) {
		return entity.isPotionActive(PotionReference.idFrozen);
	}
	
}
