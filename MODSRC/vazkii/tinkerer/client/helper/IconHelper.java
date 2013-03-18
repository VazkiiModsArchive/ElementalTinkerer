/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 17 Mar 2013
package vazkii.tinkerer.client.helper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import vazkii.tinkerer.magic.Spell;
import vazkii.tinkerer.magic.SpellLibrary;
import vazkii.tinkerer.reference.AnnotationConstants;
import vazkii.tinkerer.research.ResearchLibrary;
import vazkii.tinkerer.research.ResearchNode;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
	@SideOnly(Side.CLIENT)
	public static TextureMap researchSprites,
			   				 spellSprites;

	@SideOnly(Side.CLIENT)
	public static Icon[] researchGameBackgroundIcons = new Icon[6];

	@SideOnly(Side.CLIENT)
	public static Icon researchUnknownIcon,
					   researchPendingIcon,
					   spellBackgroundIcon,
					   spellFrameIcon;

	@SideOnly(Side.CLIENT)
	private static Icon NULL; // Empty Icon!

	@SideOnly(Side.CLIENT)
	public static void initCustonSpritesheets() {
		BufferedImage missingno = new BufferedImage(64, 64, 2);
		Graphics graphics = missingno.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 64, 64);
        graphics.setColor(Color.BLACK);
        int x = 10;
        int y = 0;
        while (x < 64){
            String s = y++ % 2 == 0 ? "missing" : "texture";
            graphics.drawString(s, 1, x);
            x += graphics.getFont().getSize();

            if (y % 2 == 0)
                x += 5;
        }
        graphics.dispose();

		researchSprites = new TextureMap(2, "research", "textures/research", missingno);
		spellSprites = new TextureMap(3, "spell", "textures/magic", missingno);

		// Init Research Sprites
		for(Short s : ResearchLibrary.allNodes.keySet()) {
			ResearchNode node = ResearchLibrary.allNodes.get(s);
			if(node != null)
				node.bindIcon();
		}

		for(int i = 0; i < 6; i++)
			researchGameBackgroundIcons[i] = forName(researchSprites, "bg" + i);
		researchUnknownIcon = forName(researchSprites, "unknown");
		researchPendingIcon = forName(researchSprites, "pending");

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
		spellBackgroundIcon = forName(spellSprites, "hudBackground");
		spellFrameIcon = forName(spellSprites, "hudFrame");

		researchSprites.func_94247_b();
		spellSprites.func_94247_b();
		//VAZ_TODO These don't work
	}


	@SideOnly(Side.CLIENT)
	public static Icon NULL(IconRegister ir) {
		if(NULL == null)
			NULL = ir.func_94245_a(AnnotationConstants.MOD_ID + ":null");
		return NULL;
	}

	@SideOnly(Side.CLIENT)
	public static Icon forName(IconRegister ir, String name) {
		return ir.func_94245_a(AnnotationConstants.MOD_ID + ":" + name);
	}

	@SideOnly(Side.CLIENT)
	public static Icon forBlock(IconRegister ir, Block block) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile.", ""));
	}

	@SideOnly(Side.CLIENT)
	public static Icon forBlock(IconRegister ir, Block block, int i) {
		return forName(ir, block.getUnlocalizedName().replaceAll("tile.", "") + i);
	}

	@SideOnly(Side.CLIENT)
	public static Icon forItem(IconRegister ir, Item item) {
		return forName(ir, item.getUnlocalizedName().replaceAll("item.", ""));
	}

	@SideOnly(Side.CLIENT)
	public static Icon forItem(IconRegister ir, Item item, int i) {
		return forName(ir, item.getUnlocalizedName().replaceAll("item.", "") + i);
	}

	@SideOnly(Side.CLIENT)
	public static Icon forResearch(IconRegister ir, ResearchNode node) {
		return forName(ir, node.label);
	}

	@SideOnly(Side.CLIENT)
	public static Icon forResearch(IconRegister ir, ResearchNode node, int i) {
		return forName(ir, node.label + i);
	}

	@SideOnly(Side.CLIENT)
	public static Icon forSpell(IconRegister ir, Spell spell) {
		return forName(ir, spell.label);
	}

	@SideOnly(Side.CLIENT)
	public static Icon forSpell(IconRegister ir, Spell spell, int i) {
		return forName(ir, spell.label + i);
	}

	public static class UnboundIcon {

		public static enum Spritesheet {
			ITEM,
			BLOCK,
			RESEARCH,
			MAGIC;
		}

		Spritesheet type;
		Object obj;

		int mod = -1;

		public UnboundIcon(Spritesheet type, Object obj) {
			this(type, obj, -1);
		}

		public UnboundIcon(Spritesheet type, Object obj, int mod) {
			this.type = type;
			this.obj = obj;
			this.mod = mod;
		}

		public Spritesheet getSpritesheet() {
			return type;
		}

		public Object getObject() {
			return obj;
		}

		@SideOnly(Side.CLIENT)
		public Icon asIcon(IconRegister ir) {
			switch(type) {
				case ITEM :
					if(obj instanceof Item)
						return mod != -1 ? forItem(ir, (Item) obj) : forItem(ir, (Item) obj, mod);

				case BLOCK :
					if(obj instanceof Block)
						return mod != -1 ? forBlock(ir, (Block) obj) : forBlock(ir, (Block) obj, mod);

				case RESEARCH :
					if(obj instanceof ResearchNode)
						return mod != -1 ? forResearch(ir, (ResearchNode) obj) : forResearch(ir, (ResearchNode) obj, mod);

				case MAGIC :
					if(obj instanceof Short)
						return mod != -1 ? forSpell(ir, (Spell) obj) : forSpell(ir, (Spell) obj, mod);
			}
			return NULL(ir);
		}

	}
}
