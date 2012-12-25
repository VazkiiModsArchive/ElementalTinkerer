/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.entity;

import vazkii.tinkerer.reference.EntityReference;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * ElementalTinkererEntities
 *
 * This class initializes the entities for the mod.
 *
 * @author Vazkii
 */
public final class ElementalTinkererEntities {
	
	public static void init() {
		// Register entity IDs, passing in classes, names and spawn egg colors
		EntityRegistry.registerGlobalEntityID(EntityElementiumGuardian.class, 
											  EntityReference.NAME_ELEMENTIUM_GUARDIAN, 
											  EntityRegistry.findGlobalUniqueEntityId(),
											  EntityReference.SPAWN_EGG_BG_COLOR_ELEMENTIUM_GUARDIAN,
											  EntityReference.SPAWN_EGG_FG_COLOR_ELEMENTIUM_GUARDIAN);
		
		// Register the entity's names, mostly used for the Spawn Eggs
		LanguageRegistry.instance().addStringLocalization("entity." + EntityReference.NAME_ELEMENTIUM_GUARDIAN + ".name", 
														  EntityReference.DISPLAY_NAME_ELEMENTIUM_GUARDIAN);
	}

}
