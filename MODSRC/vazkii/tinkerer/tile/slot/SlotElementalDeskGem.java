/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.tile.slot;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.tile.TileEntityElementalDesk;

/**
 * SlotElementalDeskGem
 *
 * Slot for the Elemental Desk that can only take an Elementium Gem
 *
 * @author Vazkii
 */
public class SlotElementalDeskGem extends Slot {

	public SlotElementalDeskGem(TileEntityElementalDesk par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	@Override
    public boolean isItemValid(ItemStack par1ItemStack) {
        return par1ItemStack.itemID == ItemIDs.elementiumGem;
    }

}
