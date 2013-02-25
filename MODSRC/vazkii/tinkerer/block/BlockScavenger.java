/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Feb 2013
package vazkii.tinkerer.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.tinkerer.item.ItemET;
import vazkii.tinkerer.item.ItemMetadataCompatBlock;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.RenderIDs;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.TileEntityScavenger;

/**
 * BlockScavenger
 *
 * The Scavenger block. The block doesn't do anything, it's all in the tile.
 *
 * @author Vazkii
 */
public class BlockScavenger extends BlockETContainer {

	public BlockScavenger(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_TRANSPARENT, Material.rock);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderIDs.scavenger;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityScavenger();
	}

	public static class ItemScavenger extends ItemMetadataCompatBlock {

		//VAZ_TODO Remove

		public ItemScavenger(int par1) {
			super(par1);
		}

		@Override
		public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
			ItemET.addNYIInfo(par3List);
			par3List.add(FormattingCode.BLUE + "(This block is a model test, place it in the world!)");
		}

	}
}
