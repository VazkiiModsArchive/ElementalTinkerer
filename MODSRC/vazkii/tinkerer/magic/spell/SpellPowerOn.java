/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 8 Apr 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.helper.LightningHelper;
import vazkii.tinkerer.lightning.Vector3;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.EffectReference;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellPowerOn
 *
 * The Power On spell.
 *
 * @author Vazkii
 */
public class SpellPowerOn extends SpellImpl {

	public SpellPowerOn() {
		super(SpellReference.ID_POWER_ON,
				SpellReference.LABEL_POWER_ON,
				SpellReference.DISPLAY_NAME_POWER_ON,
				SpellType.ACTIVE,
				Element.AIR.ordinal());
		bindNode(ResearchReference.ID_POWER_ON);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		int rangeHoriz = bonus ? 3 : 4;
		int rangeVert = 2;

		int playerX = (int) Math.floor(player.posX);
		int playerY = (int) Math.ceil(player.posY);
		int playerZ = (int) Math.floor(player.posZ);

		boolean heated = false;

		for(int x = playerX - rangeHoriz; x < playerX + rangeHoriz; x++)
			for(int z = playerZ - rangeHoriz; z < playerZ + rangeHoriz; z++)
				for(int y = playerY - rangeVert; y < playerY + rangeVert; y++) {
					TileEntity tile = player.worldObj.getBlockTileEntity(x, y, z);
					if(tile != null && tile instanceof TileEntityFurnace) {
						TileEntityFurnace furnace = (TileEntityFurnace) tile;
						LightningHelper.spawnLightningBolt(player.worldObj, Vector3.fromEntityCenter(player), Vector3.fromTileEntityCenter(furnace), EffectReference.LIGHTNING_BOLT_SPEED_THUNDERBOLT, player.worldObj.rand.nextLong(), EffectReference.LIGHTNING_COLOR_THUNDERBOLT_OUTER, EffectReference.LIGHTNING_COLOR_THUNDERBOLT_INNER);
						furnace.furnaceBurnTime += 240;
						BlockFurnace.updateFurnaceBlockState(true, player.worldObj, x, y, z);
						heated = true;
					}
				}

        return heated;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_POWER_ON;
	}

}
