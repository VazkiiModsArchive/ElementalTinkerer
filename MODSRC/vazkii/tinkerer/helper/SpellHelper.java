/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Jan 2013
package vazkii.tinkerer.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.world.World;
import vazkii.tinkerer.magic.PassiveSpell;
import vazkii.tinkerer.magic.PlayerSpellData;
import vazkii.tinkerer.magic.Spell;
import vazkii.tinkerer.magic.SpellLibrary;
import vazkii.tinkerer.network.packet.PacketSpells;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;
import vazkii.tinkerer.research.PlayerResearch;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * SpellHelper
 * 
 * Helper class to help managing spells.
 * 
 * @author Vazkii
 */
public class SpellHelper {

	/** The map that maps the player spell data instances to each player **/
	public static Map<String, PlayerSpellData> spellsForPlayers = new HashMap();
	
	/** Used only in the client, where the spells for the client are stored,
	 * the data here is received from packets **/
	@SideOnly(Side.CLIENT)
	public static PlayerSpellData clientSpells;

	/** Gets the spell data for the player username passed in **/
	public static PlayerSpellData getSpellDataForPlayer(String player) {
		if (spellsForPlayers.containsKey(player))
			return spellsForPlayers.get(player);

		PlayerSpellData data = new PlayerSpellData(player);
		spellsForPlayers.put(player, data);
		return getSpellDataForPlayer(player);
	}
	
	private static boolean hasReadSpells = false;
	
	public static void handlePlayerLogin(EntityPlayer player) {
		// Is the player a new player, if so, init their data
		boolean isPlayerNew = !spellsForPlayers.containsKey(player);

		// Cheap way of reading the spells with a valid world instance
		if(!hasReadSpells) {
			loadAllSpellData(player.worldObj);
			hasReadSpells = true;
		}
		
		if(isPlayerNew)
			updateSpellsForPlayer(player);
		syncSpellData(player);
	}
	
	/** Updates the spell for this player, saves it to NBT. **/
	public static void updateSpellsForPlayer(EntityPlayer player) {
		updateSpells(player.worldObj, getSpellDataForPlayer(player.username));
	}

	/** Sends a packet with the spell data of a player to
	 * said player. **/
	public static void syncSpellData(EntityPlayer player) {
		PacketSpells dataPacket = new PacketSpells(getSpellDataForPlayer(player.username));
		PacketHelper.sendPacketToClient((Player) player, dataPacket);
	}

	/** Just updates the research and writes it to NBT,
	 * syncResearchData is in duty of sending the packets **/
	public static void updateSpells(World world, PlayerSpellData spells) {
		if(spells.isClientSided())
			return; //Avoid writing anything in the client
		
		NBTTagCompound cmp = IOHelper.getPlayerWorldCache(world, spells.playerLinkedTo);
		writeSpellsToNBT(world, cmp, spells);
	}

	public static void writeSpellsToNBT(World world, NBTTagCompound cmp, PlayerSpellData spells) {
		NBTTagCompound spellCmp = cmp.hasKey(SpellReference.COMPOUND_TAG_NAME) ? cmp.getCompoundTag(SpellReference.COMPOUND_TAG_NAME) : new NBTTagCompound();
		for(int i = 0; i < 5; i++) {
			short value = -1;
			if(i < spells.getSpells().length)
				value = spells.getSpells()[i];
			spellCmp.setShort("spell" + i, value);
		}
		
		for(int i = 0; i < 4; i++) {
			short value = -1;
			if(i < spells.getPassives().length)
				value = spells.getPassives()[i];
			spellCmp.setShort("passive" + i, value);
		}
		spellCmp.setByte("selected", spells.getSpellSelected());
		
		NBTTagCompound cooldownCmp = new NBTTagCompound(); // Always create a new compound for the cooldowns
		if(!spells.spellCooldowns.isEmpty()) {
			for(short s : spells.spellCooldowns.keySet()) {
				int cool = spells.spellCooldowns.get(s);
				cooldownCmp.setInteger("cool" + s, cool);
			}
		}
		spellCmp.setCompoundTag(SpellReference.COMPOUND_COOLDOWN_TAG_NAME, cooldownCmp);
		
		cmp.setCompoundTag(SpellReference.COMPOUND_TAG_NAME, spellCmp);
		IOHelper.updatePlayerNBTTagCompound(world, spells.playerLinkedTo, cmp);
	}

