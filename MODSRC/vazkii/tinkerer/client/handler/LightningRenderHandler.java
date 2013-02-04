/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ ?
package vazkii.tinkerer.client.handler;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.lightning.LightningBolt;
import vazkii.tinkerer.lightning.LightningBolt.Segment;
import vazkii.tinkerer.lightning.Vector3;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * LightningRenderHandler
 * 
 * RenderWirelessBolt class, part of CodeChickenCore. Available at:
 * http://www.minecraftforum.net/topic/909223-
 * 
 * @author ChickenBones
 */
public class LightningRenderHandler {
	public static final LightningRenderHandler INSTANCE = new LightningRenderHandler();

	private LightningRenderHandler() {}

	private static Vector3 getRelativeViewVector(Vector3 pos) {
		Entity renderEntity = MiscHelper.getMc().renderViewEntity;
		return new Vector3((float) renderEntity.posX - pos.x, (float) renderEntity.posY + renderEntity.getEyeHeight() - pos.y, (float) renderEntity.posZ - pos.z);
	}

	@ForgeSubscribe
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		float frame = event.partialTicks;
		Entity entity = MiscHelper.getClientPlayer();

		interpPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * frame;
		interpPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * frame;
		interpPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * frame;

		GL11.glTranslated(-interpPosX, -interpPosY, -interpPosZ);

		Tessellator tessellator = Tessellator.instance;

		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		ForgeHooksClient.bindTexture(ResourcesReference.LIGHTNING_OUTER_TEXTURE, 0);
		tessellator.startDrawingQuads();
		tessellator.setBrightness(0xF000F0);
		for (LightningBolt bolt : LightningBolt.boltlist)
			renderBolt(bolt, tessellator, frame, ActiveRenderInfo.rotationX, ActiveRenderInfo.rotationXZ, ActiveRenderInfo.rotationZ, ActiveRenderInfo.rotationXY, 0, false);
		tessellator.draw();

		ForgeHooksClient.bindTexture(ResourcesReference.LIGHTNING_INNER_TEXTURE, 0);
		tessellator.startDrawingQuads();
		tessellator.setBrightness(0xF000F0);
		for (LightningBolt bolt : LightningBolt.boltlist)
			renderBolt(bolt, tessellator, frame, ActiveRenderInfo.rotationX, ActiveRenderInfo.rotationXZ, ActiveRenderInfo.rotationZ, ActiveRenderInfo.rotationXY, 1, true);
		tessellator.draw();

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);

		GL11.glTranslated(interpPosX, interpPosY, interpPosZ);
	}

	private void renderBolt(LightningBolt bolt, Tessellator tessellator, float partialframe, float cosyaw, float cospitch, float sinyaw, float cossinpitch, int pass, boolean inner) {
		float boltage = bolt.particleAge < 0 ? 0 : (float) bolt.particleAge / (float) bolt.particleMaxAge;
		float mainalpha = 1;
		if (pass == 0) {
			mainalpha = (1 - boltage) * 0.4F;
		} else {
			mainalpha = 1 - boltage * 0.5F;
		}

		int expandTime = (int) (bolt.length * bolt.speed);

		int renderstart = (int) ((expandTime / 2 - bolt.particleMaxAge + bolt.particleAge) / (float) (expandTime / 2) * bolt.numsegments0);
		int renderend = (int) ((bolt.particleAge + expandTime) / (float) expandTime * bolt.numsegments0);

		for (Segment rendersegment : bolt.segments) {
			if (rendersegment.segmentno < renderstart || rendersegment.segmentno > renderend) {
				continue;
			}

			Vector3 playervec = getRelativeViewVector(rendersegment.startpoint.point).multiply(-1);

			double width = 0.025F * (playervec.mag() / 5 + 1) * (1 + rendersegment.light) * 0.5F;

			Vector3 diff1 = playervec.copy().crossProduct(rendersegment.prevdiff).normalize().multiply(width / rendersegment.sinprev);
			Vector3 diff2 = playervec.copy().crossProduct(rendersegment.nextdiff).normalize().multiply(width / rendersegment.sinnext);

			Vector3 startvec = rendersegment.startpoint.point;
			Vector3 endvec = rendersegment.endpoint.point;

			tessellator.setColorRGBA_I(inner ? bolt.colorInner : bolt.colorOuter, (int) (mainalpha * rendersegment.light * 255));

			tessellator.addVertexWithUV(endvec.x - diff2.x, endvec.y - diff2.y, endvec.z - diff2.z, 0.5, 0);
			tessellator.addVertexWithUV(startvec.x - diff1.x, startvec.y - diff1.y, startvec.z - diff1.z, 0.5, 0);
			tessellator.addVertexWithUV(startvec.x + diff1.x, startvec.y + diff1.y, startvec.z + diff1.z, 0.5, 1);
			tessellator.addVertexWithUV(endvec.x + diff2.x, endvec.y + diff2.y, endvec.z + diff2.z, 0.5, 1);

			if (rendersegment.next == null) {
				Vector3 roundend = rendersegment.endpoint.point.copy().add(rendersegment.diff.copy().normalize().multiply(width));

				tessellator.addVertexWithUV(roundend.x - diff2.x, roundend.y - diff2.y, roundend.z - diff2.z, 0, 0);
				tessellator.addVertexWithUV(endvec.x - diff2.x, endvec.y - diff2.y, endvec.z - diff2.z, 0.5, 0);
				tessellator.addVertexWithUV(endvec.x + diff2.x, endvec.y + diff2.y, endvec.z + diff2.z, 0.5, 1);
				tessellator.addVertexWithUV(roundend.x + diff2.x, roundend.y + diff2.y, roundend.z + diff2.z, 0, 1);
			}

			if (rendersegment.prev == null) {
				Vector3 roundend = rendersegment.startpoint.point.copy().subtract(rendersegment.diff.copy().normalize().multiply(width));

				tessellator.addVertexWithUV(startvec.x - diff1.x, startvec.y - diff1.y, startvec.z - diff1.z, 0.5, 0);
				tessellator.addVertexWithUV(roundend.x - diff1.x, roundend.y - diff1.y, roundend.z - diff1.z, 0, 0);
				tessellator.addVertexWithUV(roundend.x + diff1.x, roundend.y + diff1.y, roundend.z + diff1.z, 0, 1);
				tessellator.addVertexWithUV(startvec.x + diff1.x, startvec.y + diff1.y, startvec.z + diff1.z, 0.5, 1);
			}
		}
	}

	static double interpPosX;
	static double interpPosY;
	static double interpPosZ;
}
