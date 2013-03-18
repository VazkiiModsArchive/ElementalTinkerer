/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 2 Feb 2013
package vazkii.tinkerer.item;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.simpleanim.SimpleAnimations;

/**
 * ItemElementiumDetector
 *
 * The Elementium Detector item that "glows"
 * if there's elementium ore above or under the
 * player.
 *
 * @author Vazkii
 */
public class ItemElementiumDetector extends ItemET {

	public ItemElementiumDetector(int par1) {
		super(par1);
	}

	Icon[] icons = new Icon[2];

	@Override
	public void func_94581_a(IconRegister par1IconRegister) {
		for(int i = 0; i < 2; i++)
			icons[i] =  IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return icons[par2];
	}

	static final float hue = 280F / 360F; // Purple tone

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		if(par2 == 0)
			return super.getColorFromItemStack(par1ItemStack, par2);

		float saturation = (float) SimpleAnimations.ANIMATIONS[SimpleAnimations.ELEMENTIUM_DETECTOR_ANIM_INDEX].getCurrentValue() / (float) ResourcesReference.ANIM_MAX_ELEMENTIUM_DETECTOR;
		return Color.HSBtoRGB(hue, saturation, 0.9F);
	}
}
