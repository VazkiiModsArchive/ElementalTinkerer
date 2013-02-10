/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Feb 2013
package vazkii.tinkerer.magic.passive;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.magic.PassiveSpell;
import vazkii.tinkerer.magic.SpellType;

/**
 * PassiveSpellImpl
 *
 * TODO Add Desc.
 *
 * @author Vazkii
 */
public class PassiveImpl extends PassiveSpell {

	public PassiveImpl(short index, String label, String displayName, int spriteIndex, int element) {
		super(index, label, displayName, spriteIndex, element);
	}

	@Override
	public final SpellType getSpellType() {
		return SpellType.PASSIVE;
	}

	public void cast(EntityPlayer player) {
		// NO-OP
	}

	@Override
	public final boolean cast(EntityPlayer player, boolean bonus) {
		cast(player);
		return false;
	}

	@Override
	public final boolean castOnEntity(EntityPlayer player, boolean bonus, EntityLiving entity) {
		return false;
	}

	@Override
	public final boolean castOnBlock(EntityPlayer player, boolean bonus, int x, int y, int z) {
		return false;
	}

}
