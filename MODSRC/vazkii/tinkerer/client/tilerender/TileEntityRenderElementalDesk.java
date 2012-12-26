/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.client.tilerender;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.model.ModelElementalDesk;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.TileEntityElementalDesk;

/**
 * TileEntityRenderElementalDesk
 *
 * Render for the Elemental Desk Tile Entity.
 *
 * @author Vazkii
 */
public class TileEntityRenderElementalDesk extends TileEntitySpecialRenderer {

	public static final TileEntityRenderElementalDesk INSTANCE = new TileEntityRenderElementalDesk();

	TileEntityRenderElementalDesk() { }

	/** Synthetic bridge method, casts the tile entity object to
	 * TileEntityElementalDesk and passes it in to another method. **/
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		renderElementalDeskAt((TileEntityElementalDesk) var1, var2, var4, var6, var8);
	}

	public void renderElementalDeskAt(TileEntityElementalDesk desk, double x, double y, double z, float ticks) {
		int metadata = desk.getBlockMetadata();
		int degreeRotation = metadata * 90;
		bindTextureByName(ResourcesReference.MODEL_TEX_ELEMENTAL_DESK);
        GL11.glPushMatrix();
        // GL11.glEnable(GL12.GL_RESCALE_NORMAL); XXX Is it neeeded?
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GL11.glRotatef(degreeRotation, 0F, 1F, 0F);
        GL11.glRotatef(180F, 0.5F, 0F, 0.5F);
        ModelElementalDesk.INSTANCE.render();
        // GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
	}
}
