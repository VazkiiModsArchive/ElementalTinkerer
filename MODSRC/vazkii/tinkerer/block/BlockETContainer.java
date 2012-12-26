/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import vazkii.tinkerer.gui.CreativeTabET;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * BlockETContainer
 *
 * Parent Abstract class for container blocks from Elemental Tinkerer
 *
 * @author Vazkii
 */
public abstract class BlockETContainer extends BlockContainer {

	public BlockETContainer(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
		setCreativeTab(CreativeTabET.INSTANCE);
	}

	@Override
	public String getTextureFile() {
		return ResourcesReference.BLOCKS_SPRITESHEET;
	}

}
