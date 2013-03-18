/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Mar 2013
package vazkii.tinkerer.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import vazkii.tinkerer.gui.CreativeTabET;

/**
 * BlockETStairs
 *
 * Elemental Tinkerer Implementation of BlockStairs.
 *
 * @author Vazkii
 */
public class BlockETStairs extends BlockStairs {

	protected BlockETStairs(int par1, Block par2Block, int par3) {
		super(par1, par2Block, par3);
		setCreativeTab(CreativeTabET.INSTANCE);
	}

}
