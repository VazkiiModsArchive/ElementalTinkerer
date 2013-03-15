/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 8 Mar 2013
package vazkii.tinkerer.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.tile.TileEntityWaveInputter;

/**
 * BlockWaveInputter
 *
 * The Wave Inputter block. It has rotating cubes.
 *
 * @author Vazkii
 */
public class BlockWaveInputter extends BlockRotatingCubes {

	public BlockWaveInputter(int par1) {
		super(par1);
	}

	@Override
	int getGuiID() {
		return GuiIDs.ID_WAVE_INPUTTER;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityWaveInputter();
	}

}
