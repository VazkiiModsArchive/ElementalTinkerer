/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 14 Apr 2013
package vazkii.tinkerer.client.tilerender;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.model.ModelCogwheel;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.cogwork.TileEntityCogwork;

/**
 * TileEntityRenderCogwork
 *
 * The Tile Entity Render for Cogwork blocks.
 *
 * @author Vazkii
 */
public class TileEntityRenderCogwork extends TileEntitySpecialRenderer {

	public static final TileEntityRenderCogwork INSTANCE = new TileEntityRenderCogwork();
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		renderCogworkTileAt((TileEntityCogwork)tile , x, y, z, f);
	}
	
	public void renderCogworkTileAt(TileEntityCogwork tile, double x, double y, double z, float partialTicks) {
		bindTextureByName(ResourcesReference.MODEL_TEX_COGWHEEL);
        GL11.glPushMatrix();
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GL11.glRotatef(180F, 0.5F, 0F, 0.5F);
        renderCogwheel(0F, true);
        GL11.glPopMatrix();
	}
	
	/** Renders a cogwheel, must be translated previously. **/
	public void renderCogwheel(float deg, boolean rotation) {
        ModelCogwheel.INSTANCE.render();
	}

}
