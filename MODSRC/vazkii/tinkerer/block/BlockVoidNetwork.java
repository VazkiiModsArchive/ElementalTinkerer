/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Mar 2013
package vazkii.tinkerer.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.tile.TileEntityVoidNetwork;
import cpw.mods.fml.common.network.PacketDispatcher;

/**
 * BlockVoidNetwork
 *
 * The Void Network block.
 *
 * @author Vazkii
 */
public class BlockVoidNetwork extends BlockETContainer {

	public BlockVoidNetwork(int par1) {
		super(par1, Material.iron);
	}

	Icon[] icons = new Icon[2];

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < 2; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return par1 == 1 ? icons[1] : icons[0];
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEntityVoidNetwork tile = (TileEntityVoidNetwork)par1World.getBlockTileEntity(par2, par3, par4);

		if(tile != null) {
			boolean did = false;
			if(tile.addGem(par5EntityPlayer.getHeldItem()))
				did = true;

			if(did) {
				int current = par5EntityPlayer.inventory.currentItem;
				ItemStack stack = par5EntityPlayer.inventory.getCurrentItem();
				stack.stackSize--;
				if(stack.stackSize == 0)
					par5EntityPlayer.inventory.setInventorySlotContents(current, null);
				PacketDispatcher.sendPacketToAllPlayers(tile.getDescriptionPacket());
			} else
				par5EntityPlayer.openGui(ElementalTinkerer.instance, GuiIDs.ID_VOID_NETWORK, par1World, par2, par3, par4);

			return true;
		}

		return false;
	}

	@Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        // Particle code, the same as ender chests.
		for (int var6 = 0; var6 < 6; ++var6) {
            double var9 = par3 + par5Random.nextFloat();
            double var13 = 0.0D;
            double var15 = 0.0D;
            double var17 = 0.0D;
            int var19 = par5Random.nextInt(2) * 2 - 1;
            int var20 = par5Random.nextInt(2) * 2 - 1;
            var13 = (par5Random.nextFloat() - 0.5D) * 0.125D;
            var15 = (par5Random.nextFloat() - 0.5D) * 0.125D;
            var17 = (par5Random.nextFloat() - 0.5D) * 0.125D;
            double var11 = par4 + 0.5D + 0.25D * var20;
            var17 = par5Random.nextFloat() * 1.0F * var20;
            double var7 = par2 + 0.5D + 0.25D * var19;
            var13 = par5Random.nextFloat() * 1.0F * var19;
            par1World.spawnParticle("portal", var7, var9, var11, var13, var15, var17);
        }
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityVoidNetwork();
	}

}
