/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.reference;

import net.minecraft.potion.Potion;

/**
 * EntityReference
 *
 * Reference for Entity behaviour and internal constants.
 *
 * @author Vazkii
 */
public final class EntityReference {

	/** Internal names for the entities **/
	public static final String NAME_ELEMENTIUM_GUARDIAN = AnnotationConstants.MOD_ID + "_elementalGuardian",
							   NAME_FIREBALL = AnnotationConstants.MOD_ID + "_fireball",
							   NAME_FROSTBOLT = AnnotationConstants.MOD_ID + "_frostbolt",
							   NAME_BOULDER = AnnotationConstants.MOD_ID + "_boulder",
							   NAME_FLAME_RING = AnnotationConstants.MOD_ID + "_flameRing",
							   NAME_ELEMENTIUM_LOCATOR = AnnotationConstants.MOD_ID + "_elementiumLocator";

	/** Internal Local IDs for the entities **/
	public static final int LOCAL_ID_FIREBALL = 0,
							LOCAL_ID_FROSTBOLT = 1,
							LOCAL_ID_BOULDER = 2,
							LOCAL_ID_FLAME_RING = 3,
							LOCAL_ID_ELEMENTIUM_LOCATOR = 4;

	/** Display Names for the entities **/
	public static final String DISPLAY_NAME_ELEMENTIUM_GUARDIAN = "Elementium Guardian";

	/** Spawn Egg Background Colors **/
	public static final int SPAWN_EGG_BG_COLOR_ELEMENTIUM_GUARDIAN = 0xD83DFF;

	/** Spawn Egg Foreground Colors **/
	public static final int SPAWN_EGG_FG_COLOR_ELEMENTIUM_GUARDIAN = 0xCDA8F2;

	/** Mob Health Values **/
	public static final int MOB_HEALTH_ELEMENTIUM_GUARDIAN = 18;

	/** Mob Attack Strenght Values **/
	public static final int DMG_ELEMENTIUM_GUARDIAN = 3,
							DMG_FIREBALL = 4,
							DMG_FIREBALL_MAG = 6,
							FIRE_FIREBALL = 3,
							FIRE_FIREBALL_MAG = 5,
							DMG_FROSTBOLT = 1,
							DMG_FROSTBOLT_MAG = 2,
							FREEZE_FROSTBOLT = 3,
							FREEZE_FROSTBOLT_MAG = 5,
							DMG_BOULDER = 6,
							DMG_BOULDER_MAG = 8;

	/** Mob Movement Speeds **/
	public static final float SPEED_ELEMENTIUM_GUARDIAN = 1F;

	/** Other Elementium Guardian Constants **/
	public static final int ELEMENTIUM_GUARDIAN_PARTICLE_COUNT = 4,
							ELEMENTIUM_GUARDIAN_DEBUFF_ID = Potion.digSlowdown.id,
							ELEMENTIUM_GUARDIAN_DEBUFF_LEVEL = 1,
							ELEMENTIUM_GUARDIAN_DEBUFF_TIME = 600;

}
