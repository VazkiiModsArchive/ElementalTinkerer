/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.tile.TileEntityDislocator;

/**
 * BlockDislocator
 *
 * The Dislocator Block. It has rotating cubes.
 *
 * @author Vazkii
 */
public class BlockDislocator extends BlockRotatingCubes {

	public BlockDislocator(int par1) {
		super(par1);
	}

	@Override
	int getGuiID() {
		return GuiIDs.ID_DISLOCATOR;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityDislocator();
	}

}
