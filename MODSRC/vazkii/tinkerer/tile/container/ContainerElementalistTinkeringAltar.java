/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 15 Jan 2013
package vazkii.tinkerer.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;
import vazkii.tinkerer.tile.slot.SlotElementalTinkeringAltar;
import vazkii.tinkerer.tile.slot.SlotElementalTinkeringAltarCatalyst;
import vazkii.tinkerer.tile.slot.SlotElementalTinkeringAltarOutput;

/**
 * ContainerElementalistTinkeringAltar
 *
 * The Container for the Elementalist's Tinkering Altar.
 *
 * @author Vazkii
 */
public class ContainerElementalistTinkeringAltar extends Container {

	public TileEntityElementalTinkeringAltar altar;

	public ContainerElementalistTinkeringAltar(InventoryPlayer playerInv, TileEntityElementalTinkeringAltar altar) {
		this.altar = altar;

		int shift = 19; // The shift for the container start, this is used
		// to correct shift clicking

		addSlotToContainer(new SlotElementalTinkeringAltarCatalyst(altar, 0, 14, 6 + shift));
		addSlotToContainer(new SlotElementalTinkeringAltarCatalyst(altar, 1, 146, 6 + shift));
		addSlotToContainer(new SlotElementalTinkeringAltarCatalyst(altar, 2, 14, 42 + shift));
		addSlotToContainer(new SlotElementalTinkeringAltarCatalyst(altar, 3, 146, 42 + shift));

		for(int y = 0; y < 5; ++y)
			for(int x = 0; x < 5; ++x)
				addSlotToContainer(new SlotElementalTinkeringAltar(altar, 4 + x + y * 5, 44 + x * 18, -12 + y * 18 + shift));

		addSlotToContainer(new SlotElementalTinkeringAltarOutput(altar, 29, 138, 91 + shift));

		// Add slots for Player Inventory
        for (int x = 0; x < 3; ++x)
            for (int y = 0; y < 9; ++y)
                addSlotToContainer(new Slot(playerInv, y + x * 9 + 9, 8 + y * 18, 116 + x * 18 + shift));

        for (int x = 0; x < 9; ++x)
            addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 174 + shift));
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 < 30)
            {
                if (!mergeItemStack(var5, 30, 66, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 >= 30 && par2 < 57)
            {
                if (!mergeItemStack(var5, 57, 66, false))
                {
                    return null;
                }
            }
            else if (par2 >= 57 && par2 < 66)
            {
                if (!mergeItemStack(var5, 30, 56, false))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(var5, 30, 56, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }

}
