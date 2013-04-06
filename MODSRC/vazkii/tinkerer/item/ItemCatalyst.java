/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Jan 2013
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;

/**
 * ItemCatalyst
 *
 * The various catalyst items, which are going to be used for the
 * elementalist's tinkering altar crafting.
 *
 * @author Vazkii
 */
public class ItemCatalyst extends ItemET implements ICatalyst {

	public ItemCatalyst(int par1) {
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
	public Icon getIconFromDamage(int par1) {
		return icons[par1];
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return par1ItemStack == null ? "" : nameFromMeta(par1ItemStack.getItemDamage());
	}

	public static String nameFromMeta(int meta) {
		int lvl = getLevel(meta);
		return String.format(ItemNames.CATALYST_ITEM_DISPLAY_NAME, lvl >= ItemNames.CATALYST_LEVELS.length ? "Impossible" : ItemNames.CATALYST_LEVELS[lvl], Element.getName(getElement(meta)));
	}

	public static int getElement(int meta) {
		return meta % 4;
	}

	public static int getLevel(int meta) {
		return meta / 4;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		super.getSubItems(par1, par2CreativeTabs, par3List);
		for(int i = 1; i < 16; i++)
			par3List.add(new ItemStack(par1, 1, i));
	}

	@Override
	public boolean canFit(TileEntityElementalTinkeringAltar altar, ItemStack stack) {
		return true;
	}
}
