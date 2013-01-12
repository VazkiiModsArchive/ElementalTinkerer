/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Jan 2013
package vazkii.tinkerer.client.helper;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.GuiReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.ResearchNode;

/**
 * RenderHelper
 *
 * Helper for Renders. To render things on screen that are rendered
 * various wells
 *
 * @author Vazkii
 */
public final class RenderHelper {

	/** Renders a research icon at the given positions, if checkStatus is true,
	 * checks the research's status, and renders the default icon if it's not
	 * researched **/
	public static void renderResearchIcon(ResearchNode node, boolean checkStatus, double x, double y, double z) {
		Minecraft mc = MiscHelper.getMc();
    	RenderEngine engine = mc.renderEngine;
    	int textureID = engine.getTexture(!checkStatus || ResearchHelper.clientResearch.isResearchCompleted(node.index) ? node.spritesheet : ResourcesReference.RESEARCH_SPRITESHEET);
    	engine.bindTexture(textureID);
    	int index = !checkStatus || ResearchHelper.clientResearch.isResearchDone(node.index) ? ResearchHelper.clientResearch.isResearchCompleted(node.index) ? node.spriteIndex : ResourcesReference.RESEARCH_INDEX_ELLIPSES : ResourcesReference.RESEARCH_INDEX_QUESTIONMARK;
    	int xSpritesheetStart = index % 16 * 16;
    	int ySpritesheetStart = index / 16 * 16;
    	drawTexturedModalRect(x, y, z, xSpritesheetStart, ySpritesheetStart, 16, 16);
	}

	public static void renderTooltip(int x, int y, String... tooltipData) {
		renderTooltip(x, y, GuiReference.TOOLTIP_DEFAULT_COLOR, tooltipData);
	}

	public static void renderTooltip(int x, int y, int color, String... tooltipData) {
		renderTooltip(x, y, color, Arrays.asList(tooltipData));
	}

	public static void renderTooltip(int x, int y, List<String> tooltipData) {
		renderTooltip(x, y, GuiReference.TOOLTIP_DEFAULT_COLOR, tooltipData);
	}

	/** Renders tooltip at the given position, with the given color **/
	public static void renderTooltip(int x, int y, int color,  List<String> tooltipData) {
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        if (!tooltipData.isEmpty()) {
            int var5 = 0;
            int var6;
            int var7;
            FontRenderer fontRenderer = MiscHelper.getMc().fontRenderer;
            for (var6 = 0; var6 < tooltipData.size(); ++var6) {
                var7 = fontRenderer.getStringWidth(tooltipData.get(var6));
                if (var7 > var5)
                    var5 = var7;
            }
            var6 = x + 12;
            var7 = y - 12;
            int var9 = 8;
            if (tooltipData.size() > 1)
                var9 += 2 + (tooltipData.size() - 1) * 10;
            float z = 300.0F;
            int var10 = -267386864;
            drawGradientRect(var6 - 3, var7 - 4, z, var6 + var5 + 3, var7 - 3, var10, var10);
            drawGradientRect(var6 - 3, var7 + var9 + 3, z, var6 + var5 + 3, var7 + var9 + 4, var10, var10);
            drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 + var9 + 3, var10, var10);
            drawGradientRect(var6 - 4, var7 - 3, z, var6 - 3, var7 + var9 + 3, var10, var10);
            drawGradientRect(var6 + var5 + 3, var7 - 3, z, var6 + var5 + 4, var7 + var9 + 3, var10, var10);
            int var12 = (color & 0xFFFFFF) >> 1 | color & 0;
            drawGradientRect(var6 - 3, var7 - 3 + 1, z, var6 - 3 + 1, var7 + var9 + 3 - 1, color, var12);
            drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, z, var6 + var5 + 3, var7 + var9 + 3 - 1, color, var12);
            drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 - 3 + 1, color, color);
            drawGradientRect(var6 - 3, var7 + var9 + 2, z, var6 + var5 + 3, var7 + var9 + 3, var12, var12);
            for (int var13 = 0; var13 < tooltipData.size(); ++var13) {
            	String var14 = tooltipData.get(var13);
                fontRenderer.drawStringWithShadow(var14, var6, var7, -1);
                if (var13 == 0)
                    var7 += 2;
                var7 += 10;
            }
        }
	}

	// Copy from Gui.drawTexturedModalRect.
    public static void drawTexturedModalRect(double par1, double par2, double z, int par3, int par4, int par5, int par6)
    {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV(par1 + 0, par2 + par6, z, (par3 + 0) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + par6, z, (par3 + par5) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + 0, z, (par3 + par5) * var7, (par4 + 0) * var8);
        var9.addVertexWithUV(par1 + 0, par2 + 0, z, (par3 + 0) * var7, (par4 + 0) * var8);
        var9.draw();
    }

    // Copy from Gui.drawGradientRect.
    public static void drawGradientRect(int par1, int par2, float z, int par3, int par4, int par5, int par6) {
        float var7 = (par5 >> 24 & 255) / 255.0F;
        float var8 = (par5 >> 16 & 255) / 255.0F;
        float var9 = (par5 >> 8 & 255) / 255.0F;
        float var10 = (par5 & 255) / 255.0F;
        float var11 = (par6 >> 24 & 255) / 255.0F;
        float var12 = (par6 >> 16 & 255) / 255.0F;
        float var13 = (par6 >> 8 & 255) / 255.0F;
        float var14 = (par6 & 255) / 255.0F;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        Tessellator var15 = Tessellator.instance;
        var15.startDrawingQuads();
        var15.setColorRGBA_F(var8, var9, var10, var7);
        var15.addVertex(par3, par2, z);
        var15.addVertex(par1, par2, z);
        var15.setColorRGBA_F(var12, var13, var14, var11);
        var15.addVertex(par1, par4, z);
        var15.addVertex(par3, par4, z);
        var15.draw();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

}
