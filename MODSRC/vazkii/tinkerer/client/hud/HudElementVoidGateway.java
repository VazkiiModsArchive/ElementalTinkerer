/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Feb 2013
package vazkii.tinkerer.client.hud;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.MovingObjectPosition;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.block.voidStorage.VoidEntry;
import vazkii.tinkerer.block.voidStorage.VoidMap;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.reference.BlockIDs;

/**
 * HudElementVoidGateway
 *
 * The Void Gateway HUD Display, this shows
 * what items are on a void gateway when one
 * is being hovered.
 *
 * @author Vazkii
 */
public class HudElementVoidGateway implements IHudElement {

	public static final HudElementVoidGateway INSTANCE = new HudElementVoidGateway();

	private HudElementVoidGateway() { }

	VoidEntry currentRendering;

	@Override
	public boolean shouldRender() {
		Minecraft mc = MiscHelper.getMc();
		MovingObjectPosition look = mc.objectMouseOver;

		if(VoidMap.theMap != null && mc.theWorld != null && look != null && mc.currentScreen == null && !HudElementSpellCircle.INSTANCE.shouldRender()) {
			if(mc.theWorld.getBlockId(look.blockX, look.blockY, look.blockZ) == BlockIDs.voidGateway) {
				VoidEntry entry = VoidMap.theMap.getEntryAtCoord(look.blockX, look.blockZ);
				if(entry != null && entry.stack != null) {
					currentRendering = entry;
					return true;
				}
			}
		}

		// No entry to render, nullify the field
		currentRendering = null;
		return false;
	}

	@Override
	public void render(float partialTicks) {
		Minecraft mc = MiscHelper.getMc();
		FontRenderer font = mc.fontRenderer;
		ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		RenderItem itemRender = new RenderItem();
		int x = res.getScaledWidth() / 2 + 15;
		int y = res.getScaledHeight() / 2 - 8;
		String str1 = currentRendering.stack.getDisplayName();
		String str2 = currentRendering.qtd + "x" + (currentRendering.stack.getMaxStackSize() != 1 ? " (" + currentRendering.qtd / currentRendering.stack.getMaxStackSize() + " Stacks)" : "");
		int size = 16 + Math.max(font.getStringWidth(str1), font.getStringWidth(str2)) / 2;

		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.setColorRGBA_F(1F, 1F, 1F, 1F);
		tess.addVertex(x - 2, 0, y - 2);
		tess.addVertex(x - 2 , 0, y + size);
		tess.addVertex(x + 18, 0, y + size);
		tess.addVertex(x + 18, 0, y - 2);
		tess.draw();

		new RenderBlocks();
		boolean block = currentRendering.stack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.blocksList[currentRendering.stack.itemID].getRenderType());
		if(!block)
			RenderHelper.enableStandardItemLighting();
		itemRender.renderItemAndEffectIntoGUI(font, mc.renderEngine, currentRendering.stack, x, y);
		if(!block)
			RenderHelper.disableStandardItemLighting();
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		font.drawStringWithShadow(str1, (x + 17) * 2, (y + 4) * 2, 0xFFFFFF);
		font.drawStringWithShadow(str2, (x + 17) * 2, (y + 10) * 2, 0xFFFFFF);
		GL11.glScalef(1F, 1F, 1F);
	}

	@Override
	public void clientTick() {
		// NO-OP
	}
}
