/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.item;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;
import vazkii.tinkerer.gui.CreativeTabET;
import vazkii.tinkerer.reference.MiscReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemETSword
 *
 * Elemental Tinkerer implementation of ItemSword.
 *
 * @author Vazkii
 */
public class ItemETSword extends ItemSword {

	public ItemETSword(int par1, int par2, EnumToolMaterial par2EnumToolMaterial) {
		super(par1 - MiscReference.ITEM_INDEX_SHIFT, par2EnumToolMaterial);
		// Pass in accurate IDs, negating the index shift
		iconIndex = par2;
		setCreativeTab(CreativeTabET.INSTANCE);
	}

	@Override
	public String getTextureFile() {
		return ResourcesReference.ITEMS_SPRITESHEET;
	}
}
