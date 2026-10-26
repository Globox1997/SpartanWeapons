package net.spartanweapons.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.entity.JavelinEntity;
import net.spartanweapons.entity.KunaiEntity;
import net.spartanweapons.item.*;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ItemInit {

    // Item Group
    public static final RegistryKey<ItemGroup> SPARTANWEAPONS_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, SpartanWeaponsMain.identifierOf("item_group"));
    // Bonus
    public static final Identifier ATTACK_BONUS_MODIFIER_ID = SpartanWeaponsMain.identifierOf("attack_bonus");
    public static final Identifier KNOCKBACK_BONUS_MODIFIER_ID = SpartanWeaponsMain.identifierOf("knockback_bonus");
    // Lists
    public static final ArrayList<String> WOOD_MATERIALS = new ArrayList<>(
            Arrays.asList("acacia", "bamboo", "birch", "cherry", "crimson", "dark_oak", "jungle", "mangrove", "oak", "spruce", "warped"));
    // Map
    public static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

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

    public static final ArrayList<Item> STICKS = new ArrayList<>(Arrays.asList(ItemInit.ACACIA_STICK, ItemInit.BAMBOO_STICK, ItemInit.BIRCH_STICK, ItemInit.CHERRY_STICK,
            ItemInit.CRIMSON_STICK, ItemInit.DARK_OAK_STICK, ItemInit.JUNGLE_STICK, ItemInit.MANGROVE_STICK, ItemInit.SPRUCE_STICK, ItemInit.WARPED_STICK));

    // Poles
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

    public static Item register(String name, Item item) {
        ITEMS.put(SpartanWeaponsMain.identifierOf(name), item);
        return item;
    }

    public record WeaponType(
            String suffix,
            WeaponFactory constructor,
            int damage,
            float speed,
            boolean hasEntity,
            @Nullable RegistryEntry<EntityAttribute> attribute,
            @Nullable EntityAttributeModifier modifier,
            @Nullable AttributeModifierSlot slot,
            String[] pattern,
            float knockback
    ) {
    }

    @FunctionalInterface
    interface WeaponFactory {
        Item create(ToolMaterial material, Item.Settings settings, String fullId);
    }

    public static final List<WeaponType> TYPES = List.of(
            new WeaponType("battle_axe", (material, settings, id) -> new BattleAxe(material, settings), ConfigInit.CONFIG.battle_axe_attack_damage, ConfigInit.CONFIG.battle_axe_attack_speed, false, null, null, null, new String[]{"iii", "ipi", " s "}, 0.0f),
            new WeaponType("dagger", (material, settings, id) -> new Dagger(material, settings), ConfigInit.CONFIG.dagger_attack_damage, ConfigInit.CONFIG.dagger_attack_speed, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.dagger_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"i", "s"}, 0.0f),
            new WeaponType("glaive", (material, settings, id) -> new Glaive(material, settings), ConfigInit.CONFIG.glaive_attack_damage, ConfigInit.CONFIG.glaive_attack_speed, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.glaive_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"i", "i", "p"}, ConfigInit.CONFIG.glaive_knockback),
            new WeaponType("greatsword", (material, settings, id) -> new GreatSword(material, settings), ConfigInit.CONFIG.greatsword_attack_damage, ConfigInit.CONFIG.greatsword_attack_speed, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.greatsword_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" i ", " i ", "isi"}, 0.0f),
            new WeaponType("halberd", (material, settings, id) -> new Halberd(material, settings), ConfigInit.CONFIG.halberd_attack_damage, ConfigInit.CONFIG.halberd_attack_speed, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.halberd_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" i", "ii", "is"}, ConfigInit.CONFIG.halberd_knockback),
            new WeaponType("katana", (material, settings, id) -> new Katana(material, settings), ConfigInit.CONFIG.katana_attack_damage, ConfigInit.CONFIG.katana_attack_speed, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.katana_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" i", "ii", "s "}, 0.0f),
            new WeaponType("mace", (material, settings, id) -> new Mace(material, settings), ConfigInit.CONFIG.mace_attack_damage, ConfigInit.CONFIG.mace_attack_speed, false, null, null, null, new String[]{"b", "i", "s"}, 0.0f),
            new WeaponType("rapier", (material, settings, id) -> new Rapier(material, settings), ConfigInit.CONFIG.rapier_attack_damage, ConfigInit.CONFIG.rapier_attack_speed, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.rapier_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"  i", " i ", "s  "}, 0.0f),
            new WeaponType("scythe", (material, settings, id) -> new Scythe(material, settings), ConfigInit.CONFIG.scythe_attack_damage, ConfigInit.CONFIG.scythe_attack_speed, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.scythe_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" ii", "iip", "i s"}, 0.0f),
            new WeaponType("spear", (material, settings, id) -> new Spear(material, settings), ConfigInit.CONFIG.spear_attack_damage, ConfigInit.CONFIG.spear_attack_speed, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.spear_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"i", "p"}, 0.0f),
            new WeaponType("kunai", (material, settings, id) ->
                    new Kunai(material, () -> (EntityType<KunaiEntity>) EntityInit.ENTITY_TYPES.get(SpartanWeaponsMain.identifierOf(id)), settings), ConfigInit.CONFIG.kunai_attack_damage, ConfigInit.CONFIG.kunai_attack_speed, true, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.kunai_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"xi", "s "}, 0.0f),
            new WeaponType("javelin", (material, settings, id) ->
                    new Javelin(material, () -> (EntityType<JavelinEntity>) EntityInit.ENTITY_TYPES.get(SpartanWeaponsMain.identifierOf(id)), settings), ConfigInit.CONFIG.javelin_attack_damage, ConfigInit.CONFIG.javelin_attack_speed, true, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.javelin_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"i", "x", "p"}, 0.0f),
            new WeaponType("cutlass", (material, settings, id) -> new Cutlass(material, settings), ConfigInit.CONFIG.cutlass_attack_damage, ConfigInit.CONFIG.cutlass_attack_speed, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.cutlass_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" ii", "i  ", "s  "}, 0.0f)
    );


    public static void init() {
        for (ToolMaterials toolMaterial : ToolMaterials.values()) {
            if (toolMaterial == ToolMaterials.WOOD || toolMaterial == ToolMaterials.STONE) continue;

            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (WeaponType type : TYPES) {
                    String fullName = getFormattedName(toolMaterial, woodHandle, type.suffix());

                    Item.Settings settings = createWeaponSettings(toolMaterial, type);
                    if (toolMaterial == ToolMaterials.NETHERITE) settings = settings.fireproof();

                    Item weapon = type.constructor().create(toolMaterial, settings, fullName);
                    register(fullName, weapon);

                    if (type.hasEntity()) {
                        registerWeaponEntity(fullName, weapon);
                    }
                }
            }
        }
        for (String woodHandle : ItemInit.WOOD_MATERIALS) {
            register(woodHandle + "_spear", new Spear(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(Spear.createAttributeModifiers(ToolMaterials.WOOD, ConfigInit.CONFIG.wooden_spear_attack_damage, ConfigInit.CONFIG.wooden_spear_attack_speed).with(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.wooden_spear_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND))));
            register(woodHandle + "_club", new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(Club.createAttributeModifiers(ToolMaterials.WOOD, ConfigInit.CONFIG.club_attack_damage, ConfigInit.CONFIG.club_attack_speed))));
            register(woodHandle + "_staff", new Staff(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(Staff.createAttributeModifiers(ToolMaterials.WOOD, ConfigInit.CONFIG.staff_attack_damage, ConfigInit.CONFIG.staff_attack_speed).with(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.staff_range, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND))));
        }

        Registry.register(Registries.ITEM_GROUP, SPARTANWEAPONS_ITEM_GROUP,
                FabricItemGroup.builder().icon(() -> new ItemStack(OAK_POLE)).displayName(Text.translatable("item.spartanweapons.item_group")).build());

        for (Identifier id : ITEMS.keySet()) {
            Registry.register(Registries.ITEM, id, ITEMS.get(id));
        }
        ItemGroupEvents.modifyEntriesEvent(SPARTANWEAPONS_ITEM_GROUP).register(entries -> ITEMS.values().forEach(entries::add));
    }

    public static String getFormattedName(ToolMaterials mat, String wood, String suffix) {
        String matName = mat.name().toLowerCase();
        if (matName.equals("gold")) matName = "golden";
        return matName + "_" + wood + "_" + suffix;
    }

    private static Item.Settings createWeaponSettings(ToolMaterial mat, WeaponType type) {
        AttributeModifiersComponent modifiers = SwordItem.createAttributeModifiers(mat, type.damage(), type.speed());

        if (type.attribute() != null && type.modifier() != null) {
            modifiers = modifiers.with(type.attribute(), type.modifier(), type.slot());
        }
        if (type.knockback() != 0.0f) {
            modifiers = modifiers.with(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, new EntityAttributeModifier(KNOCKBACK_BONUS_MODIFIER_ID, type.knockback(), EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND);
        }

        return new Item.Settings().attributeModifiers(modifiers);
    }

    private static void registerWeaponEntity(String id, Item item) {
        EntityType type = null;
        if (item instanceof Javelin) {
            type = EntityType.Builder.<JavelinEntity>create(
                    JavelinEntity::new,
                    SpawnGroup.MISC).dimensions(0.5F, 0.5F).build();
        } else if (item instanceof Kunai) {
            type = EntityType.Builder.<KunaiEntity>create(
                    KunaiEntity::new,
                    SpawnGroup.MISC).dimensions(0.5F, 0.5F).build();
        }
        EntityInit.register(id, type);
    }

}
