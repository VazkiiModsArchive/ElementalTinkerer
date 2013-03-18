/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Feb 2013
package vazkii.tinkerer.magic.spell;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.EntityReference;
import vazkii.tinkerer.reference.PotionReference;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellFrostshock
 *
 * The Frostshock spell.
 *
 * @author Vazkii
 */
public class SpellFrostshock extends SpellImpl {

	public SpellFrostshock() {
		super(SpellReference.ID_FROSTSHOCK,
				SpellReference.LABEL_FROSTSHOCK,
				SpellReference.DISPLAY_NAME_FROSTSHOCK,
				SpellType.CHARGE,
				Element.WATER.ordinal());
		bindNode(ResearchReference.ID_FROSTSHOCK);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		int range = 3;
		AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range);
		List<EntityLiving> entities = player.worldObj.getEntitiesWithinAABB(EntityLiving.class, boundingBox);

		if(entities.isEmpty() || entities.size() == 1)
			return false; // No entities or just the player

		for(EntityLiving entity : entities) {
			if(entity == null || entity == player || !MiscHelper.isServerPVP() && entity instanceof EntityPlayer)
				continue;

			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), bonus ? SpellReference.DMG_FROSTSHOCK_MAG : SpellReference.DMG_FROSTSHOCK);
			entity.addPotionEffect(new PotionEffect(PotionReference.idFrozen, (bonus ? EntityReference.FREEZE_FROSTBOLT_MAG : EntityReference.FREEZE_FROSTBOLT) * 20));
		}
		return true;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_FROSTSHOCK;
	}
}
