/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 1 Feb 2013
package vazkii.tinkerer.entity;

import java.util.List;

import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.reference.EntityReference;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * EntityBoulder
 *
 * The Boulder entity from the Boulder Toss spell.
 *
 * @author Vazkii
 */
public class EntityBoulder extends EntityThrowable {

	/** Is the spell magnified (casted with the right wand)? **/
	boolean mag = false;
	
	public EntityBoulder(World world) {
		super(world);
	}
	
	public EntityBoulder(World par1World, EntityLiving par2EntityLiving, boolean mag) {
		super(par1World, par2EntityLiving);
		this.mag = mag;
	}
	
    public EntityBoulder(World par1World, double par2, double par4, double par6) {
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
		AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(posX - 3, posY - 3, posZ - 3, posX + 3, posY + 3, posZ + 3);
		List<EntityLiving> entities = worldObj.getEntitiesWithinAABB(EntityLiving.class, boundingBox);
		for(EntityLiving entity : entities)
			if(entity != null && entity instanceof EntityLiving)
				if((MiscHelper.isServerPVP() || !(entity instanceof EntityPlayer)) && entity != getThrower() && getThrower() instanceof EntityPlayer)
					entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) getThrower()), mag ? EntityReference.DMG_BOULDER_MAG : EntityReference.DMG_BOULDER);
		
		if(!worldObj.isRemote)
			setDead();
	}
	
	@Override
    protected float getGravityVelocity() {
        return 0.06F;
    }
}
