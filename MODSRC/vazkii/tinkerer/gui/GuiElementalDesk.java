/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.gui;

import java.awt.Color;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.GLU;

import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.reference.MiscReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.TileEntityReference;
import vazkii.tinkerer.tile.TileEntityElementalDesk;
import vazkii.tinkerer.tile.container.ContainerElementalDesk;

/**
 * GuiElementalDesk
 *
 * Gui for the Elemental Desk block.
 *
 * @author Vazkii
 */
public class GuiElementalDesk extends GuiContainer {

	/** Coordinates for the gem charge meters
	 * [0] : Index,
	 * [1] : X coordinate
	 * [2] : Y coordinate **/
	private static final int[][] CHARGE_METER_COORDINATES = new int[][] {
		{ 0, 28, 24 },
		{ 1, 28, 46 },
		{ 2, 146, 24 },
		{ 3, 146, 46 }
	};

    private static ModelBook bookModel = new ModelBook();

	TileEntityElementalDesk deskTile;

	public GuiElementalDesk(InventoryPlayer playerInv, TileEntityElementalDesk deskTile) {
		super(new ContainerElementalDesk(playerInv, deskTile));
		this.deskTile = deskTile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		int texture = mc.renderEngine.getTexture(ResourcesReference.GUI_ELEMENTAL_DESK_TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

        // Draw the Progress bar
        drawTexturedModalRect(xStart + 48, yStart + 46, 176, 0, (int) Math.round(deskTile.getProgress() / (TileEntityReference.ELEMENTAL_DESK_ENCHANT_TIME / 80D)), 12);

        // Draw the charge bars
        int color = Color.getHSBColor((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GEM), 1F, 1F).getRGB();
        Tessellator tess = Tessellator.instance;
        for(int[] i : CHARGE_METER_COORDINATES) {
        	int index = i[0];
        	int x = i[1];
        	int endY = i[2] + TileEntityReference.ELEMENTAL_DESK_GEM_CHARGE;
        	int startY = endY - deskTile.getCharge(index);
        	tess.startDrawingQuads();
            tess.setColorOpaque_I(color);
        	tess.addVertex(xStart + x, yStart + startY, zLevel);
        	tess.addVertex(xStart + x, yStart + endY, zLevel);
        	tess.addVertex(xStart + x + 2, yStart + endY, zLevel);
        	tess.addVertex(xStart + x + 2, yStart + startY, zLevel);
        	tess.draw();
        }

        // Render the Book Model, if present
        ItemStack shouldBeABook = deskTile.getStackInSlot(4);
        if(shouldBeABook != null && shouldBeABook.itemID == Item.book.shiftedIndex) {
        	 GL11.glPushMatrix();
             GL11.glMatrixMode(GL11.GL_PROJECTION);
             GL11.glPushMatrix();
             GL11.glLoadIdentity();
             ScaledResolution var7 = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
             GL11.glViewport((var7.getScaledWidth() - 320) / 2 * var7.getScaleFactor(), (var7.getScaledHeight() - 240) / 2 * var7.getScaleFactor(), 320 * var7.getScaleFactor(), 240 * var7.getScaleFactor());
             GL11.glTranslatef(0F, 0.25F, 0F);
             GLU.gluPerspective(90F, 1.3333334F, 9F, 80F);
             GL11.glMatrixMode(GL11.GL_MODELVIEW);
             GL11.glLoadIdentity();
             RenderHelper.enableStandardItemLighting();
             float cos = (float)(Math.cos(ClientTickHandler.elapsedClientTicks / 3) / 10);
             GL11.glTranslatef(0.4F, 3.65F + (deskTile.getIsAdvancing() ? cos : -0.1F), -16F);
             GL11.glScalef(1F, 1F, 1F);
             float var9 = 5.0F;
             GL11.glScalef(var9, var9, var9);
             GL11.glRotatef(180F, -0.29F, 0F, 1F);
             float var10 = 0.6F;
             GL11.glTranslatef((1F - var10) * 0.2F, (1F - var10) * 0.1F, (1F - var10) * 0.25F);
             GL11.glRotatef(-(1F - var10) * 90F - 90F, 0F, 1F, 0F);
             GL11.glRotatef(180F, 1F, 0F, 0F);
             mc.renderEngine.bindTexture(mc.renderEngine.getTexture("/item/book.png"));
             GL11.glEnable(GL12.GL_RESCALE_NORMAL);
             bookModel.render(null, 0F, 0F, 0F, (float)deskTile.getProgress() / (float)TileEntityReference.ELEMENTAL_DESK_ENCHANT_TIME, 0F, MiscReference.MODEL_DEFAULT_RENDER_SCALE);
             GL11.glDisable(GL12.GL_RESCALE_NORMAL);
             RenderHelper.disableStandardItemLighting();
             GL11.glMatrixMode(GL11.GL_PROJECTION);
             GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
             GL11.glPopMatrix();
             GL11.glMatrixMode(GL11.GL_MODELVIEW);
             GL11.glPopMatrix();
             RenderHelper.disableStandardItemLighting();
             GL11.glColor4f(1F, 1F, 1F, 1F);
        }
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString("Inventory", 8, ySize - 94, 4210752);
	}
}
