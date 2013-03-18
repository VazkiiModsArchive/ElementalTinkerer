/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.client.tilerender;

import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.client.model.ModelElementalDesk;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.item.ElementalTinkererItems;
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.reference.MiscReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.TileEntityReference;
import vazkii.tinkerer.tile.TileEntityElementalDesk;

/**
 * TileEntityRenderElementalDesk
 *
 * Render for the Elemental Desk Tile Entity. Renders it both in world
 * and in item form.
 *
 * @author Vazkii
 */
public class TileEntityRenderElementalDesk extends TileEntitySpecialRenderer implements IItemRenderer {

	public static final TileEntityRenderElementalDesk INSTANCE = new TileEntityRenderElementalDesk();

    private ModelBook book = new ModelBook();

	EntityItem entity = null;

	TileEntityRenderElementalDesk() {}

	/** Synthetic bridge method, casts the tile entity object to
	 * TileEntityElementalDesk and passes it in to another method. **/
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
		renderElementalDeskAt((TileEntityElementalDesk) var1, var2, var4, var6, var8);
	}

	public void renderElementalDeskAt(TileEntityElementalDesk desk, double x, double y, double z, float ticks) {
		if(entity == null)
			entity = new EntityItem(desk.worldObj, desk.xCoord, desk.yCoord, desk.zCoord, new ItemStack(ElementalTinkererItems.elementiumGem));
		else entity.age = (int) ClientTickHandler.elapsedClientTicks;

		// The entity is not in the world, it's client sided and doessn't get updated,
		// this ensures that the age can be advanced continuously without any problems
		// and that you can't pick the item up either.

		int metadata = desk.getBlockMetadata();
		int degreeRotation = metadata * 90;
		bindTextureByName(ResourcesReference.MODEL_TEX_ELEMENTAL_DESK);
        GL11.glPushMatrix();
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
        GL11.glRotatef(degreeRotation, 0F, 1F, 0F);
        GL11.glRotatef(180F, 0.5F, 0F, 0.5F);
        ModelElementalDesk.INSTANCE.render();

        // Render the Book, if present
        ItemStack shouldBeABook = desk.getStackInSlot(4);
        if(shouldBeABook != null && (shouldBeABook.itemID == Item.book.itemID || shouldBeABook.itemID == ItemIDs.elementalBook || shouldBeABook.itemID == Item.enchantedBook.itemID || shouldBeABook.itemID == ItemIDs.unboundBook)) {
        	boolean isNonEnchantedBook = shouldBeABook.itemID == Item.book.itemID;
        	boolean isUnboundBook = shouldBeABook.itemID == ItemIDs.unboundBook;
        	boolean isEnchantedBook = shouldBeABook.itemID == Item.enchantedBook.itemID;
        	GL11.glPushMatrix();
            GL11.glTranslatef(0.1F, 0.1F + (desk.getIsAdvancing() ? (float)(Math.cos(ClientTickHandler.elapsedClientTicks / 3D) / 20D) : 0.07F), -0.2F);
            GL11.glRotatef(-1F * 180.0F / (float)Math.PI, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(180F, -0.5F, 0F, -0.5F);
            GL11.glRotatef(32.5F, 0F, 0F, -1F);
            GL11.glRotatef(90F, 0F, 1F, 0F);
            GL11.glTranslatef(-0.7F, 0.1F, 0.1F);
            GL11.glScalef(0.75F, 0.75F, 0.75F);
            bindTextureByName(isNonEnchantedBook || shouldBeABook.getItemDamage() >= 4 ? "/item/book.png" : isEnchantedBook ? ResourcesReference.ROOT_BOOK_TEXTURES + "Enchanting.png" : isUnboundBook ? ResourcesReference.ROOT_BOOK_TEXTURES + "Unbound.png" : ResourcesReference.ROOT_BOOK_TEXTURES + Element.getName(shouldBeABook.getItemDamage()) + ".png");
            GL11.glEnable(GL11.GL_CULL_FACE);
            book.render(null, 0F, 0F, 0F, isNonEnchantedBook ? (float)desk.getProgress() / (float)TileEntityReference.ELEMENTAL_DESK_ENCHANT_TIME : 1F, 0F, MiscReference.MODEL_DEFAULT_RENDER_SCALE);
        	GL11.glPopMatrix();
        }

        for(int i = 0; i < 4 && entity != null; i++) {
        	int chargeAt = desk.getCharge(i);
        	if(chargeAt > 0) {
        		GL11.glPushMatrix();
        		GL11.glScalef(0.25F, 0.25F, 0.25F);
                GL11.glRotatef(180F, -0.5F, 0F, -0.5F);
                GL11.glTranslatef((float)-x + 1.2F, (float)-y - 2.5F, (float)-z - 1.25F + i * 0.8F + (i > 1 ? 0.1F : 0F));
        		GL11.glEnable(GL11.GL_BLEND);
        		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        		GL11.glColor4f(1F, 1F, 1F, chargeAt / TileEntityReference.ELEMENTAL_DESK_GEM_CHARGE);
                ((Render) RenderManager.instance.entityRenderMap.get(EntityItem.class)).doRender(entity, x, y, z, 1F, ticks);
        		GL11.glPopMatrix();
        	}
        }
        GL11.glPopMatrix();
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

		bindTextureByName(ResourcesReference.MODEL_TEX_ELEMENTAL_DESK);
        GL11.glPushMatrix();
        GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
        GL11.glRotatef(180F, 0.5F, 0F, 0.5F);
        if(type == ItemRenderType.INVENTORY)
        	GL11.glRotatef(270F, 0F, 1F, 0F);
        ModelElementalDesk.INSTANCE.render();
        GL11.glPopMatrix();
	}
}
