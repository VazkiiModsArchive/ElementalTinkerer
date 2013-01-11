/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 5 Jan 2013
package vazkii.tinkerer.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemElementalistLexicon
 *
 * The Elementalist's Lexicon Item, used to open the research GUI.
 *
 * @author Vazkii
 */
public class ItemElementalistLexicon extends ItemET {

	public ItemElementalistLexicon(int par1) {
		super(par1);
		setMaxStackSize(1);
		iconIndex = ResourcesReference.ITEM_INDEX_ELEMENTALIST_LEXICON;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.openGui(ElementalTinkerer.instance, GuiIDs.ID_ELEMENTALIST_LEXICON, par2World, 0, 0, 0);
		return par1ItemStack;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

}
