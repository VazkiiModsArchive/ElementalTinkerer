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
import vazkii.tinkerer.reference.TileEntityReference;
import vazkii.tinkerer.tile.TileEntityDislocator;
import vazkii.tinkerer.tile.container.ContainerDislocator;

/**
 * GuiDislocator
 *
 * The GUI for the Dislocator inventory.
 *
 * @author Vazkii
 */
public class GuiDislocator extends GuiFilterInventory {

	public GuiDislocator(TileEntityDislocator tile, EntityPlayer player) {
		super(new ContainerDislocator(tile, player.inventory));
	}

	@Override
	String getBindTexture() {
		return ResourcesReference.GUI_DISLOCATOR_TEXTURE;
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		super.drawGuiContainerForegroundLayer(par1, par2);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;

        if(hoveredSlot != null && !hoveredSlot.getHasStack()) {
        	// 48? Weird, I know, but the input and check gem slots are added after the inv
    		if(hoveredSlot.slotNumber == 48)
    			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "Search Slot", FormattingCode.GRAY + "The Inventory correspondent to this gem will get", FormattingCode.GRAY + "searched for items that will be transported according", FormattingCode.GRAY + "to the filter settings.", FormattingCode.RED + "Max Distance: " + TileEntityReference.LGEM_MAX_DISTANCE_DISLOCATOR);
        	if(hoveredSlot.slotNumber == 49)
    			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(par1 - xStart, par2 - yStart, FormattingCode.AQUA + "Input Slot", FormattingCode.GRAY + "Items will get pulled to here before being filtered.");
        }
	}

}
