/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 8 Apr 2013
package vazkii.tinkerer.magic.passive;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveHighStep
 *
 * The High Step passive.
 *
 * @author Vazkii
 */
public class PassiveHighStep extends PassiveImpl {

	public PassiveHighStep() {
		super(SpellReference.PID_HIGH_STEP,
				SpellReference.LABEL_HIGH_STEP,
				SpellReference.DISPLAY_NAME_HIGH_STEP,
				Element.AIR.ordinal());
		bindNode(ResearchReference.ID_HIGH_STEP);
	}

	@Override
	public void cast(EntityPlayer player) {
		if(player.worldObj.isRemote)
			player.stepHeight = 1F;
	}

}
