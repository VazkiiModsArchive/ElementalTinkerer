/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.client.render;

import java.awt.Color;

import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.entity.monster.EntitySilverfish;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * RenderElementiumGuardian
 *
 * Render class for the Elementium Guardian mob. Extension of RenderSilverfish,
 * but colorizes the render as a hue gradient.
 *
 * @author Vazkii
 */
public class RenderElementiumGuardian extends RenderSilverfish {

    @Override
	public void renderSilverfish(EntitySilverfish par1EntitySilverfish, double par2, double par4, double par6, float par8, float par9) {
		Color rgbColor = new Color(Color.HSBtoRGB((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GUARDIAN), 0.8F, 1F));
        // Get a color based on hue, acquired trough a cosine function on the elapsed
		// ticks, in order to create an animated spectrum effect. It's kept as an
        // instance of Color to be used in a glColor3f call.

    	GL11.glPushMatrix();
        GL11.glColor3ub((byte) rgbColor.getRed(), (byte) rgbColor.getGreen(), (byte) rgbColor.getBlue());
    	super.renderSilverfish(par1EntitySilverfish, par2, par4, par6, par8, par9);
    	GL11.glPopMatrix();
    }

}
