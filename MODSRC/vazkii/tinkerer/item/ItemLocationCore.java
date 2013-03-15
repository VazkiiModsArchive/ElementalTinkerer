/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemLocationCore
 *
 * The item class for the Location Core.
 *
 * @author Vazkii
 */
public class ItemLocationCore extends ItemET {

	public ItemLocationCore(int par1) {
		super(par1);
		iconIndex = ResourcesReference.ITEM_INDEX_LOCATING_CORE_BASE;
		setHasSubtypes(true);
	}

	@Override
	public int getIconFromDamage(int par1) {
		return iconIndex + par1;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		super.getSubItems(par1, par2CreativeTabs, par3List);
		for(int i = 1; i < 3; i++)
			par3List.add(new ItemStack(par1, 1, i));
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return par1ItemStack.getItemDamage() >= 3 ? "" : String.format(ItemNames.LOCATING_CORE_DISPLAY_NAME, ItemNames.LOCATION_CORE_LEVELS[par1ItemStack.getItemDamage()]);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return par1ItemStack.getItemDamage() == 2 ? EnumRarity.uncommon : EnumRarity.common;
	}

}
