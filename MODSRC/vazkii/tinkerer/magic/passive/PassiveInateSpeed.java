/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Feb 2013
package vazkii.tinkerer.magic.passive;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveInateSpeed
 *
 * The Inate Speed passive
 *
 * @author Vazkii
 */
public class PassiveInateSpeed extends PassiveImpl {

	public PassiveInateSpeed() {
		super(SpellReference.PID_INATE_SPEED,
				SpellReference.LABEL_INATE_SPEED,
				SpellReference.DISPLAY_NAME_INATE_SPEED,
				Element.AIR.ordinal());
		bindNode(ResearchReference.ID_INATE_SPEED);
	}

	@Override
	public void cast(EntityPlayer player) {
		player.landMovementFactor *= 1.08F;
	}
}