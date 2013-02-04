/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 3 Feb 2013
package vazkii.tinkerer.magic;

/**
 * IChargableSpell
 *
 * Interface for spells that need to be charged, this counts
 * for both channeled and charged spells.
 *
 * @author Vazkii
 */
public interface IChargableSpell {
	
	public int getMaxTime();

}
