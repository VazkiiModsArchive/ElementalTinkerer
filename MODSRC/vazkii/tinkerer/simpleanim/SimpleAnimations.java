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
	public static final IIntegerCycle[] ANIMATIONS =  new IIntegerCycle[] {
			new TickBasedIntegerCycle(ResourcesReference.ITEM_32_ANIM_ELEMENTIUM_GEM_START,
					ResourcesReference.ITEM_32_ANIM_ELEMENTIUM_GEM_END,
					ResourcesReference.ANIM_SPEED_ELEMENTIUM_GEM), // Elementium Gem Animation

			new TickBasedIntegerCycle(ResourcesReference.ITEM_32_ANIM_ELEMENTIUM_INGOT_START,
					ResourcesReference.ITEM_32_ANIM_ELEMENTIUM_INGOT_END,
					ResourcesReference.ANIM_SPEED_ELEMENTIUM_INGOT), // Elementium Ingot Animation

			new ElementiumDetectorCycle(0,
					ResourcesReference.ANIM_MAX_ELEMENTIUM_DETECTOR,
					ResourcesReference.ANIM_SPEED_ELEMENTIUM_DETECTOR) // Elementium Detector Animation
	};

	/** Integers for the array indexes for the various animations **/
	public static final int ELEMENTIUM_GEM_ANIM_INDEX = 0,
							ELEMENTIUM_INGOT_ANIM_INDEX = 1,
							ELEMENTIUM_DETECTOR_ANIM_INDEX = 2;

	public static void updateTick() {
		for(IIntegerCycle a : ANIMATIONS)
			a.updateTick(); //Update all the animations
	}

}
