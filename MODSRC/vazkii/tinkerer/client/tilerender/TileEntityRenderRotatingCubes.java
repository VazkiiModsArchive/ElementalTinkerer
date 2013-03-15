/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Feb 2013
package vazkii.tinkerer.client.tilerender;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.model.ModelRotating;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.TileEntityRotatingCubes;

/**
 * TileEntityRenderScavenger
 *
 * The Tile Entity Render for the Scavenger Block. Renders it both in world
 * and in item form.
 *
 * @author Vazkii
 */
public class TileEntityRenderRotatingCubes extends TileEntitySpecialRenderer implements IItemRenderer {

	public static final TileEntityRenderRotatingCubes INSTANCE = new TileEntityRenderRotatingCubes();

	private TileEntityRenderRotatingCubes() { }

	public void renderRotatingCubesTileAt(TileEntityRotatingCubes tile, double x, double y, double z, float partialTicks) {
		bindTextureByName(ResourcesReference.MODEL_TEX_ROTATING_CUBES);
        GL11.glPushMatrix();
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GL11.glRotatef(180F, 0.5F, 0F, 0.5F);
        ModelRotating.INSTANCE.render();
        ModelRotating.INSTANCE.renderSpinningCubes(tile.getQtd(), tile.getHue() / 360F);
        GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTicks) {
		renderRotatingCubesTileAt((TileEntityRotatingCubes) tile, x, y, z, partialTicks);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		float x, y, z = y = x = 0F;
        switch (type) {
        case ENTITY :
        	x = z = -0.5F;
        	y = 0F;
        	z = -0.5F;
            break;

        case EQUIPPED :
        	x = z = 0F;
        	y = 0.4F;
            break;

        case INVENTORY :
        	x = z = 1F;
        	y = 0.65F;
            break;

        }

		bindTextureByName(ResourcesReference.MODEL_TEX_ROTATING_CUBES);
        GL11.glPushMatrix();
        GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
        GL11.glRotatef(180F, 0.5F, 0F, 0.5F);
        if(type == ItemRenderType.INVENTORY)
        	GL11.glRotatef(270F, 0F, 1F, 0F);
        ModelRotating.INSTANCE.render();
        GL11.glPopMatrix();
	}


}
