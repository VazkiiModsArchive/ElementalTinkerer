/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Mar 2013
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.helper.ItemNBTHelper;
import vazkii.tinkerer.reference.ResourcesReference;

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
		iconIndex = ResourcesReference.ITEM_INDEX_ATTUNED_TOME;
		setMaxStackSize(1);
	}

	@Override
	public float getSmeltingExperience(ItemStack item) {
		return super.getSmeltingExperience(item);
	}

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
