/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Mar 2013
package vazkii.tinkerer.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemBlock;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.block.voidStorage.NetworkEntry;
import vazkii.tinkerer.block.voidStorage.VoidEntry;
import vazkii.tinkerer.helper.InventoryHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.network.packet.PacketVoidNetworkButton;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.TileEntityVoidNetwork;

/**
 * GuiVoidNetwork
 *
 * The GUI for the Void Network.
 *
 * @author Vazkii
 */
public class GuiVoidNetwork extends GuiScreen {

	public static boolean[] requestsNumber = new boolean[] {
		true, false, false, true, true, false
	};

	int xSize, ySize, xStart, yStart;

	int selected = -1;
	int looking = -1;
	int buttonLooking = -1;

	String number = "";

	TileEntityVoidNetwork tile;

	public GuiVoidNetwork(TileEntityVoidNetwork tile) {
		this.tile = tile;
	}

	@Override
	public void initGui() {
		super.initGui();
		xSize = 176;
		ySize = 166;
        xStart = (width - xSize) / 2;
        yStart = (height - ySize) / 2;
	}

	public void addButtons() {
		clearButtons();
		buttonList.add(new GuiButton(0, xStart + 10, yStart + 80, 20, 20, "M"));
		buttonList.add(new GuiButton(1, xStart + 10, yStart + 105, 20, 20, "O"));
		buttonList.add(new GuiButton(2, xStart + 10, yStart + 130, 20, 20, "I"));
		buttonList.add(new GuiButton(3, xStart + 146, yStart + 80, 20, 20, "R"));
		buttonList.add(new GuiButton(4, xStart + 146, yStart + 105, 20, 20, "S"));
		buttonList.add(new GuiButton(5, xStart + 146, yStart + 130, 20, 20, "U"));
	}

	public void clearButtons() {
		buttonList.clear();
	}

	@Override
	public void drawScreen(int var1, int var2, float var3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(ResourcesReference.GUI_VOID_NETWORK_TEXTURE);
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        super.drawScreen(var1, var2, var3);

        RenderItem itemRender = new RenderItem();
        List<String> tooltip = null;
        boolean foundALooking = false;
        boolean foundALookingButton = false;

        for(int i = 0; i < tile.entries.length; i++) {
        	int x = xStart + 8 + 18 * (i % 9);
        	int y = yStart + 17 + 18 * (i / 9);
        	if(tile.entries[i] != null) {
        		VoidEntry entry = tile.entries[i].getEntry(tile.worldObj);
        		if(entry != null) { // Shouldn't be an issue...
            		boolean block = entry.stack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[entry.stack.itemID].getRenderType());

            		if(!block)
            			RenderHelper.enableStandardItemLighting();
            		itemRender.renderItemIntoGUI(fontRenderer, MiscHelper.getMc().renderEngine, entry.stack, x, y);
            		if(!block)
            			RenderHelper.disableStandardItemLighting();
            		if(var1 >= x && var2 >= y && var1 < x + 18 && var2 < y + 18) {
            			tooltip = entry.stack.getTooltip(MiscHelper.getClientPlayer(), false);
            			looking = i;
            			foundALooking = true;
            		}
        		}
        	}
        }

