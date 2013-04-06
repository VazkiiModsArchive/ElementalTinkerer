/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.client.particle;

import java.awt.Color;

import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.world.World;
import vazkii.tinkerer.helper.MiscHelper;

/**
 * EntityFXColoredPortal
 *
 * Similar to the portal FX, but colorizable.
 *
 * @author Vazkii
 */
public class EntityFXColoredPortal extends EntityPortalFX {

	double originalMotionX, originalMotionY, originalMotionZ;
	boolean staticMotion;

	public static void spawn(Color color, World world, double x, double y, double z, double motionX, double motionY, double motionZ, boolean staticMotion) {
		EntityFXColoredPortal entity = new EntityFXColoredPortal(color, world, x, y, z, motionX, motionY, motionZ, staticMotion);
		MiscHelper.getMc().effectRenderer.addEffect(entity);
	}

	public EntityFXColoredPortal(Color color, World par1World, double par2, double par4, double par6, double par8, double par10, double par12, boolean staticMotion) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		particleRed = color.getRed() / 255F; // Particle colors are 0F-1F, RGB colors are 0-255
		particleGreen = color.getGreen() / 255F;
		particleBlue =  color.getBlue() / 255F;

		originalMotionX = par8;
		originalMotionY = par10;
		originalMotionZ = par12;
		this.staticMotion = staticMotion;
	}

	@Override
	public void onUpdate() {
		if(!staticMotion) {
			super.onUpdate();
		} else {
			prevPosX = posX;
			prevPosY = posY;
			prevPosZ = posZ;

			posX += originalMotionX / 5;
			posY += originalMotionY / 5;
			posZ += originalMotionZ / 5;

	        if (particleAge++ >= particleMaxAge)
	        	setDead();
		}
	}
}
