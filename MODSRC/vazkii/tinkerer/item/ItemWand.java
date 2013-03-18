/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Jan 2013
package vazkii.tinkerer.item;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.handler.InteractionAccessHandler;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.magic.IWand;
import vazkii.tinkerer.magic.PlayerSpellData;
import vazkii.tinkerer.magic.Spell;
import vazkii.tinkerer.magic.SpellLibrary;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.network.packet.PacketSpells;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResourcesReference;
import cpw.mods.fml.common.network.Player;

/**
 * ItemWand
 *
 * A Wand. This item can cast various spells.
 *
 * @author Vazkii
 */
public class ItemWand extends ItemET implements IWand {

	public ItemWand(int par1) {
		super(par1);
		setMaxStackSize(1);
		setHasSubtypes(true);
	}

	Icon[] icons = new Icon[2];

	@Override
	public void func_94581_a(IconRegister par1IconRegister) {
		for(int i = 0; i < 2; i++)
			icons[i] =  IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		return nameFromMeta(par1ItemStack.getItemDamage());
	}

	public static String nameFromMeta(int meta) {
		return ItemNames.WAND_NAME_PREFIX + Element.getSuffix(meta);
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		super.getSubItems(par1, par2CreativeTabs, par3List);
		for(int i = 1; i < 4; i++)
			par3List.add(new ItemStack(par1, 1, i));
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return icons[par2];
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		if(par2 == 0)
			return super.getColorFromItemStack(par1ItemStack, par2);

		Element element = Element.class.getEnumConstants()[par1ItemStack.getItemDamage()];
		float bright = (float) Math.cos((double) ClientTickHandler.elapsedClientTicks / (double) ResourcesReference.BRIGHTNESS_DIVISOR_WAND);
		return Color.HSBtoRGB(element.getHue() / 360F, 1F, ConfigurationHandler.wandFlicker ? Math.max(0.2F, (bright + 1F) / 2F) : 0.9F);
	}

	@Override
	public void handleKeystroke(EntityPlayer player, ItemStack stack) {
		if(player.isUsingItem())
			return;

		PlayerSpellData spellData = SpellHelper.getSpellDataForPlayer(player.username);

		byte select = (byte) (spellData.getSpellSelected() + 1);
		spellData.select(select >= spellData.getSpells().length ? 0 : select);

		SpellHelper.updateSpells(player.worldObj, spellData);
		PacketSpells packet = new PacketSpells(spellData);
		PacketHelper.sendPacketToClient((Player)player, packet);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(par2World.isRemote)
			return par1ItemStack;

		PlayerSpellData spellData = SpellHelper.getSpellDataForPlayer(par3EntityPlayer.username);

		if(spellData.getSpells().length > 0) {
			byte spellSelected = spellData.getSpellSelected();
			short spellIndex = spellData.getSpells()[spellSelected];
			Spell spell = SpellLibrary.allSpells.get(spellIndex);

			if(spellData.canCastSpell(spellIndex)) {
				boolean casted = spell.cast(par3EntityPlayer, par1ItemStack.getItemDamage() == spell.element);
				if(casted) {
					spellData.mapCooldown(spell, par3EntityPlayer);
					PacketSpells packet = new PacketSpells(spellData);
					PacketHelper.sendPacketToClient((Player)par3EntityPlayer, packet);
				}
			}
		}

		return par1ItemStack;
	}

	@Override
	public boolean onItemUseFirst(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(par3World.isRemote)
			return false;

		PlayerSpellData spellData = SpellHelper.getSpellDataForPlayer(par2EntityPlayer.username);

		if(spellData.getSpells().length > 0) {
			byte spellSelected = spellData.getSpellSelected();
			short spellIndex = spellData.getSpells()[spellSelected];
			Spell spell = SpellLibrary.allSpells.get(spellIndex);

			if(spellData.canCastSpell(spellIndex)) {
				boolean casted = spell.castOnBlock(par2EntityPlayer, par1ItemStack.getItemDamage() == spell.element, par4, par5, par6);
				if(casted) {
					spellData.mapCooldown(spell, par2EntityPlayer);
					PacketSpells packet = new PacketSpells(spellData);
					PacketHelper.sendPacketToClient((Player)par2EntityPlayer, packet);
				}
				return casted;
			}
		}
		return false;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving) {
		if(par2EntityLiving.worldObj.isRemote)
			return false;

		EntityPlayer interactingPlayer = InteractionAccessHandler.getLastInteractingPlayer();
		PlayerSpellData spellData = SpellHelper.getSpellDataForPlayer(interactingPlayer.username);

		if(spellData.getSpells().length > 0) {
			byte spellSelected = spellData.getSpellSelected();
			short spellIndex = spellData.getSpells()[spellSelected];
			Spell spell = SpellLibrary.allSpells.get(spellIndex);

			if(spell.getSpellType() == SpellType.ACTIVE && spellData.canCastSpell(spellIndex)) {
				boolean casted = spell.castOnEntity(interactingPlayer, par1ItemStack.getItemDamage() == spell.element, par2EntityLiving);
				if(casted) {
					spellData.mapCooldown(spell, interactingPlayer);
					PacketSpells packet = new PacketSpells(spellData);
					PacketHelper.sendPacketToClient((Player)interactingPlayer, packet);
				}
				return casted;
			}
		}
		return false;
	}
}