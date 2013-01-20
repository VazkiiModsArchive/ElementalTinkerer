/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Jan 2013
package vazkii.tinkerer.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.RenderIDs;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.TileEntityCatalystCapsule;

/**
 * BlockCatalystCapsule
 *
 * The Catalyst Capsule block, to be attached to the Elementalist
 * Tinkering Altar block, in order to have it accept catalysts.
 *
 * @author Vazkii
 */
public class BlockCatalystCapsule extends BlockETContainer {

	public BlockCatalystCapsule(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_TRANSPARENT, Material.iron);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving) {
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving);
		int meta = 0;
        if(par1World.getBlockId(par2 + 1, par3, par4) == BlockIDs.elementalistTinkeringAltar)
        	meta = 1;
        else if(par1World.getBlockId(par2 - 1, par3, par4) == BlockIDs.elementalistTinkeringAltar)
        	meta = 3;
        else if(par1World.getBlockId(par2, par3, par4 + 1) == BlockIDs.elementalistTinkeringAltar)
        	meta = 0;
        else if(par1World.getBlockId(par2, par3, par4 - 1) == BlockIDs.elementalistTinkeringAltar)
        	meta = 2;

        par1World.setBlockMetadataWithNotify(par2, par3, par4, meta);
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockId(par2 + 1, par3, par4) == BlockIDs.elementalistTinkeringAltar
				|| par1World.getBlockId(par2 - 1, par3, par4) == BlockIDs.elementalistTinkeringAltar
				|| par1World.getBlockId(par2, par3, par4 + 1) == BlockIDs.elementalistTinkeringAltar
				|| par1World.getBlockId(par2, par3, par4 - 1) == BlockIDs.elementalistTinkeringAltar;
	}

	@Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        if (!canBlockStay(par1World, par2, par3, par4)) {
            dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
    }

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		switch(par1World.getBlockMetadata(par2, par3, par4)) {
		case 0 :
			if(par1World.getBlockId(par2, par3, par4 + 1) == BlockIDs.elementalistTinkeringAltar)
				return true;
		case 1 :
			if(par1World.getBlockId(par2 + 1, par3, par4) == BlockIDs.elementalistTinkeringAltar)
				return true;
		case 2 :
			if(par1World.getBlockId(par2, par3, par4 - 1) == BlockIDs.elementalistTinkeringAltar)
				return true;
		case 3 :
			if(par1World.getBlockId(par2 - 1, par3, par4) == BlockIDs.elementalistTinkeringAltar)
				return true;
		}
		return false;
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
		return RenderIDs.catalystContainer;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityCatalystCapsule();
	}

}
