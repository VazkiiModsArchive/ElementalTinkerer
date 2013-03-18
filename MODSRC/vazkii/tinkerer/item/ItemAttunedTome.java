/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Mar 2013
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.helper.ItemNBTHelper;

/**
 * ItemAttunedTome
 *
 * The Attuned Tome item, that checks for Enchanting Tables
 * and allows to remotely enchant.
 *
 * @author Vazkii
 */
public class ItemAttunedTome extends ItemET {

	public ItemAttunedTome(int par1) {
		super(par1);
		setMaxStackSize(1);
	}

	Icon[] icons = new Icon[2];

	@Override
	public void func_94581_a(IconRegister par1IconRegister) {
		for(int i = 0; i < 2; i++)
			icons[i] =  IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return icons[par2];
	}

	//VAZ_TODO Implement!

	public static boolean hasCmp(ItemStack stack) {
		return ItemNBTHelper.detectNBT(stack);
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		addNYIInfo(par3List);
	}
}
