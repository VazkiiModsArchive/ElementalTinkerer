/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 17 Mar 2013
package vazkii.tinkerer.client.helper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.magic.Spell;
import vazkii.tinkerer.magic.SpellLibrary;
import vazkii.tinkerer.reference.AnnotationConstants;
import vazkii.tinkerer.research.ResearchLibrary;
import vazkii.tinkerer.research.ResearchNode;

/**
 * IconHelper
 *
 * Helper for Icons. Icons those being the new render system introduced
 * in the 1.5 update. This class simply helps around the place to
 * generate Icon instances for blocks/items/etc. to use.
 *
 * @author Vazkii
 */
public final class IconHelper {

	public static Icon[] researchGameBackgroundIcons = new Icon[6];

	public static Icon researchUnknownIcon,
					   researchPendingIcon,
					   spellBackgroundIcon,
					   spellFrameIcon;

	private static Icon NULL; // Empty Icon!

	private static boolean initted = false;

	public static void initCustonSpritesheets() {
		if(initted)
			return;
		initted = true;

		TextureMap map = MiscHelper.getMc().renderEngine.textureMapItems;

		// Init Research Sprites
		for(Short s : ResearchLibrary.allNodes.keySet()) {
			ResearchNode node = ResearchLibrary.allNodes.get(s);
			if(node != null)
				node.bindIcon();
		}

		for(int i = 0; i < 6; i++)
			researchGameBackgroundIcons[i] = forName(map, "bg" + i);
		researchUnknownIcon = forName(map, "unknown");
		researchPendingIcon = forName(map, "pending");

		// Init Spell Sprites
		for(Short s : SpellLibrary.allSpells.keySet()) {
			Spell spell = SpellLibrary.allSpells.get(s);
			if(spell != null)
				spell.bindIcon();
		}
		for(Short s : SpellLibrary.allPassives.keySet()) {
			Spell spell = SpellLibrary.allPassives.get(s);
			if(spell != null)
				spell.bindIcon();
		}
		spellBackgroundIcon = forName(map, "hudBackground");
		spellFrameIcon = forName(map, "hudFrame");
	}

	public static Icon NULL(IconRegister ir) {
		if(NULL == null)
			NULL = forName(ir, "null");
		return NULL;
	}

	public static Icon forName(IconRegister ir, String name) {
		return ir.registerIcon(AnnotationConstants.MOD_ID + ":" + name);
	}

	public static Icon forBlock(IconRegister ir, Block block) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile.", ""));
	}

	public static Icon forBlock(IconRegister ir, Block block, int i) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile.", "") + i);
	}

	public static Icon forItem(IconRegister ir, Item item) {
		return forName(ir, item.getUnlocalizedName().replaceAll("item.", ""));
	}

	public static Icon forItem(IconRegister ir, Item item, int i) {
		return forName(ir, item.getUnlocalizedName().replaceAll("item.", "") + i);
	}

	public static Icon forResearch(IconRegister ir, ResearchNode node) {
		return forName(ir, node.label);
	}

	public static Icon forResearch(IconRegister ir, ResearchNode node, int i) {
		return forName(ir, node.label + i);
	}

	public static Icon forSpell(IconRegister ir, Spell spell) {
		return forName(ir, spell.label);
	}

	public static Icon forSpell(IconRegister ir, Spell spell, int i) {
		return forName(ir, spell.label + i);
	}
}
