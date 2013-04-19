/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.reference;

/**
 * ResourcesReference
 *
 * Reference for Resource constants.
 *
 * @author Vazkii
 */
public final class ResourcesReference {

	/** Root location for all resources **/
	public static final String ROOT = "/mods/ElementalTinkerer/";

	/** Root location for model texture resources **/
	public static final String ROOT_MODEL = ROOT + "model/";

	/** Root location for entity texture resources **/
	public static final String ROOT_ENTITY = ROOT + "entity/";

	/** Root Location for the lightning resources **/
	public static final String ROOT_LIGHTNING = ROOT + "lightning/";

	/** Root location for gui texture resources **/
	public static final String ROOT_GUI = ROOT + "gui/";

	/** Root location for armor texture resources **/
	public static final String ROOT_ARMOR = ROOT + "armor/";

	/** The Spritehseet containing the potion effect sprites **/
	public static final String POTIONS_SPRITESHEET = ROOT_GUI + "potionEffects.png";

	/** The location of the Texture File for the Elemental Desk Model **/
	public static final String MODEL_TEX_ELEMENTAL_DESK = ROOT_MODEL + "elementalDesk.png";

	/** The Location of the Texture File for the Catalyst Capsule Model **/
	public static final String MODEL_TEX_CATALYST_CAPSULE = ROOT_MODEL + "catalystCapsule.png";

	/** The Location of the Texture File for the Model with the Rotating Cubes **/
	public static final String MODEL_TEX_ROTATING_CUBES = ROOT_MODEL + "rotatingCubes.png";
	
	/** The Location of the Texture File for the Cogwheel Model **/
	public static final String MODEL_TEX_COGWHEEL = ROOT_MODEL + "cogwheel.png";

	/** The location of book textures **/
	public static final String ROOT_BOOK_TEXTURES = ROOT_MODEL + "book";

	/** Textures for the Lightning **/
	public static final String LIGHTNING_INNER_TEXTURE = ROOT_LIGHTNING + "inner.png",
							   LIGHTNING_OUTER_TEXTURE = ROOT_LIGHTNING + "outer.png";

	/** Integers for the Item Spritesheet (32x) Animations **/
	public static final int ANIM_ENDER_PARTICLE_START = 0,
							ANIM_ENDER_PARTICLE_END = 6,
							ANIM_ELEMENTIUM_GEM_START = 0,
							ANIM_ELEMENTIUM_GEM_END = 7,
							ANIM_ELEMENTIUM_INGOT_START = 0,
							ANIM_ELEMENTIUM_INGOT_END = 7;

	/** Integers for the Potion Effect Spritesheet indexes **/
	public static final int POTION_INDEX_FROZEN = 0;

	/** Integers for Animation tick speeds **/
	public static final int ANIM_SPEED_ELEMENTIUM_GEM = 2,
							ANIM_SPEED_ELEMENTIUM_INGOT = 2,
							ANIM_SPEED_ELEMENTIUM_DETECTOR = 1,
							ANIM_SPEED_ENDER_PARTICLE = 3,
							ANIM_MAX_ELEMENTIUM_DETECTOR = 45;

	/** The file that contains the Elementium Guardian Mob Texture **/
	public static final String MOB_ELEMENTAL_GUARDIAN_TEXTURE = ROOT_ENTITY + "oreGuardian.png";

	/** The files that contain the background textures for the guis **/
	public static final String GUI_ELEMENTAL_DESK_TEXTURE = ROOT_GUI + "elementalDesk.png",
							   GUI_ELEMENTALIST_LEXICON_INDEX_TEXTURE = ROOT_GUI + "researchBook.png",
							   GUI_ELEMENTALIST_LEXICON_RESEARCH_TEXTURE = ROOT_GUI + "recipeBook.png",
							   GUI_RESEARCH_GAME_TEXTURE = ROOT_GUI + "researchGame.png",
							   GUI_ELEMENTALIST_TINKERING_ALTAR_TEXTURE = ROOT_GUI + "infusion.png",
							   GUI_ATTUNER_TEXTURE = ROOT_GUI + "attuner.png",
							   GUI_VOID_NETWORK_TEXTURE = ROOT_GUI + "voidNetwork.png",
							   GUI_WAVE_INPUTTER_TEXTURE = ROOT_GUI + "waveInputter.png",
							   GUI_DISLOCATOR_TEXTURE = ROOT_GUI + "dislocator.png",
							   GUI_SCAVENGER_TEXTURE = ROOT_GUI + "scavenger.png",
							   GUI_INCINERATOR_TEXTURE = ROOT_GUI + "incinerator.png";

	/** The files that contain the textures for armors **/
	public static final String ARMOR_ORICHALCUM_TEXTURE = ROOT_ARMOR + "orichalcum.png",
							   ARMOR_ORICHALCUM_LEGS_TEXTURE = ROOT_ARMOR + "orichalcumLegs.png";

	/** The spell backgrounc coordinate on the spell spritesheet **/
	public static final int SPELL_BACKGROUND_X_COORD = 240,
							SPELL_BACKGROUND_Y_COORD = 240;

	/** The reserach background coordinates on the research spritesheet **/
	public static final int RESEARCH_BACKGROUND_X_COORD = 160,
							RESEARCH_BACKGROUND_Y_COORD = 240;

	/** The Elementium Guardian mob is colored trough the spectrum, this integer is
	 ** the divisor for the cosine function's speed that defines the color. **/
	public static final int SPECTRUM_DIVISOR_ELEMENTIUM_GUARDIAN = 20;

	/** The Elementium Gem is colored trough the spectrum, this integer is
	 ** the divisor for the cosine function's speed that defines the color. **/
	public static final int SPECTRUM_DIVISOR_ELEMENTIUM_GEM = 60;

	/** The Progress bar on the Elementalist's Tinkering Altar
	 * GUI  is colored trough the spectrum, this integer is
	 ** the divisor for the cosine function's speed that defines the color. **/
	public static final int SPECTRUM_DIVISOR_INFUSION = 25;

	/** The Elementium Locator entity is colored trough the spectrum, this integer is
	 ** the divisor for the cosine function's speed that defines the color. **/
	public static final int SPECTRUM_DIVISOR_ELEMENTIUM_LOCATOR = 20;

	/** The Wands are colored with a varying brightess, this integer is
	 * the divisor for the consine function's speed that defines the color **/
	public static final int BRIGHTNESS_DIVISOR_WAND = 35;

	/** The root folder where the research data (texts) is located. **/
	public static final String RESEARCH_DATA_FILE = ROOT + "research/research.txt";

	/** The location of the world cache folder in a world **/
	public static final String WORLD_CACHE_FOLDER = "/" + AnnotationConstants.MOD_ID;

	/** The location of the name of a global cache file **/
	public static final String CACHE_FILE_NAME = "cache.dat";

	/** The prefix of a player folder in the world cache folder, this is
	 * used to format the folder names to see who's the player that
	 * the file inside belongs to **/
	public static final String WORLD_PLAYER_CACHE_FOLDER_PREFIX = "player_";

	/** The location of a player folder in the world cache folder, this is
	 * meant to be formatted with the player's name **/
	public static final String WORLD_PLAYER_CACHE_FOLDER = WORLD_CACHE_FOLDER + "/" + WORLD_PLAYER_CACHE_FOLDER_PREFIX + "%s";
}
