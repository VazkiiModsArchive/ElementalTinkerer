/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Feb 2013
package vazkii.tinkerer.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.tinkerer.block.voidStorage.VoidEntry;
import vazkii.tinkerer.block.voidStorage.VoidMap;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.network.packet.PacketVoidStorage;
import vazkii.tinkerer.tile.TileEntityVoidGateway;

/**
 * BlockVoidGateway
 *
 * The Void Gateway block. This has a tile entity, no GUI,
 * interactions happen in right/left click. Tile entities
 * on this block exist to help with description packets.
 *
 * @author Vazkii
 */
public class BlockVoidGateway extends BlockETContainer {

	public BlockVoidGateway(int par1) {
		super(par1, Material.iron);
		setTickRandomly(true);
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		VoidEntry entry = VoidMap.theMap.getEntryAtCoord(par2, par4);
		if(entry != null) {
			ItemStack stack = entry.stack;
			if(stack != null && stack.itemID < Block.blocksList.length) {
				Block block = Block.blocksList[stack.itemID];
				if(block != null && block != this)
					block.randomDisplayTick(par1World, par2, par3, par4, par5Random);
			}
		}
	}

	@Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)  {
        int var6 = par1IBlockAccess.getBlockId(par2, par3, 1 - par4);
        return var6 == blockID ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, 1 - par4, par5);
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityVoidGateway();
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return par3 <= 8 && super.canPlaceBlockAt(par1World, par2, par3, par4);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		if(par3 > 8 && !par1World.isRemote) {
			par1World.setBlock(par2, par3, par4, 0, 0, 2);
			dropBlockAsItem(par1World, par2, par3, par4, 0, 0);
		}
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		ItemStack holding = par5EntityPlayer.getCurrentEquippedItem();
		VoidEntry entry = VoidMap.theMap.getEntryAtCoord(par2, par4);

		if(entry == null) {
			entry = new VoidEntry(null, 0);
			VoidMap.theMap.setEntryAtCoord(par2, par4, entry);
		}

		if(entry.stack == null && holding != null && !par1World.isRemote) {
			if(holding.hasTagCompound()) {
				PacketHelper.sendMessageToPlayer(par5EntityPlayer, "The gateway refuses to absorb this item.");
				return true;
			}

			ItemStack copyStack = holding.copy();
			entry.stack = copyStack;
			entry.qtd = copyStack.stackSize;

			VoidMap.theMap.writeToNBT(par1World);
			PacketHelper.sendPacketToAllClients(new PacketVoidStorage(entry, par2, par4));
			par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
		} else if(holding != null && MiscHelper.areStacksEqualIgnoreSize(entry.stack, holding) && !par1World.isRemote) {
			if(holding.hasTagCompound()) {
				PacketHelper.sendMessageToPlayer(par5EntityPlayer, "The gateway refuses to absorb this item.");
				return true;
			}

			entry.qtd += holding.stackSize;
			VoidMap.theMap.writeToNBT(par1World);
			PacketHelper.sendPacketToAllClients(new PacketVoidStorage(entry, par2, par4));
			par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
		} else if(entry.stack != null && holding == null && par5EntityPlayer.isSneaking()) {
			for(int i = 0; i < par5EntityPlayer.inventory.getSizeInventory() - 4; i++) {
				ItemStack stack = par5EntityPlayer.inventory.getStackInSlot(i);
				if(stack != null && MiscHelper.areStacksEqualIgnoreSize(entry.stack, stack)) {
					if(!par1World.isRemote)
						entry.qtd += stack.stackSize;
					par5EntityPlayer.inventory.setInventorySlotContents(i, null);
				}
			}
			if(!par1World.isRemote) {
				VoidMap.theMap.writeToNBT(par1World);
				PacketHelper.sendPacketToAllClients(new PacketVoidStorage(entry, par2, par4));
			}
		}
		return true;
	}

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
		par5EntityPlayer.getCurrentEquippedItem();
		VoidEntry entry = VoidMap.theMap.getEntryAtCoord(par2, par4);

		if(entry == null) {
			entry = new VoidEntry(null, 0);
			VoidMap.theMap.setEntryAtCoord(par2, par4, entry);
		}

		if(entry.stack != null && !par1World.isRemote) {
			int take = (int) Math.min(par5EntityPlayer.isSneaking() ? 1 : entry.stack.getMaxStackSize(), entry.qtd);
			if(take > 0) {
				ItemStack stack = entry.stack.copy();
				stack.stackSize = take;

				EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY, par5EntityPlayer.posZ, stack);
				entity.setVelocity(0, 0.01, 0);
				par1World.spawnEntityInWorld(entity);

				entry.qtd -= take;
				if(entry.qtd == 0)
					entry.stack = null;
				VoidMap.theMap.writeToNBT(par1World);
				PacketHelper.sendPacketToAllClients(new PacketVoidStorage(entry, par2, par4));
			}
		}
	}
}
