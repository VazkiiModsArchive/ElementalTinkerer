/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Feb 2013
package vazkii.tinkerer.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.reference.RenderIDs;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * BlockScavenger
 *
 * The Scavenger block. The block doesn't do anything, it's all in the tile.
 *
 * @author Vazkii
 */
public abstract class BlockRotatingCubes extends BlockETContainer {

	public BlockRotatingCubes(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_TRANSPARENT, Material.rock);
	}

	/** Returns the GUI ID to open when right clicking this block. **/
	abstract int getGuiID();

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
		return RenderIDs.rotatingBlocks;
	}

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            TileEntity tile = world.getBlockTileEntity(x, y, z);

            if (tile != null)
                player.openGui(ElementalTinkerer.instance, getGuiID(), world, x, y, z);
        }

        return true;
    }
}
