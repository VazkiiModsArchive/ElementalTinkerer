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
	public static final String ROOT = "/vazkii/tinkerer/res/";

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

	/** The Spritesheet containing the in-world block textures **/
	public static final String BLOCKS_SPRITESHEET = ROOT + "blocks.png";

	/** The Spritesheet containing the in-world block textures in 64x64 resolution **/
	public static final String BLOCKS_64_SPRITESHEET = ROOT + "blocks64.png";

	/** The Spritesheet containing the item sprites **/
	public static final String ITEMS_SPRITESHEET = ROOT + "items.png";

	/** The Spritesheet containing the item sprites in 32x32 resolution **/
	public static final String ITEMS_32_SPRITESHEET = ROOT + "items32.png";

	/** The Spritesheet containing research sprites **/
	public static final String RESEARCH_SPRITESHEET = ROOT + "research.png";

	/** The Spritesheet containing the spell sprites **/
	public static final String MAGIC_SPRITESHEET = ROOT + "magic.png";

	/** The Spritehseet containing the potion effect sprites **/
	public static final String POTIONS_SPRITESHEET = ROOT_GUI + "potionEffects.png";

	/** The location of the Texture File for the Elemental Desk Model **/
	public static final String MODEL_TEX_ELEMENTAL_DESK = ROOT_MODEL + "elementalDesk.png";

	/** The Location of the Texture File for the Catalyst Capsule Model **/
	public static final String MODEL_TEX_CATALYST_CAPSULE = ROOT_MODEL + "catalystCapsule.png";

	/** The Location of the Texture File for the Model with the Rotating Cubes **/
	public static final String MODEL_TEX_ROTATING_CUBES = ROOT_MODEL + "rotatingCubes.png";

	/** The location of book textures **/
	public static final String ROOT_BOOK_TEXTURES = ROOT_MODEL + "book";

	/** Textures for the Lightning **/
	public static final String LIGHTNING_INNER_TEXTURE = ROOT_LIGHTNING + "inner.png",
							   LIGHTNING_OUTER_TEXTURE = ROOT_LIGHTNING + "outer.png";

	/** Integers for the Blocks Spritesheet indexes **/
	public static final int BLOCK_INDEX_ELEMENTIUM_ORE_START = 0,
							BLOCK_INDEX_ELEMENTIUM_ORE_NON_COLORED = 5,
							BLOCK_INDEX_ELEMENTIUM_GEM = 16,
							BLOCK_INDEX_ATTUNER_GLASS = 17,
							BLOCK_INDEX_ATTUNER_TOP = 18,
							BLOCK_INDEX_VOID_GATEWAY = 32,
							BLOCK_INDEX_VOID_NETWORK_REG = 33,
							BLOCK_INDEX_VOID_NETWORK_TOP = 34,
							BLOCK_INDEX_TRANSPARENT = 255;

	/** Integers for the Blocks Spritesheet (64x) indexes **/
	public static final int BLOCK_64_INDEX_ELEMENTALIST_TINKERING_ALTAR = 0;

	/** Integers for the Items Spritesheet indexes **/
	public static final int ITEM_INDEX_ELEMENTAL_BOOK_START = 0,
							ITEM_INDEX_ELEMENTALIST_LEXICON = 4,
							ITEM_INDEX_ELEMENTIUM_DUST = 15,
							ITEM_INDEX_CATALYST_START = 16,
							ITEM_INDEX_ELEMENTAL_BARK = 32,
							ITEM_INDEX_WAND_REGULAR = 33,
							ITEM_INDEX_WAND_COLORIZE = 34,
							ITEM_INDEX_ELEMENTIUM_DETECTOR_REGULAR = 49,
							ITEM_INDEX_ELEMENTIUM_DETECTOR_COLORIZE = 50,
							ITEM_INDEX_ODD_CLAW = 64,
							ITEM_INDEX_LOCATION_GEM = 46,
							ITEM_INDEX_GEM_START = 192,
							ITEM_INDEX_GASEOUS_GLOWSTONE = 48,
							ITEM_INDEX_ORICHALCUM = 77,
							ITEM_INDEX_ORICHALCUM_BLADE = 78,
							ITEM_INDEX_ORICHALCUM_SPADE = 94,
							ITEM_INDEX_ORICHALCUM_PICK = 110,
							ITEM_INDEX_ORICHALCUM_HATCHET = 126,
							ITEM_INDEX_ORICHALCUM_HELMET = 79,
							ITEM_INDEX_ORICHALCUM_CHESTPLATE = 95,
							ITEM_INDEX_ORICHALCUM_PLATELEGS = 111,
							ITEM_INDEX_ORICHALCUM_BOOTS = 127,
							ITEM_INDEX_LOCATING_CORE_BASE = 43,
							ITEM_INDEX_UNBOUND_BOOK = 5,
							ITEM_INDEX_SPELLBIND_CLOTH = 76,
							ITEM_INDEX_MYSTICAL_TALISMAN = 75,
							ITEM_INDEX_ATTUNED_TOME = 6,
							ITEM_INDEX_ROASTED_CARROT = 80,
							ITEM_INDEX_CARAMEL = 81,
							ITEM_INDEX_TRANSPARENT = 255;

	/** Integers for the Item Spritesheet Animations **/
	public static final int ITEM_ANIM_ENDER_PARTICLE_START = 57,
							ITEM_ANIM_ENDER_PARTICLE_END = 63;

	/** Integers for the Item Spritesheet (32x) Animations **/
	public static final int ITEM_32_ANIM_ELEMENTIUM_GEM_START = 0,
							ITEM_32_ANIM_ELEMENTIUM_GEM_END = 7,
							ITEM_32_ANIM_ELEMENTIUM_INGOT_START = 16,
							ITEM_32_ANIM_ELEMENTIUM_INGOT_END = 23;

	/** Integers for the Research Spritesheet indexes **/
	public static final int RESEARCH_INDEX_QUESTIONMARK = 0,
							RESEARCH_INDEX_ELLIPSES = 1,
							RESEARCH_INDEX_ELEMENTIUM_ORE = 2,
							RESEARCH_INDEX_ELEMENTIUM_GEM = 3,
							RESEARCH_INDEX_ELEMENTAL_DESK = 4,
							RESEARCH_INDEX_RESEARCH_BOOKS = 5,
							RESERACH_INDEX_ELEMENTIUM_INGOT = 6,
							RESEARCH_INDEX_WAND_START = 7,
							RESEARCH_INDEX_ELEMENTAL_TINKERING = 11,
							RESEARCH_INDEX_CATALYST_CAPSULE = 12,
							RESEARCH_INDEX_ATTUNER = 13,
							RESEARCH_INDEX_ELEMENTIUM_DETECTOR = 14,
							RESEARCH_INDEX_VOID_GATEWAY = 15,
							RESEARCH_INDEX_VOID_NETWORK = 16,
							RESEARCH_INDEX_WAVE_INPUTTER = 17,
							RESEARCH_INDEX_DISLOCATOR = 18,
							RESEARCH_INDEX_SCAVENGER = 19,
							RESEARCH_INDEX_INCINERATOR = 20,
							RESEARCH_INDEX_NEW_FOOD = 21,
							RESEARCH_INDEX_SPELLBIND_CLOTH = 22;

	/** Integers for the Magic Spritesheet indexes **/
	public static final int MAGIC_INDEX_BACKGROUND = 255,
							MAGIC_SPRITESHEET_Y_OFFSET_FRAME = 220,
							MAGIC_INDEX_THUNDERBOLT = 0,
							MAGIC_INDEX_FROSTBOLT = 1,
							MAGIC_INDEX_BOULDER_TOSS = 2,
							MAGIC_INDEX_FIREBALL = 3,
							MAGIC_INDEX_AEREAL_PUSH = 16,
							MAGIC_INDEX_FROSTSHOCK = 17,
							MAGIC_INDEX_IMPLOSION = 18,
							MAGIC_INDEX_FLAME_RING = 19,
							MAGIC_INDEX_EXTENDED_BREATH = 4,
							MAGIC_INDEX_RAIN_ACCUMULATION = 5,
							MAGIC_INDEX_NATURE_AURA = 6,
							MAGIC_INDEX_BURNING_CLOUD = 7,
							MAGIC_INDEX_UNDERSHIRT = 9,
							MAGIC_INDEX_INATE_SPEED = 20,
							MAGIC_INDEX_FREEZING_WALK = 21,
							MAGIC_INDEX_IRONSKIN = 22,
							MAGIC_INDEX_BLOOD_BOIL = 23,
							MAGIC_INDEX_ENDER_ABSORPTION = 8,
							MAGIC_INDEX_GUILLOTINE = 10,
							MAGIC_INDEX_SHATTERING_RECALL = 11;

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
