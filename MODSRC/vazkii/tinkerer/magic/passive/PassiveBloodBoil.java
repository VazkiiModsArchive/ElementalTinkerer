/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Feb 2013
package vazkii.tinkerer.magic.passive;

import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveBloodBoil
 *
 * The Blood Boil passive.
 *
 * @author Vazkii
 */
public class PassiveBloodBoil extends PassiveImpl {

	public PassiveBloodBoil() {
		super(SpellReference.PID_BLOOD_BOIL,
				SpellReference.LABEL_BLOOD_BOIL,
				SpellReference.DISPLAY_NAME_BLOOD_BOIL,
				ResourcesReference.MAGIC_INDEX_BLOOD_BOIL,
				Element.FIRE.ordinal());
		bindNode(ResearchReference.ID_BLOOD_BOIL);
	}
}
