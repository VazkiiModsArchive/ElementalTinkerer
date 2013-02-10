/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 7 Feb 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.magic.IWand;
import vazkii.tinkerer.reference.NetworkReference;
import cpw.mods.fml.common.network.Player;

/**
 * PacketKeybind
 *
 * A packet indicating a keystroke on the client side.
 *
 * @author Vazkii
 */
public class PacketKeybind extends ETPacket {

	public static final PacketKeybind INSTANCE = new PacketKeybind();

	private PacketKeybind() { }

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		return emptyStream(); // There's only 1 keybind in the mod, no data required
	}

	@Override
	public boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) throws IOException {
		if(subchannel.equals(getSubchannel()) && player instanceof EntityPlayer) {
			EntityPlayer entityPlayer = (EntityPlayer)player;
			ItemStack usingStack = entityPlayer.getCurrentEquippedItem();
			if(usingStack != null) {
				Item usingItem = usingStack.getItem();
				if(usingItem != null && usingItem instanceof IWand)
					((IWand)usingItem).handleKeystroke(entityPlayer, usingStack);
			}
			return true;
		}
		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_KEYBIND;
	}

}
