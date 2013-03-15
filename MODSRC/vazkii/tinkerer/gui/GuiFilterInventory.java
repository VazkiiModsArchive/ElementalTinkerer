/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.tile.container.ContainerFilterInventory;
import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 * GuiFilterInventory
 *
 * Base GUI for the Filter Inventories.
 *
 * @author Vazkii
 */
public abstract class GuiFilterInventory extends GuiContainer {

	ContainerFilterInventory container;

	public GuiFilterInventory(ContainerFilterInventory par1Container) {
		super(par1Container);
		container = par1Container;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		int texture = mc.renderEngine.getTexture(getBindTexture());
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("Inventory", 8, ySize - 94, 4210752);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        hoveredSlot = ReflectionHelper.getPrivateValue(GuiContainer.class, this, 6);
        if(hoveredSlot != null && !hoveredSlot.getHasStack()) {
        	if(hoveredSlot == container.getIfSlot())
    			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "IF Slot", FormattingCode.GRAY + "Items will get put into here if they match the filter.", FormattingCode.GRAY + "If there is nothing in the filter, nothing will go here.", FormattingCode.RED + "Max Distance: " + container.getMaxDistance());
        	else if(hoveredSlot == container.getElseSlot())
				vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "ELSE Slot", FormattingCode.GRAY + "Items will get put into here if they don't match the filter.", FormattingCode.GRAY + "If there is nothing in the filter, everything will go here.", FormattingCode.GRAY + "If there's items in the filter and no item in this slot", FormattingCode.GRAY + "only items matching the filter will get pulled.", FormattingCode.RED + "Max Distance: " + container.getMaxDistance());
        	else if(hoveredSlot.slotNumber >= 0 && hoveredSlot.slotNumber < 9)
				vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "Filter Slot", FormattingCode.GRAY + "Items in these slots will serve as a filter.");
        }
	}

	// Keep this as a field, to avoid having to do reflection twice.
	Slot hoveredSlot;

	/** Gets the name of the texture to bind when rendering the background **/
	abstract String getBindTexture();


}
