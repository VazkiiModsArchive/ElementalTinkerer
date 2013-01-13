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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
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

	/** Metadata correspondent to the rotation of the player placing
	 * the block. **/
	public int[] metaPerRotation = new int[] {
			2, 1, 4, 3
	};

	public BlockCatalystCapsule(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_TRANSPARENT, Material.iron);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving) {
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving);
        int metaToGet = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        par1World.setBlockMetadataWithNotify(par2, par3, par4, metaPerRotation[metaToGet]);
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
