/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 2 Feb 2013
package vazkii.tinkerer.handler;

import vazkii.tinkerer.reference.GameReference;
import vazkii.tinkerer.reference.ItemIDs;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/**
 * ElementiumDustDropsHandler
 *
 * Mobs have a chance to drop elementium dust,
 * there's recipes to use emeralds/diamonds to make
 * elementium gems. This is used to make the elementium
 * detector before elementium is found.
 *
 * @author Vazkii
 */
public class ElementiumDustDropsHandler {

	public static final ElementiumDustDropsHandler INSTANCE = new ElementiumDustDropsHandler();
	
	private ElementiumDustDropsHandler() { }
	
    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (event.source.getDamageType().equals("player") && event.entity instanceof IMob && Math.random() < GameReference.ELEMENTIUM_DUST_MOB_DROP_CHANCE)
            	event.entity.dropItem(ItemIDs.elementiumDust, 1);
    }
}
