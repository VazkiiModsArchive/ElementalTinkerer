/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.item;

import java.awt.Color;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.simpleanim.SimpleAnimations;

/**
 * ItemElementiumGem
 *
 * Elementium Gem Item, this class handles the sprite index, thus, the animation.
 *
 * @author Vazkii
 */
public class ItemElementiumGem extends ItemET {

	public ItemElementiumGem(int par1) {
		super(par1);
	}

	Icon[] icons = new Icon[8];

	@Override
	public void func_94581_a(IconRegister par1IconRegister) {
		for(int i = 0; i < 8; i++)
			icons[i] =  IconHelper.forItem(par1IconRegister, this, i);

		// This is sort of a hack for my mod's other textures to work:
		IconHelper.initCustonSpritesheets();
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return !ConfigurationHandler.elementiumGemAnimate ? icons[0] :
			   icons[SimpleAnimations.ANIMATIONS[SimpleAnimations.ELEMENTIUM_GEM_ANIM_INDEX].getCurrentValue()];
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return !ConfigurationHandler.elementiumGemSpectrum ? 0xD83DFF :
			   Color.getHSBColor((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GEM), 0.6F, 1F).getRGB();
			   // Get a color based on hue, acquired trough a cosine function on the elapsed
			   // ticks, in order to create an animated spectrum effect.
	}
}
