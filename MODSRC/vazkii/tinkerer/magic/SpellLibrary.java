/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Jan 2013
package vazkii.tinkerer.magic;

import java.util.Map;
import java.util.TreeMap;

import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.magic.spell.SpellBoulderToss;
import vazkii.tinkerer.magic.spell.SpellFireball;
import vazkii.tinkerer.magic.spell.SpellFrostbolt;
import vazkii.tinkerer.magic.spell.SpellImpl;
import vazkii.tinkerer.magic.spell.SpellThunderbolt;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

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
		
		// Aerial Push Spell
		registerSpell(new SpellImpl(SpellReference.ID_AEREAL_PUSH, 
					SpellReference.LABEL_AEREAL_PUSH,
					SpellReference.DISPLAY_NAME_AEREAL_PUSH, 
					ResourcesReference.MAGIC_INDEX_AEREAL_PUSH, 
					SpellType.ACTIVE,
					Element.AIR.ordinal())
					.bindNode(ResearchReference.ID_AEREAL_PUSH));
		
		// Frostshock Spell
		registerSpell(new SpellImpl(SpellReference.ID_FROSTSHOCK, 
					SpellReference.LABEL_FROSTSHOCK,
					SpellReference.DISPLAY_NAME_FROSTSHOCK, 
					ResourcesReference.MAGIC_INDEX_FROSTSHOCK, 
					SpellType.ACTIVE,
					Element.WATER.ordinal())
					.bindNode(ResearchReference.ID_FROSTSHOCK));
		
		// Implosion Spell
		registerSpell(new SpellImpl(SpellReference.ID_IMPLOSION, 
					SpellReference.LABEL_IMPLOSION,
					SpellReference.DISPLAY_NAME_IMPLOSION, 
					ResourcesReference.MAGIC_INDEX_IMPLOSION, 
					SpellType.ACTIVE,
					Element.EARTH.ordinal())
					.bindNode(ResearchReference.ID_IMPLOSION));
		
		// Flame Ring Spell
		registerSpell(new SpellImpl(SpellReference.ID_FLAME_RING, 
					SpellReference.LABEL_FLAME_RING,
					SpellReference.DISPLAY_NAME_FLAME_RING, 
					ResourcesReference.MAGIC_INDEX_FLAME_RING, 
					SpellType.ACTIVE,
					Element.FIRE.ordinal())
					.bindNode(ResearchReference.ID_FLAME_RING));
	}
	
	public static void registerSpell(Spell spell) {
		allSpells.put(spell.index, spell);
	}
}
