/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Apr 2013
package vazkii.tinkerer.tile.cogwork;

import vazkii.tinkerer.block.cogwork.BlockCogs;
import net.minecraft.tileentity.TileEntity;

/**
 * TileCogwork
 *
 * The Cogwork base tile, for cogwork blocks.
 *
 * @author Vazkii
 */
public abstract class TileEntityCogwork extends TileEntity implements ICogworkTile {

	private long elapsedEnabledTicks;
	
	@Override
	public void updateEntity() {
		if(isEnabled())
			elapsedEnabledTicks++;
	}

	@Override
	public boolean isEnabled() {
		return BlockCogs.isEnabledFromMeta(getBlockMetadata());
	}
	
	public long getElapsedEnabledTicks() {
		return elapsedEnabledTicks;
	}

}
