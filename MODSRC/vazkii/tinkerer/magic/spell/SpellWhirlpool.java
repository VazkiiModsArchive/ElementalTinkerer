/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 7 Apr 2013
package vazkii.tinkerer.magic.spell;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.lightning.Vector3;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellWhirlpool
 *
 * The Whirlpool Spell.
 *
 * @author Vazkii
 */
public class SpellWhirlpool extends SpellImpl {

	public SpellWhirlpool() {
		super(SpellReference.ID_WHIRLPOOL,
				SpellReference.LABEL_WHIRLPOOL,
				SpellReference.DISPLAY_NAME_WHIRLPOOL,
				SpellType.ACTIVE,
				Element.WATER.ordinal());
		bindNode(ResearchReference.ID_WHIRLPOOL);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		int range = 8;
		float potency = bonus ? 2F : 1.5F;
		AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(player.posX - range, player.posY - range, player.posZ - range, player.posX + range, player.posY + range, player.posZ + range);
		List<EntityLiving> entities = player.worldObj.getEntitiesWithinAABB(EntityLiving.class, boundingBox);

		if(entities.isEmpty() || entities.size() == 1)
			return false; // No entities or just the player

		Vector3.fromEntityCenter(player);
		for(EntityLiving entity : entities) {
			if(entity == null || entity == player || !MiscHelper.isServerPVP() && entity instanceof EntityPlayer || !entity.isInWater())
				continue;

			MathHelper.moveEntityTowardsPos(entity, player.posX, player.posY, player.posZ, potency);
			entity.spawnExplosionParticle();
		}
		return true;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_WHIRLPOOL;
	}

}
