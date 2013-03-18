/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Dec 2012
package vazkii.tinkerer.research;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.Icon;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.magic.Spell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**
 * ResearchNode
 *
 * A Node of research. This stores the name, and some other data regarding it,
 * it also allows to check if the node has been researched.
 *
 * @author Vazkii
 */
public class ResearchNode implements Comparable<ResearchNode> {

	@SideOnly(Side.CLIENT)
	public Icon icon;

	/** The object to convert into an icon, can be Item or
	 * Spell and it'll convert accoringly depending on what
	 * it is. If it's null it'll convert using the instance. **/
	public Object iconObj;

	/** For cooperation with iconObj, used as "metadata" for
	 * the IconHelper methods, will be ignored if it's -1 **/
	public int iconInteger = -1;

	public String label, displayName;
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

	public ResearchNode(short index, String label, String displayName, ResearchType type) {
		this.label = label;
		this.displayName = displayName;
		this.index = index;
		this.type = type;
		requirement = -1;
	}

	@SideOnly(Side.CLIENT)
	public Icon bindIcon() {
		IconRegister ir = MiscHelper.getMc().renderEngine.field_94155_m;

		if(iconObj instanceof Item)
			return iconInteger != -1 ? (icon = IconHelper.forItem(ir, (Item) iconObj, iconInteger)) : (icon = IconHelper.forItem(ir, (Item) iconObj));
		else if(iconObj instanceof Spell)
			return iconInteger != -1 ? (icon = IconHelper.forSpell(ir, (Spell) iconObj, iconInteger)) : (icon = IconHelper.forSpell(ir, (Spell) iconObj));

		return icon = IconHelper.forResearch(ir, this);
	}

	public ResearchNode setIconObj(Object icon) {
		iconObj = icon;
		return this;
	}

	public ResearchNode setIconObj(Object icon, int i) {
		iconInteger = i;
		return setIconObj(icon);
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

	/** Binds the latest crafting recipe added to the list **/
	public ResearchNode bindLatestCraftingRecipe() {
		List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
		IRecipe recipe = recipeList.get(recipeList.size() - 1);
		return bindRecipe(recipe);
	}

	/** Binds the latest tinkering recipe added to the list **/
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
		boolean first = !research.isResearchDone(index);
		boolean second = requirement == -1 || research.isResearchCompleted(requirement);

		return first && second;
	}

	public IRecipe getBoundRecipe() {
		return boundRecipe;
	}

	public boolean isBoundRecipeAltarRecipe() {
		return boundRecipe != null && boundRecipe instanceof TinkeringAltarRecipe;
	}
}
