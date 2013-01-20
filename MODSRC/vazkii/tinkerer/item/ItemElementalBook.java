/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Dec 2012
package vazkii.tinkerer.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.research.ResearchCategory;
import vazkii.tinkerer.research.ResearchLibrary;
import vazkii.tinkerer.research.ResearchNode;

/**
 * ItemElementalBook
 *
 * Elemental book item.
 *
 * @author Vazkii
 */
public class ItemElementalBook extends ItemET {

	public ItemElementalBook(int par1) {
		super(par1);
		setMaxStackSize(1);
		setHasSubtypes(true);
		iconIndex = ResourcesReference.ITEM_INDEX_ELEMENTAL_BOOK_START;
	}

	@Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
    	if(!par2World.isRemote) {
    		short s = findReadyResearch(par3EntityPlayer, par1ItemStack.getItemDamage());
    		if(s >= 0) {
    			ResearchHelper.formulateResearchNode(s, par3EntityPlayer, Element.getName(par1ItemStack.getItemDamage()));
    			par1ItemStack.stackSize--;
    		} else {
    			PacketHelper.sendMessageToPlayer(par3EntityPlayer, FormattingCode.RED + "There seems to be nothing this book can teach you.");

    		}
    	}
    	return par1ItemStack;
    }

	/** Finds a research valid for researching. If it can't find any, it
	 * returns -1. **/
	public short findReadyResearch(EntityPlayer player, int element) {
		element += 2;
		ResearchCategory category = ResearchLibrary.categories.get((byte) element);
		List<ResearchNode> availableNodes = new ArrayList();
		PlayerResearch research = ResearchHelper.getResearchDataForPlayer(player.username);
		for(ResearchNode node : category.getNodes())
			if(node.isAvailable(research) && !node.isNoBook())
				availableNodes.add(node);

		if(availableNodes.isEmpty())
			return -1;

		ResearchNode node = availableNodes.get(player.worldObj.rand.nextInt(availableNodes.size()));
		return node.index;
	}

	@Override
	public ItemStack getContainerItemStack(ItemStack itemStack) {
		return itemStack;
	}

	@Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
        return false;
    }

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public int getIconFromDamage(int par1) {
		return super.getIconFromDamage(par1) + (par1 <= 3 ? par1 : 0);
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return ItemNames.ELEMENT_BOOK_NAME_PREFIX + Element.getSuffix(par1ItemStack.getItemDamage());
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		super.getSubItems(par1, par2CreativeTabs, par3List);
		for(int i = 1; i < 4; i++)
			par3List.add(new ItemStack(par1, 1, i));
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

}
