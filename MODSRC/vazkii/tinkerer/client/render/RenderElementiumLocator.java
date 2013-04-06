/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 6 Apr 2013
package vazkii.tinkerer.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.helper.RenderHelper;

/**
 * RenderElementiumLocator
 *
 * Render for the Elementium Locator entity.
 *
 * @author Vazkii
 */
public class RenderElementiumLocator extends Render {

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslated(d0, d1 + 1, d2 + 2);
		RenderHelper.renderStar(0xFF00FF, 0.025F, 0.025F, 0.025F);
		GL11.glPopMatrix();
	}

}
