/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Feb 2013
package vazkii.tinkerer.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.simpleanim.SimpleAnimations;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;

/**
 * ItemEnderParticle
 *
 * The ender particle item. It's icon changes with an integer
 * cycle.
 *
 * @author Vazkii
 */
public class ItemEnderParticle extends ItemET implements ICatalyst {

	public ItemEnderParticle(int par1) {
		super(par1);
	}

	@Override
	public int getIconFromDamage(int par1) {
		return SimpleAnimations.ANIMATIONS[SimpleAnimations.ENDER_PARTICLE_ANIM_INDEX].getCurrentValue();
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	public boolean canFit(TileEntityElementalTinkeringAltar altar, ItemStack stack) {
		return true;
	}
}
