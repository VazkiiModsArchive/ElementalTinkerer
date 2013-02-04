/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Jan 2013
package vazkii.tinkerer.helper;

import net.minecraft.world.World;
import vazkii.tinkerer.lightning.LightningBolt;
import vazkii.tinkerer.lightning.Vector3;
import vazkii.tinkerer.network.packet.PacketLightning;

/**
 * LightningHelper
 * 
 * Helper class for Lightning. This class exists to simplify creating lightning
 * bolts.
 * 
 * @author Vazkii
 */
public final class LightningHelper {

	/** Spawns a lightning bolt and syncs it over to the clients **/
	public static LightningBolt spawnAndSyncLightningBolt(World world, Vector3 vectorStart, Vector3 vectorEnd, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
		LightningBolt bolt = spawnLightningBolt(world, vectorStart, vectorEnd, ticksPerMeter, seed, colorOuter, colorInner);
		PacketLightning packet = new PacketLightning(bolt);
		PacketHelper.sendPacketToAllClients(packet);
		return bolt;
	}

	public static LightningBolt spawnLightningBolt(World world, Vector3 vectorStart, Vector3 vectorEnd, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
		LightningBolt bolt = new LightningBolt(world, vectorStart, vectorEnd, ticksPerMeter, seed, colorOuter, colorInner);
		bolt.defaultFractal();
		bolt.finalizeBolt();
		LightningBolt.boltlist.add(bolt);
		return bolt;
	}

}
