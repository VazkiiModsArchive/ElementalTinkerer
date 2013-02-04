/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 29 Jan 2013
package vazkii.tinkerer.magic;

import vazkii.tinkerer.reference.PotionReference;
import vazkii.tinkerer.reference.ResourcesReference;
import net.minecraft.potion.Potion;

/**
 * ElementalTinkererPotions
 *
 * The class that holds the potion effects in the mod.
 *
 * @author Vazkii
 */
public class ElementalTinkererPotions {

	public static Potion frozen;
	
	public static void initPotions() {
		// Construct the potions
		frozen = new PotionET(PotionReference.idFrozen, 
							  false,
							  0x8BCFFB)
							  .setPotionName(PotionReference.DISPLAY_NAME_FROZEN)
							  .setIconIndex(ResourcesReference.POTION_INDEX_FROZEN);
	}
}
