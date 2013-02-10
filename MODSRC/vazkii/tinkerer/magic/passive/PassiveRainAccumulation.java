/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Feb 2013
package vazkii.tinkerer.magic.passive;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveRainAccumulation
 *
 * The Rain Accumulation passive.
 *
 * @author Vazkii
 */
public class PassiveRainAccumulation extends PassiveImpl {

	public PassiveRainAccumulation() {
		super(SpellReference.PID_RAIN_ACCUMULATION,
				SpellReference.LABEL_RAIN_ACCUMULATION,
				SpellReference.DISPLAY_NAME_RAIN_ACCUMULATION,
				ResourcesReference.MAGIC_INDEX_RAIN_ACCUMULATION,
				Element.WATER.ordinal());
		bindNode(ResearchReference.ID_RAIN_ACCUMULATION);
	}

	@Override
	public void cast(EntityPlayer player) {
		World world = player.worldObj;
		WorldInfo info = world.getWorldInfo();
		if(player.ticksExisted % 80 == 0 && info.isRaining() && world.canBlockSeeTheSky((int) Math.round(player.posX), (int) Math.round(player.posY), (int) Math.round(player.posZ))) {
			InventoryPlayer inv = player.inventory;
			int active = inv.currentItem;
			ItemStack stack = inv.mainInventory[active];
			if(stack != null && stack.itemID == Item.bucketEmpty.itemID && stack.stackSize == 1)
				inv.setInventorySlotContents(active, new ItemStack(Item.bucketWater));
		}
	}

}
