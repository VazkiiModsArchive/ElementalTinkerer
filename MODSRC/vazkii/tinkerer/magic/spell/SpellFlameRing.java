/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Feb 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.entity.EntityFlameRing;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellFlameRing
 *
 * The Flame Ring spell.
 *
 * @author Vazkii
 */
public class SpellFlameRing extends SpellImpl {

	public SpellFlameRing() {
		super(SpellReference.ID_FLAME_RING,
				SpellReference.LABEL_FLAME_RING,
				SpellReference.DISPLAY_NAME_FLAME_RING,
				ResourcesReference.MAGIC_INDEX_FLAME_RING,
				SpellType.CHARGE,
				Element.FIRE.ordinal());
		bindNode(ResearchReference.ID_FLAME_RING);
	}

	@Override
	public boolean castOnBlock(EntityPlayer player, boolean bonus, int x, int y, int z) {
		EntityFlameRing entity = new EntityFlameRing(player.worldObj, player.username, bonus);
		entity.setPosition(x + 0.5, y + 1, z + 0.5);
		player.worldObj.spawnEntityInWorld(entity);
		return true;
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		PacketHelper.sendMessageToPlayer(player, FormattingCode.RED + "This spell must be cast on a block.");
		return false;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_FLAME_RING;
	}
}
