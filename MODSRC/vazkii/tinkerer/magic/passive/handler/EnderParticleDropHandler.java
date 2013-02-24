/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 19 Feb 2013
package vazkii.tinkerer.magic.passive.handler;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.reference.GameReference;
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.reference.SpellReference;

/**
 * EnderParticleDropHandler
 *
 * This handler handles drops of Ender Particles. These drop
 * on a random chance when killing an enderman in the end
 * with the Ender Absorption passive enabled.
 *
 * @author Vazkii
 */
public class EnderParticleDropHandler {

	public static final EnderParticleDropHandler INSTANCE = new EnderParticleDropHandler();

	private EnderParticleDropHandler() { }

    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (event.source.getEntity() != null
        	&& event.source.getEntity() instanceof EntityPlayer
        	&& ((EntityPlayer)event.source.getEntity()).dimension == 1
        	&& SpellHelper.doesPlayerHavePassive(((EntityPlayer)event.source.getEntity()).username, SpellReference.PID_ENDER_ABSORPTION)
        	&& event.entity instanceof EntityEnderman
        	&& Math.random() < GameReference.ENDER_PARTICLE_DROP_CHANCE)
            	event.entity.dropItem(ItemIDs.enderParticle, 1);
    }
}
