/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 30 Jan 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.entity.EntityFrostBolt;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellFrostbolt
 *
 * The Frostbolt spell.
 *
 * @author Vazkii
 */
public class SpellFrostbolt extends SpellImpl {

	public SpellFrostbolt() {
		super(SpellReference.ID_FROSTBOLT,
				SpellReference.LABEL_FROSTBOLT,
				SpellReference.DISPLAY_NAME_FROSTBOLT,
				ResourcesReference.MAGIC_INDEX_FROSTBOLT,
				SpellType.ACTIVE,
				Element.WATER.ordinal());
		bindNode(ResearchReference.ID_FROSTBOLT);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
        player.worldObj.spawnEntityInWorld(new EntityFrostBolt(player.worldObj, player, bonus));

        return true;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_FROSTBOLT;
	}

}
