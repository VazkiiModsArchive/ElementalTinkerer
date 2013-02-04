/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Jan 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.helper.LightningHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.lightning.LightningBolt;
import vazkii.tinkerer.lightning.Vector3;
import vazkii.tinkerer.reference.NetworkReference;
import cpw.mods.fml.common.network.Player;

/**
 * PacketLightning
 * 
 * Packet for Lightning Bolts.
 * 
 * @author Vazkii
 */
public class PacketLightning extends ETPacket {
	public static final PacketLightning RECIEVER_INSTANCE = new PacketLightning();

	LightningBolt bolt;

	/** Constructor with no params required for the reciever end **/
	private PacketLightning() {}

	public PacketLightning(LightningBolt bolt) {
		this.bolt = bolt;
	}

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		writeVector(data, bolt.start);
		writeVector(data, bolt.end);
		data.writeFloat(bolt.speed);
		data.writeLong(bolt.seed);
		data.writeInt(bolt.colorOuter);
		data.writeInt(bolt.colorInner);
		return stream;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_LIGHTNING;
	}

	@Override
	public boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) throws IOException {
		if (subchannel.equals(getSubchannel())) {
			ByteArrayInputStream stream = new ByteArrayInputStream(packet.data);
			DataInputStream inputStream = new DataInputStream(stream);
			skipSubchannel(inputStream);
			Vector3 start = readVector(inputStream);
			Vector3 end = readVector(inputStream);
			float speed = inputStream.readFloat();
			long seed = inputStream.readLong();
			int colorOuter = inputStream.readInt();
			int colorInner = inputStream.readInt();
			LightningHelper.spawnLightningBolt(MiscHelper.getClientWorld(), start, end, speed, seed, colorOuter, colorInner);
			return true;
		}

		return false;
	}

	public Vector3 readVector(DataInputStream stream) throws IOException {
		double x = stream.readDouble();
		double y = stream.readDouble();
		double z = stream.readDouble();
		return new Vector3(x, y, z);
	}

	public void writeVector(DataOutputStream stream, Vector3 vector) throws IOException {
		stream.writeDouble(vector.x);
		stream.writeDouble(vector.y);
		stream.writeDouble(vector.z);
	}

}
