/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 6 Apr 2013
package vazkii.tinkerer.gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.client.helper.RenderHelper;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.network.packet.PacketCompleteResearch;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.ResearchLibrary;
import vazkii.tinkerer.research.ResearchNode;

/**
 * GuiResearchGame
 *
 * The Research Game for the second stage of research.
 *
 * @author Vazkii
 */
public class GuiResearchGame extends GuiScreen {

	ResearchNode node;
	Icon icon,
	background;

	Random rand;

	int xStart,
	yStart;

	public byte stage = -1;

	public short[] asignedValues = new short[4],
				   choiceValues = new short[4];

	short looking;

	public GuiResearchGame(ResearchNode node, int element) {
		this.node = node;
		icon = node.icon;
		background = IconHelper.researchGameBackgroundIcons[element];

		resetGame();
	}

	@Override
	public void initGui() {
		super.initGui();
		xStart = (width - 144) / 2;
		yStart = (height - 144) / 2;
		xStart -= xStart % 4; // Dirty fix for the icon renders
		yStart -= yStart % 4;
		buttonList.clear();
		buttonList.add(new GuiInvisibleButton(0, xStart + 146, yStart, 12, 12));
		buttonList.add(new GuiInvisibleButton(1, xStart + 146, yStart + 14, 12, 12));
		buttonList.add(new GuiInvisibleButton(2, xStart + 146, yStart + 28, 12, 12));
		buttonList.add(new GuiInvisibleButton(3, xStart - 12, yStart, 12, 12));
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);

		if(looking != -1) {
			asignedValues[stage] = choiceValues[looking];
			advanceStage();
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		boolean matchDone = isMatchDone();
		fontRenderer.drawStringWithShadow("\u2718", xStart + 146, yStart, 0xFFFFFF);
		fontRenderer.drawStringWithShadow("?", xStart - 12, yStart, 0xFFFFFF);
		fontRenderer.drawStringWithShadow("\u27A5", xStart + 146, yStart + 14, 0xFFFFFF);
		if(matchDone)
			fontRenderer.drawStringWithShadow("\u2714", xStart + 146, yStart + 28, 0xFFFFFF);

		drawCenteredString(fontRenderer, "Organize your mind...", xStart + 72, yStart - 12, 0xFFFFFF);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(ResourcesReference.GUI_RESEARCH_GAME_TEXTURE);
		drawTexturedModalRect(xStart, yStart, 0, 0, 144, 144);
		GL11.glPushMatrix();
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		drawTexturedModalRect((xStart + 152) * 2, (yStart + 72) * 2 , 0, 0, 144, 144);
		GL11.glPopMatrix();

		drawPlayerHead();

		mc.renderEngine.bindTexture("/gui/items.png");
		GL11.glPushMatrix();
		GL11.glScalef(4F, 4F, 4F);
		if(!matchDone) {
			drawIcon(ResearchLibrary.allNodes.get(choiceValues[0]).icon, (xStart + 20) / 4, (yStart + 20) / 4, stage == 0 || stage == 2 ? 0 : 1, stage == 0 || stage == 1 ? 0 : 1);
			drawIcon(ResearchLibrary.allNodes.get(choiceValues[2]).icon, (xStart + 20) / 4, (yStart + 92) / 4, stage == 0 || stage == 2 ? 0 : 1, stage == 0 || stage == 1 ? 0 : 1);
			drawIcon(ResearchLibrary.allNodes.get(choiceValues[1]).icon, (xStart + 92) / 4, (yStart + 20) / 4, stage == 0 || stage == 2 ? 0 : 1, stage == 0 || stage == 1 ? 0 : 1);
			drawIcon(ResearchLibrary.allNodes.get(choiceValues[3]).icon, (xStart + 92) / 4, (yStart + 92) / 4, stage == 0 || stage == 2 ? 0 : 1, stage == 0 || stage == 1 ? 0 : 1);
		}

		for(int i = 0; i < stage; i++)
			drawIcon(ResearchLibrary.allNodes.get(asignedValues[i]).icon, (xStart + 156 + i % 2 * 32) / 4, (yStart + 76 + i / 2 * 32) / 4, i == 0 || i == 2 ? 0 : 1, i == 0 || i == 1 ? 0 : 1);
		GL11.glPopMatrix();

		if(!matchDone) {
			int mouseX = par1 - xStart;
			int mouseY = par2 - yStart;

			int xBoxPos = 0;
			int yBoxPos = 0;

			boolean foundLooking = false;

			if(mouseY >= 20 && mouseY <= 48) {
				yBoxPos = 20;

				if(mouseX >= 20 && mouseX <= 48) {
					xBoxPos = 20;
					looking = 0;
					foundLooking = true;
				}

				if(mouseX >= 92 && mouseX <= 120) {
					xBoxPos = 92;
					looking = 1;
					foundLooking = true;
				}
			}

			if(mouseY >= 92 && mouseY <= 120) {
				yBoxPos = 92;

				if(mouseX >= 20 && mouseX <= 48) {
					xBoxPos = 20;
					looking = 2;
					foundLooking = true;
				}

				if(mouseX >= 92 && mouseX <= 120) {
					xBoxPos = 92;
					looking = 3;
					foundLooking = true;
				}
			}

			if(!foundLooking)
				looking = -1;
			else {
				mc.renderEngine.bindTexture(ResourcesReference.GUI_RESEARCH_GAME_TEXTURE);
				drawTexturedModalRect(xStart + xBoxPos, yStart + yBoxPos, 144, 0, 32, 32);
			}
		} else looking = -1;

		if(((GuiInvisibleButton)buttonList.get(0)).isHovered())
			RenderHelper.renderTooltip(par1, par2, "Exit");
		else if(((GuiInvisibleButton)buttonList.get(1)).isHovered())
			RenderHelper.renderTooltip(par1, par2, "Restart");
		else if(matchDone && ((GuiInvisibleButton)buttonList.get(2)).isHovered())
			RenderHelper.renderTooltip(par1, par2, "Commit", FormattingCode.GRAY + "(Complete the Game)");
		else if(((GuiInvisibleButton)buttonList.get(3)).isHovered())
			RenderHelper.renderTooltip(par1, par2, "How To...", FormattingCode.GRAY + "Select the piece that fits what you", FormattingCode.GRAY + "are trying to research.", FormattingCode.GRAY + "Upon selecting the 4 matching pieces", FormattingCode.GRAY + "you complete the research.");

		super.drawScreen(par1, par2, par3);
	}

