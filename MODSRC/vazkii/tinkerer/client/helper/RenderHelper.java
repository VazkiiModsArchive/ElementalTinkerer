/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Jan 2013
package vazkii.tinkerer.client.helper;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureStitched;
import net.minecraft.util.Icon;
import net.minecraft.util.StringUtils;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ObjectPair;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.magic.PlayerSpellData;
import vazkii.tinkerer.magic.Spell;
import vazkii.tinkerer.magic.SpellLibrary;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.GuiReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.ResearchNode;

/**
 * RenderHelper
 *
 * Helper for Renders. To render things on screen that are rendered
 * various times.
 *
 * @author Vazkii
 */
public final class RenderHelper {

	private static final RenderItem renderItem = new RenderItem();

	/** Renders a 16x16 icon at the given positions from the
	 * given spritesheet **/
	public static void renderIcon(TextureStitched icon, double x, double y, double z) {
		Minecraft mc = MiscHelper.getMc();
    	RenderEngine engine = mc.renderEngine;
		engine.bindTexture("/gui/items.png");
		// It's binding "/gui/items.png" as that refers to a texture sheet.
		// I use a hack to store my icons in there
		renderItem.renderIcon((int) x, (int) y, icon, 16, 16);
	}

	/** Renders a research icon at the given positions, if checkStatus is true,
	 * checks the research's status, and renders the default icon if it's not
	 * researched **/
	public static void renderResearchIcon(ResearchNode node, boolean checkStatus, double x, double y, double z) {
		if(node == null)
    		return;

		Icon icon = !checkStatus || ResearchHelper.clientResearch.isResearchCompleted(node.index) ? node.icon : ResearchHelper.clientResearch.isResearchDone(node.index) ? IconHelper.researchPendingIcon : IconHelper.researchUnknownIcon;
    	renderIcon((TextureStitched) icon, x, y, z);
	}

	public static void renderSpellIcon(Spell spell, double x, double y, double z) {
		if(spell == null)
			return;

		Icon icon = spell.icon;
    	renderIcon((TextureStitched) icon, x, y, z);
	}

	public static void renderSpellFrame(double x, double y, double z) {
		Minecraft mc = MiscHelper.getMc();
		RenderEngine render = mc.renderEngine;
		Icon icon = IconHelper.spellFrameIcon;
		render.bindTexture("/gui/items.png");
		// It's binding "/gui/items.png" as that refers to a texture sheet.
		// I use a hack to store my icons in there
		renderItem.renderIcon((int) x, (int) y, icon, 18, 18);
	}

	public static void renderCooldown(double x, double y, double z, int cooldown) {
		if(cooldown > 0) {
			GL11.glPushMatrix();
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			String time = FormattingCode.RED + "" + FormattingCode.BOLD + StringUtils.ticksToElapsedTime(cooldown);
			FontRenderer fr = MiscHelper.getMc().fontRenderer;
			int timeStrWidth = fr.getStringWidth(time);
			fr.drawStringWithShadow(time, (int) ((x + 6) * 2 - timeStrWidth), (int) ((y + 4) * 2), 0xFFFFFF);
			GL11.glPopMatrix();
			GL11.glColor3f(1F, 1F, 1F);
		}
	}

	public static void renderSpellIconWithBackgroundAndFrame(Spell spell, double x, double y, double z) {
		renderSpellFrame(x - 1, y - 1, z);
		renderIcon((TextureStitched) IconHelper.spellBackgroundIcon, x, y, z);
		renderSpellIcon(spell, x, y, z);
	}

	public static void renderTooltip(int x, int y, String... tooltipData) {
		renderTooltip(x, y, GuiReference.TOOLTIP_DEFAULT_COLOR, GuiReference.TOOLTIP_DEFAULT_COLOR_BG, tooltipData);
	}

	public static void renderTooltip(int x, int y, int color, int color2, String... tooltipData) {
		renderTooltip(x, y, color, color2, Arrays.asList(tooltipData));
	}

	public static void renderTooltip(int x, int y, List<String> tooltipData) {
		renderTooltip(x, y, GuiReference.TOOLTIP_DEFAULT_COLOR, GuiReference.TOOLTIP_DEFAULT_COLOR_BG, tooltipData);
	}

