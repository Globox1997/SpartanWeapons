package net.spartanweapons.init;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Weapon;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.entity.JavelinEntity;
import net.spartanweapons.entity.KunaiEntity;
import net.spartanweapons.item.*;
import net.spartanweapons.platform.Services;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ItemInit {

    // Item Group
    public static final ResourceKey<CreativeModeTab> SPARTANWEAPONS_ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, SpartanWeaponsMain.identifierOf("item_group"));
    // Bonus
    public static final Identifier ATTACK_BONUS_MODIFIER_ID = SpartanWeaponsMain.identifierOf("attack_bonus");
    public static final Identifier KNOCKBACK_BONUS_MODIFIER_ID = SpartanWeaponsMain.identifierOf("knockback_bonus");
    // Lists
    public static final ArrayList<String> WOOD_MATERIALS = new ArrayList<>(
            Arrays.asList("acacia", "bamboo", "birch", "cherry", "crimson", "dark_oak", "jungle", "mangrove", "oak", "spruce", "warped"));
    public static final Map<String, ToolMaterial> WEAPON_MATERIALS = new LinkedHashMap<>();
    // Map
    public static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();

    static {
        WEAPON_MATERIALS.put("iron", ToolMaterial.IRON);
        WEAPON_MATERIALS.put("diamond", ToolMaterial.DIAMOND);
        WEAPON_MATERIALS.put("gold", ToolMaterial.GOLD);
        WEAPON_MATERIALS.put("netherite", ToolMaterial.NETHERITE);
    }

    // Sticks
    public static Item ACACIA_STICK;
    public static Item BAMBOO_STICK;
    public static Item BIRCH_STICK;
    public static Item CHERRY_STICK;
    public static Item CRIMSON_STICK;
    public static Item DARK_OAK_STICK;
    public static Item JUNGLE_STICK;
    public static Item MANGROVE_STICK;
    public static Item SPRUCE_STICK;
    public static Item WARPED_STICK;

    public static final ArrayList<Item> STICKS = new ArrayList<>();

    // Poles
    public static Item ACACIA_POLE;
    public static Item BAMBOO_POLE;
    public static Item BIRCH_POLE;
    public static Item CHERRY_POLE;
    public static Item CRIMSON_POLE;
    public static Item DARK_OAK_POLE;
    public static Item JUNGLE_POLE;
    public static Item MANGROVE_POLE;
    public static Item OAK_POLE;
    public static Item SPRUCE_POLE;
    public static Item WARPED_POLE;

    public static Item register(String name, Function<Item.Properties, Item> factory) {
        return register(name, factory, new Item.Properties());
    }

    public static Item register(String name, Function<Item.Properties, Item> factory, Item.Properties settings) {
        Identifier id = SpartanWeaponsMain.identifierOf(name);
        Item item = factory.apply(settings.setId(ResourceKey.create(Registries.ITEM, id)));
        ITEMS.put(id, item);
        return item;
    }

    public record WeaponType(
            String suffix,
            WeaponFactory constructor,
            int damage,
            float speed,
            boolean hasEntity,
            @Nullable Holder<Attribute> attribute,
            @Nullable AttributeModifier modifier,
            @Nullable EquipmentSlotGroup slot,
            String[] pattern,
            float knockback
    ) {
    }

    @FunctionalInterface
    interface WeaponFactory {
        Item create(Item.Properties settings, String fullId);
    }

    public static final List<WeaponType> TYPES = List.of(
            new WeaponType("battle_axe", (settings, id) -> new BattleAxe(settings), ConfigInit.CONFIG.battle_axe_attack_damage, ConfigInit.CONFIG.battle_axe_attack_speed, false, null, null, null, new String[]{"iii", "ipi", " s "}, 0.0f),
            new WeaponType("dagger", (settings, id) -> new Dagger(settings), ConfigInit.CONFIG.dagger_attack_damage, ConfigInit.CONFIG.dagger_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.dagger_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"i", "s"}, 0.0f),
            new WeaponType("glaive", (settings, id) -> new Glaive(settings), ConfigInit.CONFIG.glaive_attack_damage, ConfigInit.CONFIG.glaive_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.glaive_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"i", "i", "p"}, ConfigInit.CONFIG.glaive_knockback),
            new WeaponType("greatsword", (settings, id) -> new GreatSword(settings), ConfigInit.CONFIG.greatsword_attack_damage, ConfigInit.CONFIG.greatsword_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.greatsword_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" i ", " i ", "isi"}, 0.0f),
            new WeaponType("halberd", (settings, id) -> new Halberd(settings), ConfigInit.CONFIG.halberd_attack_damage, ConfigInit.CONFIG.halberd_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.halberd_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" i", "ii", "is"}, ConfigInit.CONFIG.halberd_knockback),
            new WeaponType("katana", (settings, id) -> new Katana(settings), ConfigInit.CONFIG.katana_attack_damage, ConfigInit.CONFIG.katana_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.katana_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" i", "ii", "s "}, 0.0f),
            new WeaponType("mace", (settings, id) -> new Mace(settings), ConfigInit.CONFIG.mace_attack_damage, ConfigInit.CONFIG.mace_attack_speed, false, null, null, null, new String[]{"b", "i", "s"}, 0.0f),
            new WeaponType("rapier", (settings, id) -> new Rapier(settings), ConfigInit.CONFIG.rapier_attack_damage, ConfigInit.CONFIG.rapier_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.rapier_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"  i", " i ", "s  "}, 0.0f),
            new WeaponType("scythe", (settings, id) -> new Scythe(settings), ConfigInit.CONFIG.scythe_attack_damage, ConfigInit.CONFIG.scythe_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.scythe_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" ii", "iip", "i s"}, 0.0f),
            new WeaponType("spear", (settings, id) -> new Spear(settings), ConfigInit.CONFIG.spear_attack_damage, ConfigInit.CONFIG.spear_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.spear_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"i", "p"}, 0.0f),
            new WeaponType("kunai", (settings, id) ->
                    new Kunai(() -> (EntityType<KunaiEntity>) EntityInit.ENTITY_TYPES.get(SpartanWeaponsMain.identifierOf(id)), settings), ConfigInit.CONFIG.kunai_attack_damage, ConfigInit.CONFIG.kunai_attack_speed, true, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.kunai_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"xi", "s "}, 0.0f),
            new WeaponType("javelin", (settings, id) ->
                    Services.PLATFORM.createJavelin(() -> (EntityType<JavelinEntity>) EntityInit.ENTITY_TYPES.get(SpartanWeaponsMain.identifierOf(id)), settings), ConfigInit.CONFIG.javelin_attack_damage, ConfigInit.CONFIG.javelin_attack_speed, true, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.javelin_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"i", "x", "p"}, 0.0f),
            new WeaponType("cutlass", (settings, id) -> new Cutlass(settings), ConfigInit.CONFIG.cutlass_attack_damage, ConfigInit.CONFIG.cutlass_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.cutlass_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" ii", "i  ", "s  "}, 0.0f)
    );


    public static void register(BiConsumer<Identifier, Item> registrar) {
        ACACIA_STICK = register("acacia_stick", Item::new);
        BAMBOO_STICK = register("bamboo_stick", Item::new);
        BIRCH_STICK = register("birch_stick", Item::new);
        CHERRY_STICK = register("cherry_stick", Item::new);
        CRIMSON_STICK = register("crimson_stick", Item::new);
        DARK_OAK_STICK = register("dark_oak_stick", Item::new);
        JUNGLE_STICK = register("jungle_stick", Item::new);
        MANGROVE_STICK = register("mangrove_stick", Item::new);
        SPRUCE_STICK = register("spruce_stick", Item::new);
        WARPED_STICK = register("warped_stick", Item::new);

        STICKS.addAll(Arrays.asList(ACACIA_STICK, BAMBOO_STICK, BIRCH_STICK, CHERRY_STICK, CRIMSON_STICK, DARK_OAK_STICK, JUNGLE_STICK, MANGROVE_STICK, SPRUCE_STICK, WARPED_STICK));

        ACACIA_POLE = register("acacia_pole", Item::new);
        BAMBOO_POLE = register("bamboo_pole", Item::new);
        BIRCH_POLE = register("birch_pole", Item::new);
        CHERRY_POLE = register("cherry_pole", Item::new);
        CRIMSON_POLE = register("crimson_pole", Item::new);
        DARK_OAK_POLE = register("dark_oak_pole", Item::new);
        JUNGLE_POLE = register("jungle_pole", Item::new);
        MANGROVE_POLE = register("mangrove_pole", Item::new);
        OAK_POLE = register("oak_pole", Item::new);
        SPRUCE_POLE = register("spruce_pole", Item::new);
        WARPED_POLE = register("warped_pole", Item::new);

        for (Map.Entry<String, ToolMaterial> toolMaterial : WEAPON_MATERIALS.entrySet()) {
            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (WeaponType type : TYPES) {
                    String fullName = getFormattedName(toolMaterial.getKey(), woodHandle, type.suffix());

                    Item.Properties settings = createWeaponSettings(toolMaterial.getValue(), type);
                    if (toolMaterial.getValue() == ToolMaterial.NETHERITE) {
                        settings = settings.fireResistant();
                    }

                    register(fullName, properties -> type.constructor().create(properties, fullName), settings);
                }
            }
        }
        for (String woodHandle : ItemInit.WOOD_MATERIALS) {
            register(woodHandle + "_spear", Spear::new, new Item.Properties().sword(ToolMaterial.WOOD, ConfigInit.CONFIG.wooden_spear_attack_damage, ConfigInit.CONFIG.wooden_spear_attack_speed).attributes(createAttributes(ToolMaterial.WOOD, ConfigInit.CONFIG.wooden_spear_attack_damage, ConfigInit.CONFIG.wooden_spear_attack_speed).add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.wooden_spear_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()));
            Item.Properties clubSettings = new Item.Properties().sword(ToolMaterial.WOOD, ConfigInit.CONFIG.club_attack_damage, ConfigInit.CONFIG.club_attack_speed).attributes(createAttributes(ToolMaterial.WOOD, ConfigInit.CONFIG.club_attack_damage, ConfigInit.CONFIG.club_attack_speed).build());
            if (ConfigInit.CONFIG.club_disables_shield) {
                clubSettings = clubSettings.component(DataComponents.WEAPON, new Weapon(1, Weapon.AXE_DISABLES_BLOCKING_FOR_SECONDS));
            }
            register(woodHandle + "_club", Club::new, clubSettings);
            register(woodHandle + "_staff", Staff::new, new Item.Properties().sword(ToolMaterial.WOOD, ConfigInit.CONFIG.staff_attack_damage, ConfigInit.CONFIG.staff_attack_speed).attributes(createAttributes(ToolMaterial.WOOD, ConfigInit.CONFIG.staff_attack_damage, ConfigInit.CONFIG.staff_attack_speed).add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.staff_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()));
        }

        ITEMS.forEach(registrar);
    }

    public static CreativeModeTab createItemGroup(CreativeModeTab.Builder builder) {
        return builder.icon(() -> new ItemStack(OAK_POLE)).title(Component.translatable("item.spartanweapons.item_group")).build();
    }

    public static String getFormattedName(String matName, String wood, String suffix) {
        if (matName.equals("gold")) {
            matName = "golden";
        }
        return matName + "_" + wood + "_" + suffix;
    }

    private static ItemAttributeModifiers.Builder createAttributes(ToolMaterial mat, float damage, float speed) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, damage + mat.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
    }

    private static Item.Properties createWeaponSettings(ToolMaterial mat, WeaponType type) {
        ItemAttributeModifiers.Builder modifiers = createAttributes(mat, type.damage(), type.speed());

        if (type.attribute() != null && type.modifier() != null) {
            modifiers = modifiers.add(type.attribute(), type.modifier(), type.slot());
        }
        if (type.knockback() != 0.0f) {
            modifiers = modifiers.add(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(KNOCKBACK_BONUS_MODIFIER_ID, type.knockback(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        }

        Item.Properties settings = new Item.Properties().sword(mat, type.damage(), type.speed()).attributes(modifiers.build());
        if (type.suffix().equals("mace") && ConfigInit.CONFIG.mace_disables_shield) {
            settings = settings.component(DataComponents.WEAPON, new Weapon(1, Weapon.AXE_DISABLES_BLOCKING_FOR_SECONDS));
        }
        return settings;
    }

}
