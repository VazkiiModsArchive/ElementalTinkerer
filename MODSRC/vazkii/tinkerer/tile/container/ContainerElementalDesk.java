/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.tile.TileEntityElementalDesk;
import vazkii.tinkerer.tile.slot.SlotElementalDeskBook;
import vazkii.tinkerer.tile.slot.SlotElementalDeskGem;

/**
 * ContainerElementalDesk
 *
 * Container type for the elemental desk block.
 *
 * @author Vazkii
 */
public class ContainerElementalDesk extends Container {

	public ContainerElementalDesk(InventoryPlayer playerInv, TileEntityElementalDesk desk) {
		// Add slots for Elementium Gems
		addSlotToContainer(new SlotElementalDeskGem(desk, 0, 8, 23));
		addSlotToContainer(new SlotElementalDeskGem(desk, 1, 8, 45));
		addSlotToContainer(new SlotElementalDeskGem(desk, 2, 152, 23));
		addSlotToContainer(new SlotElementalDeskGem(desk, 3, 152, 45));

		// Add the slot for the book
		addSlotToContainer(new SlotElementalDeskBook(desk, 4, 80, 62));

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
    	return null; // Returns null so no stack overflows happen...
	}
}
