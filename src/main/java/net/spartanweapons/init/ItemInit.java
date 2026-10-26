package net.spartanweapons.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.entity.JavelinEntity;
import net.spartanweapons.entity.KunaiEntity;
import net.spartanweapons.item.*;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ItemInit {

    // Item Group
    public static final ResourceKey<CreativeModeTab> SPARTANWEAPONS_ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, SpartanWeaponsMain.identifierOf("item_group"));
    // Bonus
    public static final ResourceLocation ATTACK_BONUS_MODIFIER_ID = SpartanWeaponsMain.identifierOf("attack_bonus");
    public static final ResourceLocation KNOCKBACK_BONUS_MODIFIER_ID = SpartanWeaponsMain.identifierOf("knockback_bonus");
    // Lists
    public static final ArrayList<String> WOOD_MATERIALS = new ArrayList<>(
            Arrays.asList("acacia", "bamboo", "birch", "cherry", "crimson", "dark_oak", "jungle", "mangrove", "oak", "spruce", "warped"));
    // Map
    public static final Map<ResourceLocation, Item> ITEMS = new LinkedHashMap<>();

    // Sticks
    public static final Item ACACIA_STICK = register("acacia_stick", new Item(new Item.Properties()));
    public static final Item BAMBOO_STICK = register("bamboo_stick", new Item(new Item.Properties()));
    public static final Item BIRCH_STICK = register("birch_stick", new Item(new Item.Properties()));
    public static final Item CHERRY_STICK = register("cherry_stick", new Item(new Item.Properties()));
    public static final Item CRIMSON_STICK = register("crimson_stick", new Item(new Item.Properties()));
    public static final Item DARK_OAK_STICK = register("dark_oak_stick", new Item(new Item.Properties()));
    public static final Item JUNGLE_STICK = register("jungle_stick", new Item(new Item.Properties()));
    public static final Item MANGROVE_STICK = register("mangrove_stick", new Item(new Item.Properties()));
    public static final Item SPRUCE_STICK = register("spruce_stick", new Item(new Item.Properties()));
    public static final Item WARPED_STICK = register("warped_stick", new Item(new Item.Properties()));

    public static final ArrayList<Item> STICKS = new ArrayList<>(Arrays.asList(ItemInit.ACACIA_STICK, ItemInit.BAMBOO_STICK, ItemInit.BIRCH_STICK, ItemInit.CHERRY_STICK,
            ItemInit.CRIMSON_STICK, ItemInit.DARK_OAK_STICK, ItemInit.JUNGLE_STICK, ItemInit.MANGROVE_STICK, ItemInit.SPRUCE_STICK, ItemInit.WARPED_STICK));

    // Poles
    public static final Item ACACIA_POLE = register("acacia_pole", new Item(new Item.Properties()));
    public static final Item BAMBOO_POLE = register("bamboo_pole", new Item(new Item.Properties()));
    public static final Item BIRCH_POLE = register("birch_pole", new Item(new Item.Properties()));
    public static final Item CHERRY_POLE = register("cherry_pole", new Item(new Item.Properties()));
    public static final Item CRIMSON_POLE = register("crimson_pole", new Item(new Item.Properties()));
    public static final Item DARK_OAK_POLE = register("dark_oak_pole", new Item(new Item.Properties()));
    public static final Item JUNGLE_POLE = register("jungle_pole", new Item(new Item.Properties()));
    public static final Item MANGROVE_POLE = register("mangrove_pole", new Item(new Item.Properties()));
    public static final Item OAK_POLE = register("oak_pole", new Item(new Item.Properties()));
    public static final Item SPRUCE_POLE = register("spruce_pole", new Item(new Item.Properties()));
    public static final Item WARPED_POLE = register("warped_pole", new Item(new Item.Properties()));

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
            @Nullable Holder<Attribute> attribute,
            @Nullable AttributeModifier modifier,
            @Nullable EquipmentSlotGroup slot,
            String[] pattern,
            float knockback
    ) {
    }

    @FunctionalInterface
    interface WeaponFactory {
        Item create(Tier material, Item.Properties settings, String fullId);
    }

    public static final List<WeaponType> TYPES = List.of(
            new WeaponType("battle_axe", (material, settings, id) -> new BattleAxe(material, settings), ConfigInit.CONFIG.battle_axe_attack_damage, ConfigInit.CONFIG.battle_axe_attack_speed, false, null, null, null, new String[]{"iii", "ipi", " s "}, 0.0f),
            new WeaponType("dagger", (material, settings, id) -> new Dagger(material, settings), ConfigInit.CONFIG.dagger_attack_damage, ConfigInit.CONFIG.dagger_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.dagger_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"i", "s"}, 0.0f),
            new WeaponType("glaive", (material, settings, id) -> new Glaive(material, settings), ConfigInit.CONFIG.glaive_attack_damage, ConfigInit.CONFIG.glaive_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.glaive_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"i", "i", "p"}, ConfigInit.CONFIG.glaive_knockback),
            new WeaponType("greatsword", (material, settings, id) -> new GreatSword(material, settings), ConfigInit.CONFIG.greatsword_attack_damage, ConfigInit.CONFIG.greatsword_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.greatsword_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" i ", " i ", "isi"}, 0.0f),
            new WeaponType("halberd", (material, settings, id) -> new Halberd(material, settings), ConfigInit.CONFIG.halberd_attack_damage, ConfigInit.CONFIG.halberd_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.halberd_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" i", "ii", "is"}, ConfigInit.CONFIG.halberd_knockback),
            new WeaponType("katana", (material, settings, id) -> new Katana(material, settings), ConfigInit.CONFIG.katana_attack_damage, ConfigInit.CONFIG.katana_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.katana_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" i", "ii", "s "}, 0.0f),
            new WeaponType("mace", (material, settings, id) -> new Mace(material, settings), ConfigInit.CONFIG.mace_attack_damage, ConfigInit.CONFIG.mace_attack_speed, false, null, null, null, new String[]{"b", "i", "s"}, 0.0f),
            new WeaponType("rapier", (material, settings, id) -> new Rapier(material, settings), ConfigInit.CONFIG.rapier_attack_damage, ConfigInit.CONFIG.rapier_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.rapier_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"  i", " i ", "s  "}, 0.0f),
            new WeaponType("scythe", (material, settings, id) -> new Scythe(material, settings), ConfigInit.CONFIG.scythe_attack_damage, ConfigInit.CONFIG.scythe_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.scythe_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" ii", "iip", "i s"}, 0.0f),
            new WeaponType("spear", (material, settings, id) -> new Spear(material, settings), ConfigInit.CONFIG.spear_attack_damage, ConfigInit.CONFIG.spear_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.spear_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"i", "p"}, 0.0f),
            new WeaponType("kunai", (material, settings, id) ->
                    new Kunai(material, () -> (EntityType<KunaiEntity>) EntityInit.ENTITY_TYPES.get(SpartanWeaponsMain.identifierOf(id)), settings), ConfigInit.CONFIG.kunai_attack_damage, ConfigInit.CONFIG.kunai_attack_speed, true, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.kunai_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"xi", "s "}, 0.0f),
            new WeaponType("javelin", (material, settings, id) ->
                    new Javelin(material, () -> (EntityType<JavelinEntity>) EntityInit.ENTITY_TYPES.get(SpartanWeaponsMain.identifierOf(id)), settings), ConfigInit.CONFIG.javelin_attack_damage, ConfigInit.CONFIG.javelin_attack_speed, true, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.javelin_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{"i", "x", "p"}, 0.0f),
            new WeaponType("cutlass", (material, settings, id) -> new Cutlass(material, settings), ConfigInit.CONFIG.cutlass_attack_damage, ConfigInit.CONFIG.cutlass_attack_speed, false, Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.cutlass_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND, new String[]{" ii", "i  ", "s  "}, 0.0f)
    );


    public static void init() {
        for (Tiers toolMaterial : Tiers.values()) {
            if (toolMaterial == Tiers.WOOD || toolMaterial == Tiers.STONE) continue;

            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (WeaponType type : TYPES) {
                    String fullName = getFormattedName(toolMaterial, woodHandle, type.suffix());

                    Item.Properties settings = createWeaponSettings(toolMaterial, type);
                    if (toolMaterial == Tiers.NETHERITE) settings = settings.fireResistant();

                    Item weapon = type.constructor().create(toolMaterial, settings, fullName);
                    register(fullName, weapon);

                    if (type.hasEntity()) {
                        registerWeaponEntity(fullName, weapon);
                    }
                }
            }
        }
        for (String woodHandle : ItemInit.WOOD_MATERIALS) {
            register(woodHandle + "_spear", new Spear(Tiers.WOOD, new Item.Properties().attributes(Spear.createAttributes(Tiers.WOOD, ConfigInit.CONFIG.wooden_spear_attack_damage, ConfigInit.CONFIG.wooden_spear_attack_speed).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.wooden_spear_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))));
            register(woodHandle + "_club", new Club(Tiers.WOOD, new Item.Properties().attributes(Club.createAttributes(Tiers.WOOD, ConfigInit.CONFIG.club_attack_damage, ConfigInit.CONFIG.club_attack_speed))));
            register(woodHandle + "_staff", new Staff(Tiers.WOOD, new Item.Properties().attributes(Staff.createAttributes(Tiers.WOOD, ConfigInit.CONFIG.staff_attack_damage, ConfigInit.CONFIG.staff_attack_speed).withModifierAdded(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(ATTACK_BONUS_MODIFIER_ID, ConfigInit.CONFIG.staff_range, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND))));
        }

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SPARTANWEAPONS_ITEM_GROUP,
                FabricItemGroup.builder().icon(() -> new ItemStack(OAK_POLE)).title(Component.translatable("item.spartanweapons.item_group")).build());

        for (ResourceLocation id : ITEMS.keySet()) {
            Registry.register(BuiltInRegistries.ITEM, id, ITEMS.get(id));
        }
        ItemGroupEvents.modifyEntriesEvent(SPARTANWEAPONS_ITEM_GROUP).register(entries -> ITEMS.values().forEach(entries::accept));
    }

    public static String getFormattedName(Tiers mat, String wood, String suffix) {
        String matName = mat.name().toLowerCase();
        if (matName.equals("gold")) matName = "golden";
        return matName + "_" + wood + "_" + suffix;
    }

    private static Item.Properties createWeaponSettings(Tier mat, WeaponType type) {
        ItemAttributeModifiers modifiers = SwordItem.createAttributes(mat, type.damage(), type.speed());

        if (type.attribute() != null && type.modifier() != null) {
            modifiers = modifiers.withModifierAdded(type.attribute(), type.modifier(), type.slot());
        }
        if (type.knockback() != 0.0f) {
            modifiers = modifiers.withModifierAdded(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(KNOCKBACK_BONUS_MODIFIER_ID, type.knockback(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        }

        return new Item.Properties().attributes(modifiers);
    }

    private static void registerWeaponEntity(String id, Item item) {
        EntityType type = null;
        if (item instanceof Javelin) {
            type = EntityType.Builder.<JavelinEntity>of(
                    JavelinEntity::new,
                    MobCategory.MISC).sized(0.5F, 0.5F).build();
        } else if (item instanceof Kunai) {
            type = EntityType.Builder.<KunaiEntity>of(
                    KunaiEntity::new,
                    MobCategory.MISC).sized(0.5F, 0.5F).build();
        }
        EntityInit.register(id, type);
    }

}
