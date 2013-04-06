/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.tile;

import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.item.ElementalTinkererItems;
import vazkii.tinkerer.network.packet.PacketElementalDeskSync;
import vazkii.tinkerer.reference.BlockNames;
import vazkii.tinkerer.reference.TileEntityReference;

/**
 * TileEntityElementalDesk
 *
 * Tile Entity for the Elemental Desk block.
 *
 * @author Vazkii
 */
public class TileEntityElementalDesk extends TileEntity implements IInventory {

	/** The Charge of the various crystals inputed **/
	byte[] charges = new byte[4];

	/** The progress of enchanting the book in the slot **/
	int progress = 0;

	/** True if the desk is enchanting a book, used to
	 *  stop if there is no charge available **/
	boolean isAdvancing = false;

	ItemStack[] inventorySlots = new ItemStack[5];

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public byte[] getCharges() {
		return charges;
	}

	public byte getCharge(int index) {
		return charges[index];
	}

	public void setCharge(int index, byte charge) {
		charges[index] = charge;
	}

	public boolean getIsAdvancing() {
		return isAdvancing;
	}

	public void setAdvancing(boolean advancing) {
		isAdvancing = advancing;
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
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);

        progress = par1NBTTagCompound.getInteger("progress");
        charges = par1NBTTagCompound.getByteArray("charges");
        isAdvancing = par1NBTTagCompound.getBoolean("isAdvancing");

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
		return PacketHelper.generatePacket(new PacketElementalDeskSync(this));
	}

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);

        par1NBTTagCompound.setInteger("progress", progress);
        par1NBTTagCompound.setByteArray("charges", charges);
        par1NBTTagCompound.setBoolean("isAdvancing", isAdvancing);

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
		super.updateEntity();

		boolean isBookIn = inventorySlots[4] != null && (inventorySlots[4].itemID == Item.book.itemID || inventorySlots[4].itemID == ElementalTinkererItems.unboundBook.itemID);

		// Check if there isn't a book in the book slot, sets progress to 0 if so
		if(!isBookIn) {
			progress = 0;
			isAdvancing = false;
		}

		// Check for gems in the gem slots and charge the table if there are
		checkForChargeReadyGems();

		// If the progress is at a stage where it requires more charge
		// to continue, tries to get it
		if(isBookIn && progress < TileEntityReference.ELEMENTAL_DESK_ENCHANT_TIME && (progress == 0 || progress % 40 == 0))
			isAdvancing = iterablySpendCharge(2);

		if(isBookIn && isAdvancing)
			progress++;

		if(progress >= TileEntityReference.ELEMENTAL_DESK_ENCHANT_TIME) {
			setInventorySlotContents(4, getStackOnEnchanting());
			for(int i = 0; i < 30; i++)
        		worldObj.spawnParticle("explode", xCoord + worldObj.rand.nextFloat(), yCoord + worldObj.rand.nextFloat() / 4 + 0.75F, zCoord + worldObj.rand.nextFloat(), 0F, 0F, 0F);
        	PacketHelper.sendPacketToAllClients(new PacketElementalDeskSync(this));
		}
	}

	public ItemStack getStackOnEnchanting() {
		int rand = worldObj.rand.nextInt(100);
		if(rand < TileEntityReference.ELEMENTAL_DESK_ENCHANTED_BOOK_CHANCE) {
			ItemStack enchantedBook = new ItemStack(Item.enchantedBook);
			List<EnchantmentData> allEnchants =  EnchantmentHelper.buildEnchantmentList(worldObj.rand, new ItemStack(Item.book), TileEntityReference.ELEMENTAL_DESK_ENCHANTED_BOOK_LEVEL);
			if(allEnchants != null)
				Item.enchantedBook.func_92115_a(enchantedBook, allEnchants.get(worldObj.rand.nextInt(allEnchants.size())));
			return enchantedBook;
		} else return new ItemStack(ElementalTinkererItems.elementalBook, 1, new Random().nextInt(4));
	}

	/** Spends as much charge as requested. Will return false if
	 *  there isn't enough charge to be spent **/
	public boolean iterablySpendCharge(int charge) {
		if(!canChargeBeSpent(charge))
			return false;

		for(int i = 0; i < charge; i++) {
			spendChargeFromRandomGem();
			checkForChargeReadyGems();
			// Check after spending one charge, there may be a
			// gem in that slot ready to be used
		}
		return true;
	}

	/** Spends one charge from one of the gems that doesn't have charge 0 **/
	public boolean spendChargeFromRandomGem() {
		boolean[] available = new boolean[4];
		boolean foundAnyAvailable = false;

		for(int i = 0; i < 4; i++)
			if(charges[i] != 0) {
				available[i] = true;
				foundAnyAvailable = true;
		}

		if(!foundAnyAvailable)
			return false;

		while(true) {
			int index = worldObj.rand.nextInt(4);
			if(available[index]) {
				charges[index]--;
				return true;
			}
		}
	}

	/** Checks if there is enough charge in the desk to
	 *  spend the amount passed in **/
	public boolean canChargeBeSpent(int chargeAsked) {
		int totalFoundCharge = 0;
		for(int i : charges)
			totalFoundCharge += i;

		for(int i = 0; i < 4; i++) {
			ItemStack stack = inventorySlots[i];
				if(stack == null)
					continue;

				if(charges[i] != 0)
					totalFoundCharge += TileEntityReference.ELEMENTAL_DESK_GEM_CHARGE;
		}

		return totalFoundCharge >= chargeAsked;
	}

	/** Checks if there are any gems ready to be consumed to create charge
	 *  and consumes them if so **/
	public void checkForChargeReadyGems() {
		for(int i = 0; i < 4; i++) {
			ItemStack stack = inventorySlots[i];

			if(stack == null)
				continue;

			if(charges[i] == 0) {
				charges[i] = TileEntityReference.ELEMENTAL_DESK_GEM_CHARGE;
				setInventorySlotContents(i, null);
			}
		}
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
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false; //Can't "pipe" things in!
	}
}
