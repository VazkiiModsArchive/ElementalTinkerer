/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 29 Jan 2013
package vazkii.tinkerer.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * InteractionAccessHandler
 *
 * Class to help with the method Item.itemInteractionForEntity, this method
 * does not pass in the entity that interacted. There is a forge hook for
 * the interactions, and it's used here to help the method to know which
 * player is interacting.
 *
 * @author Vazkii
 */
public class InteractionAccessHandler {
	
	public static final InteractionAccessHandler INSTANCE = new InteractionAccessHandler();
	
	private InteractionAccessHandler() { }
	
	private static EntityPlayer lastInteractingPlayer;
	
	public static EntityPlayer getLastInteractingPlayer() {
		return lastInteractingPlayer;
	}
	
	@ForgeSubscribe
	public void onInteract(PlayerInteractEvent event) {
		lastInteractingPlayer = event.entityPlayer;
	}

}
