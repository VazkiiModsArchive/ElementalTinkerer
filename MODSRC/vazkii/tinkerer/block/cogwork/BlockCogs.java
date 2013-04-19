/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 11 Apr 2013
package vazkii.tinkerer.block.cogwork;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import vazkii.tinkerer.block.BlockET;
import vazkii.tinkerer.block.BlockETContainer;
import vazkii.tinkerer.block.ElementalTinkererBlocks;
import vazkii.tinkerer.tile.cogwork.TileEntityCogwork;

/**
 * BlockCogs
 *
 * General block with Cogs. VAZ_TODO Expand
 *
 * @author Vazkii
 */
public abstract class BlockCogs extends BlockETContainer<TileEntityCogwork> {

	public BlockCogs(int par1) {
		super(par1, Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
        return ElementalTinkererBlocks.darkQuartz.getBlockTextureFromSide(par1);
    }
	
	public static boolean isEnabledFromMeta(int meta) {
		return (meta & 8) == 8;
	}
	
	public static int getDisabledMeta(int enabledMeta) {
		return enabledMeta & 7;
	}
	
	public static int getEnabledMeta(int disabledMeta) {
		return disabledMeta | 8;
	}

}
