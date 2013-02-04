/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Jan 2013
package vazkii.tinkerer.block;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.helper.LightningHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.item.ElementalTinkererItems;
import vazkii.tinkerer.lightning.Vector3;
import vazkii.tinkerer.reference.EffectReference;
import vazkii.tinkerer.reference.GameReference;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * BlockElementiumGem
 *
 * The Elementium Gem Storage block. It is colored, but based
 * on block updates. That is intended. As to create an unstable
 * behaviour.
 *
 * @author Vazkii
 */
public class BlockElementiumGem extends BlockET {

	public BlockElementiumGem(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_ELEMENTIUM_GEM, Material.iron);
	}

    @Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        if (par5 > 0 && Block.blocksList[par5].canProvidePower()) {
            boolean var6 = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);

            if (var6)
                par1World.scheduleBlockUpdate(par2, par3, par4, blockID, 1);
        }
    }

    @Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
    	if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4)) {
        		if(!par1World.isRemote) {
        			int glowstoneFound = 0;
        			int lampsFound = 0;
        			for(int x = 0; x < 3; x++)
        				for(int y = 0; y < 3; y++)
        					for(int z = 0; z < 3; z++) {
        						if(par1World.getBlockId(par2 - 1 + x, par3 - 1 + y, par4 - 1 + z) == Block.glowStone.blockID) {
        							par1World.setBlockWithNotify(par2 - 1 + x, par3 - 1 + y, par4 - 1 + z, 0);
        							glowstoneFound += 1;
        						}
        						if(par1World.getBlockId(par2 - 1 + x, par3 - 1 + y, par4 - 1 + z) == Block.redstoneLampIdle.blockID || par1World.getBlockId(par2 - 1 + x, par3 - 1 + y, par4 - 1 + z) == Block.redstoneLampActive.blockID) {
        							par1World.setBlockWithNotify(par2 - 1 + x, par3 - 1 + y, par4 - 1 + z, 0);
        							glowstoneFound += 1;
        							lampsFound += 1;
        						}
        					}
        			par1World.setBlockWithNotify(par2, par3, par4, 0);

        			ItemStack stack = new ItemStack(ElementalTinkererItems.elementiumDust, GameReference.ELEMENTIUM_DUST_PER_BLOCK * glowstoneFound + GameReference.ELEMENTIUM_DUST_PER_LAMP * lampsFound);
        			EntityItem item = new EntityItem(par1World, par2, par3, par4, stack);
        			item.delayBeforeCanPickup = 10;
        			int power = (int) (glowstoneFound * GameReference.EXPLOSION_MULTIPLIER_GLOWSTONE + lampsFound + GameReference.EXPLOSION_MULTIPLIER_LAMP);
        			par1World.createExplosion(null, par2, par3, par4, power, true);
        			par1World.spawnEntityInWorld(item);

        			List<EntityPlayer> playerList = par1World.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(par2-8, par3-8, par4-8, par2+8, par3+8, par4+8));
        			for(EntityPlayer player : playerList)
        				ResearchHelper.formulateResearchNode(ResearchReference.ID_ELEMENTIUM_INGOT, player, ResearchReference.CATEGORY_NAME_PURE);
        		
        			LightningHelper.spawnAndSyncLightningBolt(par1World, new Vector3(par2, par3, par4), new Vector3(par2, par3 + power, par4), EffectReference.LIGHTNING_BOLT_SPEED_REACTION, par5Random.nextLong(), EffectReference.LIGHTNING_COLOR_REACTION_OUTER, EffectReference.LIGHTNING_COLOR_REACTION_INNER);
        			LightningHelper.spawnAndSyncLightningBolt(par1World, new Vector3(par2, par3, par4), new Vector3(par2 + power, par3 + power / 4, par4), EffectReference.LIGHTNING_BOLT_SPEED_REACTION, par5Random.nextLong(), EffectReference.LIGHTNING_COLOR_REACTION_OUTER, EffectReference.LIGHTNING_COLOR_REACTION_INNER);
        			LightningHelper.spawnAndSyncLightningBolt(par1World, new Vector3(par2, par3, par4), new Vector3(par2 - power, par3 + power / 4, par4), EffectReference.LIGHTNING_BOLT_SPEED_REACTION, par5Random.nextLong(), EffectReference.LIGHTNING_COLOR_REACTION_OUTER, EffectReference.LIGHTNING_COLOR_REACTION_INNER);
        			LightningHelper.spawnAndSyncLightningBolt(par1World, new Vector3(par2, par3, par4), new Vector3(par2, par3 + power / 4, par4 + power), EffectReference.LIGHTNING_BOLT_SPEED_REACTION, par5Random.nextLong(), EffectReference.LIGHTNING_COLOR_REACTION_OUTER, EffectReference.LIGHTNING_COLOR_REACTION_INNER);
        			LightningHelper.spawnAndSyncLightningBolt(par1World, new Vector3(par2, par3, par4), new Vector3(par2, par3 + power / 4, par4 - power), EffectReference.LIGHTNING_BOLT_SPEED_REACTION, par5Random.nextLong(), EffectReference.LIGHTNING_COLOR_REACTION_OUTER, EffectReference.LIGHTNING_COLOR_REACTION_INNER);
        		}
        }
    }

	@Override
	public int getBlockColor() {
		return !ConfigurationHandler.elementiumGemSpectrum ? 0xD83DFF :
			   Color.getHSBColor((float) Math.cos((double) ClientTickHandler.elapsedClientTicks / ResourcesReference.SPECTRUM_DIVISOR_ELEMENTIUM_GEM), 0.6F, 1F).getRGB();
	}

	@Override
	public int colorMultiplier(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
		return getBlockColor();
	}

	@Override
	public int getRenderColor(int par1) {
		return getBlockColor();
	}
}
