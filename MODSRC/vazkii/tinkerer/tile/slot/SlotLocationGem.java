/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 8 Mar 2013
package vazkii.tinkerer.tile.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import vazkii.tinkerer.helper.InventoryHelper;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.item.ItemLocationGem;

/**
 * SlotLocationGem
 *
 * A Slot restricted to attuned Gems of Location.
 *
 * @author Vazkii
 */
public class SlotLocationGem extends Slot {

	TileEntity tile;
	double maxDistance;

	/** Can only be called if the TileEntity parameter can be
	 * casted to IInventory **/
	public SlotLocationGem(TileEntity tile, int par2, int par3, int par4, double maxDistance) {
		super((IInventory)tile, par2, par3, par4);
		this.tile = tile;
		this.maxDistance = maxDistance;
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		return par1ItemStack.getItem() != null
				&& par1ItemStack.getItem() instanceof ItemLocationGem
				&& InventoryHelper.validateLocationGem(tile.worldObj, par1ItemStack)
				&& (maxDistance == 0
				|| MathHelper.pointDistanceSpace(tile.xCoord, tile.yCoord, tile.zCoord, ItemLocationGem.getX(par1ItemStack), ItemLocationGem.getY(par1ItemStack), ItemLocationGem.getZ(par1ItemStack)) <= maxDistance);
	}
}
