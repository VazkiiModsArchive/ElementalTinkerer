/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 14 Jan 2013
package vazkii.tinkerer.research;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.tile.container.IDualCraftingInventory;

/**
 * TinkeringAltarRecipes
 *
 * Recipes for the Tinkering altar. These are 5x5 and have 4
 * extra slots for the catalysts.
 *
 * @author Vazkii
 */
public class TinkeringAltarRecipe extends ShapedRecipes {

	public ShapelessRecipes catalystRecipe;
	private ResearchNode node;

	public TinkeringAltarRecipe(int par1, int par2, ItemStack[] par3ArrayOfItemStack, ItemStack par4ItemStack, ShapelessRecipes catalystRecipe) {
		super(par1, par2, par3ArrayOfItemStack, par4ItemStack);
		this.catalystRecipe = catalystRecipe;
	}

	/** Binds a research node to this recipe. Used to check
	 * if the recipe is valid based on the player. **/
	public ResearchNode bindNode(ResearchNode node) {
		if(this.node == null)
			this.node = node;
		return node;
	}

	// Copy of the same method in ShapedRecipes, with the for loop maxing at
	// 5x5 rather than 3x3.
	@Override
    public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World) {
		if(par1InventoryCrafting instanceof IDualCraftingInventory) {
			IDualCraftingInventory dual = (IDualCraftingInventory) par1InventoryCrafting;
			for (int var3 = 0; var3 <= 5 - recipeWidth; ++var3) {
	            for (int var4 = 0; var4 <= 5 - recipeHeight; ++var4) {
	                if (checkMatch(dual.getFirstInventory(), var3, var4, false))
	                    return catalystRecipe.recipeItems.isEmpty() || catalystRecipe.matches(dual.getSecondInventory(), par2World);

	                if(checkMatch(dual.getFirstInventory(), var3, var4, true))
		                return catalystRecipe.recipeItems.isEmpty() || catalystRecipe.matches(dual.getSecondInventory(), par2World);
	            }
	        }
		}

        return false;
    }

	/** Implementation of matches that checks researches. **/
	public boolean matches(InventoryCrafting par1InventoryCrafting, PlayerResearch research, World world) {
		boolean matches = matches(par1InventoryCrafting, world);
		if(!matches)
			return false;

		if(node == null || research == null)
			return true;

		for(Short s : ResearchLibrary.allNodes.keySet()) {
			ResearchNode node = ResearchLibrary.allNodes.get(s);
			if(node.getBoundRecipe() != null && node.isBoundRecipeAltarRecipe() && MiscHelper.areStacksEqualIgnoreSize(node.getBoundRecipe().getRecipeOutput(), getRecipeOutput()))
				return research.isResearchCompleted(s); // A research was found, return true if the player has it
		}

		return true; // A research was not found, return true
	}

	// Copy of the same method in ShapedRecipes (because the method is private
	// and because it maxed at 3x3 rather than 5x5)
    private boolean checkMatch(InventoryCrafting par1InventoryCrafting, int par2, int par3, boolean par4)
    {
        for (int var5 = 0; var5 < 5; ++var5)
        {
            for (int var6 = 0; var6 < 5; ++var6)
            {
                int var7 = var5 - par2;
                int var8 = var6 - par3;
                ItemStack var9 = null;

                if (var7 >= 0 && var8 >= 0 && var7 < recipeWidth && var8 < recipeHeight)
                {
                    if (par4)
                    {
                        var9 = recipeItems[recipeWidth - var7 - 1 + var8 * recipeWidth];
                    }
                    else
                    {
                        var9 = recipeItems[var7 + var8 * recipeWidth];
                    }
                }

                ItemStack var10 = par1InventoryCrafting.getStackInRowAndColumn(var5, var6);

                if (var10 != null || var9 != null)
                {
                    if (var10 == null && var9 != null || var10 != null && var9 == null)
                    {
                        return false;
                    }

                    if (var9.itemID != var10.itemID)
                    {
                        return false;
                    }

                    if (var9.getItemDamage() != -1 && var9.getItemDamage() != var10.getItemDamage())
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /** Adds a recipe, works similar to the CraftingManager recipe method, but
     * the first arguments of the object array are ItemStacks portraiting the
     * catalyst items required for the recipe to be considered valid. **/
    public static ShapedRecipes registerRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;
    	List<ItemStack> catalysts = new ArrayList();

        if (par2ArrayOfObj[var4] instanceof String[])
        {
            String[] var7 = (String[])par2ArrayOfObj[var4++];

            for (int var8 = 0; var8 < var7.length; ++var8)
            {
                String var9 = var7[var8];
                ++var6;
                var5 = var9.length();
                var3 = var3 + var9;
            }
        }
        else
        {
        	while(par2ArrayOfObj[var4] instanceof ItemStack)
        		catalysts.add((ItemStack) par2ArrayOfObj[var4++]);

            while (par2ArrayOfObj[var4] instanceof String)
            {
                String var11 = (String)par2ArrayOfObj[var4++];
                ++var6;
                var5 = var11.length();
                var3 = var3 + var11;
            }
        }

        HashMap var12;

        for (var12 = new HashMap(); var4 < par2ArrayOfObj.length; var4 += 2)
        {
            Character var13 = (Character)par2ArrayOfObj[var4];
            ItemStack var14 = null;

            if (par2ArrayOfObj[var4 + 1] instanceof Item)
            {
                var14 = new ItemStack((Item)par2ArrayOfObj[var4 + 1]);
            }
            else if (par2ArrayOfObj[var4 + 1] instanceof Block)
            {
                var14 = new ItemStack((Block)par2ArrayOfObj[var4 + 1], 1, -1);
            }
            else if (par2ArrayOfObj[var4 + 1] instanceof ItemStack)
            {
                var14 = (ItemStack)par2ArrayOfObj[var4 + 1];
            }

            var12.put(var13, var14);
        }

        ItemStack[] var15 = new ItemStack[var5 * var6];

        for (int var16 = 0; var16 < var5 * var6; ++var16)
        {
            char var10 = var3.charAt(var16);

            if (var12.containsKey(Character.valueOf(var10)))
            {
                var15[var16] = ((ItemStack)var12.get(Character.valueOf(var10))).copy();
            }
            else
            {
                var15[var16] = null;
            }
        }

        ShapelessRecipes catalystRecipe = new ShapelessRecipes(null, catalysts);
        TinkeringAltarRecipe var17 = new TinkeringAltarRecipe(var5, var6, var15, par1ItemStack, catalystRecipe);
        ResearchLibrary.recipes.add(var17);
        return var17;
    }
}
