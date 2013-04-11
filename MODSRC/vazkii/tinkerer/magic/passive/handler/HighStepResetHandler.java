/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 8 Apr 2013
package vazkii.tinkerer.magic.passive.handler;

import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.reference.SpellReference;

/**
 * HighStepResetHandler
 *
 * Handler to reset the effect of the High Step passive.
 *
 * @author Vazkii
 */
public class HighStepResetHandler {

	public static final HighStepResetHandler INSTANCE = new HighStepResetHandler();

	public static Set<String> playersWithHighStep = new LinkedHashSet();

	private HighStepResetHandler () { }

	@ForgeSubscribe(priority = EventPriority.HIGH)
	public void onLivingUpdate(LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityPlayer && event.entityLiving.worldObj.isRemote) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			boolean highStepListed = playersWithHighStep.contains(player.username);
			boolean hasHighStep = SpellHelper.doesPlayerHavePassive(player.username, SpellReference.PID_HIGH_STEP);

			if(hasHighStep && !highStepListed)
				playersWithHighStep.add(player.username);

			if(!hasHighStep && highStepListed) {
				playersWithHighStep.remove(player.username);
				player.stepHeight = 0.5F;
			}
		}
	}

}
