/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Feb 2013
package vazkii.tinkerer.magic.passive;

import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveIronskin
 *
 * The Ironskin passive
 *
 * @author Vazkii
 */
public class PassiveIronskin extends PassiveImpl {

	public PassiveIronskin() {
		super(SpellReference.PID_IRONSKIN,
				SpellReference.LABEL_IRONSKIN,
				SpellReference.DISPLAY_NAME_IRONSKIN,
				Element.EARTH.ordinal());
		bindNode(ResearchReference.ID_IRONSKIN);
	}

}
