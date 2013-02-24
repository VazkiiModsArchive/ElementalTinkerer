/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 19 Feb 2013
package vazkii.tinkerer.magic.passive;

import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveEnderAbsorption
 *
 * The Ender Absorption passive.
 *
 * @author Vazkii
 */
public class PassiveEnderAbsorption extends PassiveImpl {

	public PassiveEnderAbsorption() {
		super(SpellReference.PID_ENDER_ABSORPTION,
				SpellReference.LABEL_ENDER_ABSORPTION,
				SpellReference.DISPLAY_NAME_ENDER_ABSORPTION,
				ResourcesReference.MAGIC_INDEX_ENDER_ABSORPTION,
				4); // Pure
		bindNode(ResearchReference.ID_ENDER_ABSORPTION);
	}

}
