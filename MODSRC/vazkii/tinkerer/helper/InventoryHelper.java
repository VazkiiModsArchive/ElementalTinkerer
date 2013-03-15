/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 8 Mar 2013
package vazkii.tinkerer.helper;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import vazkii.tinkerer.item.ItemLocationGem;

/**
 * InventoryHelper
 *
 * Helper for interacting with inventories. Being that trough gems of
 * location or direct IInventory parameters.
 *
 * @author Vazkii
 */
public final class InventoryHelper {

	/** Validates if a location gem is valid for the currentIndex world **/
	public static boolean validateLocationGem(World world, ItemStack stack) {
		return stack != null && stack.getItem() != null && stack.getItem() instanceof ItemLocationGem && ItemLocationGem.isAttuned(stack) &&  ItemLocationGem.getDim(stack) == world.getWorldInfo().getDimension();
	}

	/** Gets an inventory correspondent to the location gem passed in, if it is one **/
	public static IInventory getInventoryForLocationGem(World world, ItemStack stack) {
		if(validateLocationGem(world, stack)) {
			TileEntity tile = world.getBlockTileEntity(ItemLocationGem.getX(stack), ItemLocationGem.getY(stack), ItemLocationGem.getZ(stack));
			if(tile instanceof IInventory)
				return (IInventory) tile;
		}
		return null;
	}

	/** Adds an ItemStack to an inventory, general called method **/
	public static boolean addStackToInventory(ItemStack stack, IInventory inventory, ForgeDirection direction) {
		return addStackToInventory(stack, inventory, direction, false);
	}

	/** Adds an ItemStack to an inventory, this is recursive if it finds a double chest **/
	public static boolean addStackToInventory(ItemStack stack, IInventory inventory, ForgeDirection direction, boolean doubleChest) {
		if(stack == null)
			return false;

		boolean moved = false;

		int startIndex = 0;
		int endIndex = inventory.getSizeInventory() - 1;

		if (inventory instanceof ISidedInventory && direction != null) {
			startIndex = ((ISidedInventory)inventory).getStartInventorySide(direction);
			endIndex = startIndex + ((ISidedInventory)inventory).getSizeInventorySide(direction) - 1;
		}

		int currentIndex = startIndex;

		if (stack.isStackable()) {
			while (stack.stackSize > 0 && currentIndex <= endIndex) {
				ItemStack stackInSlot = inventory.getStackInSlot(currentIndex) != null ? inventory.getStackInSlot(currentIndex).copy() : inventory.getStackInSlot(currentIndex);

				if (stackInSlot != null && stackInSlot.itemID == stack.itemID && (!stack.getHasSubtypes() || stack.getItemDamage() == stackInSlot.getItemDamage()) && ItemStack.areItemStackTagsEqual(stack, stackInSlot)) {
					int size = stackInSlot.stackSize + stack.stackSize;

					if (size <= stack.getMaxStackSize()){
						stack.stackSize = 0;
						stackInSlot = inventory.getStackInSlot(currentIndex).copy();
						stackInSlot.stackSize = size;
						inventory.setInventorySlotContents(currentIndex, stackInSlot);
						moved = true;
					}
					else if (stackInSlot.stackSize < stack.getMaxStackSize()) {
						stack.stackSize -= stack.getMaxStackSize() - stackInSlot.stackSize;
						stackInSlot = inventory.getStackInSlot(currentIndex).copy();
						stackInSlot.stackSize = stack.getMaxStackSize();
						inventory.setInventorySlotContents(currentIndex, stackInSlot);
						moved = true;
					}
				}
				currentIndex++;
			}
		}

		if (stack.stackSize > 0) {
			if (inventory instanceof ISidedInventory && direction != null)
				currentIndex = ((ISidedInventory)inventory).getStartInventorySide(direction);
			else currentIndex = 0;

			while (currentIndex <= endIndex) {
				ItemStack stackInSlot = inventory.getStackInSlot(currentIndex) != null ? inventory.getStackInSlot(currentIndex).copy() :  inventory.getStackInSlot(currentIndex);

				if (stackInSlot == null) {
					inventory.setInventorySlotContents(currentIndex, stack.copy());
					stack.stackSize = 0;
					moved = true;
					break;
				}
				currentIndex++;
			}
		}

		if (!doubleChest && stack.stackSize > 0 && inventory instanceof TileEntityChest) {
			if (((TileEntityChest)inventory).adjacentChestXNeg != null)
				moved = addStackToInventory(stack, ((TileEntityChest)inventory).adjacentChestXNeg, direction, true);

			else if (((TileEntityChest)inventory).adjacentChestZNeg != null)
				moved = addStackToInventory(stack, ((TileEntityChest)inventory).adjacentChestZNeg, direction, true);

			else if (((TileEntityChest)inventory).adjacentChestXPos != null)
				moved = addStackToInventory(stack, ((TileEntityChest)inventory).adjacentChestXPos, direction, true);

			else if (((TileEntityChest)inventory).adjacentChestZPosition != null)
				moved = addStackToInventory(stack, ((TileEntityChest)inventory).adjacentChestZPosition, direction, true);
		}

		return moved;
	}

