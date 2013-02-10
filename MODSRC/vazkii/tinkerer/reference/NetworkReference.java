/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 28 Dec 2012
package vazkii.tinkerer.reference;

/**
 * NetworkReference
 *
 * Reference for networking based constants.
 *
 * @author Vazkii
 */
public final class NetworkReference {

	/** The mod's channel for the network **/
	public static final String CHANNEL_NAME = "ElementTinkerer";

	/** Subchannels, used by the mod's packet handler to see how to handle the packet
	 * recieved, while still keeping the mod only using one network channel. **/
	public static final String SUBCHANNEL_VERIFY = "verify",
							   SUBCHANNEL_ELEMENTAL_DESK_SYNC = "elementalDeskSync",
							   SUBCHANNEL_RESEARCH = "research",
							   SUBCHANNEL_RESEARCH_COMPLETE = "researchComplete",
							   SUBCHANNEL_ELEMENTAL_TINKERING_ALTAR_SYNC = "altarSync",
							   SUBCHANNEL_ELEMENTAL_TINKERING_START_RECIPE = "altarStartRecipe",
							   SUBCHANNEL_LIGHTNING = "lightning",
							   SUBCHANNEL_SPELLS =  "spells",
							   SUBCHANNEL_CLIENT_SPELLS = "clientSpells",
							   SUBCHANNEL_KEYBIND = "keybind",
							   SUBCHANNEL_WAND_START_USE = "wandStartUsing";
}
