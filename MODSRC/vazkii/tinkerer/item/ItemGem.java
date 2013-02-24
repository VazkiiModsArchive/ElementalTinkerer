/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Feb 2013
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemGem
 *
 * The Gem item, currently it doesn't do much,
 * but it has different names and texture depending on the
 * metadata.
 *
 * @author Vazkii
 */
public class ItemGem extends ItemET {

	public ItemGem(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int var4 = 0; var4 < 16; ++var4)
			par3List.add(new ItemStack(par1, 1, var4));
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		int dmg = par1ItemStack.getItemDamage();
		return dmg >= 16 ? "" : "Mystic " + ItemNames.GEM_NAMES[dmg];
	}

	@Override
	public int getIconFromDamage(int par1) {
		int x = par1 % 4;
		int y = par1 / 4;
		return ResourcesReference.ITEM_INDEX_GEM_START + y * 16 + x;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		addNYIInfo(par3List);
	}
}
