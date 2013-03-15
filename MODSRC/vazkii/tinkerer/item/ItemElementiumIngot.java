/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Jan 2013
package vazkii.tinkerer.item;

import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.simpleanim.SimpleAnimations;

/**
 * ItemElementiumIngot
 *
 * Elementium Ingot Item. This item is animated.
 *
 * @author Vazkii
 */
public class ItemElementiumIngot extends ItemET {

	public ItemElementiumIngot(int par1) {
		super(par1);
		iconIndex = ResourcesReference.ITEM_32_ANIM_ELEMENTIUM_INGOT_START;
	}

	@Override
	public boolean useDoubleResolution() {
		return true;
	}

	@Override
	public int getIconFromDamage(int par1) {
		return !ConfigurationHandler.elementiumIngotAnimate ? super.getIconFromDamage(par1) :
			   SimpleAnimations.ANIMATIONS[SimpleAnimations.ELEMENTIUM_INGOT_ANIM_INDEX].getCurrentValue();
	}

}
