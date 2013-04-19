/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Apr 2013
package vazkii.tinkerer.block.cogwork;

import net.minecraft.world.World;
import vazkii.tinkerer.tile.cogwork.TileEntityCogwork;
import vazkii.tinkerer.tile.cogwork.TileEntityRSEmitter;

/**
 * BlockRSEmitter
 *
 * The RS Emitter block, the metadata is 1 whilst powered, and 0 whilst not.
 *
 * @author Vazkii
 */
public class BlockRSEmitter extends BlockCogs {

	public BlockRSEmitter(int par1) {
		super(par1);
	}
	
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        boolean powered = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        boolean enabled = isEnabledFromMeta(meta);

        if(powered && !enabled)
        	par1World.setBlockMetadataWithNotify(par2, par3, par4, getEnabledMeta(meta), 2);
        
        if(!powered && enabled)
        	par1World.setBlockMetadataWithNotify(par2, par3, par4, getDisabledMeta(meta), 2);
    }

	@Override
	public TileEntityCogwork createNewTileEntity(World world) {
		return new TileEntityRSEmitter();
	}

}
