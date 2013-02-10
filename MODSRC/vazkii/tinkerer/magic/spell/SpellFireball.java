/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 29 Jan 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.entity.EntityFireball;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellFireball
 *
 * The Fireball spell.
 *
 * @author Vazkii
 */
public class SpellFireball extends SpellImpl {

	public SpellFireball() {
		super(SpellReference.ID_FIREBALL,
				SpellReference.LABEL_FIREBALL,
				SpellReference.DISPLAY_NAME_FIREBALL,
				ResourcesReference.MAGIC_INDEX_FIREBALL,
				SpellType.ACTIVE,
				Element.FIRE.ordinal());
		bindNode(ResearchReference.ID_FIREBALL);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
        player.worldObj.spawnEntityInWorld(new EntityFireball(player.worldObj, player, bonus));

        return true;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_FIREBALL;
	}
}
