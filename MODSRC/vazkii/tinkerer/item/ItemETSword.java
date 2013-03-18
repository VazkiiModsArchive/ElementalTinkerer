/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.gui.CreativeTabET;
import vazkii.tinkerer.reference.MiscReference;

/**
 * ItemETSword
 *
 * Elemental Tinkerer implementation of ItemSword.
 *
 * @author Vazkii
 */
public class ItemETSword extends ItemSword {

	public ItemETSword(int par1, EnumToolMaterial par2EnumToolMaterial) {
		super(par1 - MiscReference.ITEM_INDEX_SHIFT, par2EnumToolMaterial);
		// Pass in accurate IDs, negating the index shift
		setCreativeTab(CreativeTabET.INSTANCE);
	}

	@Override
	public void func_94581_a(IconRegister par1IconRegister) {
		iconIndex = IconHelper.forItem(par1IconRegister, this);
	}
}
