/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.item;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.EnumHelper;
import vazkii.tinkerer.block.ElementalTinkererBlocks;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.research.ResearchLibrary;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * ElementalTinkererItems
 *
 * This class holds the items for the mod.
 *
 * @author Vazkii
 */
public final class ElementalTinkererItems {

	public static Item elementiumGem,
					   elementalBook,
					   elementalistLexicon,
					   catalyst,
					   elementalBark,
					   wand,
					   elementiumIngot,
					   elementiumDust,
					   elementiumDetector,
					   oddClaw,
					   locationGem,
					   gem,
					   enderParticle,
					   gaseousGlowstone,
					   orichalcum,
					   orichalcumBlade,
					   orichalcumSpade,
					   orichalcumPick,
					   orichalcumHatchet,
					   orichalcumHelmet,
					   orichalcumChestplate,
					   orichalcumPlatelegs,
					   orichalcumBoots,
					   locatingCore,
					   unboundBook,
					   spellbindCloth,
					   mysticalTalisman,
					   attunedTome,
					   roastedCarrot,
					   caramel,
					   darkQuartz;

	public static EnumToolMaterial orichalcumToolMaterial;
	public static EnumArmorMaterial orichalcumArmorMaterial;

	public static void init() {
		// Init the materials
		orichalcumToolMaterial = EnumHelper.addToolMaterial(ItemNames.ORICHALCUM_NAME,
				3, 2650, 8.6F, 3, 26);
		orichalcumArmorMaterial = EnumHelper.addArmorMaterial(ItemNames.ORICHALCUM_NAME,
				52, new int[]{3, 8, 6, 3}, 26);

		// Construct the items
		elementiumGem = new ItemElementiumGem(ItemIDs.elementiumGem).setUnlocalizedName(ItemNames.ELEMENTIUM_GEM_NAME);
		elementalBook = new ItemElementalBook(ItemIDs.elementalBook).setUnlocalizedName(ItemNames.ELEMENT_BOOK_NAME);
		elementalistLexicon = new ItemElementalistLexicon(ItemIDs.elementalistLexicon).setUnlocalizedName(ItemNames.ELEMENTALIST_LEXICON_NAME);
		catalyst = new ItemCatalyst(ItemIDs.catalyst).setUnlocalizedName(ItemNames.CATALYST_ITEM_NAME);
		elementalBark = new ItemET(ItemIDs.elementalBark).setUnlocalizedName(ItemNames.ELEMENTAL_BARK_ITEM_NAME);
		wand = new ItemWand(ItemIDs.wand).setUnlocalizedName(ItemNames.WAND_NAME);
		elementiumIngot = new ItemElementiumIngot(ItemIDs.elementiumIngot).setUnlocalizedName(ItemNames.ELEMENTIUM_INGOT_NAME);
		elementiumDust = new ItemET(ItemIDs.elementiumDust).setUnlocalizedName(ItemNames.ELEMENTIUM_DUST_NAME);
		elementiumDetector = new ItemElementiumDetector(ItemIDs.elementiumDetector).setUnlocalizedName(ItemNames.ELEMENTIUM_DETECTOR_NAME);
		oddClaw = new ItemOddClaw(ItemIDs.oddClaw).setUnlocalizedName(ItemNames.ODD_CLAW_NAME);
		locationGem = new ItemLocationGem(ItemIDs.locationGem).setUnlocalizedName(ItemNames.LOCATION_GEM_NAME);
		gem = new ItemGem(ItemIDs.gem).setUnlocalizedName(ItemNames.GEM_NAME);
		enderParticle = new ItemEnderParticle(ItemIDs.enderParticle).setUnlocalizedName(ItemNames.ENDER_PARTICLE_NAME);
		gaseousGlowstone = new ItemGlowstoneAir(ItemIDs.gaseousGlowstone).setUnlocalizedName(ItemNames.GASEOUS_GLOWSTONE_NAME);
		orichalcum = new ItemET(ItemIDs.orichalcum).setUnlocalizedName(ItemNames.ORICHALCUM_NAME);
		orichalcumBlade = new ItemETSword(ItemIDs.orichalcumBlade, orichalcumToolMaterial).setUnlocalizedName(ItemNames.ORICHALCUM_BLADE_NAME);
		orichalcumSpade = new ItemETTool(ItemIDs.orichalcumSpade, orichalcumToolMaterial, 0).setUnlocalizedName(ItemNames.ORICHALCUM_SPADE_NAME);
		orichalcumPick = new ItemETTool(ItemIDs.orichalcumPick, orichalcumToolMaterial, 1).setUnlocalizedName(ItemNames.ORICHALCUM_PICK_NAME);
		orichalcumHatchet = new ItemETTool(ItemIDs.orichalcumHatchet, orichalcumToolMaterial, 2).setUnlocalizedName(ItemNames.ORICHALCUM_HATCHET_NAME);
		orichalcumHelmet = new ItemOrichalcumArmor(ItemIDs.orichalcumHelmet, orichalcumArmorMaterial, 0).setUnlocalizedName(ItemNames.ORICHALCUM_HELMET_NAME);
		orichalcumChestplate = new ItemOrichalcumArmor(ItemIDs.orichalcumChestplate, orichalcumArmorMaterial, 1).setUnlocalizedName(ItemNames.ORICHALCUM_CHESTPLATE_NAME);
		orichalcumPlatelegs = new ItemOrichalcumArmor(ItemIDs.orichalcumPlatelegs, orichalcumArmorMaterial, 2).setUnlocalizedName(ItemNames.ORICHALCUM_PLATELEGS_NAME);
		orichalcumBoots = new ItemOrichalcumArmor(ItemIDs.orichalcumBoots, orichalcumArmorMaterial, 3).setUnlocalizedName(ItemNames.ORICHALCUM_BOOTS_NAME);
		locatingCore = new ItemLocationCore(ItemIDs.locatingCore).setUnlocalizedName(ItemNames.LOCATING_CORE_NAME);
		unboundBook = new ItemET(ItemIDs.unboundBook).setUnlocalizedName(ItemNames.UNBOUND_BOOK_NAME);
		spellbindCloth = new ItemSpellbindCloth(ItemIDs.spellbindCloth).setUnlocalizedName(ItemNames.SPELLBIND_CLOTH_NAME);
		mysticalTalisman = new ItemMysticalTalisman(ItemIDs.mysticalTalisman).setUnlocalizedName(ItemNames.MYSTICAL_TALISMAN_NAME);
		attunedTome = new ItemAttunedTome(ItemIDs.attunedTome).setUnlocalizedName(ItemNames.ATTUNED_TOME_NAME);
		roastedCarrot = new ItemETFood(ItemIDs.roastedCarrot, 6, 0.4F, false).setUnlocalizedName(ItemNames.ROASTED_CARROT_NAME);
		caramel = new ItemETFood(ItemIDs.caramel, 4, 0.4F, false).setPotionEffect(Potion.moveSpeed.id, 15, 0, 1F).setUnlocalizedName(ItemNames.CARAMEL_NAME);
		darkQuartz = new ItemET(ItemIDs.darkQuartz).setUnlocalizedName(ItemNames.DARK_QUARTZ_NAME);

		// Name the items
		LanguageRegistry.addName(elementiumGem, ItemNames.ELEMENTIUM_GEM_DISPLAY_NAME);
		LanguageRegistry.addName(elementalistLexicon, ItemNames.ELEMENTALIST_LEXICON_DISPLAY_NAME);
		LanguageRegistry.addName(elementalBark, ItemNames.ELEMENTAL_BARK_ITEM_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumIngot, ItemNames.ELEMENTIUM_INGOT_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumDust, ItemNames.ELEMENTIUM_DUST_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumDetector, ItemNames.ELEMENTIUM_DETECTOR_DISPLAY_NAME);
		LanguageRegistry.addName(oddClaw, ItemNames.ODD_CLAW_DISPLAY_NAME);
		LanguageRegistry.addName(locationGem, ItemNames.LOCATION_GEM_DISPLAY_NAME);
		LanguageRegistry.addName(enderParticle, ItemNames.ENDER_PARTICLE_DISPLAY_NAME);
		LanguageRegistry.addName(gaseousGlowstone, ItemNames.GASEOUS_GLOWSTONE_DISPLAY_NAME);
		LanguageRegistry.addName(orichalcum, ItemNames.ORICHALCUM_DISPLAY_NAME);
		LanguageRegistry.addName(orichalcumBlade, ItemNames.ORICHALCUM_BLADE_DISPLAY_NAME);
		LanguageRegistry.addName(orichalcumSpade, ItemNames.ORICHALCUM_SPADE_DISPLAY_NAME);
		LanguageRegistry.addName(orichalcumPick, ItemNames.ORICHALCUM_PICK_DISPLAY_NAME);
		LanguageRegistry.addName(orichalcumHatchet, ItemNames.ORICHALCUM_HATCHET_DISPLAY_NAME);
		LanguageRegistry.addName(orichalcumHelmet, ItemNames.ORICHALCUM_HELMET_DISPLAY_NAME);
		LanguageRegistry.addName(orichalcumChestplate, ItemNames.ORICHALCUM_CHESTPLATE_DISPLAY_NAME);
		LanguageRegistry.addName(orichalcumPlatelegs, ItemNames.ORICHALCUM_PLATELEGS_DISPLAY_NAME);
		LanguageRegistry.addName(orichalcumBoots, ItemNames.ORICHALCUM_BOOTS_DISPLAY_NAME);
		LanguageRegistry.addName(unboundBook, ItemNames.UNBOUND_BOOK_DISPLAY_NAME);
		LanguageRegistry.addName(spellbindCloth, ItemNames.SPELLBIND_CLOTH_DISPLAY_NAME);
		LanguageRegistry.addName(mysticalTalisman, ItemNames.MYSTICAL_TALISMAN_DISPLAY_NAME);
		LanguageRegistry.addName(attunedTome, ItemNames.ATTUNED_TOME_DISPLAY_NAME);
		LanguageRegistry.addName(roastedCarrot, ItemNames.ROASTED_CARROT_DISPLAY_NAME);
		LanguageRegistry.addName(caramel, ItemNames.CARAMEL_DISPLAY_NAME);
		LanguageRegistry.addName(darkQuartz, ItemNames.DARK_QUARTZ_DISPLAY_NAME);

		// Add the items to the researches
		ResearchHelper.setIconicItem(new ItemStack(elementiumGem), ResearchReference.ID_ELEMENTIUM_GEM);
		ResearchHelper.setIconicItem(new ItemStack(elementalBook, 1, -1), ResearchReference.ID_RESEARCH_BOOKS);
		ResearchHelper.setIconicItem(new ItemStack(elementalistLexicon), ResearchReference.ID_ELEMENTALIST_LEXICON);
		for(int i = 0; i < 16; i++)
			ResearchHelper.setIconicItem(new ItemStack(catalyst, 1, i), (short) (ResearchReference.ID_CATALYST_START + i));
		ResearchHelper.setIconicItem(new ItemStack(elementalBark), ResearchReference.ID_ELEMENTAL_BARK);
		for(int i = 0; i < 4; i++)
			ResearchHelper.setIconicItem(new ItemStack(wand, 1, i), (short) (ResearchReference.ID_WAND_START + i));
		ResearchHelper.setIconicItem(new ItemStack(elementiumIngot), ResearchReference.ID_ELEMENTIUM_INGOT);
		ResearchHelper.setIconicItem(new ItemStack(elementiumDust), ResearchReference.ID_ELEMENTIUM_DUST);
		ResearchHelper.setIconicItem(new ItemStack(elementiumDetector), ResearchReference.ID_ELEMENTIUM_DETECTOR);
		ResearchHelper.setIconicItem(new ItemStack(locationGem), ResearchReference.ID_LOCATION_GEM);
		ResearchHelper.setIconicItem(new ItemStack(enderParticle), ResearchReference.ID_ENDER_ABSORPTION);
		ResearchHelper.setIconicItem(new ItemStack(gaseousGlowstone), ResearchReference.ID_GASEOUS_GLOWSTONE);
		ResearchHelper.setIconicItem(new ItemStack(orichalcum), ResearchReference.ID_ORICHALCUM);
		ResearchHelper.setIconicItem(new ItemStack(unboundBook), ResearchReference.ID_UNBOUND_BOOK);
		ResearchHelper.setIconicItem(new ItemStack(locatingCore, 1, 0), ResearchReference.ID_BASIC_CORE_LOCATION);
		ResearchHelper.setIconicItem(new ItemStack(locatingCore, 1, 1), ResearchReference.ID_ADV_CORE_LOCATION);
		ResearchHelper.setIconicItem(new ItemStack(locatingCore, 1, 2), ResearchReference.ID_ULTIMATE_CORE_LOCATION);
		ResearchHelper.setIconicItem(new ItemStack(darkQuartz), ResearchReference.ID_DARK_QUARTZ);
	}

