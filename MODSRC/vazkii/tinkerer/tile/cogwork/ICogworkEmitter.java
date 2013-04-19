/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Apr 2013
package vazkii.tinkerer.tile.cogwork;

/**
 * ICogworkEmitter
 *
 * An Interface to flag a cogwork tile as an emitter.
 *
 * @author Vazkii
 */
public interface ICogworkEmitter extends ICogworkTile {

	/** Is emitting power? Independent of the regular isEnabled **/
	public boolean isEnabledForEmitting();
	
}
