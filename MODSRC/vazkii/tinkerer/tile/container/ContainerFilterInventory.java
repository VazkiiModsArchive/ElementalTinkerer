/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.tile.TileEntityFilter;
import vazkii.tinkerer.tile.slot.SlotLocationGem;
import vazkii.tinkerer.tile.slot.SlotNoInput;

/**
 * ContainerFilterInventory
 *
 * A Container for a Filter Inventory.
 *
 * @author Vazkii
 */
public class ContainerFilterInventory extends Container {

	private TileEntityFilter tile;

	public ContainerFilterInventory(TileEntityFilter tile, InventoryPlayer playerInv) {
		this.tile = tile;

		// Add slots for filter and outputs
		for(int x = 0; x < 3; ++x)
			for(int y = 0; y < 3; ++y)
				addSlotToContainer(new Slot(tile, y + x * 3 + tile.getFilterStart(), 50 + y * 18, 17 + x * 18));
		addSlotToContainer(new SlotNoInput(tile, tile.getFilterStart() + 9, 123, 35));
		addSlotToContainer(new SlotLocationGem(tile, tile.getFilterStart() + 10, 148, 12, tile.getMaxDistance()));
		addSlotToContainer(new SlotLocationGem(tile, tile.getFilterStart() + 11, 148, 58, tile.getMaxDistance()));

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
		return null;
	}

	public int getFilterStart() {
		return tile.getFilterStart();
	}

	/** Gets the slot correspondent to the slot that items will
	 * go in if they match the filter. **/
	public Slot getIfSlot() {
		return getSlot(10);
	}

	/** Gets the slot correspondent to the slot that items will
	 * go in if they don't match the filter. **/
	public Slot getElseSlot() {
		return getSlot(11);
	}

	public int getMaxDistance() {
		return tile.getMaxDistance();
	}
}
