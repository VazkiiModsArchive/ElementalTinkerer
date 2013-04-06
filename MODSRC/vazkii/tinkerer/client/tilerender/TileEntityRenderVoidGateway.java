/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Feb 2013
package vazkii.tinkerer.client.tilerender;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.block.voidStorage.VoidEntry;
import vazkii.tinkerer.block.voidStorage.VoidMap;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.client.helper.RenderHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.reference.MiscReference;

/**
 * TileEntityRenderVoidGateway
 *
 * The Tile Entity Render for the Void Gateway block.
 *
 * @author Vazkii
 */
public class TileEntityRenderVoidGateway extends TileEntitySpecialRenderer {

	public static final TileEntityRenderVoidGateway INSTANCE = new TileEntityRenderVoidGateway();

	private TileEntityRenderVoidGateway() { }

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float ticks) {
		VoidEntry entry = VoidMap.theMap.getEntryAtCoord(tile.xCoord, tile.zCoord);

		if(entry != null && entry.stack != null) {
			ItemStack stack = entry.stack.copy();
			stack.stackSize = 1;

			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 0.1F, (float) z + 0.4F + MiscReference.MODEL_DEFAULT_RENDER_SCALE);
			GL11.glRotatef((float) ClientTickHandler.elapsedClientTicks % 360L / 20.0F * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
            Minecraft mc = MiscHelper.getMc();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture(stack.getItem() instanceof ItemBlock ? "/terrain.png" : "/gui/items.png"));
            Tessellator var5 = Tessellator.instance;
            RenderBlocks var6 = new RenderBlocks();
            if(stack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[stack.itemID].getRenderType())) {
            	GL11.glScalef(0.5F, 0.5F, 0.5F);
            	GL11.glTranslatef(0F, 0.9F, 0F);
            	var6.renderBlockAsItem(Block.blocksList[stack.itemID], stack.getItemDamage(), 1F);

            } else {
            	int renderPass = 0;
            	GL11.glScalef(0.75F, 0.75F, 0.75F);
    			GL11.glRotatef((float) ClientTickHandler.elapsedClientTicks % 360L / 20.0F * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
    			GL11.glTranslatef(-0.5F, 0F, -MiscReference.MODEL_DEFAULT_RENDER_SCALE);
            	do {
                	Icon icon = stack.getItem().getIcon(stack, renderPass);
                	if(icon != null) {
                		 Color color = new Color(stack.getItem().getColorFromItemStack(stack, renderPass));

                		 GL11.glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
                		 float f = icon.getMinU();
                         float f1 = icon.getMaxU();
                         float f2 = icon.getMinV();
                         float f3 = icon.getMaxV();
                         // TODO Is this correct?
                         ItemRenderer.renderItemIn2D(var5, f1, f2, f, f3, icon.getOriginX(), icon.getOriginY(), MiscReference.MODEL_DEFAULT_RENDER_SCALE);
                         GL11.glColor3f(1F, 1F, 1F);
                	}
                	renderPass++;
            	} while(renderPass < stack.getItem().getRenderPasses(stack.getItemDamage()));
            }

            GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glTranslatef((float) x + 2.5F, (float) y - 0.5F, (float) z + 0.5F);
			GL11.glRotatef(180F, 0.5F, 0F, 0.5F);
			RenderHelper.renderStar(0x140025, 0.03F, 0.03F, 0.03F);
			GL11.glPopMatrix();
		}
	}
}
