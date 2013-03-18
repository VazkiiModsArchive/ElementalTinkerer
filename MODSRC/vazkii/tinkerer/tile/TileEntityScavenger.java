/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.tile;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.BlockNames;
import vazkii.tinkerer.reference.TileEntityReference;

/**
 * TileEntityScavenger
 *
 * The Tile Entity for the Scavenger block. It picks
 * up items on the floor and then tries to put them in
 * the inventories.
 *
 * @author Vazkii
 */
public class TileEntityScavenger extends TileEntityFilter {

	ItemStack[] inventorySlots = new ItemStack[13];

	@Override
	public int getHue() {
		return Element.EARTH.getHue();
	}

	@Override
	public int getQtd() {
		return 9;
	}

	@Override
	public void updateEntity() {
		if(!worldObj.isRemote && ElementalTinkerer.proxy.getGameTicksElapsed() % 3L == 0L) {
			super.updateEntity();
			AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(xCoord + 0.5 - TileEntityReference.SCAVENGER_SEARCH_RANGE, yCoord + 0.5 - TileEntityReference.SCAVENGER_SEARCH_RANGE, zCoord + 0.5 - TileEntityReference.SCAVENGER_SEARCH_RANGE, xCoord + 0.5 + TileEntityReference.SCAVENGER_SEARCH_RANGE, yCoord + 0.5 + TileEntityReference.SCAVENGER_SEARCH_RANGE, zCoord + 0.5 + TileEntityReference.SCAVENGER_SEARCH_RANGE);
			List<EntityItem> itemEntities = worldObj.getEntitiesWithinAABB(EntityItem.class, boundingBox);
			for(EntityItem entity : itemEntities) {
				ItemStack stack = entity.getDataWatcher().getWatchableObjectItemStack(10);
				if(canGetItem(stack)) {
					setInventorySlotContents(getInputSlot(), stack);
					entity.setDead();
					break;
				}
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return inventorySlots.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return inventorySlots[var1];
	}

	@Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (inventorySlots[par1] != null) {
            ItemStack stackAt;

            if (inventorySlots[par1].stackSize <= par2) {
                stackAt = inventorySlots[par1];
                inventorySlots[par1] = null;
                return stackAt;
            } else {
                stackAt = inventorySlots[par1].splitStack(par2);

                if (inventorySlots[par1].stackSize == 0)
                    inventorySlots[par1] = null;

                return stackAt;
            }
        }

        return null;
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return getStackInSlot(var1);
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		inventorySlots[var1] = var2;

        if (var2 != null && var2.stackSize > getInventoryStackLimit())
            var2.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInvName() {
		return BlockNames.WAVE_INPUTTER_NAME;
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
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);

        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        inventorySlots = new ItemStack[getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < inventorySlots.length)
                inventorySlots[var5] = ItemStack.loadItemStackFromNBT(var4);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);

    	// Item writing code "stolen" from TileEntityFurnace
    	// (because I'm lazy and that one works well enough)

    	NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < inventorySlots.length; ++var3) {
            if (inventorySlots[var3] != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                inventorySlots[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        par1NBTTagCompound.setTag("Items", var2);
    }

	@Override
	public int getFilterStart() {
		return 1;
	}

	@Override
	public int getInputSlot() {
		return 0;
	}

	@Override
	public int getMidSlot() {
		return 10;
	}

	@Override
	public int getIfSlot() {
		return 11;
	}

	@Override
	public int getElseSlot() {
		return 12;
	}

	@Override
	public int getMaxDistance() {
		return TileEntityReference.LGEM_MAX_DISTANCE_SCAVENGER;
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
		return i == 0;
	}
}
