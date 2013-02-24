/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 19 Feb 2013
package vazkii.tinkerer.item;

import net.minecraft.item.ItemStack;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;

/**
 * ICatalyst
 *
 * This Interface flags an item as a catalyst for the Elementalist
 * Tinkering Altar.
 *
 * @author Vazkii
 */
public interface ICatalyst {

	/** Can the item enter the catalyst slot? **/
	public boolean canFit(TileEntityElementalTinkeringAltar altar, ItemStack stack);
}
