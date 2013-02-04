/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 30 Jan 2013
package vazkii.tinkerer.magic.spell;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.helper.LightningHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.lightning.LightningBolt;
import vazkii.tinkerer.lightning.Vector3;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.EffectReference;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellThunderbolt
 *
 * The Thunderbolt spell.
 *
 * @author Vazkii
 */
public class SpellThunderbolt extends SpellImpl {

	public SpellThunderbolt() {
		super(SpellReference.ID_THUNDERBOLT, 
				SpellReference.LABEL_THUNDERBOLT, 
				SpellReference.DISPLAY_NAME_THUNDERBOLT, 
				ResourcesReference.MAGIC_INDEX_THUNDERBOLT, 
				SpellType.ACTIVE, 
				Element.AIR.ordinal());
		bindNode(ResearchReference.ID_THUNDERBOLT);
	}
	
	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		Vec3 entityVector = Vec3.vec3dPool.getVecFromPool(player.posX + player.width / 2, player.posY + player.getEyeHeight(), player.posZ + player.width / 2);
		int range = 8;
		Vec3 entityLook = player.getLookVec(); // Normalized Vector
		Vec3 finalVector = entityVector.addVector(entityLook.xCoord * range, entityLook.yCoord * range, entityLook.zCoord * range);
		AxisAlignedBB boudingBox = player.boundingBox.addCoord(entityLook.xCoord * range, entityLook.yCoord * range, entityLook.zCoord * range).expand(1.2F, 1.2F, 1.2F);
		
		attack : {
			List<EntityLiving> entities = player.worldObj.getEntitiesWithinAABB(EntityLiving.class, boudingBox);
			if(entities.isEmpty())
				break attack;
			
			List<EntityLiving> entitiesToAttack = new ArrayList();
			for(EntityLiving entity : entities)
				if(entity != player && // Shouldn't happen, but it's possible
				   (MiscHelper.isServerPVP() || !(entity instanceof EntityPlayer))
				   && !entity.isDead)
						entitiesToAttack.add(entity);
			if(entitiesToAttack.isEmpty())
				break attack;
			
			for(EntityLiving entity : entitiesToAttack)
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), bonus ? SpellReference.DMG_THUNDERBOLT_MAG : SpellReference.DMG_THUNDERBOLT);
		}
				
		LightningHelper.spawnAndSyncLightningBolt(player.worldObj, Vector3.fromEntity(player), Vector3.fromVec3(finalVector), EffectReference.LIGHTNING_BOLT_SPEED_THUNDERBOLT, player.worldObj.rand.nextLong(), EffectReference.LIGHTNING_COLOR_THUNDERBOLT_OUTER, EffectReference.LIGHTNING_COLOR_THUNDERBOLT_INNER);
		return true;
	}
	
	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_THUNDERBOLT;
	}
}
