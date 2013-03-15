/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import vazkii.tinkerer.gui.GuiAttuner;
import vazkii.tinkerer.gui.GuiDislocator;
import vazkii.tinkerer.gui.GuiElementalDesk;
import vazkii.tinkerer.gui.GuiElementalistLexiconIndex;
import vazkii.tinkerer.gui.GuiElementalistTinkeringAltar;
import vazkii.tinkerer.gui.GuiIncinerator;
import vazkii.tinkerer.gui.GuiScavenger;
import vazkii.tinkerer.gui.GuiVoidNetwork;
import vazkii.tinkerer.gui.GuiWaveInputter;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.tile.TileEntityDislocator;
import vazkii.tinkerer.tile.TileEntityElementalDesk;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;
import vazkii.tinkerer.tile.TileEntityIncinerator;
import vazkii.tinkerer.tile.TileEntityScavenger;
import vazkii.tinkerer.tile.TileEntityVoidNetwork;
import vazkii.tinkerer.tile.TileEntityWaveInputter;
import vazkii.tinkerer.tile.container.ContainerDislocator;
import vazkii.tinkerer.tile.container.ContainerElementalDesk;
import vazkii.tinkerer.tile.container.ContainerElementalistTinkeringAltar;
import vazkii.tinkerer.tile.container.ContainerIncinerator;
import vazkii.tinkerer.tile.container.ContainerScavenger;
import vazkii.tinkerer.tile.container.ContainerWaveInputter;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * GuiHandler
 *
 * Handler for the GUIs in the mod. This class constructs
 * the containers or guis depending on the side.
 *
 * @author Vazkii
 */
public class GuiHandler implements IGuiHandler {

	public static final GuiHandler INSTANCE = new GuiHandler();

	private GuiHandler() { }

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case GuiIDs.ID_ELEMENTAL_DESK :
			return new ContainerElementalDesk(player.inventory, (TileEntityElementalDesk) world.getBlockTileEntity(x, y, z));

		case GuiIDs.ID_ELEMENTALIST_TINKERING_ALTAR :
			return new ContainerElementalistTinkeringAltar(player.inventory, (TileEntityElementalTinkeringAltar) world.getBlockTileEntity(x, y, z));

		case GuiIDs.ID_WAVE_INPUTTER :
			return new ContainerWaveInputter((TileEntityWaveInputter) world.getBlockTileEntity(x, y, z), player.inventory);

		case GuiIDs.ID_DISLOCATOR :
			return new ContainerDislocator((TileEntityDislocator) world.getBlockTileEntity(x, y, z), player.inventory);

		case GuiIDs.ID_SCAVENGER :
			return new ContainerScavenger((TileEntityScavenger) world.getBlockTileEntity(x, y, z), player.inventory);

		case GuiIDs.ID_INCINERATOR :
			return new ContainerIncinerator((TileEntityIncinerator) world.getBlockTileEntity(x, y, z), player.inventory);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case GuiIDs.ID_ELEMENTAL_DESK :
			return new GuiElementalDesk(player.inventory, (TileEntityElementalDesk) world.getBlockTileEntity(x, y, z));

		case GuiIDs.ID_ELEMENTALIST_LEXICON :
			return new GuiElementalistLexiconIndex();

		case GuiIDs.ID_ELEMENTALIST_TINKERING_ALTAR :
			return new GuiElementalistTinkeringAltar((TileEntityElementalTinkeringAltar) world.getBlockTileEntity(x, y, z), player);

		case GuiIDs.ID_ATTUNER :
			return new GuiAttuner();

		case GuiIDs.ID_VOID_NETWORK :
			return new GuiVoidNetwork((TileEntityVoidNetwork) world.getBlockTileEntity(x, y, z));

		case GuiIDs.ID_WAVE_INPUTTER :
			return new GuiWaveInputter((TileEntityWaveInputter) world.getBlockTileEntity(x, y, z), player);

		case GuiIDs.ID_DISLOCATOR :
			return new GuiDislocator((TileEntityDislocator) world.getBlockTileEntity(x, y, z), player);

		case GuiIDs.ID_SCAVENGER :
			return new GuiScavenger((TileEntityScavenger) world.getBlockTileEntity(x, y, z), player);

		case GuiIDs.ID_INCINERATOR :
			return new GuiIncinerator((TileEntityIncinerator) world.getBlockTileEntity(x, y, z), player);
		}

		return null;
	}



}
