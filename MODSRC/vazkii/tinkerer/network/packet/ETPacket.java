/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 28 Dec 2012
package vazkii.tinkerer.network.packet;

import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * ETPacket
 *
 * Not really a packet, it's more of a class that creates and reads packet
 * 250s with data passed in.
 *
 * @author Vazkii
 */
public abstract class ETPacket {
	
	public ETPacket() { 
		doNothing(this);
	}
	
	/** Returns in the instance as a custom payload, ready to be sent. **/
	public abstract Packet250CustomPayload asCustomPayload();
	
	/** Tries to read the packet, returns false if it's not intended to read
	 *  this type of packet, true if it read it, stopping the execution of
	 *  further attempts to pass in the packet **/
	public abstract boolean readPayload(Packet250CustomPayload packet);

	public static void doNothing(ETPacket packet) { }
	
}