	public void drawPlayerHead() {
		int tex = mc.renderEngine.getTextureForDownloadableImage(mc.thePlayer.skinUrl, mc.thePlayer.getTexture());

		mc.renderEngine.bindTexture(mc.thePlayer.getTexture());

		if (tex >= 0) {
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex);
			mc.renderEngine.resetBoundTexture();
		}

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glScalef(2F, 2F, 2F);
		int x = (xStart + 62) / 2;
		int y = (yStart + 62) / 2;

		float xU = 1F / 64F;
		float yU = 1F / 32F;

		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + 8, zLevel, xU * 8, yU * 16);
		tessellator.addVertexWithUV(x + 8, y + 8, zLevel, xU * 16, yU * 16);
		tessellator.addVertexWithUV(x + 8, y + 0, zLevel, xU * 16, yU * 8);
		tessellator.addVertexWithUV(x + 0, y + 0, zLevel, xU * 8, yU * 8);
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 0, y + 8, zLevel, xU * 40, yU * 16);
		tessellator.addVertexWithUV(x + 8, y + 8, zLevel, xU * 48, yU * 16);
		tessellator.addVertexWithUV(x + 8, y + 0, zLevel, xU * 48, yU * 8);
		tessellator.addVertexWithUV(x + 0, y + 0, zLevel, xU * 40, yU * 8);
		tessellator.draw();

		GL11.glPopMatrix();

		fontRenderer.setUnicodeFlag(true);
		final int marks = 8;
		final int degChangePerMark = 360 / marks;
		final double startDeg = ElementalTinkerer.proxy.getGameTicksElapsed() * 4 % 360D;
		final int radius = (int) (20 + Math.cos(ElementalTinkerer.proxy.getGameTicksElapsed() / 5D) * 3);

		for(int i = 0; i < marks; i++) {
			Point point = MathHelper.getPointInCircle(new Point(xStart + 69, yStart + 67), (int) (startDeg + degChangePerMark * i), radius);
			fontRenderer.drawStringWithShadow("?", point.x, point.y, 0xFFFFFF);
		}


		fontRenderer.setUnicodeFlag(false);
	}

	public void drawIcon(Icon icon, int x, int y, int xBox, int yBox) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		float uOrigin = icon.getMinU();
		float uEnd = icon.getMaxU();

		float vOrigin = icon.getMinV();
		float vEnd = icon.getMaxV();

		float uDistance = uEnd - uOrigin;
		float vDistance = vEnd - vOrigin;

		float actualUStart = uOrigin + uDistance / 2 * xBox;
		float actualVStart = vOrigin + vDistance / 2 * yBox;

		float actualUEnd = uOrigin + uDistance / 2 * (xBox + 1);
		float actualVEnd = vOrigin + vDistance / 2 * (yBox + 1);

		tessellator.addVertexWithUV(x + 0, y + 8, zLevel, actualUStart, actualVEnd);
		tessellator.addVertexWithUV(x + 8, y + 8, zLevel, actualUEnd, actualVEnd);
		tessellator.addVertexWithUV(x + 8, y + 0, zLevel, actualUEnd, actualVStart);
		tessellator.addVertexWithUV(x + 0, y + 0, zLevel, actualUStart, actualVStart);
		tessellator.draw();
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		switch(par1GuiButton.id) {
		case 0 :
			MiscHelper.getMc().displayGuiScreen(new GuiElementalistLexiconIndex());
			break;
		case 1 :
			resetGame();
			break;
		case 2 :
			if(isMatchDone()) {
				if(isMatchCorrect())
					finalizeGame();
				else resetGame();
			}
		}

		super.actionPerformed(par1GuiButton);
	}

	// ========================= GAME START ========================= //

	public void resetGame() {
		rand = new Random(node.index);
		stage = -1;

		Arrays.fill(asignedValues, (short) -1);

		advanceStage();
	}

	public void advanceStage() {
		++stage;
		randomizeOptions();
	}

	public void randomizeOptions() {
		List<Short> availableValues = new ArrayList(ResearchLibrary.allNodes.keySet());
		if(availableValues.contains(node.index))
			availableValues.remove(availableValues.indexOf(node.index));

		short[] choiceValues = new short[4];

		choiceValues[0] = availableValues.get(rand.nextInt(availableValues.size()));
		availableValues.remove(availableValues.indexOf(choiceValues[0]));
		choiceValues[1] = availableValues.get(rand.nextInt(availableValues.size()));
		availableValues.remove(availableValues.indexOf(choiceValues[1]));
		choiceValues[2] = availableValues.get(rand.nextInt(availableValues.size()));
		availableValues.remove(availableValues.indexOf(choiceValues[2]));
		choiceValues[3] = node.index;

		List<Short> availableIndexes = new ArrayList();
		for(short i = 0; i < 4; i++)
			availableIndexes.add(i);

		for(int i = 0; i < 4; i++) {
			int index = rand.nextInt(availableIndexes.size());
			int arrayIndex = availableIndexes.get(index);
			availableIndexes.remove(index);

			this.choiceValues[i] = choiceValues[arrayIndex];
		}
	}

	public boolean isMatchDone() {
		return stage == 4;
	}

	public boolean isMatchCorrect() {
		return asignedValues[0] == asignedValues[1] && asignedValues[1] == asignedValues[2] && asignedValues[2] == asignedValues[3] && asignedValues[3] == node.index;
	}

	public void finalizeGame() {
		PacketCompleteResearch packet = new PacketCompleteResearch(node);
		PacketHelper.sendPacketToServer(packet);
		ResearchHelper.clientResearch.completeResearch(node.index, false, null);
		MiscHelper.getMc().displayGuiScreen(new GuiElementalistLexiconIndex());
	}

}
