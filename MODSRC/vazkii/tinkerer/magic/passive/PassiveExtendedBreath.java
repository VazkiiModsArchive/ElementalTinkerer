/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Feb 2013
package vazkii.tinkerer.magic.passive;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellExtendedBreath
 *
 * The Extended Breath passive.
 *
 * @author Vazkii
 */
public class PassiveExtendedBreath extends PassiveImpl {

	public PassiveExtendedBreath() {
		super(SpellReference.PID_EXTENDED_BREATH,
				SpellReference.LABEL_EXTENDED_BREATH,
				SpellReference.DISPLAY_NAME_EXTENDED_BREATH,
				Element.AIR.ordinal());
		bindNode(ResearchReference.ID_EXTENDED_BREATH);
	}

	@Override
	public void cast(EntityPlayer player) {
		if(player.getAir() < 300 && player.getAir() >= 0 && player.ticksExisted % 4 == 0)
				player.setAir(player.getAir() + 1);
	}
}
