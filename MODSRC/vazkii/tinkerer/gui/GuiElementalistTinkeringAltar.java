/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 15 Jan 2013
package vazkii.tinkerer.gui;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.network.packet.PacketElementalistTinkeringAltarStartRecipe;
import vazkii.tinkerer.reference.BlockNames;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.GuiReference;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.TileEntityReference;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;
import vazkii.tinkerer.tile.container.ContainerElementalistTinkeringAltar;
import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 * GuiElementalistTinkeringAltar
 *
 * The GUI for the Elementalist's Tinkering Altar. This gui has
 * the recipe, the slot with the item to trigger the recipe, a progress
 * bar and updating slots with icons to show if they're enabled or not,
 * depending on the presence of Catalyst Containers.
 *
 * @author Vazkii
 */
public class GuiElementalistTinkeringAltar extends GuiContainer {

	TileEntityElementalTinkeringAltar altar;

	public GuiElementalistTinkeringAltar(TileEntityElementalTinkeringAltar altar, EntityPlayer player) {
		super(new ContainerElementalistTinkeringAltar(player.inventory, altar));
		this.altar = altar;
	}

	@Override
	public void initGui() {
		super.initGui();
		ySize = 230;
		guiTop -= 19; // Correct the gui top
		// If this hadn't been corrected shift clicking
		// on the top slots in the interface wouldn't work
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;

    	int xPos = xStart + 80;
    	int yPos = yStart + 123;
		buttonList.clear();
		buttonList.add(new GuiInvisibleButton(0, xPos, yPos, 16, 16));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		mc.renderEngine.getTexture(ResourcesReference.GUI_ELEMENTALIST_TINKERING_ALTAR_TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.func_98187_b(ResourcesReference.GUI_ELEMENTALIST_TINKERING_ALTAR_TEXTURE);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;

        // Draw Arrow
    	drawTexturedModalRect(xStart + 102, yStart + 123, xSize, 18, 26, 15);
        if(altar.getIsCreating()) {
        	int time = altar.getProgress();
        	int size = (int) Math.round(MathHelper.crossMuliply(TileEntityReference.ELEMENTALIST_TINKERING_ALTAR_TIME, 28, time));

            Color color = Color.getHSBColor((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_INFUSION), 1F, 1F);
            GL11.glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
            drawTexturedModalRect(xStart + 102, yStart + 123, xSize, 33, size, 15);
            GL11.glColor3f(1F, 1F, 1F);
        }

        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        // Draw Missing Catalyst Capsule Icons
        if(!altar.hasCatalystCapsuleOnSide(0))
        	drawTexturedModalRect(xStart + 13, yStart + 37, xSize, 0, 18, 18);
        if(!altar.hasCatalystCapsuleOnSide(1))
        	drawTexturedModalRect(xStart + 145, yStart + 37, xSize, 0, 18, 18);
        if(!altar.hasCatalystCapsuleOnSide(2))
        	drawTexturedModalRect(xStart + 13, yStart + 73, xSize, 0, 18, 18);
        if(!altar.hasCatalystCapsuleOnSide(3))
        	drawTexturedModalRect(xStart + 145, yStart + 73, xSize, 0, 18, 18);
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        ItemStack stack = altar.getCraftingPrevision(ResearchHelper.clientResearch);
        if(stack != null) {
        	RenderItem itemRender = new RenderItem();
        	RenderEngine render = MiscHelper.getMc().renderEngine;
        	int xPos = xStart / 2 - 46;
        	int yPos = yStart + 49;
        	if(!ForgeHooksClient.renderInventoryItem(new RenderBlocks(), render, stack, itemRender.field_77024_a, zLevel, xPos, yPos))
        		itemRender.renderItemIntoGUI(fontRenderer, render, stack, xPos, yPos);
        }

    	if(((GuiInvisibleButton)buttonList.get(0)).isHovered()) {
    		if(stack != null) {
        		List<String> tooltip = stack.getTooltip(MiscHelper.getClientPlayer(), false);
    			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, tooltip);
    		} else vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, GuiReference.TOOLTIP_ERROR_COLOR, GuiReference.TOOLTIP_ERROR_COLOR_BG, FormattingCode.RED + "No Recipe");

    	}

        Slot hoveredSlot = ReflectionHelper.getPrivateValue(GuiContainer.class, this, 6);
        if(hoveredSlot != null && hoveredSlot.getStack() == null) {
        	if(hoveredSlot.slotNumber < 4)
        		if(altar.hasCatalystCapsuleOnSide(hoveredSlot.slotNumber))
        			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart - 32, FormattingCode.AQUA + ItemNames.CATALYST_BARE_DISPLAY_NAME + " Slot", FormattingCode.GRAY + "Place a " + ItemNames.CATALYST_BARE_DISPLAY_NAME + " here to fuel the infusion.");
        		else vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart - 32, GuiReference.TOOLTIP_ERROR_COLOR, GuiReference.TOOLTIP_ERROR_COLOR_BG, FormattingCode.RED + "No Capsule", FormattingCode.GRAY + "Attach a " + BlockNames.CATALYST_CAPSULE_DISPLAY_NAME + " to this block.");
        }
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.id == 0) {
	        ItemStack stack = altar.getCraftingPrevision(ResearchHelper.clientResearch);
	        if(!altar.getIsCreating() && stack != null) {
	        	PacketHelper.sendPacketToServer(new PacketElementalistTinkeringAltarStartRecipe(altar));
	        	altar.setCreating(true);
	        }
		}

		super.actionPerformed(par1GuiButton);
	}

}
