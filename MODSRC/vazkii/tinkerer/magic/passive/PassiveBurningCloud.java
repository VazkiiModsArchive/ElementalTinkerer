/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Feb 2013
package vazkii.tinkerer.magic.passive;

import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveBurningCloud
 *
 * The Burning Cloud passive.
 *
 * @author Vazkii
 */
public class PassiveBurningCloud extends PassiveImpl {

	public PassiveBurningCloud() {
		super(SpellReference.PID_BURNING_CLOUD,
				SpellReference.LABEL_BURNING_CLOUD,
				SpellReference.DISPLAY_NAME_BURNING_CLOUD,
				ResourcesReference.MAGIC_INDEX_BURNING_CLOUD,
				Element.EARTH.ordinal());
		bindNode(ResearchReference.ID_BURNING_CLOUD);
	}
}
