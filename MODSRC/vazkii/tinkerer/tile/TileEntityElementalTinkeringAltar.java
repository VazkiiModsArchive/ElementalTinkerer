/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 17 Jan 2013
package vazkii.tinkerer.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.network.packet.PacketElementalistTinkeringAltarSync;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.BlockNames;
import vazkii.tinkerer.reference.TileEntityReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.research.ResearchLibrary;
import vazkii.tinkerer.research.TinkeringAltarRecipe;
import vazkii.tinkerer.tile.container.ElementalTinkeringInventoryCrafting;

/**
 * TileEntityElementalTinkeringAltar
 *
 * The Tile Entity for the Tinkering Altar Block.
 *
 * @author Vazkii
 */
public class TileEntityElementalTinkeringAltar extends TileEntity implements IInventory {

	private boolean isCreating = false;
	private int progress = 0;

	ItemStack[] inventorySlots = new ItemStack[30];

	ElementalTinkeringInventoryCrafting craftingInventory = new ElementalTinkeringInventoryCrafting();

	/** Gets a prevision for what recipe is the one correspondent
	 * to the items in the crafting grids. This implementation
	 * checks for the players' research to see if the player
	 * has the research for this recipe. **/
	public ItemStack getCraftingPrevision(PlayerResearch research) {
		for(TinkeringAltarRecipe recipe : ResearchLibrary.recipes)
			if(recipe.matches(craftingInventory, research, worldObj))
				return recipe.getRecipeOutput();

		return null;
	}

	/** Gets if the altar is currently creating an item, this
	 * is used to prevent items from being removed while a recipe
	 * is ongoing.
	 */
	public boolean getIsCreating() {
		return isCreating;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public void setCreating(boolean creating) {
		isCreating = creating;
	}

	/** Checks if there is a catalyst capsule present
	 * on the side passed in **/
	public boolean hasCatalystCapsuleOnSide(int side) {
		switch(side) {
		case 0 : return worldObj.getBlockId(xCoord + 1, yCoord, zCoord) == BlockIDs.catalystCapsule;
		case 1 : return worldObj.getBlockId(xCoord - 1, yCoord, zCoord) == BlockIDs.catalystCapsule;
		case 2 : return worldObj.getBlockId(xCoord, yCoord, zCoord + 1) == BlockIDs.catalystCapsule;
		case 3 : return worldObj.getBlockId(xCoord, yCoord, zCoord - 1) == BlockIDs.catalystCapsule;
		}
		return false;
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
		return BlockNames.ELEMENTAL_DESK_NAME;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void updateEntity() {
		craftingInventory.getFirstInventory();
		InventoryCrafting shapeless = craftingInventory.getSecondInventory();
		craftingInventory.updateShapedInventory(this);
        craftingInventory.updateShapelessInventory(this);

        if(getIsCreating() && getCraftingPrevision(null) != null) {
        	if(getProgress() >= TileEntityReference.ELEMENTALIST_TINKERING_ALTAR_TIME && getStackInSlot(inventorySlots.length - 1) == null) {
        		craftItem();
        		setProgress(0);
        	} else setProgress(getProgress() + 1);
        } else {
        	setCreating(false);
        	setProgress(0);
        }

		for(int i = 0; i < shapeless.getSizeInventory(); i++) {
			ItemStack stack = getStackInSlot(i);
			if(!worldObj.isRemote && !hasCatalystCapsuleOnSide(i)) {
				if (stack != null) {
                    float xOffset = worldObj.rand.nextFloat() * 0.8F + 0.1F;
                    float yOffset = worldObj.rand.nextFloat() * 0.8F + 0.1F;
                    float zOffset = worldObj.rand.nextFloat() * 0.8F + 0.1F;

                    while (stack.stackSize > 0) {
                        int dropSize = worldObj.rand.nextInt(21) + 10;

                        if (dropSize > stack.stackSize)
                            dropSize = stack.stackSize;

                        stack.stackSize -= dropSize;
                        EntityItem item = new EntityItem(worldObj, xCoord + xOffset, yCoord + yOffset, zCoord + zOffset, new ItemStack(stack.itemID, dropSize, stack.getItemDamage()));

                        if (stack.hasTagCompound())
                            item.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());

                        item.motionX = (float)worldObj.rand.nextGaussian() / 20;
                        item.motionY = (float)worldObj.rand.nextGaussian() / 20;
                        item.motionZ = (float)worldObj.rand.nextGaussian() / 20;
                        worldObj.spawnEntityInWorld(item);
                        setInventorySlotContents(i, null);
                    }
				}
			}
		}

		if(getIsCreating()) {
			double smoke = worldObj.rand.nextFloat() * 2.0f - 1.0f;
			if(smoke > 0.5f) {
				if(hasCatalystCapsuleOnSide(0))
					ElementalTinkerer.proxy.spawnSteamParticle(worldObj, xCoord + 1.55, yCoord + 0.2, zCoord + 0.5, 0, 0, 0);
				if(hasCatalystCapsuleOnSide(1))
					ElementalTinkerer.proxy.spawnSteamParticle(worldObj, xCoord - 0.55, yCoord + 0.2, zCoord + 0.5, 0, 0, 0);
				if(hasCatalystCapsuleOnSide(2))
					ElementalTinkerer.proxy.spawnSteamParticle(worldObj, xCoord + 0.5, yCoord + 0.2, zCoord + 1.55, 0, 0, 0);
				if(hasCatalystCapsuleOnSide(3))
					ElementalTinkerer.proxy.spawnSteamParticle(worldObj, xCoord + 0.5, yCoord + 0.2, zCoord - 0.55, 0, 0, 0);
			}
		}
	}

	public void craftItem() {
		for(int i=0; i < inventorySlots.length - 1; i++)
			setInventorySlotContents(i, null);

		ItemStack stack = getCraftingPrevision(null);
		setInventorySlotContents(inventorySlots.length - 1, stack.copy());
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
	public Packet getDescriptionPacket() {
		return PacketHelper.generatePacket(new PacketElementalistTinkeringAltarSync(this));
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
		return false; //Can't "pipe" things in!
	}

}
