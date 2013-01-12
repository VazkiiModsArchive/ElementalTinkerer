/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Jan 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.NetworkReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.research.ResearchNode;
import cpw.mods.fml.common.network.Player;

/**
 * PacketCompleteResearch
 *
 * A Packet sent by the client to complete the research, the server, when it
 * receives the packet returns a packet to the client with the updated
 * research data.
 *
 * @author Vazkii
 */
public class PacketCompleteResearch extends ETPacket {

	public static final PacketCompleteResearch RECIEVER_INSTANCE = new PacketCompleteResearch();

	/** Constructor with no params required for the reciever end **/
	private PacketCompleteResearch() { }

	ResearchNode node;

	public PacketCompleteResearch(ResearchNode node) {
		this.node = node;
	}

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		data.writeShort(node.index);
		return stream;
	}

	@Override
	public boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) throws IOException {
		if(subchannel.equals(getSubchannel()) && player != null && player instanceof EntityPlayer) {
			ByteArrayInputStream stream = new ByteArrayInputStream(packet.data);
			DataInputStream inputStream = new DataInputStream(stream);
			skipSubchannel(inputStream);
			short id = inputStream.readShort();
			PlayerResearch research = ResearchHelper.getResearchDataForPlayer(((EntityPlayer)player).username);
			if(research.isResearchDone(id))
				research.completeResearch(id, true, ((EntityPlayer)player).worldObj);
			PacketResearchData dataPacket = new PacketResearchData(research);
			PacketHelper.sendPacketToClient(player, dataPacket);
			return true;
		}

		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_RESEARCH_COMPLETE;
	}

}
