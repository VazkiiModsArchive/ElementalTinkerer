/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Mar 2013
package vazkii.tinkerer.tile;

import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import vazkii.tinkerer.block.voidStorage.NetworkEntry;
import vazkii.tinkerer.block.voidStorage.VoidEntry;
import vazkii.tinkerer.block.voidStorage.VoidMap;
import vazkii.tinkerer.helper.InventoryHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.item.ItemLocationGem;
import vazkii.tinkerer.network.packet.PacketVoidNetworkSync;
import vazkii.tinkerer.network.packet.PacketVoidStorage;
import vazkii.tinkerer.reference.BlockIDs;
import cpw.mods.fml.common.network.PacketDispatcher;

/**
 * TileEntityVoidNetwork
 *
 * The Tile Entity for the Void Network.
 *
 * @author Vazkii
 */
public class TileEntityVoidNetwork extends TileEntity {

	private static final int entryArraySize = 9 * 3;

	public NetworkEntry[] entries = new NetworkEntry[entryArraySize];

	@Override
	public void updateEntity() {
		if(!worldObj.isRemote) {
			boolean removed = false;
			for(int i = 0; i < entries.length; i++) {
				if(entries[i] != null) {
					int id = worldObj.getBlockId(entries[i].x, entries[i].y, entries[i].z);
					if(id != BlockIDs.voidGateway) {
						removeEntry(i);
						removed = true;
					} else {
						VoidEntry entry = VoidMap.theMap.getEntryAtCoord(entries[i].x, entries[i].z);
						if(entry == null || entry.stack == null || entry.qtd == 0) {
							removeEntry(i);
							removed = true;
						}
					}
				}
			}
			if(removed)
				PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
		}


		IInventory invOnTop = getInvOnTop();
		if(invOnTop == null)
			return;

		handleOutput(invOnTop);
		handleMaintenance(invOnTop);
	}

	/** Handles Auto-Output of items **/
	public void handleOutput(IInventory inv) {
		for(int i = 0; i < entries.length; i++) {
			if(entries[i] != null && entries[i].autoStore) {
				int keep = entries[i].keep; // Keep the amount requested...
				int itemsInInv = InventoryHelper.getQtdOfItemInInventory(entries[i].getEntry(worldObj).stack, inv, ForgeDirection.DOWN);
				int take = itemsInInv - keep;
				if(take > 0)
					store(inv, i, Math.min(16, take));
			}
		}
	}

	/** Handles Maintaining a correct amount of items in the inventory **/
	public void handleMaintenance(IInventory inv) {
		for(int i = 0; i < entries.length; i++) {
			if(entries[i] != null && entries[i].keep > 0) {
				int keep = entries[i].keep;
				int itemsInInv = InventoryHelper.getQtdOfItemInInventory(entries[i].getEntry(worldObj).stack, inv, ForgeDirection.DOWN);
				int missing = keep - itemsInInv;
				if(missing > 0)
					request(inv, i, Math.min(8, missing));
			}
		}
	}

	/** Fired on the server via packet, handles a button click. **/
	public void handleButtonClick(EntityPlayer player, int button, int selected, int number) {
		if(selected < entries.length && entries[selected] != null) {
			boolean triggerPacket = false;
			switch(button) {
				case 0 : {
					entries[selected].keep = number;
					triggerPacket = true;
					break;
				}
				case 1 : {
					entries[selected].autoStore = !entries[selected].autoStore;
					triggerPacket = true;
					break;
				}
				case 2 : break;
				case 3 : {
					request(player.inventory, selected, number);
					break;
				}
				case 4 : {
					IInventory invOnTop = getInvOnTop();
					if(invOnTop != null)
						store(invOnTop, selected, number);
					break;

				}
				case 5 : {
					removeEntry(selected);
					triggerPacket = true;
					break;
				}
			}

			if(triggerPacket)
				PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
		}
	}

	/** Adds the items to an inventory **/
	public void request(IInventory inv, int selected, int qtd) {
		ItemStack stack = entries[selected].getEntry(worldObj).stack.copy();
		int knockoffQtd = (int) Math.min(entries[selected].getEntry(worldObj).qtd - 1, qtd);
		// qtd - 1 to make sure the gateway doesn't get depleted
		int moved = 0;
		while(knockoffQtd > 0) {
			int stackSize = stack.stackSize = Math.min(64, knockoffQtd);
			stack.stackSize = stackSize;
			if(InventoryHelper.addStackToInventory(stack, inv, ForgeDirection.DOWN)) {
				knockoffQtd -= stackSize;
				moved += stackSize;
			} else break;
		}

		if(moved > 0 && !worldObj.isRemote) {
			entries[selected].getEntry(worldObj).qtd -= moved;
			VoidMap.theMap.setEntryAtCoord(entries[selected].x, entries[selected].z, entries[selected].getEntry(worldObj));

			PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
			PacketHelper.sendPacketToAllClients(new PacketVoidStorage(entries[selected].getEntry(worldObj), entries[selected].x, entries[selected].z));
		}
	}

