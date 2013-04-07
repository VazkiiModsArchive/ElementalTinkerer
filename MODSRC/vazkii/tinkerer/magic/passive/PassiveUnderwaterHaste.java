/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 7 Apr 2013
package vazkii.tinkerer.magic.passive;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveUnderwaterHaste
 *
 * TODO Add Desc.
 *
 * @author Vazkii
 */
public class PassiveUnderwaterHaste extends PassiveImpl {

	public PassiveUnderwaterHaste() {
		super(SpellReference.PID_UNDERWATER_HASTE,
				SpellReference.LABEL_UNDERWATER_HASTE,
				SpellReference.DISPLAY_NAME_UNDERWATER_HASTE,
				Element.WATER.ordinal());
		bindNode(ResearchReference.ID_UNDERWATER_HASTE);
	}

	@Override
	public void cast(EntityPlayer player) {
		if(player.isInsideOfMaterial(Material.water)) {
			double motionX = player.motionX * 1.3;
			double motionZ = player.motionZ * 1.3;

			boolean changeX = Math.min(1.3, Math.abs(motionX)) == Math.abs(motionX);
			boolean changeZ = Math.min(1.3, Math.abs(motionZ)) == Math.abs(motionZ);

			if(changeX)
				player.motionX = motionX;
			if(changeZ)
				player.motionZ = motionZ;
		}
	}

}
