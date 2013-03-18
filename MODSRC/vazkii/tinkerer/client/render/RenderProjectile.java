/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 29 Jan 2013
package vazkii.tinkerer.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * RenderProjectile
 *
 * Renders an instance of EntityThrowable.
 *
 * @author Vazkii
 */
public class RenderProjectile extends Render {

	ItemStack stack;
	float scale;

	public RenderProjectile(ItemStack stack, float scale) {
		this.stack = stack;
		this.scale = scale;
	}

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
		GL11.glPushMatrix();
        GL11.glTranslatef((float)var2, (float)var4, (float)var6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(scale, scale, scale);
        Icon icon = stack.getItem().getIconIndex(stack);
        loadTexture("/gui/items.png");
        Tessellator var12 = Tessellator.instance;
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        float var17 = 1.0F;
        float var18 = 0.5F;
        float var19 = 0.25F;
        GL11.glRotatef(180.0F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        var12.startDrawingQuads();
        var12.setNormal(0.0F, 1.0F, 0.0F);
        var12.addVertexWithUV(0.0F - var18, 0.0F - var19, 0.0D, f, f3);
        var12.addVertexWithUV(var17 - var18, 0.0F - var19, 0.0D, f1, f3);
        var12.addVertexWithUV(var17 - var18, 1.0F - var19, 0.0D, f1, f2);
        var12.addVertexWithUV(0.0F - var18, 1.0F - var19, 0.0D, f, f2);
        var12.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
	}



}
