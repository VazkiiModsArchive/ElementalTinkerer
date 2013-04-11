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
 * PassiveCloudSole
 *
 * The Cloud Sole passive.
 *
 * @author Vazkii
 */
public class PassiveCloudSole extends PassiveImpl {

	public PassiveCloudSole() {
		super(SpellReference.PID_CLOUD_SOLE,
				SpellReference.LABEL_CLOUD_SOLE,
				SpellReference.DISPLAY_NAME_CLOUD_SOLE,
				Element.AIR.ordinal());
		bindNode(ResearchReference.ID_CLOUD_SOLE);
	}

	@Override
	public void cast(EntityPlayer player) {
		player.fallDistance = Math.min(player.getMaxHealth() + 2F, player.fallDistance);
	}

}
