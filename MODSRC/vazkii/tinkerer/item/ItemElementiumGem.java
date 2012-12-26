/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.item;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import vazkii.tinkerer.client.handler.ClientTickHandler;
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
		iconIndex = ResourcesReference.ITEM_32_ANIM_ELEMENTIUM_GEM_START;
	}

	@Override
	public boolean useDoubleResolution() {
		return true;
	}

	@Override
	public int getIconFromDamage(int par1) {
		return super.getIconFromDamage(par1) + (!ConfigurationHandler.elementiumGemAnimate ? 0 :
			   SimpleAnimations.ANIMATIONS[SimpleAnimations.ELEMENTIUM_GEM_ANIM_INDEX].getCurrentIndex());
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return !ConfigurationHandler.elementiumGemSpectrum ? 0xD83DFF :
			   Color.getHSBColor((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GEM), 0.6F, 1F).getRGB();
			   // Get a color based on hue, acquired trough a cosine function on the elapsed
			   // ticks, in order to create an animated spectrum effect.
	}
}
