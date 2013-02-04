/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Jan 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.magic.PlayerSpellData;
import vazkii.tinkerer.reference.NetworkReference;
import cpw.mods.fml.common.network.Player;

/**
 * PacketSpells
 *
 * Packet for sending the spell data to a client.
 *
 * @author Vazkii
 */
public class PacketSpells extends ETPacket {
	
	public static final PacketSpells RECIEVER_INSTANCE = new PacketSpells();

	PlayerSpellData data;
	
	short[] spellsArray,
			passivesArray;
	byte selectedSpell;

	/** Constructor with no params required for the reciever end **/
	PacketSpells() { }
	
	public PacketSpells(PlayerSpellData data) {
		this.data = data;
	}
	
	void writeSpells(Player player, DataInputStream inputStream) throws IOException {
		writeSpells(player, SpellHelper.clientSpells, inputStream);
	}
	
	void writeSpells(Player player, PlayerSpellData data, DataInputStream inputStream) throws IOException {
		data.updateSpells(spellsArray);
		data.updatePassives(passivesArray);
		data.forceSelect(selectedSpell);
		
		if(data.isClientSided()) {
			int size = inputStream.readInt();
			for(int i = 0; i < size; i++) {
				short spell = inputStream.readShort();
				int cooldown = inputStream.readInt();
				data.mapCooldown(spell, cooldown);
			}
		}
	}

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		data.writeByte(this.data.getSpellSelected());
		data.writeByte(this.data.getSpells().length);
		for(short s : this.data.getSpells())
			data.writeShort(s);
		data.writeByte(this.data.getPassives().length);
		for(short s : this.data.getPassives())
			data.writeShort(s);
				
		if(!this.data.isClientSided()) {
			data.writeInt(this.data.spellCooldowns.size());
			for(short s : this.data.spellCooldowns.keySet()) {
				data.writeShort(s);
				data.writeInt(this.data.spellCooldowns.get(s));
			}
		}
		return stream;
	}

	@Override
	public boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) throws IOException {
		if(subchannel.equals(getSubchannel())) {
			ByteArrayInputStream stream = new ByteArrayInputStream(packet.data);
			DataInputStream inputStream = new DataInputStream(stream);
			skipSubchannel(inputStream);

			ElementalTinkerer.proxy.initClientSpells();
			
			selectedSpell = inputStream.readByte();
			
			List<Short> spells = new ArrayList();
			List<Short> passives = new ArrayList();
			
			byte spellSize =  inputStream.readByte();
			for(int i = 0; spellSize > 0 && i < spellSize; i++)
					spells.add(inputStream.readShort());
			
			byte passiveSize =  inputStream.readByte();
			for(int i = 0; passiveSize > 0 && i < passiveSize; i++)
					passives.add(inputStream.readShort());
			
			spellsArray = new short[spells.size()];
			for(int i = 0; i < spells.size(); i++)
				spellsArray[i] = spells.get(i);
			
			passivesArray = new short[passives.size()];
			for(int i = 0; i < passives.size(); i++)
				passivesArray[i] = passives.get(i);
			
			writeSpells(player, inputStream);
			return true;
		}
		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_SPELLS;
	}

}
