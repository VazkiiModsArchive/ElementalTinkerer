/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.simpleanim;

import vazkii.tinkerer.reference.ResourcesReference;

/**
 * SimpleAnimations
 *
 * Stores tick based integer cycles used for minimalistic animations;
 *
 * @author Vazkii
 */
public final class SimpleAnimations {
	
	/** Array with the animations **/
	public static final TickBasedIntegerCycle[] ANIMATIONS =  new TickBasedIntegerCycle[] {	
			new TickBasedIntegerCycle(ResourcesReference.ITEM_32_ANIM_ELEMENTIUM_GEM_START, 
					ResourcesReference.ITEM_32_ANIM_ELEMENTIUM_GEM_END, 
					ResourcesReference.ANIM_SPEED_ELEMENTIUM_GEM), // Elementium Gem Animation
	};
	
	/** Integers for the array indexes for the various animations **/
	public static final int ELEMENTIUM_GEM_ANIM_INDEX = 0;
	
	public static void updateTick() {
		for(TickBasedIntegerCycle c : ANIMATIONS)
			c.updateTick(); //Update all the animations
	}
	
}
