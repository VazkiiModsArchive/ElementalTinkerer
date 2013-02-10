 /**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 343):
 * <Pchan3> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return, Pchan3
 * ----------------------------------------------------------------------------
 * https://dl.dropbox.com/u/23495823/Airship/Files.7z
 *
 *
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ ?
package vazkii.tinkerer.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import vazkii.tinkerer.helper.MiscHelper;

/**
 * EntityFXSteam
 *
 * Steam looking particles, very subtle. Original EntitySteamFX class
 * from Pchan3's airship mod, thread available here (discontinued):
 * http://www.minecraftforum.net/topic/164940-open-source-abandoned-pchan3s-mods/
 *
 * @author Pchan3, Vazkii (code refractor)
 */
public class EntityFXSteam extends EntityFX {

    float originalParticleScale;

    public static void spawn(World world, double x, double y, double z, double xMotion, double yMotion, double zMotion) {
    	EntityFXSteam entity = new EntityFXSteam(world, x, y, z, xMotion, yMotion, zMotion);
		MiscHelper.getMc().effectRenderer.addEffect(entity);
    }

    public EntityFXSteam(World world, double d, double d1, double d2, double d3, double d4, double d5) {
        this(world, d, d1, d2, d3, d4, d5, 1.0F);
    }

    public EntityFXSteam(World world, double d, double d1, double d2, double d3, double d4, double d5, float f) {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        motionX = 0;
        motionY *= 0.10000000149011612D;
        motionZ = 0;
        motionX += d3;
        motionY += d4;
        motionZ += d5;
        particleRed = 125;
        particleGreen = 125;
        particleBlue = 125;
        particleScale *= 0.5F;
        particleScale *= f;
        originalParticleScale = particleScale;
        particleMaxAge = (int)(8D / (Math.random() * 0.80000000000000004D + 0.20000000000000001D));
        particleMaxAge *= f;
        noClip = true; // Originally false, but I want it as true - Vazkii
    }

    @Override
    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float f6 = (particleAge + f) / particleMaxAge * 32F;
        if(f6 < 0.0F)
            f6 = 0.0F;

        if(f6 > 1.0F)
            f6 = 1.0F;
        particleScale = originalParticleScale * f6;
        super.renderParticle(tessellator, f, f1, f2, f3, f4, f5);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }
}
