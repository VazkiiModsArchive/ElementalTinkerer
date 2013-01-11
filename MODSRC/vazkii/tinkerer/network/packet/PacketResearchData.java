/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Jan 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.NetworkReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.research.ResearchLibrary;
import cpw.mods.fml.common.network.Player;

/**
 * PacketResearchData
 *
 * Packet for sending the research data to a client.
 *
 * @author Vazkii
 */
public class PacketResearchData extends ETPacket {

	public static final PacketResearchData RECIEVER_INSTANCE = new PacketResearchData();

	PlayerResearch data;

	/** Constructor with no params required for the reciever end **/
	private PacketResearchData() { }

	public PacketResearchData(PlayerResearch data) {
		this.data = data;
	}

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		data.writeShort(ResearchLibrary.allNodes.keySet().size());
		for(Short i : ResearchLibrary.allNodes.keySet())  {
			data.writeShort(i);
			data.writeBoolean(this.data.isResearchDone(i));
		}

		return stream;
	}

	@Override
	public boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) throws IOException {
		if(subchannel.equals(getSubchannel())) {
			ByteArrayInputStream stream = new ByteArrayInputStream(packet.data);
			DataInputStream inputStream = new DataInputStream(stream);
			skipSubchannel(inputStream);

			if(ResearchHelper.clientResearch == null)
				ResearchHelper.clientResearch = new PlayerResearch(MiscHelper.getClientPlayer().username);

			short size = inputStream.readShort();

			for(short i = 0; i < size; i++) {
				short id = inputStream.readShort();
				if(inputStream.readBoolean())
					ResearchHelper.clientResearch.research(id, false, null);
			}

			return true;
		}

		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_RESEARCH;
	}

}
