/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Mar 2013
package vazkii.tinkerer.block;

import java.util.Random;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vazkii.tinkerer.gui.CreativeTabET;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.BlockNames;

/**
 * BlockDarkQuartzSlab
 *
 * Half Slab implementation for the Dark Quartz Blocks.
 *
 * @author Vazkii
 */
public class BlockDarkQuartzSlab extends BlockHalfSlab {

	public BlockDarkQuartzSlab(int par1, boolean par2) {
		super(par1, par2, Material.rock);
		if(!par2) {
			setCreativeTab(CreativeTabET.INSTANCE);
			setLightOpacity(0);
		}
	}

	@Override
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
        return ElementalTinkererBlocks.darkQuartz.getBlockTextureFromSide(par1);
    }

    @Override
	public int idDropped(int par1, Random par2Random, int par3) {
        return BlockIDs.darkQuartzSlab;
    }

    @Override
    protected ItemStack createStackedBlock(int par1) {
        return new ItemStack(ElementalTinkererBlocks.darkQuartzSlab);
    }

	@Override
	public String getFullSlabName(int i) {
		return BlockNames.DARK_QUARTZ_FULL_SLAB_NAME;
	}

	@Override
    public void func_94332_a(IconRegister par1IconRegister) {
		// NO-OP
	}
}
