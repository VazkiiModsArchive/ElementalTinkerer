/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Feb 2013
package vazkii.tinkerer.magic.passive;

import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveUndershirt
 *
 * The Undershirt passive.
 *
 * @author Vazkii
 */
public class PassiveUndershirt extends PassiveImpl {

	public PassiveUndershirt() {
		super(SpellReference.PID_UNDERSHIRT,
				SpellReference.LABEL_UNDERSHIRT,
				SpellReference.DISPLAY_NAME_UNDERSHIRT,
				4); // Pure
		bindNode(ResearchReference.ID_UNDERSHIRT);
	}
}
