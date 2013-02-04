/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Jan 2013
package vazkii.tinkerer.client.tilerender;

import java.awt.Color;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.client.helper.RenderHelper;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * TileEntityRenderAttuner
 * 
 * The Tile Entity Render for the Attuner Block.
 * 
 * @author Vazkii
 */
public class TileEntityRenderAttuner extends TileEntitySpecialRenderer {

	public static final TileEntityRenderAttuner INSTANCE = new TileEntityRenderAttuner();

	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8) {
		GL11.glPushMatrix();
		GL11.glColor4f(1F, 1F, 1F, 1F);
		GL11.glTranslatef((float) x + 2.5F, (float) y - 0.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.5F, 0F, 0.5F);
		Color rgbColor = new Color(Color.HSBtoRGB((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GEM), 0.9F, 0.7F));
		RenderHelper.renderStar(rgbColor.getRGB(), 0.03F, 0.03F, 0.03F);
		GL11.glPopMatrix();
	}

}
