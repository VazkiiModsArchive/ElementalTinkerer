/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Feb 2013
package vazkii.tinkerer.tile;

import net.minecraft.tileentity.TileEntity;

/**
 * TileEntityRotatingCubes
 *
 * The Tile Entity for the blocks with rotating cubes.
 *
 * @author Vazkii
 */
public abstract class TileEntityRotatingCubes extends TileEntity {

	/** Gets the hue to render the cubes **/
	public abstract int getHue();

	/** Gets how many cubes should be rendered **/
	public abstract int getQtd();
}
