/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Jan 2013
package vazkii.tinkerer.magic;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.research.PlayerResearch;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Spell
 *
 * The abstract class of a spell.
 *
 * @author Vazkii
 */
public abstract class Spell {

	private short boundNode = -1;

	public short index;
	public String label, displayName;
	public int element;

	@SideOnly(Side.CLIENT)
	public Icon icon;

	public Spell(short index, String label, String displayName, int element) {
		this.label = label;
		this.displayName = displayName;
		this.index = index;
		this.element = element;
	}

	@SideOnly(Side.CLIENT)
	public void bindIcon() {
		icon = IconHelper.forSpell(IconHelper.spellSprites, this);
	}

	public Spell bindNode(short node) {
		if(boundNode == -1)
			boundNode = node;
		return this;
	}

	public short getBoundNode() {
		return boundNode;
	}

	public boolean isAvailable(PlayerResearch research) {
		return boundNode == -1 || research.isResearchCompleted(boundNode);
	}

	public int getCooldown(EntityPlayer player) {
		return 0;
	}

	public boolean isPassive() {
		return false;
	}

	public abstract SpellType getSpellType();

	/** Called on right click for active spells, on release for
	 * charge spells, every tick for passive spells and every
	 * tick while holding for channelled spells. The bonus field
	 * is whether the correct wand for the spell is being used,
	 * this is always false for spells in the pure branch or
	 * passives. **/
	public abstract boolean cast(EntityPlayer player, boolean bonus);

	public abstract boolean castOnEntity(EntityPlayer player, boolean bonus, EntityLiving entity);

	public abstract boolean castOnBlock(EntityPlayer player, boolean bonus, int x, int y, int z);
}
