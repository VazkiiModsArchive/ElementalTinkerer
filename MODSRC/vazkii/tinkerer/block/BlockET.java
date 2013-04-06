/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.gui.CreativeTabET;

/**
 * ElementalTinkererBlock
 *
 * Parent Abstract class for blocks from Elemental Tinkerer
 *
 * @author Vazkii
 */
public class BlockET extends Block {

	public BlockET(int par1, Material par3Material) {
		super(par1, par3Material);
		if(registerInCreative())
			setCreativeTab(CreativeTabET.INSTANCE);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		blockIcon = IconHelper.forBlock(par1IconRegister, this);
	}

	/** Does the block register in the creative inv? **/
	boolean registerInCreative() {
		return true;
	}
}