	/** Gets, and removes an ItemStack to an inventory, general called method **/
	public static int getStackFromInventory(ItemStack stack, IInventory inventory, ForgeDirection direction) {
		return getStackFromInventory(stack, inventory, direction, false);
	}

	/** Gets, and removes an ItemStack to an inventory, this is recursive if it finds a double chest **/
	public static int getStackFromInventory(ItemStack stack, IInventory inventory, ForgeDirection direction, boolean doubleChest) {
		if(stack == null)
			return -1;

		int startIndex = 0;
		int endIndex = inventory.getSizeInventory() - 1;

		if (inventory instanceof ISidedInventory && direction != null) {
			startIndex = ((ISidedInventory)inventory).getStartInventorySide(direction);
			endIndex = endIndex + ((ISidedInventory)inventory).getSizeInventorySide(direction) - 1;
		}

		int currentIndex = startIndex;

		for(; currentIndex < endIndex; currentIndex++) {
			ItemStack stackInSlot = inventory.getStackInSlot(currentIndex) != null ? inventory.getStackInSlot(currentIndex).copy() : inventory.getStackInSlot(currentIndex);

			if(MiscHelper.areStacksEqualIgnoreSize(stack, stackInSlot))
				return currentIndex;
		}

		if (!doubleChest && inventory instanceof TileEntityChest) {
			if (((TileEntityChest)inventory).adjacentChestXNeg != null)
				return getStackFromInventory(stack, ((TileEntityChest)inventory).adjacentChestXNeg, direction, true);

			else if (((TileEntityChest)inventory).adjacentChestZNeg != null)
				return getStackFromInventory(stack, ((TileEntityChest)inventory).adjacentChestZNeg, direction, true);

			else if (((TileEntityChest)inventory).adjacentChestXPos != null)
				return getStackFromInventory(stack, ((TileEntityChest)inventory).adjacentChestXPos, direction, true);

			else if (((TileEntityChest)inventory).adjacentChestZPosition != null)
				return getStackFromInventory(stack, ((TileEntityChest)inventory).adjacentChestZPosition, direction, true);
		}

		return -1;
	}

	/** Gets how much of an ItemStack exists in an inventory, general calling
	 * method **/
	public static int getQtdOfItemInInventory(ItemStack stack, IInventory inventory, ForgeDirection direction) {
		return getQtdOfItemInInventory(stack, inventory, direction, false);
	}

	/** Gets how much of an ItemStack exists in an inventory, this is recursive if
	 * it finds a double chest **/
	public static int getQtdOfItemInInventory(ItemStack stack, IInventory inventory, ForgeDirection direction, boolean doubleChest) {
		int startIndex = 0;
		int endIndex = inventory.getSizeInventory() - 1;

		if (inventory instanceof ISidedInventory && direction != null) {
			startIndex = ((ISidedInventory)inventory).getStartInventorySide(direction);
			endIndex = endIndex + ((ISidedInventory)inventory).getSizeInventorySide(direction) - 1;
		}

		int currentIndex = startIndex;
		int found = 0;

		for(; currentIndex <= endIndex; currentIndex++) {
			ItemStack stackInSlot = inventory.getStackInSlot(currentIndex) != null ? inventory.getStackInSlot(currentIndex).copy() : inventory.getStackInSlot(currentIndex);

			if(stack == null && stackInSlot == null || MiscHelper.areStacksEqualIgnoreSize(stack, stackInSlot))
				found += stackInSlot.stackSize;
		}

		if (!doubleChest && inventory instanceof TileEntityChest) {
			if (((TileEntityChest)inventory).adjacentChestXNeg != null)
				found += getQtdOfItemInInventory(stack, ((TileEntityChest)inventory).adjacentChestXNeg, direction, true);

			else if (((TileEntityChest)inventory).adjacentChestZNeg != null)
				found += getQtdOfItemInInventory(stack, ((TileEntityChest)inventory).adjacentChestZNeg, direction, true);

			else if (((TileEntityChest)inventory).adjacentChestXPos != null)
				found += getQtdOfItemInInventory(stack, ((TileEntityChest)inventory).adjacentChestXPos, direction, true);

			else if (((TileEntityChest)inventory).adjacentChestZPosition != null)
				found += getQtdOfItemInInventory(stack, ((TileEntityChest)inventory).adjacentChestZPosition, direction, true);
		}

		return found;
	}
}
