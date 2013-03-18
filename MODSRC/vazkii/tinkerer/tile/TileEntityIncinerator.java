/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 3 Mar 2013
package vazkii.tinkerer.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.BlockNames;

/**
 * TileEntityIncinerator
 *
 * The Incinerator Tile Entity. It destroys any
 * item that gets put in it's only inventory slot.
 *
 * @author Vazkii
 */
public class TileEntityIncinerator extends TileEntityRotatingCubes implements IInventory {

	ItemStack stack;

	@Override
	public int getHue() {
		return Element.FIRE.getHue();
	}

	@Override
	public int getQtd() {
		return 6;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return stack;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
        if (stack != null) {
            if (stack.stackSize <= var2) {
                stack = null;
            } else {
                stack = stack.splitStack(var2);

                if (stack.stackSize == 0)
                    stack= null;
            }
        }
        return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return stack;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		for(int i = 0; var2 != null && i < 9; i++)
			worldObj.spawnParticle("flame", xCoord + Math.random(), yCoord + Math.random(), zCoord + Math.random(), Math.random() / 20, Math.random() / 10, Math.random() / 20);
	}

	@Override
	public String getInvName() {
		return BlockNames.INCINERATOR_NAME;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openChest() {
		// NO-OP
	}

	@Override
	public void closeChest() {
		// NO-OP
	}

	@Override
	public boolean func_94042_c() {
		return false;
	}

	@Override
	public boolean func_94041_b(int i, ItemStack itemstack) {
		return true;
	}
}
