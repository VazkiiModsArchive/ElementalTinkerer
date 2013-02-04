/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Jan 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.magic.PlayerSpellData;
import vazkii.tinkerer.reference.NetworkReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.tile.container.ContainerElementalistTinkeringAltar;
import cpw.mods.fml.common.network.Player;

/**
 * PacketClientSpells
 *
 * Packet to send the client spells from the client to
 * the server. This insures some verification in the
 * server side.
 *
 * @author Vazkii
 */
public class PacketClientSpells extends PacketSpells {

	public static final PacketClientSpells RECIEVER_INSTANCE = new PacketClientSpells();

	/** Constructor with no params required for the reciever end **/
	private PacketClientSpells() { }
	
	public PacketClientSpells(PlayerSpellData data) {
		super(data);
	}
	
	@Override
	void writeSpells(Player player, DataInputStream inputStream) {
		if(!(player instanceof EntityPlayer))
			return;
		
		EntityPlayer entityPlayer = (EntityPlayer)player;
		String username = entityPlayer.username;
		PlayerResearch data = ResearchHelper.getResearchDataForPlayer(username);
		
		for(short s : spellsArray) {
			if(!SpellHelper.isSpellAvailable(s, data)) 
				return;
		}
				
		for(short s : passivesArray) {
			if(!SpellHelper.isPassiveAvailable(s, data))
				return;
		}
				
		PlayerSpellData spellData = SpellHelper.getSpellDataForPlayer(username);
		writeSpells(player, spellData, inputStream);
		
		PacketSpells spellsPacket = new PacketSpells(spellData);
		PacketHelper.sendPacketToClient(player, spellsPacket);
	}
	
	@Override
	void writeSpells(Player player, PlayerSpellData data, DataInputStream inputStream) {
		data.updateSpells(spellsArray);
		data.updatePassives(passivesArray);
		data.select(selectedSpell);
		SpellHelper.updateSpells(((EntityPlayer)player).worldObj, data);
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_CLIENT_SPELLS;
	}

}
