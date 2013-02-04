/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 30 Jan 2013
package vazkii.tinkerer.entity;

import java.awt.Color;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.reference.EntityReference;
import vazkii.tinkerer.reference.PotionReference;

/**
 * EntityFrostBolt
 *
 * Frostbolt entity, cast by the frostbolt spell.
 *
 * @author Vazkii
 */
public class EntityFrostBolt extends EntityThrowable {
	
	/** Is the spell magnified (casted with the right wand)? **/
	boolean mag = false;
	
	public EntityFrostBolt(World world) {
		super(world);
	}
	
	public EntityFrostBolt(World par1World, EntityLiving par2EntityLiving, boolean mag) {
		super(par1World, par2EntityLiving);
		this.mag = mag;
	}
	
    public EntityFrostBolt(World par1World, double par2, double par4, double par6) {
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
				var1.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) getThrower()), mag ? EntityReference.DMG_FROSTBOLT_MAG : EntityReference.DMG_FROSTBOLT);
				((EntityLiving)var1.entityHit).addPotionEffect(new PotionEffect(PotionReference.idFrozen, (mag ? EntityReference.FREEZE_FROSTBOLT_MAG : EntityReference.FREEZE_FROSTBOLT) * 20));
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
		
		for(int i = 0; i < 12; i++)
			ElementalTinkerer.proxy.spawnColoredPortalParticle(Color.getHSBColor(0.5F, 1F, 1F), worldObj, lastTickPosX, lastTickPosY, lastTickPosZ, motionX * 2 + Math.random(), motionY * 2 + Math.random(), motionZ * 2 + Math.random());
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
