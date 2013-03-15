/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 8 Mar 2013
package vazkii.tinkerer.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.reference.TileEntityReference;
import vazkii.tinkerer.tile.TileEntityWaveInputter;
import vazkii.tinkerer.tile.slot.SlotLocationGem;

/**
 * ContainerWaveInputter
 *
 * The container for the Wave Inputter inventory.
 *
 * @author Vazkii
 */
public class ContainerWaveInputter extends Container {

	public ContainerWaveInputter(TileEntityWaveInputter tile, InventoryPlayer playerInv) {
		// Add slots for Inventory
		for(int x = 0; x < 4; ++x) {
			Slot slot = x == 0 ? new Slot(tile, 0, 26, 18) : new SlotLocationGem(tile, x, 26 + 36 * x, 18, TileEntityReference.LGEM_MAX_DISTANCE_WAVE_INPUTTER);
			addSlotToContainer(slot);
		}

		for(int x = 0; x < 4; ++x)
			addSlotToContainer(new SlotLocationGem(tile, x + 4, 134 - 36 * x, 52, TileEntityReference.LGEM_MAX_DISTANCE_WAVE_INPUTTER));

		// Add slots for Player Inventory
        for (int x = 0; x < 3; ++x)
            for (int y = 0; y < 9; ++y)
                addSlotToContainer(new Slot(playerInv, y + x * 9 + 9, 8 + y * 18, 84 + x * 18));

        for (int x = 0; x < 9; ++x)
            addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 >= 8) {
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
