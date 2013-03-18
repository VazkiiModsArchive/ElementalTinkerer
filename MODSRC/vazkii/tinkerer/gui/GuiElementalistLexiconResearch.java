/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 11 Jan 2013
package vazkii.tinkerer.gui;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.helper.RenderHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.GuiReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.research.ResearchLibrary;
import vazkii.tinkerer.research.ResearchNode;
import vazkii.tinkerer.research.TinkeringAltarRecipe;

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

    /** The relative mouse positions to this gui **/
    int relativeMouseX, relativeMouseY;

    /** The ItemStack to render as a tooltip, this only
     * exists when the node viewing has a recipe. **/
    ItemStack tooltipStack = null;

    /** The Container ItemStack to render as a tooltip,
     * this is used if an item has a container item (item
     * that gets placed in the crafting grid upon crafting) **/
    ItemStack tooltipContainerStack = null;

    /** If the mouse is hovering over an item that has an
     * available research node. This is used to have a click
     * handler for when a click event is triggered to take
     * the player to the research page for that item. **/
    ResearchNode redirectResearch = null;

	public GuiElementalistLexiconResearch(ResearchNode node, int category) {
		this.node = node;
		this.category = category;
	}

	@Override
	public void initGui() {
		super.initGui();
        xStart = (width - 146) / 2;
        yStart = (height - 180) / 2;
        buttonList.clear();
        buttonList.add(new GuiInvisibleButton(0, xStart + 146, yStart, 20, 20));
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		if(redirectResearch != null) {
			GuiElementalistLexiconResearch researchGui = new GuiElementalistLexiconResearch(redirectResearch, 0);
			// Since the research will always be available, it doesn't need a proper
			// research game category identifier.
			MiscHelper.getMc().displayGuiScreen(researchGui);
		}

		super.mouseClicked(par1, par2, par3);
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.id == 0)
			MiscHelper.getMc().displayGuiScreen(new GuiElementalistLexiconIndex());

		super.actionPerformed(par1GuiButton);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		relativeMouseX = par1;
		relativeMouseY = par2;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.func_98187_b(ResourcesReference.GUI_ELEMENTALIST_LEXICON_RESEARCH_TEXTURE);
        drawTexturedModalRect(xStart, yStart, 0, 0, 146, 180);
        GL11.glPushMatrix();
        GL11.glScalef(2F, 2F, 2F);
        GL11.glTranslatef(0.5F, 0.5F, 0F);
        RenderHelper.renderResearchIcon(node, true, (xStart + 57) / 2, (yStart + 17) / 2, zLevel);
        GL11.glPopMatrix();

        boolean isResearched = ResearchHelper.clientResearch.isResearchDone(node.index);
        boolean isCompleted = ResearchHelper.clientResearch.isResearchCompleted(node.index);
		String chapter = "Chapter " + node.index + ": ";
        String display = chapter + (isResearched ? node.displayName : FormattingCode.ITALICS + "Unknown Chapter");
        fontRenderer.drawStringWithShadow(display, xStart + 73 - fontRenderer.getStringWidth(display) / 2, yStart - 11, 0xFFFFFF);

        String[] description = ResearchHelper.getDesciptionForResearch(node);

		int i = 0;
		fontRenderer.setUnicodeFlag(true);
		if(isCompleted)
		for(String s : description) {
			fontRenderer.drawString(s, xStart + 16, yStart + 56 + i * 8, 0);
			++i;
		} else if(isResearched) {
			// The research isn't totally done, redirect to the research game gui
			MiscHelper.getMc().displayGuiScreen(new GuiResearchGame(node, category));
		}
		fontRenderer.setUnicodeFlag(false);


		fontRenderer.drawStringWithShadow("\u2714", xStart + 149, yStart + 5, 0);
		fontRenderer.drawStringWithShadow("\u2714", xStart + 150, yStart + 4, 0);
		fontRenderer.drawStringWithShadow("\u2714", xStart + 151, yStart + 5, 0);
		fontRenderer.drawStringWithShadow("\u2714", xStart + 150, yStart + 6, 0);
		fontRenderer.drawStringWithShadow("\u2714", xStart + 150, yStart + 5, 0xFFFFFF);

		if(((GuiInvisibleButton) buttonList.get(0)).isHovered())
			RenderHelper.renderTooltip(par1, par2, FormattingCode.AQUA + "Done");

		GL11.glColor3f(1F, 1F, 1F);

		if(node.getBoundRecipe() != null && isCompleted) {
	        mc.renderEngine.func_98187_b(ResourcesReference.GUI_ELEMENTALIST_LEXICON_RESEARCH_TEXTURE);
	        drawTexturedModalRect(xStart + 146 , yStart + 67, 146, 67, 76, 94);
	        node.getBoundRecipe().getRecipeOutput();
	        String recipeName = FormattingCode.DARK_GRAY + "Crafting";
	        IRecipe recipe = node.getBoundRecipe();
	        if(node.isBoundRecipeAltarRecipe()) {
	        	recipeName = FormattingCode.DARK_GRAY + "Tinkering";
	        	renderTinkeringRecipe((TinkeringAltarRecipe) recipe);
	        } else renderCraftingRecipe(recipe);
	        fontRenderer.drawString(recipeName, xStart + 146 + 30 - fontRenderer.getStringWidth(recipeName) / 2, yStart + 79, 0xFFFFFF);
		}

		if(tooltipStack != null) {
			List<String> tooltipData = tooltipStack.getTooltip(MiscHelper.getClientPlayer(), false);

			PlayerResearch research = ResearchHelper.clientResearch;
			for(Short s : research.researchesDone.keySet()) {
				ResearchNode node = ResearchLibrary.allNodes.get(s);
				if(MiscHelper.areStacksEqualIgnoreSize(node.getIconicItem(), tooltipStack) && node != this.node) {
					redirectResearch = node;
					tooltipData.add(FormattingCode.GRAY + "(Click for Entry)");
					break;
				}
				redirectResearch = null;
			}

			RenderHelper.renderTooltip(relativeMouseX, relativeMouseY, tooltipData);
			if(tooltipContainerStack != null)
				RenderHelper.renderTooltip(relativeMouseX, relativeMouseY + 8 + tooltipData.size() * 11, GuiReference.TOOLTIP_CONTAINER_ITEM_COLOR, GuiReference.TOOLTIP_CONTAINER_ITEM_COLOR_BG,FormattingCode.AQUA + "Returns on crafting:", tooltipContainerStack.getDisplayName());
		} else redirectResearch = null;

		tooltipStack = null;
		tooltipContainerStack = null;

		super.drawScreen(par1, par2, par3);
	}

	public void renderCraftingRecipe(IRecipe recipe) {
		if(recipe instanceof ShapedRecipes) {
			ShapedRecipes shaped = (ShapedRecipes)recipe;
			for(int y = 0; y < shaped.recipeHeight; y++)
				for(int x = 0; x < shaped.recipeWidth; x++)
					renderItemAtGridPos(1 + x, 1 + y, shaped.recipeItems[y * shaped.recipeWidth + x], true);
		} else if(recipe instanceof ShapelessRecipes) {
			ShapelessRecipes shapeless = (ShapelessRecipes) recipe;
			drawGrid : {
				for(int y = 0; y < 3; y++)
					for(int x = 0; x < 3; x++) {
						int index = y * 3 + x;
						if(index >= shapeless.recipeItems.size())
							break drawGrid;
						renderItemAtGridPos(1 + x, 1 + y, (ItemStack) shapeless.recipeItems.get(index), true);
					}
			}

		}
		renderItemAtGridPos(5, 2, recipe.getRecipeOutput(), false);
	}

	public void renderTinkeringRecipe(TinkeringAltarRecipe recipe) {
		for(int y = 0; y < recipe.recipeHeight; y++)
			for(int x = 0; x < recipe.recipeWidth; x++)
				renderItemAtGridPos(x, y, recipe.recipeItems[y * recipe.recipeWidth + x], true);
		ShapelessRecipes catalystRecipe = recipe.catalystRecipe;
		if(catalystRecipe != null) {
			List<ItemStack> catalysts = catalystRecipe.recipeItems;
			int i = 0;
			for(ItemStack catalyst : catalysts) {
				renderCatalystItem(i, catalyst);
				++i;
			}
			renderItemAtGridPos(5, 2, recipe.getRecipeOutput(), false);
		}
	}

	public void renderCatalystItem(int x, ItemStack stack) {
		if(stack == null || stack.getItem() == null)
			return;

		GL11.glPushMatrix();
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		int xPos = (xStart + 156 + x * 9) * 2;
		int yPos = (yStart + 91) * 2;
		ItemStack stack1 = stack.copy();
		if(stack1.getItemDamage() == -1)
			stack1.setItemDamage(0);

		renderItem(xPos, yPos, stack, false);
		GL11.glPopMatrix();
	}

	public void renderItemAtGridPos(int x, int y, ItemStack stack, boolean accountForContainer) {
		if(stack == null || stack.getItem() == null)
			return;

		GL11.glPushMatrix();
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		int xPos = (xStart + 152 + x * 9) * 2;
		int yPos = (yStart + 102 + y * 9) * 2;
		ItemStack stack1 = stack.copy();
		if(stack1.getItemDamage() == -1)
			stack1.setItemDamage(0);

		renderItem(xPos, yPos, stack, accountForContainer);
		GL11.glPopMatrix();
	}

	public void renderItem(int xPos, int yPos, ItemStack stack, boolean accountForContainer) {
		RenderItem render = new RenderItem();
		boolean block = stack.itemID < Block.blocksList.length;
		if(block)
			net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
		RenderEngine renderEngine = MiscHelper.getMc().renderEngine;
		if(!ForgeHooksClient.renderInventoryItem(new RenderBlocks(), renderEngine, stack, render.field_77024_a, zLevel, xPos, yPos))
			render.renderItemIntoGUI(fontRenderer, renderEngine, stack, xPos, yPos);
		render.renderItemOverlayIntoGUI(fontRenderer, renderEngine, stack, xPos, yPos);
		if(block)
			net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();

		if(relativeMouseX >= xPos / 2 && relativeMouseY >= yPos / 2 && relativeMouseX <= xPos / 2 + 8 && relativeMouseY <= yPos / 2 + 8) {
			tooltipStack = stack;
			if(accountForContainer) {
				ItemStack containerStack = stack.getItem().getContainerItemStack(stack);
				if(containerStack != null && containerStack.getItem() != null)
					tooltipContainerStack = containerStack;
			}
		}
	}
}
