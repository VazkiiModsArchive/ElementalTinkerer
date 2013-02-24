/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 2 Feb 2013
package vazkii.tinkerer.simpleanim;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.WorldGenRates;

/**
 * ElementiumDetectorCycle
 *
 * Cycle for the elementium detector cycle, checks if there's
 * elementium ore above or below the player, if so, allows for
 * the cycle to continue.
 *
 * @author Vazkii
 */
public class ElementiumDetectorCycle extends RestrictedIntegerCycle {

	public ElementiumDetectorCycle(int startIndex, int finalIndex, int ticks) {
		super(startIndex, finalIndex, ticks);
		flagUpDown();
	}

	@Override
	boolean canRestartCycle() {
		EntityPlayer player = MiscHelper.getClientPlayer();
		if(player == null)
			return false;

		boolean foundElementium = false;

		int x = (int) Math.round(player.posX);
		int y = (int) Math.floor(player.posY);
		int z = (int) Math.round(player.posZ);

		int min = y - WorldGenRates.ELEMENTIUM_LOCATOR_RANGE;
		int max = y + WorldGenRates.ELEMENTIUM_LOCATOR_RANGE;

		if(min > WorldGenRates.ELEMENTIUM_ORE_HEIGHT_MAX || max < WorldGenRates.ELEMENTIUM_ORE_HEIGHT_MIN) {
			return false; // Completely out of range, just forget it
		}

		for(int i = min; i < max; i++) {
			if(i < WorldGenRates.ELEMENTIUM_ORE_HEIGHT_MIN)
				continue; //Too low, maybe go a bit upper?

			if(i > WorldGenRates.ELEMENTIUM_ORE_HEIGHT_MAX)
				break; // Too high, out of range, just kill the loop

			if(player.worldObj.getBlockId(x, i, z) == BlockIDs.elementiumOre) {
				foundElementium = true;
				break;
			}
		}

		return foundElementium;
	}
}
