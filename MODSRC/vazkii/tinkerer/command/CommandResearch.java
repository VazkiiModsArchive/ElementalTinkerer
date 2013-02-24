/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 7 Feb 2013
package vazkii.tinkerer.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import vazkii.tinkerer.core.event.ResearchCompleteEvent;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.Commands;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.research.ResearchLibrary;

/**
 * CommandResearch
 *
 * This command researches a specific research name,
 *
 * @author Vazkii
 */
public class CommandResearch extends CommandBase {

	public static final CommandResearch INSTANCE = new CommandResearch();

	private CommandResearch() { }

	@Override
	public String getCommandName() {
		return Commands.COMMAND_RESEARCH;
	}

	@Override
    public int getRequiredPermissionLevel() {
        return 4; // I don't really know what it means, but I assume that by "4" it means OPs only
    }

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if(var2.length == 2 || var2.length == 3) {
			String player = var2[0];
			String research = var2[1];
			boolean doAll = false;
			if(research.equals("*"))
				doAll = true;
			boolean complete = false;
			if(var2.length == 3)
				if(var2[2].equalsIgnoreCase("C") || var2[2].equalsIgnoreCase("COMPLETE"))
					complete = true;

			PlayerResearch researchData = ResearchHelper.getResearchDataForPlayer(player);
			EntityPlayer playerEntity = getCommandSenderAsPlayer(var1);
			World world = playerEntity.worldObj;

			if(doAll) {
				for(Short s : ResearchLibrary.allNodes.keySet()) {
					researchData.research(s, false, world);
					if(complete) {
						researchData.completeResearch(s, false, world);
						MinecraftForge.EVENT_BUS.post(new ResearchCompleteEvent(researchData, s));
					}
				}
				ResearchHelper.updateResearch(world, researchData);
 			} else {
				int i = parseInt(var1, research);
				// If it's not a valid integer it would have broken the method already
				if(ResearchLibrary.allNodes.containsKey((short)i)) {
					researchData.research((short) i, !complete, world);
					if(complete) {
						researchData.completeResearch((short) i, true, world);
						MinecraftForge.EVENT_BUS.post(new ResearchCompleteEvent(researchData, (short) i));
					}
				}
			}
			ResearchHelper.syncResearchData(playerEntity);
		} else {
			throw new WrongUsageException("Wrong command usage, try /tinkerer", new Object[0]);
		}
	}

	@Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, MiscHelper.getServer().getAllUsernames()) : null;
    }

	@Override
    public boolean isUsernameIndex(int par1) {
        return par1 == 0;
    }

}
