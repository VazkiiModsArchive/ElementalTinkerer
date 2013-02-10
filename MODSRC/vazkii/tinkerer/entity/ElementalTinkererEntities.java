/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.entity;

import vazkii.tinkerer.ElementalTinkerer;
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

		EntityRegistry.registerGlobalEntityID(EntityFireball.class,
											  EntityReference.NAME_FIREBALL,
											  EntityRegistry.findGlobalUniqueEntityId());

		EntityRegistry.registerGlobalEntityID(EntityFrostBolt.class,
				  							  EntityReference.NAME_FROSTBOLT,
				  							  EntityRegistry.findGlobalUniqueEntityId());

		EntityRegistry.registerGlobalEntityID(EntityBoulder.class,
											  EntityReference.NAME_BOULDER,
											  EntityRegistry.findGlobalUniqueEntityId());

		EntityRegistry.registerGlobalEntityID(EntityFlameRing.class,
				  							  EntityReference.NAME_FLAME_RING,
				  							  EntityRegistry.findGlobalUniqueEntityId());

		// Register the Entities as mod entities
		EntityRegistry.registerModEntity(EntityFireball.class,
										 EntityReference.NAME_FIREBALL,
										 EntityReference.LOCAL_ID_FIREBALL,
										 ElementalTinkerer.instance,
										 64, 10, true);

		EntityRegistry.registerModEntity(EntityFrostBolt.class,
										 EntityReference.NAME_FROSTBOLT,
										 EntityReference.LOCAL_ID_FROSTBOLT,
										 ElementalTinkerer.instance,
										 64, 10, true);

		EntityRegistry.registerModEntity(EntityBoulder.class,
										 EntityReference.NAME_BOULDER,
										 EntityReference.LOCAL_ID_BOULDER,
										 ElementalTinkerer.instance,
										 64, 10, true);

		EntityRegistry.registerModEntity(EntityFlameRing.class,
										 EntityReference.NAME_FLAME_RING,
										 EntityReference.LOCAL_ID_FLAME_RING,
										 ElementalTinkerer.instance,
										 32, 40, false);

		// Register the entity's names, mostly used for the Spawn Eggs
		LanguageRegistry.instance().addStringLocalization("entity." + EntityReference.NAME_ELEMENTIUM_GUARDIAN + ".name",
														  EntityReference.DISPLAY_NAME_ELEMENTIUM_GUARDIAN);
	}

}
