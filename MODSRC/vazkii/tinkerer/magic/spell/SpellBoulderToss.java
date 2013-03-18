/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 1 Feb 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.entity.EntityBoulder;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellBoulderToss
 *
 * The Boulder Toss Spell.
 *
 * @author Vazkii
 */
public class SpellBoulderToss extends SpellImpl {

	public SpellBoulderToss() {
		super(SpellReference.ID_BOULDER_TOSS,
				SpellReference.LABEL_BOULDER_TOSS,
				SpellReference.DISPLAY_NAME_BOULDER_TOSS,
				SpellType.ACTIVE,
				Element.EARTH.ordinal());
		bindNode(ResearchReference.ID_BOULDER_TOSS);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
        player.worldObj.spawnEntityInWorld(new EntityBoulder(player.worldObj, player, bonus));

        return true;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_BOULDER_TOSS;
	}
}
