/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 7 Feb 2013
package vazkii.tinkerer.reference;

/**
 * Commands
 *
 * Reference for the chat commands in the mod.
 *
 * @author Vazkii
 */
public final class Commands {

	/** The prefix of all the commands in the mod **/
	public static final String COMMAND_PREFIX = "tinkerer";

	/** The separator between various tokens in the command **/
	public static final String COMMAND_SEPARATOR = "-";

	public static final String COMMAND_RESEARCH = COMMAND_PREFIX + COMMAND_SEPARATOR + "research",
							   COMMAND_COMPLETE_RESEARCH = COMMAND_RESEARCH + COMMAND_SEPARATOR + "complete";
}
