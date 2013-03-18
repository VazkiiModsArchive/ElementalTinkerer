/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Mar 2013
package vazkii.tinkerer.item;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.block.ElementalTinkererBlocks;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.BlockNames;

/**
 * ItemDarkQuartzSlab
 *
 * The ItemBlock for Dark Quartz slabs.
 *
 * @author Vazkii
 */
public class ItemDarkQuartzSlab extends ItemSlab {

	public ItemDarkQuartzSlab(int par1) {
		super(par1, (BlockHalfSlab) ElementalTinkererBlocks.darkQuartzSlab, (BlockHalfSlab) ElementalTinkererBlocks.darkQuartzFullSlab, par1 == BlockIDs.darkQuartzFullSlab);
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return BlockNames.DARK_QUARTZ_SLAB_DISPLAY_NAME;
	}

}
