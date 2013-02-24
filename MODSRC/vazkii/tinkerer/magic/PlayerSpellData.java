/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Jan 2013
package vazkii.tinkerer.magic;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.ElementalTinkerer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * PlayerSpellData
 *
 * The Spell Data of a player
 *
 * @author Vazkii
 */
public class PlayerSpellData {

	private short[] passives = new short[0];
	private short[] spells = new short[0];
	private byte selectedSpell = 0;

	public String playerLinkedTo;

	public Map<Short, Integer> spellCooldowns;

	/** Flag if the spell data is a client side instance, if so
	 * it won't send cooldowns as a packet, obviously. A server
	 * side instance will not accept cooldown data in packets at
	 * all either. **/
	private boolean clientSided = false;

	public PlayerSpellData(String player) {
		playerLinkedTo = player;
		spellCooldowns = new TreeMap();
	}

	@SideOnly(Side.CLIENT)
	public void flagClientSided() {
		clientSided = true;
	}

	public boolean isClientSided() {
		return clientSided;
	}

	public byte getSpellSelected() {
		return (byte) Math.max(0, Math.min(spells.length - 1 , selectedSpell));
	}

	public void selectFirstSpell() {
		if(spells.length > 0)
			select((byte) 0);
	}

	public void selectLastSpell() {
		if(spells.length > 0)
			select((byte) (spells.length - 1));
	}

	public void select(byte select) {
		if (select >= spells.length)
			selectFirstSpell(); // Generally passed in when going forward  past the end

		if (select < 0)
			selectLastSpell(); // Generally passed in when going backward past the start

		forceSelect(spells.length == 0 ? -1 : select);
	}

	public void forceSelect(byte select) {
		selectedSpell = select;
		updateTooltip();
	}

	public void updateTooltip() {
		if(selectedSpell >= 0 && spells.length > selectedSpell && SpellLibrary.allSpells.containsKey(spells[selectedSpell])) {
			Spell spell = SpellLibrary.allSpells.get(spells[selectedSpell]);
			String displayName = spell.displayName;
			ElementalTinkerer.proxy.setItemOnScreenTooltip(displayName);
		}
	}

	public void tick(EntityPlayer player) {
		tickPassives(player);
		tickCooldowns();
	}

	public void tickPassives(EntityPlayer player) {
		for(Short s : passives) {
			Spell spell = SpellLibrary.allPassives.get(s);
			if(spell != null && spell.getSpellType() == SpellType.PASSIVE)
				spell.cast(player, false);
		}
	}

	public void tickCooldowns() {
		if(spellCooldowns.isEmpty())
			return;

		Map<Short, Integer> newCooldowns = new TreeMap(spellCooldowns);
		// Map is copied to avoid any possible ConcurrentModificationException
		for(Short s : newCooldowns.keySet()) {
			int i = newCooldowns.get(s);
			if(i <= 0) {
				spellCooldowns.remove(s);
				continue;
			}
			spellCooldowns.put(s, i - 1);
		}
	}

	public boolean canCastSpell(short spell) {
		return !spellCooldowns.containsKey(spell);
	}

	public void mapCooldown(Spell spell, EntityPlayer player) {
		int cooldown = spell.getCooldown(player);
		mapCooldown(spell.index, cooldown);
	}

	public void mapCooldown(short s, int cool) {
		if(cool > 0)
			spellCooldowns.put(s, cool);
		else spellCooldowns.remove(s);
	}

	public short[] getSpells() {
		return spells;
	}

	public short[] getPassives() {
		return passives;
	}

	public void updateSpells(short[] spells) {
		if(spells.length <= 5)
			this.spells = spells;
	}

	public void updatePassives(short[] passives) {
		if(passives.length <= 4)
			this.passives = passives;
	}

	public void addSpell(short spell) {
		boolean has = false;
		for(short s : spells)
			if(s == spell)
				has = true;

		if(has || spells.length == 5)
			return;
		short[] spells = Arrays.copyOf(this.spells, this.spells.length + 1);
		spells[spells.length - 1] = spell;
		updateSpells(spells);
	}

	public void addPassive(short passive) {
		boolean has = false;
		for(short s : passives)
			if(s == passive)
				has = true;

		if(has || passives.length == 5)
			return;
		short[] passives = Arrays.copyOf(this.passives, this.passives.length + 1);
		passives[passives.length - 1] = passive;
		updatePassives(passives);
	}

	public void removeSpell(short spell) {
		boolean has = false;
		for(short s : spells)
			if(s == spell)
				has = true;

		if(!has)
			return;

		short[] spells = new short[this.spells.length-1];
		boolean offset = false;
		for(int i = 0; i < this.spells.length; i++)
			if(this.spells[i] != spell)
				spells[offset ? i - 1 : i] = this.spells[i];
			else offset = true;
		updateSpells(spells);
	}

	public void removePassive(short passive) {
		boolean has = false;
		for(short s : passives)
			if(s == passive)
				has = true;

		if(!has)
			return;

		short[] passives = new short[this.passives.length-1];
		boolean offset = false;
		for(int i = 0; i < this.passives.length; i++)
			if(this.passives[i] != passive)
				passives[offset ? i - 1 : i] = this.passives[i];
			else offset = true;
		updatePassives(passives);
	}
}
