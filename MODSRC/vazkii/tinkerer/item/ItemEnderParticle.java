/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Feb 2013
package vazkii.tinkerer.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vazkii.tinkerer.client.helper.IconHelper;
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

	Icon[] icons = new Icon[7];

	@Override
	public void func_94581_a(IconRegister par1IconRegister) {
		for(int i = 0; i < 7; i++)
			icons[i] =  IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return icons[SimpleAnimations.ANIMATIONS[SimpleAnimations.ENDER_PARTICLE_ANIM_INDEX].getCurrentValue()];
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