        List<GuiButton> buttons = buttonList;
        int buttonIndex = 0;
        for(GuiButton button : buttons) {
    		if(var1 >= button.xPosition && var2 >= button.yPosition && var1 < button.xPosition + 20 && var2 < button.yPosition + 20) {

    			String[] tooltip1 = null;

    			switch(button.id) {
    				case 0 : tooltip1 = new String[]{ FormattingCode.AQUA + "Maintain", FormattingCode.GRAY + "The Network will pull, and maintain in the", FormattingCode.GRAY + "adjacent inventory the correct amount of items.", "> " +  number, FormattingCode.GRAY + "(Enter a number of items to maintain,", FormattingCode.GRAY + "press the button to commit)" };
    					break;
    				case 1 : tooltip1 = new String[]{ FormattingCode.AQUA + "Output", FormattingCode.GRAY + "The Network will output all items of the type selected", FormattingCode.GRAY + "into the void. This respects the Maintain setting." };
						break;
    				case 2 : tooltip1 = new String[]{ FormattingCode.AQUA + "Info", FormattingCode.GRAY + "Maintaining: " + tile.entries[selected].keep, FormattingCode.GRAY + "Outputting: " + (tile.entries[selected].autoStore ? "Yes" : "No"), FormattingCode.GRAY + "(This button does nothing)" };
    					break;
    				case 3 : tooltip1 = new String[]{ FormattingCode.AQUA + "Request", FormattingCode.GRAY + "Gives, if available, to the player", FormattingCode.GRAY + "the amount of the item requested for.", "> " + number, FormattingCode.GRAY + "(Enter a number of items to request,", FormattingCode.GRAY + "press the button to commit)" };
    					break;
    				case 4 : tooltip1 = new String[]{ FormattingCode.AQUA + "Store", FormattingCode.GRAY + "Stores, if available, from the adj. inventory", FormattingCode.GRAY + "the amount of the item told.", "> " + number, FormattingCode.GRAY + "(Enter a number of items to store,", FormattingCode.GRAY + "press the button to commit)" };
    					break;
    				case 5 : tooltip1 = new String[]{ FormattingCode.AQUA + "Unbind", FormattingCode.GRAY + "Removes this Void Gateway from the Network.", FormattingCode.RED + "Can not be undone. The gem will be lost." };
    			}

    			if(tooltip1 != null)
    				tooltip = Arrays.asList(tooltip1);

    			buttonLooking = buttonIndex;
    			foundALookingButton = true;
    			break;
    		}
        	++buttonIndex;
        }
        if(selected != -1) {
        	NetworkEntry netEntry = tile.entries[selected];
        	if(netEntry != null) {
        		VoidEntry entry = tile.entries[selected].getEntry(tile.worldObj);
        		if(entry != null) {
            		boolean block = entry.stack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[entry.stack.itemID].getRenderType());

        			if(!block)
            			RenderHelper.enableStandardItemLighting();
        			GL11.glPushMatrix();
        			GL11.glScalef(2F, 2F, 2F);
            		itemRender.renderItemIntoGUI(fontRenderer, MiscHelper.getMc().renderEngine, entry.stack, (xStart + 72) / 2, (yStart + 107) / 2);
            		GL11.glPopMatrix();
            		if(!block)
            			RenderHelper.disableStandardItemLighting();

            		GL11.glPushMatrix();
            		GL11.glScalef(0.5F, 0.5F, 0.5F);
            		String name = FormattingCode.BOLD + entry.stack.getDisplayName();
            		String qtd = "Void: " + entry.qtd + "x";
            		int qtdPlr = InventoryHelper.getQtdOfItemInInventory(entry.stack, MiscHelper.getClientPlayer().inventory, null);
            		String qtdPlrStr = "Player: " + qtdPlr + "x";
            		drawCenteredString(fontRenderer, name, (xStart + 88) * 2, (yStart + 89) * 2, 0xFFFFFF);
            		drawCenteredString(fontRenderer, qtd, (xStart + 88) * 2, (yStart + 94) * 2, 0xFFFFFF);
            		drawCenteredString(fontRenderer, qtdPlrStr, (xStart + 88) * 2, (yStart + 99) * 2, 0xFFFFFF);

            		GL11.glPopMatrix();
        		}
        	} else {
        		selected = -1; // The Unbind Button was pressed
        		clearButtons();
        	}
        }

        if(tooltip != null)
			vazkii.tinkerer.client.helper.RenderHelper.renderTooltip(var1, var2, tooltip);

        if(!foundALooking)
            looking = -1;

        if(!foundALookingButton)
            buttonLooking = -1;

        if(!foundALookingButton || !requestsNumber[buttonLooking])
        	number = "";
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);

		if("0123456789\b".indexOf(par1) >= 0 && buttonLooking != -1 && requestsNumber[buttonLooking]) {
			if(par1 == '\b' && number.length() > 0)
				number = number.substring(0, number.length() - 1);
			else if(par1 != '\b') number = number + par1;
		}
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);

		if(looking != -1) {
			selected = looking;
			addButtons();
		}
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		int num = number.equals("") ? 0 : Integer.parseInt(number);
		if(!requestsNumber[par1GuiButton.id] || !number.equals(""))
			PacketHelper.sendPacketToServer(new PacketVoidNetworkButton(tile, selected, par1GuiButton.id, num));

		if(requestsNumber[par1GuiButton.id])
			number = "";
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
