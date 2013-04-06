/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 6 Apr 2013
package vazkii.tinkerer.entity;

import java.awt.Color;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.reference.GameReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * EntityElementiumLocator
 *
 * The Elementium Ore Locator entity. It creates particles
 * that head with a vector towards the nearest generated
 * elementium ore node.
 *
 * @author Vazkii
 */
public class EntityElementiumLocator extends Entity {

	public EntityElementiumLocator(World par1World) {
		super(par1World);
	}

	@Override
	protected void entityInit() {
		dataWatcher.addObject(29, 0);
		dataWatcher.addObject(30, 0);
	}

	public void bindVector(Vec3 particleVector) {
		Vec3 entityVector = Vec3.createVectorHelper(posX, 0, posZ);
		Vec3 finalVector = entityVector.subtract(particleVector).normalize();

		setParticleX(finalVector.xCoord);
		setParticleZ(finalVector.zCoord);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();

		if(ticksExisted >= GameReference.ELEMENTIUM_LOCATOR_ENTITY_DURATION)
			setDead();

		Color rgbColor = new Color(Color.HSBtoRGB((float) Math.cos((double) ElementalTinkerer.proxy.getGameTicksElapsed() / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GUARDIAN), 0.9F, 0.7F));
		ElementalTinkerer.proxy.spawnColoredPortalParticle(rgbColor, worldObj, posX, posY, posZ, getParticleX(), 0, getParticleZ(), true);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		return false;
	}

	public double getParticleX() {
		return dataWatcher.getWatchableObjectInt(29) / 1000D;
	}

	public double getParticleZ() {
		return dataWatcher.getWatchableObjectInt(30) / 1000D;
	}

	public void setParticleX(double motion) {
		dataWatcher.updateObject(29, (int) (motion * 1000));
	}

	public void setParticleZ(double motion) {
		dataWatcher.updateObject(30, (int) (motion * 1000));
	}
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		setParticleX(nbttagcompound.getDouble("particleX"));
		setParticleZ(nbttagcompound.getDouble("particleZ"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setDouble("particleX", getParticleX());
		nbttagcompound.setDouble("particleZ", getParticleZ());
	}
}
