/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Jan 2013
package vazkii.tinkerer.block;

import java.awt.Color;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * BlockElementiumGem
 *
 * The Elementium Gem Storage block. It is colored, but based
 * on block updates. That is intended. As to create an unstable
 * behaviour.
 *
 * @author Vazkii
 */
public class BlockElementiumGem extends BlockET {

	public BlockElementiumGem(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_ELEMENTIUM_GEM, Material.iron);
	}

	@Override
	public int getBlockColor() {
		return !ConfigurationHandler.elementiumGemSpectrum ? 0xD83DFF :
			   Color.getHSBColor((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GEM), 0.6F, 1F).getRGB();
	}

	@Override
	public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
		return getBlockColor();
	}

	@Override
	public int getRenderColor(int par1) {
		return getBlockColor();
	}
}
