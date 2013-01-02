/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Dec 2012
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemElementalBook
 *
 * Elemental book item.
 *
 * @author Vazkii
 */
public class ItemElementalBook extends ItemET {

	public ItemElementalBook(int par1) {
		super(par1);
		setMaxStackSize(1);
		setHasSubtypes(true);
		iconIndex = ResourcesReference.ITEM_INDEX_ELEMENTAL_BOOK_START;
	}

	@Override
	public int getIconFromDamage(int par1) {
		return super.getIconFromDamage(par1) + (par1 <= 3 ? par1 : 0);
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return ItemNames.ELEMENT_BOOK_NAME_PREFIX + Element.getSuffix(par1ItemStack.getItemDamage());
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		super.getSubItems(par1, par2CreativeTabs, par3List);
		for(int i = 1; i < 4; i++)
			par3List.add(new ItemStack(par1, 1, i));
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

}
