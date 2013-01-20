/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 20 Jan 2013
package vazkii.tinkerer.block;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * BlockAttuner
 *
 * The Attuner Block, used to set the various spells and
 * abilities per player.
 *
 * @author Vazkii
 */
public class BlockAttuner extends BlockET {

	public BlockAttuner(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_ATTUNER_TOP, Material.iron);
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(par1World.isRemote) {
			Color rgbColor = new Color(Color.HSBtoRGB((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GEM), 0.9F, 0.7F));
    		ElementalTinkerer.proxy.spawnColoredPortalParticle(rgbColor, par1World, par2 + 0.5, par3, par4 + 0.5, 0, 7E-3, 0);
		}
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
	}

	@Override
	public int getBlockTextureFromSide(int par1) {
		return par1 == 0 || par1 == 1 ? super.getBlockTextureFromSide(par1) : ResourcesReference.BLOCK_INDEX_ATTUNER_GLASS;
	}

	@Override
    public boolean isOpaqueCube() {
        return false;
    }

	@Override
    public boolean renderAsNormalBlock() {
        return false;
    }

}
