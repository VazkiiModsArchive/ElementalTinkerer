/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.tile.TileEntityScavenger;

/**
 * ContainerScavenger
 *
 * The Container for the Scavenger Inventory.
 *
 * @author Vazkii
 */
public class ContainerScavenger extends ContainerFilterInventory {

	public ContainerScavenger(TileEntityScavenger tile, InventoryPlayer playerInv) {
		super(tile, playerInv);

		addSlotToContainer(new Slot(tile, 0, 15, 35));
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 >= 1) {
                if (!mergeItemStack(var5, 0, 1, false))
                    return null;
            }

            if (var5.stackSize == 0)
                var4.putStack((ItemStack)null);
            else
                var4.onSlotChanged();

            if (var5.stackSize == var3.stackSize)
                return null;

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }

}
