/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.tile;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import vazkii.tinkerer.helper.InventoryHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.item.ItemLocationGem;

/**
 * TileEntityFilter
 *
 * A Tile Entity (implements IInventory) that has filter slots. It also extends
 * TileEntityRotatingCubes.
 *
 * @author Vazkii
 */
public abstract class TileEntityFilter extends TileEntityRotatingCubes implements IInventory {

	/** Gets the start of the filter inventory, similar implementation to
	 * ISidedInventory **/
	public abstract int getFilterStart();

	public abstract int getInputSlot();

	public abstract int getMidSlot();

	public abstract int getIfSlot();

	public abstract int getElseSlot();

	/** Gets the maximum distance of the gems of location
	 * in the if and else slots. **/
	public abstract int getMaxDistance();

	@Override
	public void updateEntity() {
		ItemStack stackInMidSlot = getStackInSlot(getMidSlot());
		ItemStack stackInInputSlot = getStackInSlot(getInputSlot());

		if(stackInMidSlot != null)
			moveToSlot();

		if(canGetItem(stackInInputSlot)) {
			setInventorySlotContents(getMidSlot(), stackInInputSlot);
			setInventorySlotContents(getInputSlot(), null);
		}
	}

	/** Moves an item in the mid slot to the Location Gem in the correspondent slot **/
	public void moveToSlot() {
		ItemStack stackInMidSlot = getStackInSlot(getMidSlot());
		boolean isInFilter = isInFilter(stackInMidSlot);
		int slot = isInFilter ? getIfSlot() : getElseSlot();
		ItemStack stack = getStackInSlot(slot);
		if(stack != null && InventoryHelper.validateLocationGem(worldObj, stack)) {
			IInventory inventory = InventoryHelper.getInventoryForLocationGem(worldObj, stack);
			if(inventory != null)
				InventoryHelper.addStackToInventory(stackInMidSlot, inventory, ForgeDirection.getOrientation(ItemLocationGem.getSide(stack)));
			if(stackInMidSlot.stackSize == 0)
				setInventorySlotContents(getMidSlot(), null);
		}
	}

	/** Checks if it can get an item into the input slot, that
	 * or move it into the mid slot. **/
	public boolean canGetItem(ItemStack stack) {
		ItemStack stackInMidSlot = getStackInSlot(getMidSlot());

		if(stack == null || stackInMidSlot != null)
			return false;

		boolean hasIfGem = getStackInSlot(getIfSlot()) != null;
		boolean hasElseGem = getStackInSlot(getElseSlot()) != null;

		if(!hasIfGem && !hasElseGem)
			return false; // Nowhere for the item to go.

		if(hasIfGem && !hasElseGem)
			return isInFilter(stack); // Only IF Gem

		if(hasElseGem && !hasIfGem)
			return !isInFilter(stack); // Only ELSE Gem

		return true; // Has both, in this case everything can go
	}

	public boolean isInFilter(ItemStack stack) {
		for(int i = getFilterStart(); i < getFilterStart() + 9; i++) {
			ItemStack stackInSlot = getStackInSlot(i);
			if(stackInSlot != null && MiscHelper.areStacksEqualIgnoreSize(stack, stackInSlot))
				return true;
		}
		return false;
	}

}
