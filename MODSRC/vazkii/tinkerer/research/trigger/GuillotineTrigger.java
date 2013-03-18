/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Feb 2013
package vazkii.tinkerer.research.trigger;

import net.minecraft.item.Item;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.ResearchReference;

/**
 * GuillotineTrigger
 *
 * A class that looks for picking up skull items, when this
 * happens, if available, the guillotine research gets
 * triggered.
 *
 * @author Vazkii
 */
public class GuillotineTrigger {

	public static final GuillotineTrigger INSTANCE = new GuillotineTrigger();

	private GuillotineTrigger() { }

	@ForgeSubscribe
	public void onItemPickup(EntityItemPickupEvent event) {
		if(event.item.getEntityItem().getItem().itemID == Item.skull.itemID && event.entityPlayer.worldObj.isRemote) {
			ResearchHelper.getResearchDataForPlayer(event.entityPlayer.username);
			ResearchHelper.formulateResearchNode(ResearchReference.ID_ENDER_ABSORPTION, event.entityPlayer, ResearchReference.CATEGORY_NAME_PURE);
		}
	}
}