	public static void initItemRecipes() {
		// Elementalist Lexicon Recipe
		CraftingManager.getInstance().addRecipe(new ItemStack(elementalistLexicon),
				" G ", "GBG", " G ",
				'G', elementiumGem,
				'B', Item.book);
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTALIST_LEXICON).bindLatestCraftingRecipe();

		// Elementium Gem Recipe
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(elementiumGem, 9),
				ElementalTinkererBlocks.elementiumGemBlock);

		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(elementiumGem),
				elementiumDust, elementiumDust, Item.diamond);
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTIUM_GEM).bindLatestCraftingRecipe();

		// Elemental Bark Recipe
		CraftingManager.getInstance().addRecipe(new ItemStack(elementalBark),
				" G ", "GLG", " G ",
				'G', elementiumGem,
				'L', new ItemStack(Block.wood, -1));
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTAL_BARK).bindLatestCraftingRecipe();

		// Elementium Ingot Recipe
		CraftingManager.getInstance().addRecipe(new ItemStack(elementiumIngot),
				"DDD", "DID", "DDD",
				'D', elementiumDust,
				'I', Item.ingotGold);
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTIUM_INGOT).bindLatestCraftingRecipe();

		// Catalyst Recipes
		for(int i = 0; i < 4; i++) {
			CraftingManager.getInstance().addShapelessRecipe(new ItemStack(catalyst, 1, i),
					new ItemStack(elementalBook, 1, i), elementiumDust);
			ResearchLibrary.allNodes.get((short) (ResearchReference.ID_CATALYST_START + i)).bindLatestCraftingRecipe();
		}

		// Elementium Detector Recipe
		CraftingManager.getInstance().addRecipe(new ItemStack(elementiumDetector),
				" SG", " SS", "S  ",
				'S', Item.stick,
				'G', elementiumGem);
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTIUM_DETECTOR).bindLatestCraftingRecipe();

		// Unbound Book Recipe
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(unboundBook),
				Item.paper, Item.paper, Item.paper, Item.paper, Item.paper);
		ResearchLibrary.allNodes.get(ResearchReference.ID_UNBOUND_BOOK).bindLatestCraftingRecipe();

		// Orichalcum Items Recipes
		CraftingManager.getInstance().addRecipe(new ItemStack(orichalcumBlade),
				"O", "O", "S",
				'O', ElementalTinkererItems.orichalcum,
				'S', Item.stick);
		CraftingManager.getInstance().addRecipe(new ItemStack(orichalcumSpade),
				"O", "S", "S",
				'O', ElementalTinkererItems.orichalcum,
				'S', Item.stick);
		CraftingManager.getInstance().addRecipe(new ItemStack(orichalcumPick),
				"OOO", " S ", " S ",
				'O', ElementalTinkererItems.orichalcum,
				'S', Item.stick);
		CraftingManager.getInstance().addRecipe(new ItemStack(orichalcumHatchet),
				"OO ", "OS ", " S ",
				'O', ElementalTinkererItems.orichalcum,
				'S', Item.stick);
		CraftingManager.getInstance().addRecipe(new ItemStack(orichalcumHatchet),
				" OO", " SO", " S ",
				'O', ElementalTinkererItems.orichalcum,
				'S', Item.stick);
		CraftingManager.getInstance().addRecipe(new ItemStack(orichalcumHelmet),
				"OOO", "O O",
				'O', ElementalTinkererItems.orichalcum);
		CraftingManager.getInstance().addRecipe(new ItemStack(orichalcumChestplate),
				"O O", "OOO", "OOO",
				'O', ElementalTinkererItems.orichalcum);
		CraftingManager.getInstance().addRecipe(new ItemStack(orichalcumPlatelegs),
				"OOO", "O O", "O O",
				'O', ElementalTinkererItems.orichalcum);
		CraftingManager.getInstance().addRecipe(new ItemStack(orichalcumBoots),
				"O O", "O O",
				'O', ElementalTinkererItems.orichalcum);

		// Roasted Carrot Recipe
		FurnaceRecipes.smelting().addSmelting(Item.carrot.itemID, new ItemStack(ElementalTinkererItems.roastedCarrot), 0.35F);

		// Caramel Recipe
		FurnaceRecipes.smelting().addSmelting(Item.sugar.itemID, new ItemStack(ElementalTinkererItems.caramel), 0.2F);

		// Dark Quartz Recipe
		CraftingManager.getInstance().addRecipe(new ItemStack(darkQuartz, 8),
				"QQQ", "QCQ", "QQQ",
				'Q', Item.field_94583_ca,
				'C', new ItemStack(Item.coal, 1, -1));
		ResearchLibrary.allNodes.get(ResearchReference.ID_DARK_QUARTZ).bindLatestCraftingRecipe();
		CraftingManager.getInstance().addRecipe(new ItemStack(ElementalTinkererBlocks.darkQuartz),
				"QQ", "QQ",
				'Q', darkQuartz);
		CraftingManager.getInstance().addRecipe(new ItemStack(ElementalTinkererBlocks.darkQuartzSlab, 6),
				"QQQ",
				'Q', ElementalTinkererBlocks.darkQuartz);
		CraftingManager.getInstance().addRecipe(new ItemStack(ElementalTinkererBlocks.darkQuartz, 2, 2),
				"Q", "Q",
				'Q', ElementalTinkererBlocks.darkQuartz);
		CraftingManager.getInstance().addRecipe(new ItemStack(ElementalTinkererBlocks.darkQuartz, 1, 1),
				"Q", "Q",
				'Q', ElementalTinkererBlocks.darkQuartzSlab);
		CraftingManager.getInstance().addRecipe(new ItemStack(ElementalTinkererBlocks.darkQuartzStairs, 4),
				"  Q", " QQ", "QQQ",
				'Q', ElementalTinkererBlocks.darkQuartz);
		CraftingManager.getInstance().addRecipe(new ItemStack(ElementalTinkererBlocks.darkQuartzStairs, 4),
				"Q  ", "QQ ", "QQQ",
				'Q', ElementalTinkererBlocks.darkQuartz);
	}
}
