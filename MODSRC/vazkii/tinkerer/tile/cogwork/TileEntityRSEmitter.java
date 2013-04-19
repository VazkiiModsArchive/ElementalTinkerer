/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Apr 2013
package vazkii.tinkerer.tile.cogwork;

/**
 * TileRSEmitter
 *
 * The Redstone Emitter tile entity. Enables when there's redstone applied
 * to it.
 *
 * @author Vazkii
 */
public class TileEntityRSEmitter extends TileEntityCogwork implements ICogworkEmitter {

	@Override
	public boolean isEnabledForEmitting() {
		return isEnabled();
	}

}
