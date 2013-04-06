/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 3 Mar 2013
package vazkii.tinkerer.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.TileEntityIncinerator;
import vazkii.tinkerer.tile.container.ContainerIncinerator;
import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 * GuiIncinerator
 *
 * The gui for the Incinerator block.
 *
 * @author Vazkii
 */
public class GuiIncinerator extends GuiContainer {

	public GuiIncinerator(TileEntityIncinerator incinerator, EntityPlayer player) {
		super(new ContainerIncinerator(incinerator, player.inventory));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(ResourcesReference.GUI_INCINERATOR_TEXTURE);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("Inventory", 8, ySize - 94, 4210752);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        Slot hoveredSlot = ReflectionHelper.getPrivateValue(GuiContainer.class, this, 6);
        if(hoveredSlot != null && hoveredSlot.slotNumber == 0 && !hoveredSlot.getHasStack())
    			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "Burn Slot", FormattingCode.GRAY + "Anything placed here will get destroyed.");
	}
}
