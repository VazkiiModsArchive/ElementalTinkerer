/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Feb 2013
package vazkii.tinkerer.magic.passive.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PlayerDamageHandler
 *
 * This handler handles player damage for various passives
 * (Burning cloud, undershirt, etc.)
 *
 * @author Vazkii
 */
public class PlayerDamageHandler {

	public static final PlayerDamageHandler INSTANCE = new PlayerDamageHandler();

	private PlayerDamageHandler() { }

	@ForgeSubscribe(priority = EventPriority.HIGHEST)
	public void onPlayerDamage(LivingHurtEvent event) {
		if(!(event.entityLiving instanceof EntityPlayer))
			return;
		EntityPlayer player = (EntityPlayer)event.entityLiving;

		if(SpellHelper.doesPlayerHavePassive(player.username, SpellReference.PID_BLOOD_BOIL))
			handleBloodBoil(event);

		if(SpellHelper.doesPlayerHavePassive(player.username, SpellReference.PID_BURNING_CLOUD))
			handleBurningCloud(event);

		if(SpellHelper.doesPlayerHavePassive(player.username, SpellReference.PID_IRONSKIN))
			handleIronskin(event);

		if(SpellHelper.doesPlayerHavePassive(player.username, SpellReference.PID_UNDERSHIRT))
			handleUndershirt(event);
	}

	public void handleBurningCloud(LivingHurtEvent event) {
		if(event.ammount > 0) {
			if(event.source.getEntity() != null) {
				Entity source = event.source.getEntity();
				if(source instanceof EntityPlayer && MiscHelper.isServerPVP())
					return; // This shouldn't really happen...

				source.setFire(event.ammount);
			}
		}
	}

	public void handleUndershirt(LivingHurtEvent event) {
		int health = event.entityLiving.getHealth();
		if(health > 3 && event.ammount >= health && event.source.isUnblockable()) {
			event.ammount = health - 1;
			PacketHelper.sendMessageToPlayer((EntityPlayer)event.entityLiving, "Your undershirt has protected you from death.");
		}
	}

	public void handleIronskin(LivingHurtEvent event) {
		if(event.ammount > 0 && !event.source.isFireDamage())
			event.ammount--;
	}

	public void handleBloodBoil(LivingHurtEvent event) {
		if(event.ammount >= 1) {
			event.ammount += 1;
			event.entityLiving.addPotionEffect(new PotionEffect(Potion.damageBoost.id, event.ammount * 15, 0));
		}
	}
}