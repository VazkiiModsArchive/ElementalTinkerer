/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Dec 2012
package vazkii.tinkerer.reference;

/**
 * EffectReference
 *
 * Reference for Graphics.
 *
 * @author Vazkii
 */
public final class EffectReference {

	/** Particles emmited by the Elementium Ore block when broken **/
	public static final int ELEMENTIUM_ORE_PARTICLE_COUNT = 12;
	
	/** Colors for the Lightning Bolts **/
	public static final int LIGHTNING_COLOR_REACTION_OUTER = 0xCC00FF,
							LIGHTNING_COLOR_REACTION_INNER = 0xFF00DE,
							LIGHTNING_COLOR_THUNDERBOLT_OUTER = 0x81B6FF,
							LIGHTNING_COLOR_THUNDERBOLT_INNER = 0xFFFFFF;

	/** Speeds for the Lightning Bolts, these speeds are in Ticks/Meter **/
	public static final float LIGHTNING_BOLT_SPEED_REACTION = 1.7F,
							  LIGHTNING_BOLT_SPEED_THUNDERBOLT = 0.25F;
	
	/** Radius of the Spell Circle in screen when holding a wand **/
	public static final int SPELL_CIRCLE_RADIUS = 60;
}
