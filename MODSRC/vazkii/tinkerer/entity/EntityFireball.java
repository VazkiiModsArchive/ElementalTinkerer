/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 29 Jan 2013
package vazkii.tinkerer.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.helper.LightningHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.lightning.Vector3;
import vazkii.tinkerer.reference.EntityReference;

/**
 * EntityFireball
 *
 * Fireball Entity, cast by the fireball spell.
 *
 * @author Vazkii
 */
public class EntityFireball extends EntityThrowable {
	
	/** Is the spell magnified (casted with the right wand)? **/
	boolean mag = false;
	
	public EntityFireball(World world) {
		super(world);
	}
	
	public EntityFireball(World par1World, EntityLiving par2EntityLiving, boolean mag) {
		super(par1World, par2EntityLiving);
		this.mag = mag;
	}
	
    public EntityFireball(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }
    
	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setBoolean("mag", mag);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		mag = par1nbtTagCompound.getBoolean("mag");
	}
	
	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if(var1.entityHit != null && var1.entityHit instanceof EntityLiving && getThrower() instanceof EntityPlayer)
			if(MiscHelper.isServerPVP() || !(var1.entityHit instanceof EntityPlayer)) {
				var1.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) getThrower()), mag ? EntityReference.DMG_FIREBALL_MAG : EntityReference.DMG_FIREBALL);
				var1.entityHit.setFire(mag ? EntityReference.FIRE_FIREBALL_MAG : EntityReference.FIRE_FIREBALL);
			}
        if (!this.worldObj.isRemote)
            setDead();
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		motionX *= 1.1;
		motionY *= 1.1;
		motionZ *= 1.1;
		
		for(int i = 0; i < 3; i++)
			worldObj.spawnParticle("flame", posX, posY, posZ, (Math.random() - 0.5) / 10, (Math.random() - 0.5) / 10, (Math.random() - 0.5) / 10);
		worldObj.spawnParticle("smoke", posX, posY, posZ, 0, 0, 0);
	}
	
	@Override
    protected float getGravityVelocity() {
        return 0F;
    }

    public float getBrightness(float par1) {
        return 1.0F;
    }

    public int getBrightnessForRender(float par1) {
        return 15728880;
    }
}
