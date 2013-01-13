/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Jan 2013
package vazkii.tinkerer.block;

import net.minecraft.block.material.Material;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * BlockElementalistTinkeringAltar
 *
 * The Elementalist Tinkering Altar block. This class handles
 * the texture, sided sprites and some important things
 * relating to the tile entity.
 *
 * @author Vazkii
 */
public class BlockElementalistTinkeringAltar extends BlockET/*Container*/ {

	public BlockElementalistTinkeringAltar(int par1) {
		super(par1, ResourcesReference.BLOCK_64_INDEX_ELEMENTALIST_TINKERING_ALTAR, Material.rock);
	}

	@Override
	public int getBlockTextureFromSide(int par1) {
		return par1 == 1 ? super.getBlockTextureFromSide(par1) + 1 : super.getBlockTextureFromSide(par1);
	}

	@Override
	public String getTextureFile() {
		return ResourcesReference.BLOCKS_64_SPRITESHEET;
	}

}
