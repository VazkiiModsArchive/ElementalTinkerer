/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Mar 2013
package vazkii.tinkerer.item;

import net.minecraft.item.ItemMultiTextureTile;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.block.ElementalTinkererBlocks;
import vazkii.tinkerer.reference.BlockNames;

/**
 * ItemDarkQuartzBlock
 *
 * The ItemBlock class for Dark Quartz. It
 * has a different name depending on the
 * metadata.
 *
 * @author Vazkii
 */
public class ItemDarkQuartzBlock extends ItemMultiTextureTile {

	public ItemDarkQuartzBlock(int par1) {
		super(par1, ElementalTinkererBlocks.darkQuartz, new String[] { });
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return par1ItemStack.getItemDamage() >= 3 ? "" : BlockNames.DARK_QUARTZ_BLOCK_NAMES[par1ItemStack.getItemDamage()];
	}
}
