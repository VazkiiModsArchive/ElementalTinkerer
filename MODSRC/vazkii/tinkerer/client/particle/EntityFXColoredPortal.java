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

	public static void spawn(Color color, World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		EntityFXColoredPortal entity = new EntityFXColoredPortal(color, world, x, y, z, motionX, motionY, motionZ);
		MiscHelper.getMc().effectRenderer.addEffect(entity);
	}

	public EntityFXColoredPortal(Color color, World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);
		particleRed = color.getRed() / 255F; // Particle colors are 0F-1F, RGB colors are 0-255
		particleGreen = color.getGreen() / 255F;
		particleBlue =  color.getBlue() / 255F;
	}
}
