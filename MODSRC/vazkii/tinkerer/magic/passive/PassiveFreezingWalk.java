/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Feb 2013
package vazkii.tinkerer.magic.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveFreezingWalk
 *
 * The Freezing Walk passive.
 *
 * @author Vazkii
 */
public class PassiveFreezingWalk extends PassiveImpl {

	public PassiveFreezingWalk() {
		super(SpellReference.PID_FREEZING_WALK,
				SpellReference.LABEL_FREEZING_WALK,
				SpellReference.DISPLAY_NAME_FREEZING_WALK,
				ResourcesReference.MAGIC_INDEX_FREEZING_WALK,
				Element.WATER.ordinal());
		bindNode(ResearchReference.ID_FREEZING_WALK);
	}

	@Override
	public void cast(EntityPlayer player) {
		if(player.isInWater() || player.isSneaking())
			return;

		for(int xO = 0; xO < 3; xO++) //xOffset
			for(int zO = 0; zO < 3; zO++) { //yOffset
				int x = (int) player.posX + xO - 1;
				int y = (int) player.posY - 1;
				int z = (int) player.posZ + zO - 1;
				int blockID = player.worldObj.getBlockId(x, y, z);
				int place = -1;

				asignPlace : {
					if(blockID == Block.waterStill.blockID || blockID == Block.waterMoving.blockID) {
						place = Block.ice.blockID;
						break asignPlace;
					}

					if(blockID == Block.lavaStill.blockID) {
						place = Block.obsidian.blockID;
						break asignPlace;
					}

					if(blockID == Block.lavaMoving.blockID)
						place = Block.cobblestone.blockID;
				}

				if(place != -1)
					player.worldObj.setBlockWithNotify(x, y, z, place);
			}
	}
}