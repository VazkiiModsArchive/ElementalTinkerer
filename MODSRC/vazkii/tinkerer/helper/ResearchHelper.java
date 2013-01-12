/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 5 Jan 2013
package vazkii.tinkerer.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.network.packet.PacketResearchData;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.research.ResearchLibrary;
import vazkii.tinkerer.research.ResearchNode;
import cpw.mods.fml.common.network.Player;

/**
 * ResearchHelper
 *
 * Helper class to help managing research.
 *
 * @author Vazkii
 */
public final class ResearchHelper {

	/** The string array descriptions correspondent to the research names **/
	private static Map<String, String[]> descriptions = new HashMap();

	/** The map that maps the player research instances to each
	 * player **/
	public static Map<String, PlayerResearch> researchForPlayers = new HashMap();

	/** Used only in the client, where the research for the client is stored,
	 * the data here is recieved from packets **/
	public static PlayerResearch clientResearch;

	public static void readResearchDescriptions() {
		InputStream stream = ElementalTinkerer.class.getResourceAsStream(ResourcesReference.RESEARCH_DATA_FILE);
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
			List<String> lineList = new ArrayList<String>();
			String line = null;
			String currentResearch = "";
			while ((line = bufferedReader.readLine()) != null) {
				if(line.startsWith("#")) {
					descriptions.put(currentResearch, lineList.toArray(new String[lineList.size()]));
					currentResearch = "";
					lineList.clear();
					continue;
				}
				if(line.endsWith(":")) {
					currentResearch = line.substring(0, line.length() - 1);
					continue;
				}
				lineList.add(line);
			}
			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] getDesciptionForResearch(ResearchNode node) {
		String label = node.label;
		if(descriptions.containsKey(label))
			return descriptions.get(label);

		return new String[0];
	}

	/** Gets the research data for the player username passed in **/
	public static PlayerResearch getResearchDataForPlayer(String player) {
		if(!ConfigurationHandler.sharedResearch.equals(ResearchReference.CONFIG_SHARE_WILDCARD))
			player = ConfigurationHandler.sharedResearch;

		if(researchForPlayers.containsKey(player))
			return researchForPlayers.get(player);

		PlayerResearch data = new PlayerResearch(player);
		researchForPlayers.put(player, data);
		return getResearchDataForPlayer(player);
	}

	public static boolean hasReadResearch = false;

	/** Handles the login of a player, syncing the research data, or creating
	 * it if it doesn't exist **/
	public static void handlePlayerLogin(EntityPlayer player) {
		// Is the player a new player, if so, init their data
		boolean isPlayerNew = ConfigurationHandler.sharedResearch.equals(ResearchReference.CONFIG_SHARE_WILDCARD) && !researchForPlayers.containsKey(player);

		// Cheap way of reading the research with a valid world instance
		if(!hasReadResearch) {
			loadAllResearchData(player.worldObj);
			hasReadResearch = true;
		}

		if(isPlayerNew)
			updateResearchForPlayer(player);
		syncResearchData(player);
	}

	/** Updates the research for this player, saves it to NBT. **/
	public static void updateResearchForPlayer(EntityPlayer player) {
		updateResearch(player.worldObj, getResearchDataForPlayer(player.username));
	}

	/** Sends a packet with the research data of a player to
	 * said player. **/
	public static void syncResearchData(EntityPlayer player) {
		PacketResearchData dataPacket = new PacketResearchData(getResearchDataForPlayer(player.username));
		PacketHelper.sendPacketToClient((Player) player, dataPacket);
	}

	/** Just updates the research and writes it to NBT,
	 * syncResearchData is in duty of sending the packets **/
	public static void updateResearch(World world, PlayerResearch research) {
		NBTTagCompound cmp = IOHelper.getPlayerWorldCache(world, research.playerLinkedTo);
		writeResearchToNBT(world, cmp, research);
	}

	public static void writeResearchToNBT(World world, NBTTagCompound cmp, PlayerResearch research) {
		NBTTagCompound researchCmp = cmp.hasKey(ResearchReference.COMPOUND_TAG_NAME) ? cmp.getCompoundTag(ResearchReference.COMPOUND_TAG_NAME) : new NBTTagCompound();
		for(Short i : ResearchLibrary.allNodes.keySet()) {
			ResearchNode node = ResearchLibrary.allNodes.get(i);
			researchCmp.setBoolean("r" + node.index, research.isResearchDone(node.index));
			researchCmp.setBoolean("c" + node.index, research.isResearchCompleted(node.index));
		}
		cmp.setCompoundTag(ResearchReference.COMPOUND_TAG_NAME, researchCmp);
		IOHelper.updatePlayerNBTTagCompound(world, research.playerLinkedTo, cmp);
	}

	public static void readResearchDataFromNBT(NBTTagCompound cmp, PlayerResearch research) {
		if(cmp.hasKey(ResearchReference.COMPOUND_TAG_NAME)) {
			NBTTagCompound subCmp = cmp.getCompoundTag(ResearchReference.COMPOUND_TAG_NAME);
			for(Short i : ResearchLibrary.allNodes.keySet()) {
				boolean hasRsc = subCmp.hasKey("r" + i);
				if(!hasRsc || !subCmp.getBoolean("r" + i))
					continue;

				research.research(i, false, null);

				boolean hasCmp = subCmp.hasKey("c" + i);
				if(!hasCmp || !subCmp.getBoolean("c" + i))
					continue;

				research.completeResearch(i, false, null);
			}
		}

  	}

	/** Loads all the research data, looking at the various research data folders **/
	public static void loadAllResearchData(World world) {
		File cacheFolder = new File(IOHelper.getWorldDirectory(world), ResourcesReference.WORLD_CACHE_FOLDER);
		for(File file : cacheFolder.listFiles()) {
			if(file.isDirectory() && file.getName().startsWith(ResourcesReference.WORLD_PLAYER_CACHE_FOLDER_PREFIX)) {
				File subFile = new File(file, ResourcesReference.CACHE_FILE_NAME);
				if(subFile.exists()) {
					NBTTagCompound cmp = IOHelper.getTagCompoundInFile(subFile);
					String playerName = file.getName().replaceAll(ResourcesReference.WORLD_PLAYER_CACHE_FOLDER_PREFIX, "");
					PlayerResearch research = getResearchDataForPlayer(playerName);
					readResearchDataFromNBT(cmp, research);
				}
			}
		}
	}
}