	public static void readSpellDataFromNBT(NBTTagCompound cmp, PlayerSpellData spells) {
		if(cmp.hasKey(SpellReference.COMPOUND_TAG_NAME)) {
			NBTTagCompound subCmp = cmp.getCompoundTag(SpellReference.COMPOUND_TAG_NAME);
			ArrayList<Short> spellsList = new ArrayList();
			for(int i = 0; i < 5; i++) {
				short s = subCmp.getShort("spell" + i);
				if(s != -1)
					spellsList.add(s);
			}
			
			ArrayList<Short> passives = new ArrayList();
			for(int i = 0; i < 4; i++) {
				short s = subCmp.getShort("passive" + i);
				if(s != -1)
					passives.add(s);
			}
			
			short[] spellsArray = new short[spellsList.size()];
			for(int i = 0; i < spellsList.size(); i++)
				spellsArray[i] = spellsList.get(i);
			
			short[] passivesArray = new short[passives.size()];
			for(int i = 0; i < passives.size(); i++)
				passivesArray[i] = passives.get(i);
			
			byte selected = subCmp.getByte("selected");
			
			spells.updateSpells(spellsArray);
			spells.updatePassives(passivesArray);
			spells.select(selected);
			
			if(subCmp.hasKey(SpellReference.COMPOUND_COOLDOWN_TAG_NAME)) {
				NBTTagCompound cooldownCmp = subCmp.getCompoundTag(SpellReference.COMPOUND_COOLDOWN_TAG_NAME);
				Collection<NBTBase> tagCollection = cooldownCmp.getTags();
				for(NBTBase nbt : tagCollection) {
					if(nbt instanceof NBTTagInt) {
						String name = nbt.getName();
						String spellIndexStr = name.replaceAll("cool", "");
						short spellIndex = Short.parseShort(spellIndexStr);
						int cooldown = ((NBTTagInt)nbt).data;
						spells.mapCooldown(spellIndex, cooldown);
					}
				}
			}
		}
  	}
	
	/** Loads all the spell data, looking at the various spell data folders **/
	public static void loadAllSpellData(World world) {
		File cacheFolder = new File(IOHelper.getWorldDirectory(world), ResourcesReference.WORLD_CACHE_FOLDER);
		if(cacheFolder.listFiles() != null)
			for(File file : cacheFolder.listFiles()) {
				if(file.isDirectory() && file.getName().startsWith(ResourcesReference.WORLD_PLAYER_CACHE_FOLDER_PREFIX)) {
					File subFile = new File(file, ResourcesReference.CACHE_FILE_NAME);
					if(subFile.exists()) {
						NBTTagCompound cmp = IOHelper.getTagCompoundInFile(subFile);
						String playerName = file.getName().replaceAll(ResourcesReference.WORLD_PLAYER_CACHE_FOLDER_PREFIX, "");
						PlayerSpellData spells = getSpellDataForPlayer(playerName);
						readSpellDataFromNBT(cmp, spells);
					}
				}
			}
	}
	
	/** Gets the available spells from the player research data passed in **/
	public static List<Short> getAvailableSpells(PlayerResearch research) {
		List<Short> spells = new ArrayList();
		for(Short s : SpellLibrary.allSpells.keySet()) {
			Spell spell = SpellLibrary.allSpells.get(s);
			if(spell.isAvailable(research))
				spells.add(s);
		}
		return spells;
	}
	
	/** Gets the available passives from the player research data passed in **/
	public static List<Short> getAvailablePassives(PlayerResearch research) {
		List<Short> passives = new ArrayList();
		for(Short s : SpellLibrary.allPassives.keySet()) {
			PassiveSpell passive = SpellLibrary.allPassives.get(s);
			if(passive.isAvailable(research))
				passives.add(s);
		}
		return passives;
	}
	
	public static boolean isSpellAvailable(short s, PlayerResearch research) {
		Spell spell = SpellLibrary.allSpells.get(s);
		return spell != null && spell.isAvailable(research);
	}
	
	public static boolean isPassiveAvailable(short s, PlayerResearch research) {
		PassiveSpell passive = SpellLibrary.allPassives.get(s);
		return passive != null && passive.isAvailable(research);
	}
}
