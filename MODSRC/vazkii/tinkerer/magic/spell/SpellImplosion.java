/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Feb 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellImplosion
 *
 * The Implosion spell.
 *
 * @author Vazkii
 */
public class SpellImplosion extends SpellImpl {

	public SpellImplosion() {
			super(SpellReference.ID_IMPLOSION,
					SpellReference.LABEL_IMPLOSION,
					SpellReference.DISPLAY_NAME_IMPLOSION,
					ResourcesReference.MAGIC_INDEX_IMPLOSION,
					SpellType.CHARGE,
					Element.EARTH.ordinal());
			bindNode(ResearchReference.ID_IMPLOSION);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		float power = bonus ? 8F : 6F;
		player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, power, true);

		return true;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_IMPLOSION;
	}
}
