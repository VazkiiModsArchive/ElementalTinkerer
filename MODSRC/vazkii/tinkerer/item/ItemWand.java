/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Jan 2013
package vazkii.tinkerer.item;

import java.awt.Color;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemWand
 *
 * A Wand. This item can cast various spells.
 *
 * @author Vazkii
 */
public class ItemWand extends ItemET {

	public ItemWand(int par1) {
		super(par1);
		setMaxStackSize(1);
		setHasSubtypes(true);
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return nameFromMeta(par1ItemStack.getItemDamage());
	}

	public static String nameFromMeta(int meta) {
		return ItemNames.WAND_NAME_PREFIX + Element.getSuffix(meta) + " (NYI)"; //VAZ_TODO Remove NYI Flag
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

	@Override
	public int getIconFromDamageForRenderPass(int par1, int par2) {
		return par2 == 0 ? ResourcesReference.ITEM_INDEX_WAND_REGULAR : ResourcesReference.ITEM_INDEX_WAND_COLORIZE;
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		if(par2 == 0)
			return super.getColorFromItemStack(par1ItemStack, par2);

		Element element = Element.class.getEnumConstants()[par1ItemStack.getItemDamage()];
		float bright = (float) Math.cos((double) ClientTickHandler.elapsedClientTicks / (double) ResourcesReference.BRIGHTNESS_DIVISOR_WAND);
		return Color.HSBtoRGB(element.getHue() / 360F, 1F, ConfigurationHandler.wandFlicker ? Math.max(0.2F, (bright + 1F) / 2F) : 0.9F);
	}
}