/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.gui;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.TileEntityScavenger;
import vazkii.tinkerer.tile.container.ContainerScavenger;

/**
 * GuiScavenger
 *
 * The GUI for the Scavenger Inventory.
 *
 * @author Vazkii
 */
public class GuiScavenger extends GuiFilterInventory {

	public GuiScavenger(TileEntityScavenger tile, EntityPlayer player) {
		super(new ContainerScavenger(tile, player.inventory));
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		super.drawGuiContainerForegroundLayer(par1, par2);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;

        if(hoveredSlot != null && !hoveredSlot.getHasStack() && hoveredSlot.slotNumber == 48) // 48? Weird, I know, but the input slot is added after the inv
    		vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "Input Slot", FormattingCode.GRAY + "Items will get pulled to here before being filtered.");
	}

	@Override
	String getBindTexture() {
		return ResourcesReference.GUI_SCAVENGER_TEXTURE;
	}

}
