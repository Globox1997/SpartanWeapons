package net.spartanweapons.init;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.MobCategory;
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
import org.jetbrains.annotations.Nullable;

import java.util.*;
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
    public static final Item ACACIA_STICK = register("acacia_stick", Item::new);
    public static final Item BAMBOO_STICK = register("bamboo_stick", Item::new);
    public static final Item BIRCH_STICK = register("birch_stick", Item::new);
    public static final Item CHERRY_STICK = register("cherry_stick", Item::new);
    public static final Item CRIMSON_STICK = register("crimson_stick", Item::new);
    public static final Item DARK_OAK_STICK = register("dark_oak_stick", Item::new);
    public static final Item JUNGLE_STICK = register("jungle_stick", Item::new);
    public static final Item MANGROVE_STICK = register("mangrove_stick", Item::new);
    public static final Item SPRUCE_STICK = register("spruce_stick", Item::new);
    public static final Item WARPED_STICK = register("warped_stick", Item::new);

    public static final ArrayList<Item> STICKS = new ArrayList<>(Arrays.asList(ItemInit.ACACIA_STICK, ItemInit.BAMBOO_STICK, ItemInit.BIRCH_STICK, ItemInit.CHERRY_STICK,
            ItemInit.CRIMSON_STICK, ItemInit.DARK_OAK_STICK, ItemInit.JUNGLE_STICK, ItemInit.MANGROVE_STICK, ItemInit.SPRUCE_STICK, ItemInit.WARPED_STICK));

    // Poles
    public static final Item ACACIA_POLE = register("acacia_pole", Item::new);
    public static final Item BAMBOO_POLE = register("bamboo_pole", Item::new);
    public static final Item BIRCH_POLE = register("birch_pole", Item::new);
    public static final Item CHERRY_POLE = register("cherry_pole", Item::new);
    public static final Item CRIMSON_POLE = register("crimson_pole", Item::new);
    public static final Item DARK_OAK_POLE = register("dark_oak_pole", Item::new);
    public static final Item JUNGLE_POLE = register("jungle_pole", Item::new);
    public static final Item MANGROVE_POLE = register("mangrove_pole", Item::new);
    public static final Item OAK_POLE = register("oak_pole", Item::new);
    public static final Item SPRUCE_POLE = register("spruce_pole", Item::new);
    public static final Item WARPED_POLE = register("warped_pole", Item::new);

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
                    new Javelin(() -> (EntityType<JavelinEntity>) EntityInit.ENTITY_TYPES.get(SpartanWeaponsMain.identifierOf(id)), settings), ConfigInit.CONFIG.javelin_attack_damage, ConfigInit.CONFIG.javelin_attack_speed, true, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.javelin_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"i", "x", "p"}, 0.0f),
            new WeaponType("cutlass", (settings, id) -> new Cutlass(settings), ConfigInit.CONFIG.cutlass_attack_damage, ConfigInit.CONFIG.cutlass_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.cutlass_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" ii", "i  ", "s  "}, 0.0f)
    );


    public static void init() {
        for (Map.Entry<String, ToolMaterial> toolMaterial : WEAPON_MATERIALS.entrySet()) {
            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (WeaponType type : TYPES) {
                    String fullName = getFormattedName(toolMaterial.getKey(), woodHandle, type.suffix());

                    Item.Properties settings = createWeaponSettings(toolMaterial.getValue(), type);
                    if (toolMaterial.getValue() == ToolMaterial.NETHERITE) settings = settings.fireResistant();

                    Item weapon = register(fullName, properties -> type.constructor().create(properties, fullName), settings);

                    if (type.hasEntity()) {
                        registerWeaponEntity(fullName, weapon);
                    }
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

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SPARTANWEAPONS_ITEM_GROUP,
                FabricCreativeModeTab.builder().icon(() -> new ItemStack(OAK_POLE)).title(Component.translatable("item.spartanweapons.item_group")).build());

        for (Identifier id : ITEMS.keySet()) {
            Registry.register(BuiltInRegistries.ITEM, id, ITEMS.get(id));
        }
        CreativeModeTabEvents.modifyOutputEvent(SPARTANWEAPONS_ITEM_GROUP).register(entries -> ITEMS.values().forEach(entries::accept));
    }

    public static String getFormattedName(String matName, String wood, String suffix) {
        if (matName.equals("gold")) matName = "golden";
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

    private static void registerWeaponEntity(String id, Item item) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, SpartanWeaponsMain.identifierOf(id));
        EntityType type = null;
        if (item instanceof Javelin) {
            type = EntityType.Builder.<JavelinEntity>of(
                    JavelinEntity::new,
                    MobCategory.MISC).sized(0.5F, 0.5F).build(key);
        } else if (item instanceof Kunai) {
            type = EntityType.Builder.<KunaiEntity>of(
                    KunaiEntity::new,
                    MobCategory.MISC).sized(0.5F, 0.5F).build(key);
        }
        EntityInit.register(id, type);
    }

}
