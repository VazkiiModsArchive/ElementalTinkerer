/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 8 Mar 2013
package vazkii.tinkerer.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.TileEntityReference;
import vazkii.tinkerer.tile.TileEntityWaveInputter;
import vazkii.tinkerer.tile.container.ContainerWaveInputter;
import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 * GuiWaveInputter
 *
 * The GUI for the Wave Inputter inventory.
 *
 * @author Vazkii
 */
public class GuiWaveInputter extends GuiContainer {

	public GuiWaveInputter(TileEntityWaveInputter waveInputter, EntityPlayer player) {
		super(new ContainerWaveInputter(waveInputter, player.inventory));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		int texture = mc.renderEngine.getTexture(ResourcesReference.GUI_WAVE_INPUTTER_TEXTURE);
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
        Slot hoveredSlot = ReflectionHelper.getPrivateValue(GuiContainer.class, this, 6);
        if(hoveredSlot != null && !hoveredSlot.getHasStack()) {
        	if(hoveredSlot.slotNumber == 0)
    			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "Input Slot", FormattingCode.GRAY + "Anything placed here will get organized.");
        	else if(hoveredSlot.slotNumber == 1)
    			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "Gem Slot #1", FormattingCode.GRAY + "Anything in the input slot will try to get placed", FormattingCode.GRAY + "in the inventory correspondent to this", FormattingCode.GRAY + "gem before any others.", FormattingCode.RED + "Max Distance: " + TileEntityReference.LGEM_MAX_DISTANCE_WAVE_INPUTTER);
        	else if(hoveredSlot.slotNumber < 8)
    			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "Gem Slot #" + hoveredSlot.slotNumber, FormattingCode.GRAY + "Anything in the input slot will try to get placed", FormattingCode.GRAY + "in the inventory correspondent to this", FormattingCode.GRAY + "gem if there is not space in previous inventory.", FormattingCode.RED + "Max Distance: " + TileEntityReference.LGEM_MAX_DISTANCE_WAVE_INPUTTER);
        }
	}
}
