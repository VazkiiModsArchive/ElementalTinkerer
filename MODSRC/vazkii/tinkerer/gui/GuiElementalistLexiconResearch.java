/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 11 Jan 2013
package vazkii.tinkerer.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.helper.RenderHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.ResearchNode;

/**
 * GuiElementalistLexiconResearch
 *
 * A research page of the Elementalist's Lexicon, used
 * to see what the research says.
 *
 * @author Vazkii
 */
public class GuiElementalistLexiconResearch extends GuiScreen {

	public int category;
	public ResearchNode node;
    int xStart, yStart;

	public GuiElementalistLexiconResearch(ResearchNode node, int category) {
		this.node = node;
		this.category = category;
	}

	@Override
	public void initGui() {
		super.initGui();
        xStart = (width - 146) / 2;
        yStart = (height - 180) / 2;
        controlList.clear();
        controlList.add(new GuiInvisibleButton(0, xStart + 146, yStart, 20, 20));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.id == 0)
			MiscHelper.getMc().displayGuiScreen(new GuiElementalistLexiconIndex());

		super.actionPerformed(par1GuiButton);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		int texture = mc.renderEngine.getTexture(ResourcesReference.GUI_ELEMENTALIST_LEXICON_RESEARCH_TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(xStart, yStart, 0, 0, 146, 180);
        GL11.glPushMatrix();
        GL11.glScalef(2F, 2F, 2F);
        GL11.glTranslatef(0.5F, 0.5F, 0F);
        RenderHelper.renderResearchIcon(node, true, (xStart + 57) / 2, (yStart + 17) / 2, zLevel);
        GL11.glPopMatrix();


        boolean isResearched = ResearchHelper.clientResearch.isResearchDone(node.index);
        boolean isCompleted = ResearchHelper.clientResearch.isResearchCompleted(node.index);
        String display = isResearched ? node.displayName : FormattingCode.ITALICS + "???";
        fontRenderer.drawStringWithShadow(display, xStart + 73 - fontRenderer.getStringWidth(display) / 2, yStart - 11, 0xFFFFFF);
		String[] description = ResearchHelper.getDesciptionForResearch(node);

		int i = 0;
		fontRenderer.setUnicodeFlag(true);
		if(isCompleted)
		for(String s : description) {
			fontRenderer.drawString(s, xStart + 16, yStart + 56 + i * 8, 0);
			++i;
		} else if(isResearched) {
			MiscHelper.getMc().displayGuiScreen(new GuiResearchGame(node, category));
		}
		fontRenderer.setUnicodeFlag(false);

		fontRenderer.drawStringWithShadow("\u2714", xStart + 150, yStart + 5, 0xFFFFFF);
		if(((GuiInvisibleButton) controlList.get(0)).isHovered())
			RenderHelper.renderTooltip(par1, par2, "Done");

		super.drawScreen(par1, par2, par3);
	}
}