	/** Renders tooltip at the given position, with the given color **/
	public static void renderTooltip(int x, int y, int color, int color2, List<String> tooltipData) {
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
            drawGradientRect(var6 - 3, var7 - 4, z, var6 + var5 + 3, var7 - 3, color2, color2);
            drawGradientRect(var6 - 3, var7 + var9 + 3, z, var6 + var5 + 3, var7 + var9 + 4, color2, color2);
            drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 + var9 + 3, color2, color2);
            drawGradientRect(var6 - 4, var7 - 3, z, var6 - 3, var7 + var9 + 3, color2, color2);
            drawGradientRect(var6 + var5 + 3, var7 - 3, z, var6 + var5 + 4, var7 + var9 + 3, color2, color2);
            int var12 = (color & 0xFFFFFF) >> 1 | color & -16777216;
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

	/** Renders a block in the inventory, must be translated previosuly **/
	public static void renderInventoryBlock(Block block, int metadata, RenderBlocks renderer) {
		Tessellator var4 = Tessellator.instance;

		if (renderer.useInventoryTint) {
			final int renderColor = block.getRenderColor(metadata);
			final float red = (renderColor >> 16 & 255) / 255.0F;
			final float green = (renderColor >> 8 & 255) / 255.0F;
			final float blue = (renderColor & 255) / 255.0F;
			GL11.glColor4f(red, green, blue, 1.0F);
		}

		block.setBlockBoundsForItemRender();
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		var4.startDrawingQuads();
		var4.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(0, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(1, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(2, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(3, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(4, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(5, metadata));
		var4.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	/** Renders a star, similar to the dragon death animation, must be
	 * translated previously **/
	public static void renderStar(int color, float xScale, float yScale, float zScale) {
		Tessellator tessellator = Tessellator.instance;
		net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();

		int ticks = (int) (ClientTickHandler.elapsedClientTicks % 200);
		if (ticks >= 100)
			ticks = 200 - ticks - 1;

		float f1 = ticks / 200F;
		float f2 = 0.0F;
		if (f1 > 0.7F)
			f2 = (f1 - 0.7F) / 0.2F;
		Random random = new Random(432L);

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(770, 1);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDepthMask(false);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, -1F, -2F);
		GL11.glScalef(xScale, yScale, zScale);
		for (int i = 0; i < (f1 + f1 * f1) / 2F * 90F + 30F; i++) {
			GL11.glRotatef(random.nextFloat() * 360F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(random.nextFloat() * 360F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(random.nextFloat() * 360F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(random.nextFloat() * 360F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(random.nextFloat() * 360F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(random.nextFloat() * 360F + f1 * 90F, 0.0F, 0.0F, 1.0F);
			tessellator.startDrawing(6);
			float f3 = random.nextFloat() * 20F + 5F + f2 * 10F;
			float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
			tessellator.setColorRGBA_I(color, (int) (255F * (1.0F - f2)));
			tessellator.addVertex(0.0D, 0.0D, 0.0D);
			tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0);
			tessellator.addVertex(-0.86599999999999999D * f4, f3, -0.5F * f4);
			tessellator.addVertex(0.86599999999999999D * f4, f3, -0.5F * f4);
			tessellator.addVertex(0.0D, f3, 1.0F * f4);
			tessellator.addVertex(-0.86599999999999999D * f4, f3, -0.5F * f4);
			tessellator.draw();
		}

		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}

	public static List<ObjectPair<Point, Short>> drawSpellCircle(short[] spells, int xCenter, int yCenter, int z, int radius, boolean star, PlayerSpellData spellData, boolean passives) {
		if(SpellHelper.clientSpells == null || spells.length == 0)
			return new ArrayList(); // No spells, return empty

		int color = Color.getHSBColor((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_INFUSION), 0.6F, 1F).getRGB();
		Point origin = new Point(xCenter, yCenter);
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		int degPerPoint = (int) Math.round(360D / spells.length);
		int currentDeg = -90;
		Point[] points = new Point[spells.length];
		for(int i = 0; i < spells.length; i++) {
			Point point = MathHelper.getPointInCircle(origin, currentDeg, radius);
			points[i] = point;
			currentDeg += degPerPoint;
		}
		startDrawingLines();
		drawCircumference(origin, z, color, radius);
		for(int i = 0; i < points.length; i++)
			for(int j = 0; j < points.length; j++)
				if(j == i)
					continue;
				else drawSimpleLine(points[i], points[j], z, color);
		endDrawingLines();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		for(int i = 0; i < spells.length; i++) {
			int x = points[i].x;
			int y = points[i].y;
			short spellIndex = spells[i];
			Spell spell = passives ? SpellLibrary.allPassives.get(spellIndex) : SpellLibrary.allSpells.get(spellIndex);
			if(i == SpellHelper.clientSpells.getSpellSelected() && star) {
				GL11.glPushMatrix();
				GL11.glTranslatef(x, y, z);
				renderStar(color, 1.7F, 1.7F, 1.7F);
				GL11.glPopMatrix();
			}
			renderSpellIconWithBackgroundAndFrame(spell, x - 8, y - 8, z);
			if(!passives && spellData != null && spellData.spellCooldowns.containsKey(spellIndex)) {
				int cooldown = spellData.spellCooldowns.get(spellIndex);
				renderCooldown(x, y, z, cooldown);
			}
		}

		GL11.glPopMatrix();

		List<ObjectPair<Point, Short>> pairs = new ArrayList();
		for(int i = 0; i < Math.min(points.length, spells.length); i++) {
			ObjectPair<Point, Short> pair = new ObjectPair(points[i], spells[i]);
			pairs.add(pair);
		}
		return pairs;
	}

	public static List<ObjectPair<Point, Short>> drawSpellCircle(short[] spells, int xCenter, int yCenter, int z, int radius, boolean passives) {
		return drawSpellCircle(spells, xCenter, yCenter, z, radius, true, passives);
	}

	public static List<ObjectPair<Point, Short>> drawSpellCircle(short[] spells, int xCenter, int yCenter, int z, int radius, PlayerSpellData spellData, boolean passives) {
		return drawSpellCircle(spells, xCenter, yCenter, z, radius, true, spellData, passives);
	}

	public static List<ObjectPair<Point, Short>> drawSpellCircle(short[] spells, int xCenter, int yCenter, int z, int radius, boolean star, boolean passives) {
		return drawSpellCircle(spells, xCenter, yCenter, z, radius, star, null, passives);
	}

	/** Sets the GL values to draw lines **/
	public static void startDrawingLines() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glLineWidth(3F);
	}

	/** Draws a circumference **/
	public static void drawCircumference(Point origin, int z, int color, int radius) {
		GL11.glBegin(GL11.GL_LINE_STRIP);
		Color colorRGB = new Color(color);
		GL11.glColor4ub((byte) colorRGB.getRed(), (byte) colorRGB.getGreen(), (byte) colorRGB.getBlue(), (byte) (0.2 * 255));
		for(int i = 0; i < 360; i++) {
			Point point = MathHelper.getPointInCircle(origin, i, radius);
			GL11.glVertex2i(point.x, point.y);
		}
		GL11.glEnd();
	}

	/** Draws a line in the plane from point A to point B **/
	public static void drawSimpleLine(Point pointA, Point pointB, int z, int color) {
		GL11.glBegin(GL11.GL_LINES);
		Color colorRGB = new Color(color);
		GL11.glColor4ub((byte) colorRGB.getRed(), (byte) colorRGB.getGreen(), (byte) colorRGB.getBlue(), (byte) (0.2 * 255));
		GL11.glVertex2i(pointA.x, pointA.y);
		GL11.glVertex2i(pointB.x, pointB.y);
		GL11.glEnd();
	}

	/** Reverts the GL values for drawing lines **/
	public static void endDrawingLines() {
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(1F, 1F, 1F, 1F);
	}

	// Copy from Gui.drawTexturedModalRect.
    public static void drawTexturedModalRect(double par1, double par2, double z, double par3, double par4, double par5, double par6) {
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
