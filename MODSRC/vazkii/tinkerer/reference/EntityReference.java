/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.reference;

import net.minecraft.potion.Potion;

/**
 * MobNames
 *
 * Reference for Entity Names.
 *
 * @author Vazkii
 */
public final class EntityReference {

	/** Internal names for the entities **/
	public static final String NAME_ELEMENTIUM_GUARDIAN = AnnotationConstants.MOD_ID + "_elementalGuardian";

	/** Display Names for the entities **/
	public static final String DISPLAY_NAME_ELEMENTIUM_GUARDIAN = "Elementium Guardian";

	/** Spawn Egg Background Colors **/
	public static final int SPAWN_EGG_BG_COLOR_ELEMENTIUM_GUARDIAN = 0xD83DFF;

	/** Spawn Egg Foreground Colors **/
	public static final int SPAWN_EGG_FG_COLOR_ELEMENTIUM_GUARDIAN = 0xCDA8F2;

	/** Mob Health Values **/
	public static final int MOB_HEALTH_ELEMENTIUM_GUARDIAN = 24;

	/** Mob Attack Strenght Values **/
	public static final int DMG_ELEMENTIUM_GUARDIAN = 3;

	/** Mob Movement Speeds **/
	public static final float SPEED_ELEMENTIUM_GUARDIAN = 1F;

	/** Other Elementium Guardian Constants **/
	public static final int ELEMENTIUM_GUARDIAN_PARTICLE_COUNT = 4,
							ELEMENTIUM_GUARDIAN_DEBUFF_ID = Potion.digSlowdown.id,
							ELEMENTIUM_GUARDIAN_DEBUFF_LEVEL = 1,
							ELEMENTIUM_GUARDIAN_DEBUFF_TIME = 600;

}
