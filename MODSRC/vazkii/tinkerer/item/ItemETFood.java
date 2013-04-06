/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Mar 2013
package vazkii.tinkerer.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.gui.CreativeTabET;
import vazkii.tinkerer.reference.MiscReference;

/**
 * ItemETFood
 *
 * Elemental Tinkerer implementation of ItemFood.
 *
 * @author Vazkii
 */
public class ItemETFood extends ItemFood {

	public ItemETFood(int par1, int par2, float par3, boolean par4) {
		super(par1 - MiscReference.ITEM_INDEX_SHIFT, par2, par3, par4);
		// Pass in accurate IDs, negating the index shift
		setCreativeTab(CreativeTabET.INSTANCE);
	}

	@Override
	public void updateIcons(IconRegister par1IconRegister) {
		iconIndex = IconHelper.forItem(par1IconRegister, this);
	}
}
