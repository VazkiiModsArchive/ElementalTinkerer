/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Dec 2012
package vazkii.tinkerer.research;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;


/**
 * ResearchNode
 *
 * A Node of research. This stores the name, and some other data regarding it,
 * it also allows to check if the node has been researched.
 *
 * @author Vazkii
 */
public class ResearchNode implements Comparable<ResearchNode> {

	public String spritesheet, label, displayName;
	public int spriteIndex;
	public short index;
	public ResearchType type;

	/** The Node required for this one to be researched **/
	public short requirement;

	/** If this is set to true, then it will be instantly added
	 * to the list of acquired researches **/
	private boolean isDefaultEnabled = false;

	/** If this is set to true, the recipe can not be researched
	 * trough elemental books. **/
	private boolean noBook = false;

	/** What recipe this research is bound to, this is used
	 * to render a crafting grid in the elementalist's lexicon
	 * gui and to check if a recipe can be done in the tinkering
	 * altar. **/
	private IRecipe boundRecipe;

	/** The item that represents this research. This item will be
	 * used when checking for researches' item recipes. **/
	private ItemStack iconicItem;

	public ResearchNode(short index, String spritesheet, String label, String displayName, int spriteIndex, ResearchType type) {
		this.spritesheet = spritesheet;
		this.label = label;
		this.displayName = displayName;
		this.spriteIndex = spriteIndex;
		this.index = index;
		this.type = type;
		requirement = -1;
	}

	public ResearchNode setIconicItem(ItemStack stack) {
		iconicItem = stack;
		return this;
	}

	public ResearchNode setNoBook() {
		noBook = true;
		return this;
	}

	public ResearchNode setDefaultEnabled() {
		isDefaultEnabled = true;
		return this;
	}

	public ResearchNode addToCategory(ResearchCategory category) {
		return category.addNode(this);
	}

	public ResearchNode bindRecipe(IRecipe recipe) {
		boundRecipe = recipe;

		if(recipe instanceof TinkeringAltarRecipe)
			return ((TinkeringAltarRecipe) recipe).bindNode(this);

		return this;
	}

	public ResearchNode bindLatestCraftingRecipe() {
		List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
		IRecipe recipe = recipeList.get(recipeList.size() - 1);
		return bindRecipe(recipe);
	}

	public ResearchNode bindLatestTinkeringRecipe() {
		List<TinkeringAltarRecipe> recipeList = ResearchLibrary.recipes;
		TinkeringAltarRecipe recipe = recipeList.get(recipeList.size() - 1);
		return bindRecipe(recipe);
	}

	public ResearchNode setRequirement(short s) {
		requirement = s;
		return this;
	}

	@Override
	public int compareTo(ResearchNode o) {
		return Integer.compare(index, o.index);
	}

	public boolean isNoBook() {
		return noBook;
	}

	public boolean isDefaultEnabled() {
		return isDefaultEnabled;
	}

	public ItemStack getIconicItem() {
		return iconicItem;
	}

	public boolean isAvailable(PlayerResearch research) {
		return !research.isResearchDone(index) && (requirement == -1 || research.isResearchCompleted(requirement));
	}

	public IRecipe getBoundRecipe() {
		return boundRecipe;
	}

	public boolean isBoundRecipeAltarRecipe() {
		return boundRecipe instanceof TinkeringAltarRecipe;
	}
}
