/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 7 Feb 2013
package vazkii.tinkerer.client.handler;

import java.util.EnumSet;
import java.util.logging.Level;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.network.packet.PacketKeybind;
import vazkii.tinkerer.reference.AnnotationConstants;
import vazkii.tinkerer.reference.SpellReference;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * KeybindHandler
 *
 * Handler for the Mod's keybind (change spells).
 *
 * @author Vazkii
 */
@SideOnly(Side.CLIENT)
public class KeybindHandler extends KeyHandler {

	// This has to be registed during the mod init state, not statically
	public static KeybindHandler INSTANCE;

	public static KeyBinding keybind = new KeyBinding(SpellReference.KEYBIND_NAME, Keyboard.KEY_C);

	public static KeybindHandler createInstance() {
		if(INSTANCE != null) {
			ElementalTinkerer.logger.log(Level.WARNING, "There is already an instance of the KeybindHandler!");
			throw new IllegalArgumentException();
		}

		return new KeybindHandler();
	}

	private KeybindHandler() {
		super(new KeyBinding[] { keybind }, new boolean[]{ false });
	}

	@Override
	public String getLabel() {
		return AnnotationConstants.MOD_ID;
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if(kb == keybind && !tickEnd)
			PacketHelper.sendPacketToServer(PacketKeybind.INSTANCE);
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		// NO-OP
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
		// Render ticks fire more often, with client ticks it tends to not
		// react here and there...
	}

}