	/** Pulls items from an inventory **/
	public void store(IInventory inv, int selected, int qtd) {
		ItemStack stack = entries[selected].getEntry(worldObj).stack.copy();
		int knockoffQtd = qtd;
		int qtdFound = 0;
		while(knockoffQtd > 0) {
			int stackIndex = InventoryHelper.getStackFromInventory(stack, inv, ForgeDirection.DOWN);
			if(stackIndex == -1)
				break; // Can't find any more items...

			ItemStack stackInv = inv.getStackInSlot(stackIndex);

			if(stackInv.stackSize >= knockoffQtd) {
				qtdFound += knockoffQtd;
				stackInv.stackSize -= knockoffQtd;
				if(stackInv.stackSize == 0)
					inv.setInventorySlotContents(stackIndex, null);
				knockoffQtd = 0; // Fulfilled!
			} else {
				qtdFound += stackInv.stackSize;
				knockoffQtd -= stackInv.stackSize;
				inv.setInventorySlotContents(stackIndex, null);
			}
		}

		if(qtdFound > 0 && !worldObj.isRemote) {
			entries[selected].getEntry(worldObj).qtd += qtdFound;
			VoidMap.theMap.setEntryAtCoord(entries[selected].x, entries[selected].z, entries[selected].getEntry(worldObj));

			PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
			PacketHelper.sendPacketToAllClients(new PacketVoidStorage(entries[selected].getEntry(worldObj), entries[selected].x, entries[selected].z));
		}
	}

	public IInventory getInvOnTop() {
		if(yCoord == 256)
			return null;

		TileEntity tile = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
		if(tile instanceof IInventory)
			return (IInventory) tile;
		return null;
	}

	/** Performs a sanity check on the size of the entries
	 * array, it can't be more or less than 27. If it isn't,
	 * then it just changes it to be that size. Also sorts the
	 * slots so null slots get pushed to the end. **/
	public void sanityCheckEntrySize() {
		if(entries.length != entryArraySize)
			entries = Arrays.copyOf(entries, entryArraySize);
		sort();
	}

	public void sort() {
		int nonNull = getNonNullEntries(false);
		NetworkEntry[] nonNullArray = new NetworkEntry[nonNull];
		int index = 0;
		for (NetworkEntry entrie : entries) {
			if(entrie != null) {
				nonNullArray[index] = entrie;
				index++;
			}
		}
		entries = Arrays.copyOf(nonNullArray, entryArraySize);
	}

	public int getNonNullEntries() {
		return getNonNullEntries(true);
	}

	public int getNonNullEntries(boolean sanityCheck) {
		if(sanityCheck)
			sanityCheckEntrySize();

		int nonNull = 0;
		for (NetworkEntry entrie : entries) {
			if(entrie == null)
				continue;
			nonNull++;
		}

		return nonNull;
	}

	public boolean addGem(ItemStack stack) {
		int nonNull = getNonNullEntries();
		if(InventoryHelper.validateLocationGem(worldObj, stack) && nonNull < entryArraySize) {
			int x = ItemLocationGem.getX(stack);
			int y = ItemLocationGem.getY(stack);
			int z = ItemLocationGem.getZ(stack);
			if(worldObj.getBlockTileEntity(x, y, z) instanceof TileEntityVoidGateway) {
				entries[nonNull] = newEntryFromGem(stack);
				return true;
			}
		}
		return false;
	}

	public void removeEntry(int entry) {
		entries[entry] = null;
	}

	public NetworkEntry newEntryFromGem(ItemStack stack) {
		int x = ItemLocationGem.getX(stack);
		int y = ItemLocationGem.getY(stack);
		int z = ItemLocationGem.getZ(stack);
		return new NetworkEntry(x, y, z);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		int nonNull = getNonNullEntries();
		par1nbtTagCompound.setInteger("qtd", nonNull);
		for(int i = 0; i < nonNull; i++) {
			NBTTagCompound cmp = new NBTTagCompound();
			entries[i].writeToNBT(cmp);
			par1nbtTagCompound.setCompoundTag("entry" + i, cmp);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		int qtd = par1nbtTagCompound.getInteger("qtd");
		Arrays.fill(entries, null);
		for(int i = 0; i < qtd; i++) {
			NBTTagCompound cmp = par1nbtTagCompound.getCompoundTag("entry" + i);
			NetworkEntry entry = NetworkEntry.getFromNBT(cmp);
			entries[i] = entry;
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		return PacketHelper.generatePacket(new PacketVoidNetworkSync(this));
	}
}
