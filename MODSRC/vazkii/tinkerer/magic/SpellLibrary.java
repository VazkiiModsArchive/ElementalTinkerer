/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Jan 2013
package vazkii.tinkerer.magic;

import java.util.Map;
import java.util.TreeMap;

import net.minecraftforge.common.MinecraftForge;
import vazkii.tinkerer.magic.passive.PassiveBloodBoil;
import vazkii.tinkerer.magic.passive.PassiveBurningCloud;
import vazkii.tinkerer.magic.passive.PassiveEnderAbsorption;
import vazkii.tinkerer.magic.passive.PassiveExtendedBreath;
import vazkii.tinkerer.magic.passive.PassiveFreezingWalk;
import vazkii.tinkerer.magic.passive.PassiveInateSpeed;
import vazkii.tinkerer.magic.passive.PassiveIronskin;
import vazkii.tinkerer.magic.passive.PassiveNatureAura;
import vazkii.tinkerer.magic.passive.PassiveRainAccumulation;
import vazkii.tinkerer.magic.passive.PassiveUndershirt;
import vazkii.tinkerer.magic.passive.handler.EnderParticleDropHandler;
import vazkii.tinkerer.magic.passive.handler.PlayerDamageHandler;
import vazkii.tinkerer.magic.spell.SpellAerialPush;
import vazkii.tinkerer.magic.spell.SpellBoulderToss;
import vazkii.tinkerer.magic.spell.SpellFireball;
import vazkii.tinkerer.magic.spell.SpellFlameRing;
import vazkii.tinkerer.magic.spell.SpellFrostbolt;
import vazkii.tinkerer.magic.spell.SpellFrostshock;
import vazkii.tinkerer.magic.spell.SpellGuillotine;
import vazkii.tinkerer.magic.spell.SpellImplosion;
import vazkii.tinkerer.magic.spell.SpellShatteringRecall;
import vazkii.tinkerer.magic.spell.SpellThunderbolt;
/**
 * SpellLibrary
 *
 * Class containing all the spells.
 *
 * @author Vazkii
 */
public final class SpellLibrary {

	public static Map<Short, Spell> allSpells = new TreeMap();

	public static Map<Short, PassiveSpell> allPassives = new TreeMap();

	public static void initSpells() {
		registerSpell(new SpellThunderbolt());
		registerSpell(new SpellFrostbolt());
		registerSpell(new SpellBoulderToss());
		registerSpell(new SpellFireball());
		registerSpell(new SpellAerialPush());
		registerSpell(new SpellFrostshock());
		registerSpell(new SpellImplosion());
		registerSpell(new SpellFlameRing());
		registerSpell(new SpellGuillotine());
		registerSpell(new SpellShatteringRecall());

		registerPassive(new PassiveExtendedBreath());
		registerPassive(new PassiveRainAccumulation());
		registerPassive(new PassiveNatureAura());
		registerPassive(new PassiveBurningCloud());
		registerPassive(new PassiveUndershirt());
		registerPassive(new PassiveInateSpeed());
		registerPassive(new PassiveFreezingWalk());
		registerPassive(new PassiveIronskin());
		registerPassive(new PassiveBloodBoil());
		registerPassive(new PassiveEnderAbsorption());

		MinecraftForge.EVENT_BUS.register(PlayerDamageHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(EnderParticleDropHandler.INSTANCE);
	}

	public static void registerSpell(Spell spell) {
		if(!(spell instanceof PassiveSpell))
			allSpells.put(spell.index, spell);
	}

	public static void registerPassive(PassiveSpell spell) {
		allPassives.put(spell.index, spell);
	}
}
