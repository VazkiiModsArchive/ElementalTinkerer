/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.block;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.client.handlers.ClientTickHandler;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.EffectReference;
import vazkii.tinkerer.reference.EntityReference;
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.simpleanim.SimpleAnimations;

/**
 * BlockElementiumOre
 *
 * Elementium Ore Block. This class handles sprite index, drops and the like.
 *
 * @author Vazkii
 */
public class BlockElementiumOre extends BlockET {

	public BlockElementiumOre(int par1) {
		super(par1, ResourcesReference.BLOCK_ANIM_ELEMENTIUM_ORE_START, Material.rock);
	}
	
	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
		if(par1World.isRemote) {
			Color rgbColor = new Color(Color.HSBtoRGB((float) Math.cos(((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GUARDIAN)), 0.9F, 0.7F));
            for(int i = 0; i < EffectReference.ELEMENTIUM_ORE_PARTICLE_COUNT; i++)
    			ElementalTinkerer.instance.proxy.spawnColoredPortalParticle(rgbColor, par1World, par2, par3+0.5, par4, par1World.rand.nextFloat(), 7E-3, par1World.rand.nextFloat());
		}
		
		super.onBlockClicked(par1World, par2, par3, par4, par5EntityPlayer);
	}
	 
	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {
		if(par1World.isRemote) {
			Color rgbColor = new Color(Color.HSBtoRGB((float) Math.cos(((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GUARDIAN)), 0.9F, 0.7F));
            for(int i = 0; i < EffectReference.ELEMENTIUM_ORE_PARTICLE_COUNT; i++)
    			ElementalTinkerer.instance.proxy.spawnColoredPortalParticle(rgbColor, par1World, par2, par3+0.5, par4, par1World.rand.nextFloat(), 7E-3, par1World.rand.nextFloat());
		}
		
		dropXpOnBlockBreak(par1World, par2, par3, par4, par1World.rand.nextBoolean() ? 1 : 0);
		
		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return blockIndexInTexture + (!ConfigurationHandler.elementiumOreColored ? 0 : par2);
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return ItemIDs.elementiumGem;
	}
	
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		super.getSubBlocks(par1, par2CreativeTabs, par3List);
		
		for(int i = 0; i < 16; i++)
			par3List.add(new ItemStack(par1, 1, i));
	}
	
	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return false;
	}
	
}
