/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 1 Feb 2013
package vazkii.tinkerer.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

/**
 * RenderBoulder
 *
 * Renders the Boulder Entity casted from the Boulder Spell.
 *
 * @author Vazkii
 */
public class RenderBoulder extends Render {

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		GL11.glPushMatrix();
        loadTexture("/terrain.png");
        GL11.glTranslatef((float)var2, (float)var4, (float)var6);
        renderBlocks.renderBlockAsItem(Block.cobblestone, 0, 1F);
        GL11.glPopMatrix();
	}
}
