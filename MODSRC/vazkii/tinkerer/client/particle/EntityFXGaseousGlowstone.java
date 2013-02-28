/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Feb 2013
package vazkii.tinkerer.client.particle;

import net.minecraft.world.World;
import vazkii.tinkerer.helper.MiscHelper;

/**
 * EntityFXGaseousGlowstone
 *
 * Similar to the Steam Particle, but yellow.
 *
 * @author Vazkii
 */
public class EntityFXGaseousGlowstone extends EntityFXSteam {

	public static void spawn(World world, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
    	EntityFXGaseousGlowstone entity = new EntityFXGaseousGlowstone(world, x, y, z, xMotion, yMotion, zMotion);
		MiscHelper.getMc().effectRenderer.addEffect(entity);
	}

	public EntityFXGaseousGlowstone(World world, double d, double d1, double d2, double d3, double d4, double d5) {
		super(world, d, d1, d2, d3, d4, d5);
        particleRed = 255;
        particleGreen = 215;
        particleBlue = 0;
	}

}
