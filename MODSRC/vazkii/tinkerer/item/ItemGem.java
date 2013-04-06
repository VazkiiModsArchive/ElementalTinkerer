/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Feb 2013
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.reference.ItemNames;

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

	Icon[] icons = new Icon[16];

	@Override
	public void updateIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < 16; i++)
			icons[i] =  IconHelper.forItem(par1IconRegister, this, i);
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
	public Icon getIconFromDamage(int par1) {
		return icons[par1];
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		addNYIInfo(par3List);
	}
}
