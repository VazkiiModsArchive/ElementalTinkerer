/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Mar 2013
package vazkii.tinkerer.item;

import net.minecraft.item.ItemFood;
import vazkii.tinkerer.gui.CreativeTabET;
import vazkii.tinkerer.reference.MiscReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemETFood
 *
 * Elemental Tinkerer implementation of ItemFood.
 *
 * @author Vazkii
 */
public class ItemETFood extends ItemFood {

	public ItemETFood(int par1, int sprite, int par2, float par3, boolean par4) {
		super(par1 - MiscReference.ITEM_INDEX_SHIFT, par2, par3, par4);
		// Pass in accurate IDs, negating the index shift
		iconIndex = sprite;
		setCreativeTab(CreativeTabET.INSTANCE);
	}

	@Override
	public String getTextureFile() {
		return ResourcesReference.ITEMS_SPRITESHEET;
	}

}
