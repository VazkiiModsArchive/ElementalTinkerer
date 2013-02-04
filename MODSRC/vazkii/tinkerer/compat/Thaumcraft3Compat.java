/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 4 Feb 2013
package vazkii.tinkerer.compat;

import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
import vazkii.tinkerer.item.ItemCatalyst;
import vazkii.tinkerer.reference.ItemIDs;

/**
 * Thaumcraft3Compat
 *
 * Compatibility class for Thaumcraft 3. Thaumcraft 3 is
 * created by azanor and is available here:
 * 
 * http://www.minecraftforum.net/topic/1585216-thaumcraft-303-updated-122013/
 *
 * @author Vazkii
 */
public final class Thaumcraft3Compat {
	
	static final EnumTag[] tagsForMetadata = new EnumTag[] {
		EnumTag.WATER,
		EnumTag.WIND,
		EnumTag.EARTH,
		EnumTag.FIRE
	};

	public static void init() {
		ThaumcraftApi.registerObjectTag(ItemIDs.elementiumGem, -1, new ObjectTags()
					.add(EnumTag.CRYSTAL, 6)
					.add(EnumTag.WATER, 1)
					.add(EnumTag.WIND, 1)
					.add(EnumTag.EARTH, 1)
					.add(EnumTag.FIRE, 1));
		
		ThaumcraftApi.registerObjectTag(ItemIDs.elementiumDust, -1, new ObjectTags()
					.add(EnumTag.MAGIC, 1)
					.add(EnumTag.LIGHT, 1));
		
		for(int i = 0; i < 16; i++) {
			ObjectTags tags = new ObjectTags();
			int level = ItemCatalyst.getLevel(i) + 1;
			int element = ItemCatalyst.getElement(i);
			tags.add(EnumTag.MAGIC, level);
			if(level == 4)
				tags.add(EnumTag.ELDRITCH, 3);
			tags.add(tagsForMetadata[element], level);
			ThaumcraftApi.registerObjectTag(ItemIDs.catalyst, i, tags);
		}
	}
}
