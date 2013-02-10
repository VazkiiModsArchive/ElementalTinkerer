/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 5 Feb 2013
package vazkii.tinkerer.client.hud;

import java.awt.Color;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.potion.Potion;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.hud.vignette.Vignette;
import vazkii.tinkerer.client.hud.vignette.VignetteLowHealth;
import vazkii.tinkerer.client.hud.vignette.VignettePotion;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.potion.ElementalTinkererPotions;

/**
 * HudElementViginette
 *
 * The HUD element for the various vignettes.
 *
 * @author Vazkii
 */
public class HudElementVignette implements IHudElement {

	public static Queue<Vignette> vignetteQueue = new ConcurrentLinkedQueue<Vignette>();
	Vignette currentRendering = null;

	public static HudElementVignette INSTANCE = new HudElementVignette();

	private HudElementVignette() { }

	public static void registerViginettes() {
		if(ConfigurationHandler.vignetteLowHealth)
			vignetteQueue.add(new VignetteLowHealth());
		if(ConfigurationHandler.vignettePoison)
			vignetteQueue.add(new VignettePotion(Potion.poison.id));
		if(ConfigurationHandler.vignetteFrozen)
			vignetteQueue.add(new VignettePotion(ElementalTinkererPotions.frozen.id));
	}

	@Override
	public boolean shouldRender() {
		checkVignettes : {
			if(MiscHelper.getClientPlayer() == null || !Minecraft.isFancyGraphicsEnabled())
				break checkVignettes;

			for(Vignette viginette : vignetteQueue) {
				currentRendering = viginette;
				if(currentRendering != null && currentRendering.shouldRender())
					return true;
			}
		}

		// No vignette wants to render, nullify the field
		currentRendering = null;
		return false;
	}

	@Override
	public void render(float partialTicks) {
		if(currentRendering == null || MiscHelper.getClientPlayer() == null)
			return;

		renderVignette(currentRendering.getColor(partialTicks));
	}

	// Method from GuiIngame
    private void renderVignette(int color) {
    	Minecraft mc = MiscHelper.getMc();
        ScaledResolution var5 = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int par2 = var5.getScaledWidth();
        int par3 = var5.getScaledHeight();

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
        Color colorRGB = new Color(color);
        GL11.glColor4f(colorRGB.getRed(), colorRGB.getGreen(), colorRGB.getBlue(), 1.0F);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
        Tessellator var4 = Tessellator.instance;
        var4.startDrawingQuads();
        var4.addVertexWithUV(0.0D, par3, -90.0D, 0.0D, 1.0D);
        var4.addVertexWithUV(par2, par3, -90.0D, 1.0D, 1.0D);
        var4.addVertexWithUV(par2, 0.0D, -90.0D, 1.0D, 0.0D);
        var4.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        var4.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glPopMatrix();
    }

	@Override
	public void clientTick() {
		// NO-OP
	}
}
