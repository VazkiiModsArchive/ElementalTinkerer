/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Feb 2013
package vazkii.tinkerer.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.helper.MiscHelper;

/**
 * EntityFlameRing
 *
 * The entity summoned by the Flame Ring spell. It burns entities which are stepping on it.
 * It won't burn the entitiy that summoned it.
 *
 * @author Vazkii
 */
public class EntityFlameRing extends Entity {

	String summoner;
	boolean mag;

	public EntityFlameRing(World world) {
		super(world);
	}

	public EntityFlameRing(World world, String summoner, boolean mag) {
		super(world);
		this.summoner = summoner;
		this.mag = mag;
	}

	@Override
	protected void entityInit() {
		setSize(0F, 0F);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();

		float radius = 5F;
		float renderRadius = (float) (radius - Math.random());

		for(int i = 0; i < 90; i++) {
			float rad = MathHelper.degreeToRadian(i * 4);
			double x = Math.cos(rad) * renderRadius;
			double z = Math.sin(rad) * renderRadius;

			worldObj.spawnParticle("flame", posX + x, posY, posZ + z, 0F, Math.random() / 10F, 0F);
			if(worldObj.rand.nextInt(100) < 1) {
				float radiusX = (float) (renderRadius * Math.random());
				float radiusY = (float) (renderRadius * Math.random());
				double x1 = Math.cos(rad) * radiusX;
				double z1 = Math.sin(rad) * radiusY;
				worldObj.spawnParticle("lava", posX + x1, posY, posZ + z1, 0F, Math.random() / 10F, 0F);
			}
		}

		if(worldObj.isRemote)
			return;

		if(ticksExisted >= (mag ? 300 : 200)) {
			setDead();
			return;
		}

		AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX, posY, posZ).expand(radius, radius, radius);
		List<EntityLiving> entities = worldObj.getEntitiesWithinAABB(EntityLiving.class, boundingBox);

		if(entities.isEmpty())
			return;

		for(EntityLiving entity : entities) {
			if(entity == null || entity instanceof EntityPlayer && ((EntityPlayer)entity).username.equals(summoner) || !MiscHelper.isServerPVP() && entity instanceof EntityPlayer || MathHelper.pointDistancePlane(posX, entity.posX, posY, entity.posY) <= radius)
				continue;

			entity.setFire(2);
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
		summoner = var1.getString("summoner");
		mag = var1.getBoolean("mag");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
		var1.setString("summoner", summoner);
		var1.setBoolean("mag", mag);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		return false;
	}
}