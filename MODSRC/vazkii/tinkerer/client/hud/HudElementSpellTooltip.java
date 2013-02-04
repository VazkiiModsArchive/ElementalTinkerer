/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 3 Feb 2013
package vazkii.tinkerer.client.hud;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.reference.EffectReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * HudElementSpellTooltip
 *
 * The spell tooltip HUD, renders below the spell circle
 * when the client has recently changed the equipped
 * spell.
 *
 * @author Vazkii
 */
public class HudElementSpellTooltip implements IHudElement {

	public static HudElementSpellTooltip INSTANCE = new HudElementSpellTooltip();
	
	private HudElementSpellTooltip() { }
	
	private String currentTooltip;
	private int tooltipDisplayTicks;
	
	@Override
	public boolean shouldRender() {
		return MiscHelper.doesClientPlayerHaveWand();
	}

	@Override
	public void render(float partialTicks) {
		 if (tooltipDisplayTicks > 0 && !MathHelper.stringNullOrLengthZero(currentTooltip)) {
             Minecraft mc = MiscHelper.getMc();
             ScaledResolution var5 = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
             int var6 = var5.getScaledWidth();
             int var7 = var5.getScaledHeight();
             FontRenderer var8 = mc.fontRenderer;
             
             int tooltipStartX = (var6 - var8.getStringWidth(currentTooltip)) / 2;
             int tooltipStartY = var7 / 2 + EffectReference.SPELL_CIRCLE_RADIUS + 15;

             int opacity = (int)((float)tooltipDisplayTicks * 256.0F / 10.0F);

             if (opacity > 160)
                 opacity = 160;

             if (opacity > 0) {
                 GL11.glPushMatrix();
                 GL11.glEnable(GL11.GL_BLEND);
                 GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         		 int color = Color.getHSBColor((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_INFUSION), 0.6F, 1F).getRGB();
                 var8.drawStringWithShadow(currentTooltip, tooltipStartX, tooltipStartY, color + (opacity << 24));
                 GL11.glDisable(GL11.GL_BLEND);
                 GL11.glPopMatrix();
             }
         }
	}
	
	public void setTooltip(String tooltip) {
		if(!tooltip.equals(currentTooltip)) {
			currentTooltip = tooltip;
			tooltipDisplayTicks = 20;
		}
	}

	@Override
	public void clientTick() {
		if(tooltipDisplayTicks > 0)
			--tooltipDisplayTicks;
	}
}
