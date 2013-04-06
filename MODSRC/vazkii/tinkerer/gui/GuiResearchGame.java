/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Jan 2013
package vazkii.tinkerer.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.client.helper.RenderHelper;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.network.packet.PacketCompleteResearch;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResourcesReference;
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
	Icon icon;

	boolean usedHotSwap = false,
			hotSwapActive = ConfigurationHandler.researchEasyMode;
	int hotSwapX = -1,
		hotSwapY = -1;

	Icon background;

    int xStart,
    	yStart;

    int xLooking,
    	yLooking;

    int moves = 0;

	int[] squares = new int[16];

	public GuiResearchGame(ResearchNode node, int element) {
		this.node = node;
		icon = node.icon;
		background = IconHelper.researchGameBackgroundIcons[element];
		randomlyAsignSquares();
	}

	@Override
	public void initGui() {
		super.initGui();
		xStart = (width - 144) / 2;
		yStart = (height - 144) / 2;
		xStart -= xStart % 8; // Dirty fix for the icon renders
		yStart -= yStart % 8;
		buttonList.clear();
		buttonList.add(new GuiInvisibleButton(0, xStart + 146, yStart, 12, 12));
		buttonList.add(new GuiInvisibleButton(1, xStart + 146, yStart + 14, 12, 12));
		if(!ConfigurationHandler.researchEasyMode)
			buttonList.add(new GuiInvisibleButton(2, xStart + 146, yStart + 134, 12, 12));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		switch(par1GuiButton.id) {
		case 0 :
			MiscHelper.getMc().displayGuiScreen(new GuiElementalistLexiconIndex());
			break;
		case 1 :
			randomlyAsignSquares();
			moves = 0;
			usedHotSwap = false;
			hotSwapX = hotSwapY = -1;
			break;
		case 2 :
			if(usedHotSwap)
				break;
			hotSwapActive = !hotSwapActive;
			if(!hotSwapActive)
				hotSwapX = hotSwapY = -1;
			break;
		}

		super.actionPerformed(par1GuiButton);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		if(verify())
			return;

		fontRenderer.drawStringWithShadow("Moves: " + moves, xStart, yStart - 12, 0xFFFFFF);
		fontRenderer.drawStringWithShadow("\u2718", xStart + 146, yStart, 0xFFFFFF);
		fontRenderer.drawStringWithShadow("\u27A5", xStart + 146, yStart + 14, 0xFFFFFF);
		fontRenderer.drawStringWithShadow((usedHotSwap ? FormattingCode.RED : hotSwapActive ? FormattingCode.GOLD : "") + "\u272B", xStart + 146, yStart + 134, 0xFFFFFF);

		if(((GuiInvisibleButton)buttonList.get(0)).isHovered())
			RenderHelper.renderTooltip(par1, par2, "Exit");
		else if(((GuiInvisibleButton)buttonList.get(1)).isHovered())
			RenderHelper.renderTooltip(par1, par2, "Re-shuffle");
		else if(!ConfigurationHandler.researchEasyMode && ((GuiInvisibleButton)buttonList.get(2)).isHovered())
			RenderHelper.renderTooltip(par1, par2, "Hot Swap", FormattingCode.GRAY + "Swaps two tiles", FormattingCode.GRAY + "Can only be used once per shuffle.");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(ResourcesReference.GUI_RESEARCH_GAME_TEXTURE);
        drawTexturedModalRect(xStart, yStart, 0, 0, 144, 144);

        zLevel += 1;
        for(int i = 0; i < 4; i++)
        	for(int j = 0; j < 4; j++)
        		drawChunk(i, j);
        mc.renderEngine.bindTexture(ResourcesReference.GUI_RESEARCH_GAME_TEXTURE);

        zLevel += 1;
        int gridX = par1-xStart-8;
        int gridY = par2-yStart-8;
        int gridLocX = -1;
        int gridLocY = -1;
        if(gridX >= 0 && gridY >= 0) {
        	gridLocX = gridX / 32;
        	gridLocY = gridY / 32;
        	if(gridLocX < 4 && gridLocY < 4) {
        		drawTexturedModalRect(gridLocX * 32 + xStart + 8, gridLocY * 32 + yStart + 8, 144, 0, 32, 32);
        	}
        }
        if(hotSwapX >= 0 && hotSwapY >= 0 && hotSwapActive) {
        	GL11.glPushMatrix();
        	GL11.glColor3f(1.0F, 0.3F, 0.3F);
    		drawTexturedModalRect(hotSwapX * 32 + xStart + 8, hotSwapY * 32 + yStart + 8, 144, 0, 32, 32);
    		GL11.glPopMatrix();
        }
        xLooking = gridLocX;
        yLooking = gridLocY;
        zLevel -= 2;

		super.drawScreen(par1, par2, par3);
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);

		if(par1 >= xStart + 8 && par1 <= xStart + 136 && par2 >= yStart + 8 && par2 <= yStart + 136) {
			if(hotSwapX >= 0 && hotSwapY >= 0 && hotSwapActive && (hotSwapX != xLooking || hotSwapY != yLooking)) {
				int swapTile = hotSwapY * 4 + hotSwapX;
				int lookingTile = yLooking * 4 + xLooking;
				int looking = squares[lookingTile];
				int swap = squares[swapTile];
				squares[swapTile] = looking;
				squares[lookingTile] = swap;
				hotSwapX = hotSwapY = -1;
				hotSwapActive = ConfigurationHandler.researchEasyMode;
				usedHotSwap = !ConfigurationHandler.researchEasyMode;
				return;
			} else if(hotSwapActive && !usedHotSwap || ConfigurationHandler.researchEasyMode) {
				hotSwapX = xLooking;
				hotSwapY = yLooking;
				return;
			}

			move(xLooking, yLooking);
		}
	}

	public void drawChunk(int x, int y) {
		int square = squares[y * 4 + x] - 1;

		if(square == -1)
			return;

		int xPos = xStart + 8 + x * 32;
		int yPos = yStart + 8 + y * 32;

		int ySquare = square / 4;
		int xSquare = square % 4;

        GL11.glPushMatrix();
        GL11.glScalef(8F, 8F, 8F);
        mc.renderEngine.bindTexture("/gui/items.png");
        drawIcon(background, xPos / 8, yPos / 8, xSquare, ySquare);
        drawIcon(icon, xPos / 8, yPos / 8, xSquare, ySquare);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        fontRenderer.drawStringWithShadow("" + (square + 1), xPos * 2, yPos * 2, 0xFFFFFF);
        GL11.glPopMatrix();
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

        float actualUStart = uOrigin + uDistance / 4 * xBox;
        float actualVStart = vOrigin + vDistance / 4 * yBox;

        float actualUEnd = uOrigin + uDistance / 4 * (xBox + 1);
        float actualVEnd = vOrigin + vDistance / 4 * (yBox + 1);

        tessellator.addVertexWithUV(x + 0, y + 4, zLevel, actualUStart, actualVEnd);
        tessellator.addVertexWithUV(x + 4, y + 4, zLevel, actualUEnd, actualVEnd);
        tessellator.addVertexWithUV(x + 4, y + 0, zLevel, actualUEnd, actualVStart);
        tessellator.addVertexWithUV(x + 0, y + 0, zLevel, actualUStart, actualVStart);
        tessellator.draw();
	}

	public void move(int x, int y) {
		int emptySquare = -1;
		int square = y * 4 + x;

		if(!(square >= 0 && square < 16))
			return;

		int up = up(square);
		if(up != -1 && squares[up] == 0)
			emptySquare = up;

		int down = down(square);
		if(down != -1 && squares[down] == 0)
			emptySquare = down;

		int right = right(square);
		if(right != -1 && squares[right] == 0)
			emptySquare = right;

		int left = left(square);
		if(left != -1 && squares[left] == 0)
			emptySquare = left;

		if(emptySquare >= 0 && emptySquare < 16 && square >= 0 && square < 16) {
			++moves;
			squares[emptySquare] = squares[square];
			squares[square] = 0;
		}
	}

	/** Gets the index of the square to the top of the one passed in,
	 * -1 if there is none. **/
	public int up(int i) {
		if(i <= 3)
			return -1;

		return i - 4;
	}

	/** Gets the index of the square to the bottom of the one passed in,
	 * -1 if there is none. **/
	public int down(int i) {
		if(i >= 12)
			return -1;

		return i + 4;
	}

	/** Gets the index of the square to the right of the one passed in,
	 * -1 if there is none. **/
	public int right(int i) {
		if(i >= 16 || (i + 1) % 4 == 0)
			return -1;

		return i + 1;
	}

	/** Gets the index of the square to the left of the one passed in,
	 * -1 if there is none. **/
	public int left(int i) {
		if(i == 0 || i == 4 || i == 8 || i == 12)
			return -1;
		return i - 1;
	}

	public boolean verify() {
		for(int i=0; i < squares.length; i++)
			if(squares[i] != i+1 && !(i == 15 && squares[i] == 0))
				return false;

		PacketCompleteResearch packet = new PacketCompleteResearch(node);
		PacketHelper.sendPacketToServer(packet);
		ResearchHelper.clientResearch.completeResearch(node.index, false, null);
		MiscHelper.getMc().displayGuiScreen(new GuiElementalistLexiconIndex());
		return true;
	}

	public void randomlyAsignSquares() {
		Random rand = new Random();
		List<Integer> currentUnusedValues = new ArrayList();
		for(int i = 0; i < 16; i++) {
			currentUnusedValues.add(i);
		}
		for(int i = 0; i < 16; i++) {
			int index = rand.nextInt(currentUnusedValues.size());
			squares[i] = currentUnusedValues.get(index);
			currentUnusedValues.remove(index);
		}
	}

}
