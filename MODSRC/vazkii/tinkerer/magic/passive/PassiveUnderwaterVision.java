/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 7 Apr 2013
package vazkii.tinkerer.magic.passive;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveUnderwaterVision
 *
 * The Underwater Vision Passive
 *
 * @author Vazkii
 */
public class PassiveUnderwaterVision extends PassiveImpl {

	public PassiveUnderwaterVision() {
		super(SpellReference.PID_UNDERWATER_VISION,
				SpellReference.LABEL_UNDERWATER_VISION,
				SpellReference.DISPLAY_NAME_UNDERWATER_VISION,
				Element.WATER.ordinal());
		bindNode(ResearchReference.ID_UNDERWATER_VISION);
	}

	@Override
	public void cast(EntityPlayer player) {
		if(player.isInsideOfMaterial(Material.water)) {
			if(player.getActivePotionEffect(Potion.nightVision) == null || player.getActivePotionEffect(Potion.nightVision).getDuration() <= 3)
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 3, 0));
		}
	}
}
