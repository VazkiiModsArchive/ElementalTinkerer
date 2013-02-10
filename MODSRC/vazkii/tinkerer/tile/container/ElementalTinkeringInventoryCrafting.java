/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 17 Jan 2013
package vazkii.tinkerer.tile.container;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;

/**
 * ElementalTinkeringInventoryCrafting
 *
 * More of a dummy than anything, this class passes in
 *
 * @author Vazkii
 */
public class ElementalTinkeringInventoryCrafting extends InventoryCrafting implements IDualCraftingInventory {

	public ElementalTinkeringInventoryCrafting() {
		super(new DummyContainer(), 0, 0);
	}

	InventoryCrafting shaped = new DummyInventoryCrafting(5, 5),
					  shapeless = new DummyInventoryCrafting(2, 2);

	public ElementalTinkeringInventoryCrafting(int par2, int par3) {
		super(new DummyContainer(), par2, par3);
	}

	@Override
	public InventoryCrafting getFirstInventory() {
		return shaped;
	}

	@Override
	public InventoryCrafting getSecondInventory() {
		return shapeless;
	}

	public void updateShapedInventory(TileEntityElementalTinkeringAltar altar) {
		for(int i = 0; i < shaped.getSizeInventory(); i++) {
			ItemStack stack = altar.getStackInSlot(i + shapeless.getSizeInventory());
			shaped.setInventorySlotContents(i, stack);
		}
	}

	public void updateShapelessInventory(TileEntityElementalTinkeringAltar altar) {
		for(int i = 0; i < shapeless.getSizeInventory(); i++) {
			ItemStack stack = altar.getStackInSlot(i);
			shapeless.setInventorySlotContents(i, stack);
		}
	}
}
