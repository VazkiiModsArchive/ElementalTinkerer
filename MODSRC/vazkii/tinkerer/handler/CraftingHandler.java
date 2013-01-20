/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 19 Jan 2013
package vazkii.tinkerer.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.item.ItemCatalyst;
import vazkii.tinkerer.item.ItemWand;
import vazkii.tinkerer.reference.ResearchReference;
import cpw.mods.fml.common.ICraftingHandler;

/**
 * CraftingHandler
 *
 * A Handler for Crafting items. At the moment all
 * it does is check if an item is a catalyst item
 * and if so create the wand research.
 *
 * @author Vazkii
 */
public class CraftingHandler implements ICraftingHandler {

	public static final CraftingHandler INSTANCE = new CraftingHandler();

	private CraftingHandler() { }

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
		if(item != null && item.getItem() != null) {
			if(item.getItem() instanceof ItemCatalyst) {
				int element = ItemCatalyst.getElement(item.getItemDamage());
				ResearchHelper.formulateResearchNode((short) (ResearchReference.ID_WAND_START + element), player, Element.getName(element));
			} else if(item.getItem() instanceof ItemWand) {
				ResearchHelper.formulateResearchNode(ResearchReference.ID_ATTUNER, player, ResearchReference.CATEGORY_NAME_PURE);
			}
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		// NO-OP
	}

}
