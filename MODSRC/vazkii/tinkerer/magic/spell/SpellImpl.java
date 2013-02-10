/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Jan 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.magic.Spell;
import vazkii.tinkerer.magic.SpellType;

/**
 * SpellImple
 *
 * Implementation of the abstract class Spell.
 *
 * @author Vazkii
 */
public class SpellImpl extends Spell {

	SpellType spellType;

	public SpellImpl(short index, String label, String displayName, int spriteIndex, SpellType type, int element) {
		super(index, label, displayName, spriteIndex, element);
		spellType = type;
	}

	@Override
	public SpellType getSpellType() {
		return spellType;
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		return false;
	}

	@Override
	public boolean castOnEntity(EntityPlayer player, boolean bonus, EntityLiving entity) {
		return false;
	}

	@Override
	public boolean castOnBlock(EntityPlayer player, boolean bonus, int x, int y, int z) {
		return false;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return 0;
	}
}
