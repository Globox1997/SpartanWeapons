package net.spartanweapons.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.spartanweapons.item.*;

public class ItemInit {

    // Item Group
    public static final RegistryKey<ItemGroup> SPARTANWEAPONS_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of("spartanweapons", "item_group"));
    // Lists
    public static final ArrayList<String> WOOD_MATERIALS = new ArrayList<String>(
            Arrays.asList("acacia", "bamboo", "birch", "cherry", "crimson", "dark_oak", "jungle", "mangrove", "oak", "spruce", "warped"));
    public static final ArrayList<String> MATERIALS = new ArrayList<String>(Arrays.asList("wooden", "stone", "iron", "golden", "diamond", "netherite"));
    public static final ArrayList<String> ITEM_STRINGS = new ArrayList<String>(Arrays.asList("cutlass", "dagger", "glaive", "greatsword", "halberd", "katana", "mace", "rapier", "scythe", "spear",
            "club", "spear", "staff", "battle_axe", "kunai", "javelin", "stick", "pole"));

    // public static final ArrayList<String> STICKS = new ArrayList<String>(
    // Arrays.asList("acacia_stick", "bamboo_stick", "birch_stick", "cherry_stick", "crimson_stick", "dark_oak_stick", "jungle_stick", "mangrove_stick", "spruce_stick", "warped_stick"));
    // Unused
    // public static final ArrayList<Item> HANDLES = new ArrayList<Item>();

    // Map
    public static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
    // public static final Map<Identifier, Item> HANDLE = new LinkedHashMap<>();

    // Sticks
    public static final Item ACACIA_STICK = register("acacia_stick", new Item(new Item.Settings()));
    public static final Item BAMBOO_STICK = register("bamboo_stick", new Item(new Item.Settings()));
    public static final Item BIRCH_STICK = register("birch_stick", new Item(new Item.Settings()));
    public static final Item CHERRY_STICK = register("cherry_stick", new Item(new Item.Settings()));
    public static final Item CRIMSON_STICK = register("crimson_stick", new Item(new Item.Settings()));
    public static final Item DARK_OAK_STICK = register("dark_oak_stick", new Item(new Item.Settings()));
    public static final Item JUNGLE_STICK = register("jungle_stick", new Item(new Item.Settings()));
    public static final Item MANGROVE_STICK = register("mangrove_stick", new Item(new Item.Settings()));
    public static final Item SPRUCE_STICK = register("spruce_stick", new Item(new Item.Settings()));
    public static final Item WARPED_STICK = register("warped_stick", new Item(new Item.Settings()));

    // Staff
    public static final Item ACACIA_POLE = register("acacia_pole", new Item(new Item.Settings()));
    public static final Item BAMBOO_POLE = register("bamboo_pole", new Item(new Item.Settings()));
    public static final Item BIRCH_POLE = register("birch_pole", new Item(new Item.Settings()));
    public static final Item CHERRY_POLE = register("cherry_pole", new Item(new Item.Settings()));
    public static final Item CRIMSON_POLE = register("crimson_pole", new Item(new Item.Settings()));
    public static final Item DARK_OAK_POLE = register("dark_oak_pole", new Item(new Item.Settings()));
    public static final Item JUNGLE_POLE = register("jungle_pole", new Item(new Item.Settings()));
    public static final Item MANGROVE_POLE = register("mangrove_pole", new Item(new Item.Settings()));
    public static final Item OAK_POLE = register("oak_pole", new Item(new Item.Settings()));
    public static final Item SPRUCE_POLE = register("spruce_pole", new Item(new Item.Settings()));
    public static final Item WARPED_POLE = register("warped_pole", new Item(new Item.Settings()));

    // public static final SmallAxeItem WOODEN_SMALL_AXE_ITEM = register("wooden_small_axe",
    // new SmallAxeItem(ToolMaterials.WOOD,
    // new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f).with(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
    // new EntityAttributeModifier(SmallAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND))));

    // public static final SmallAxeItem WOODEN_SMALL_AXE_ITEM = register("wooden_small_axe",
    // new SmallAxeItem(ToolMaterials.WOOD,
    // new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f).with(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
    // new EntityAttributeModifier(SmallAxeItem.ATTACK_BONUS_MODIFIER_ID, 1.0D, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND))));
    // Club
    public static final Item ACACIA_CLUB = register("acacia_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item BAMBOO_CLUB = register("bamboo_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item BIRCH_CLUB = register("birch_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item CHERRY_CLUB = register("cherry_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item CRIMSON_CLUB = register("crimson_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item DARK_OAK_CLUB = register("dark_oak_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item JUNGLE_CLUB = register("jungle_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item MANGROVE_CLUB = register("mangrove_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item OAK_CLUB = register("oak_club", new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item SPRUCE_CLUB = register("spruce_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item WARPED_CLUB = register("warped_club",
            new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));

    // Cutlass
    // public static final Item WOODEN_CUTLASS = register("wooden_cutlass", new Cutlass(ToolMaterials.WOOD, new
    // Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    // public static final Item STONE_CUTLASS = register("stone_cutlass", new Cutlass(ToolMaterials.STONE, new
    // Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item IRON_CUTLASS = register("iron_cutlass",
            new Cutlass(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.8f))));
    public static final Item GOLDEN_CUTLASS = register("golden_cutlass",
            new Cutlass(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.8f))));
    public static final Item DIAMOND_CUTLASS = register("diamond_cutlass",
            new Cutlass(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.8f))));
    public static final Item NETHERITE_CUTLASS = register("netherite_cutlass",
            new Cutlass(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.8f)).fireproof()));

    // public static final Item CUTLASS_OAK = registerHandleItem("cutlass_oak", new Item(new Item.Settings()));
    // public static final Item CUTLASS_ACACIA = registerHandleItem("cutlass_acacia", new Item(new Item.Settings()));
    // public static final Item CUTLASS_BAMBOO = registerHandleItem("cutlass_bamboo", new Item(new Item.Settings()));
    // public static final Item CUTLASS_BIRCH = registerHandleItem("cutlass_birch", new Item(new Item.Settings()));
    // public static final Item CUTLASS_CHERRY = registerHandleItem("cutlass_cherry", new Item(new Item.Settings()));
    // public static final Item CUTLASS_CRIMSON = registerHandleItem("cutlass_crimson", new Item(new Item.Settings()));
    // public static final Item CUTLASS_DARK_OAK = registerHandleItem("cutlass_dark_oak", new Item(new Item.Settings()));
    // public static final Item CUTLASS_JUNGLE = registerHandleItem("cutlass_jungle", new Item(new Item.Settings()));
    // public static final Item CUTLASS_MANGROVE = registerHandleItem("cutlass_mangrove", new Item(new Item.Settings()));
    // public static final Item CUTLASS_SPRUCE = registerHandleItem("cutlass_spruce", new Item(new Item.Settings()));
    // public static final Item CUTLASS_WARPED = registerHandleItem("cutlass_warped", new Item(new Item.Settings()));
    // Dagger
    // public static final Item WOODEN_DAGGER = register("wooden_dagger", new Dagger(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD,
    // 5, -2.9f))));
    // public static final Item STONE_DAGGER = register("stone_dagger", new Dagger(ToolMaterials.STONE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD,
    // 5, -2.9f))));
    public static final Item IRON_DAGGER = register("iron_dagger",
            new Dagger(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -1.6f))));
    public static final Item GOLDEN_DAGGER = register("golden_dagger",
            new Dagger(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -1.6f))));
    public static final Item DIAMOND_DAGGER = register("diamond_dagger",
            new Dagger(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -1.6f))));
    public static final Item NETHERITE_DAGGER = register("netherite_dagger",
            new Dagger(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -1.6f)).fireproof()));
    //

    // Glaive
    // public static final Item WOODEN_GLAIVE = register("wooden_glaive", new Glaive(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD,
    // 5, -2.9f))));
    // public static final Item STONE_GLAIVE = register("stone_glaive", new Glaive(ToolMaterials.STONE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD,
    // 5, -2.9f))));
    public static final Item IRON_GLAIVE = register("iron_glaive",
            new Glaive(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.6f))));
    public static final Item GOLDEN_GLAIVE = register("golden_glaive",
            new Glaive(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.6f))));
    public static final Item DIAMOND_GLAIVE = register("diamond_glaive",
            new Glaive(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.6f))));
    public static final Item NETHERITE_GLAIVE = register("netherite_glaive",
            new Glaive(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.6f)).fireproof()));

    // Greatsword
    public static final Item IRON_GREATSWORD = register("iron_greatsword",
            new GreatSword(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 7, -3.2f))));
    public static final Item GOLDEN_GREATSWORD = register("golden_greatsword",
            new GreatSword(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 7, -3.2f))));
    public static final Item DIAMOND_GREATSWORD = register("diamond_greatsword",
            new GreatSword(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 7, -3.2f))));
    public static final Item NETHERITE_GREATSWORD = register("netherite_greatsword",
            new GreatSword(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 7, -3.2f)).fireproof()));

    // Halberd
    public static final Item IRON_HALBERD = register("iron_halberd",
            new Halberd(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -3.0f))));
    public static final Item GOLDEN_HALBERD = register("golden_halberd",
            new Halberd(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -3.0f))));
    public static final Item DIAMOND_HALBERD = register("diamond_halberd",
            new Halberd(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -3.0f))));
    public static final Item NETHERITE_HALBERD = register("netherite_halberd",
            new Halberd(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -3.0f)).fireproof()));

    // Katana
    public static final Item IRON_KATANA = register("iron_katana",
            new Katana(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.3f))));
    public static final Item GOLDEN_KATANA = register("golden_katana",
            new Katana(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.3f))));
    public static final Item DIAMOND_KATANA = register("diamond_katana",
            new Katana(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.3f))));
    public static final Item NETHERITE_KATANA = register("netherite_katana",
            new Katana(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.3f)).fireproof()));

    // Mace
    public static final Item IRON_MACE = register("iron_mace", new Mace(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 9, -3.2f))));
    public static final Item GOLDEN_MACE = register("golden_mace",
            new Mace(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 9, -3.2f))));
    public static final Item DIAMOND_MACE = register("diamond_mace",
            new Mace(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 9, -3.2f))));
    public static final Item NETHERITE_MACE = register("netherite_mace",
            new Mace(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 9, -3.2f)).fireproof()));

    // Rapier
    public static final Item IRON_RAPIER = register("iron_rapier",
            new Rapier(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 1, -2.0f))));
    public static final Item GOLDEN_RAPIER = register("golden_rapier",
            new Rapier(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 1, -2.0f))));
    public static final Item DIAMOND_RAPIER = register("diamond_rapier",
            new Rapier(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 1, -2.0f))));
    public static final Item NETHERITE_RAPIER = register("netherite_rapier",
            new Rapier(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 1, -2.0f)).fireproof()));

    // Scythe
    public static final Item IRON_SCYTHE = register("iron_scythe",
            new Scythe(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -3.0f))));
    public static final Item GOLDEN_SCYTHE = register("golden_scythe",
            new Scythe(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -3.0f))));
    public static final Item DIAMOND_SCYTHE = register("diamond_scythe",
            new Scythe(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -3.0f))));
    public static final Item NETHERITE_SCYTHE = register("netherite_scythe",
            new Scythe(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -3.0f)).fireproof()));

    // Spear
    public static final Item IRON_SPEAR = register("iron_spear",
            new Spear(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.8f))));
    public static final Item GOLDEN_SPEAR = register("golden_spear",
            new Spear(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.8f))));
    public static final Item DIAMOND_SPEAR = register("diamond_spear",
            new Spear(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.8f))));
    public static final Item NETHERITE_SPEAR = register("netherite_spear",
            new Spear(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.8f)).fireproof()));

    // Battle Axe
    public static final Item IRON_BATTLE_AXE = register("iron_battle_axe",
            new BattleAxe(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.5f))));
    public static final Item GOLDEN_BATTLE_AXE = register("golden_battle_axe",
            new BattleAxe(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.5f))));
    public static final Item DIAMOND_BATTLE_AXE = register("diamond_battle_axe",
            new BattleAxe(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.5f))));
    public static final Item NETHERITE_BATTLE_AXE = register("netherite_battle_axe",
            new BattleAxe(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.5f)).fireproof()));

    // Javelin
    public static final Item IRON_JAVELIN = register("iron_javelin",
            new Javelin(ToolMaterials.IRON, () -> EntityInit.IRON_JAVELIN, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 2, -2.4f))));
    public static final Item GOLDEN_JAVELIN = register("golden_javelin",
            new Javelin(ToolMaterials.GOLD, () -> EntityInit.GOLDEN_JAVELIN, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 2, -2.4f))));
    public static final Item DIAMOND_JAVELIN = register("diamond_javelin",
            new Javelin(ToolMaterials.DIAMOND, () -> EntityInit.DIAMOND_JAVELIN, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 2, -2.4f))));
    public static final Item NETHERITE_JAVELIN = register("netherite_javelin", new Javelin(ToolMaterials.NETHERITE, () -> EntityInit.NETHERITE_JAVELIN,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 2, -2.4f)).fireproof()));

    // Kunai
    public static final Item IRON_KUNAI = register("iron_kunai",
            new Kunai(ToolMaterials.IRON, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -1.8f))));
    public static final Item GOLDEN_KUNAI = register("golden_kunai",
            new Kunai(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -1.8f))));
    public static final Item DIAMOND_KUNAI = register("diamond_kunai",
            new Kunai(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -1.8f))));
    public static final Item NETHERITE_KUNAI = register("netherite_kunai",
            new Kunai(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 0, -1.8f)).fireproof()));

    // Staff
    public static final Item ACACIA_STAFF = register("acacia_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item BAMBOO_STAFF = register("bamboo_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item BIRCH_STAFF = register("birch_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item CHERRY_STAFF = register("cherry_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item CRIMSON_STAFF = register("crimson_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item DARK_OAK_STAFF = register("dark_oak_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item JUNGLE_STAFF = register("jungle_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item MANGROVE_STAFF = register("mangrove_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item OAK_STAFF = register("oak_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item SPRUCE_STAFF = register("spruce_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));
    public static final Item WARPED_STAFF = register("warped_staff",
            new Stave(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -2.9f))));

    public static <I extends Item> I register(String name, I item) {
        ITEMS.put(Identifier.of("spartanweapons", name), item);
        return item;
    }

    // public static <I extends Item> I registerHandleItem(String name, I item) {
    // HANDLES.add(item);
    // Registry.register(Registries.ITEM, new Identifier("spartanweapons", name), item);
    // // HANDLE.put(new Identifier("spartanweapons", name), item);
    // return item;
    // }

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, SPARTANWEAPONS_ITEM_GROUP,
                FabricItemGroup.builder().icon(() -> new ItemStack(DIAMOND_CUTLASS)).displayName(Text.translatable("item.spartanweapons.item_group")).build());

        // CompatItems.loadItems();
        // CompatRecipes.createRecipes();
        // CompatRecipes.loadRecipes();
        for (Identifier id : ITEMS.keySet()) {
            Registry.register(Registries.ITEM, id, ITEMS.get(id));
            ItemGroupEvents.modifyEntriesEvent(SPARTANWEAPONS_ITEM_GROUP).register(entries -> entries.add(ITEMS.get(id)));
        }
        // for (Identifier id : HANDLE.keySet()) {
        // Registry.register(Registries.ITEM, id, HANDLE.get(id));
        // }
    }
}
