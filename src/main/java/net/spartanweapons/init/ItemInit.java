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
            String[] pattern
    ) {
    }

    @FunctionalInterface
    interface WeaponFactory {
        Item create(ToolMaterial material, Item.Settings settings, String fullId);
    }

    public static final List<WeaponType> TYPES = List.of(
            new WeaponType("battle_axe", (material, settings, id) -> new BattleAxe(material, settings), 4, -2.5f, false, null, null, null, new String[]{"iii", "ipi", " s "}),
            new WeaponType("dagger", (material, settings, id) -> new Dagger(material, settings), 0, -1.6f, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, -0.5f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"i", "s"}),
            new WeaponType("glaive", (material, settings, id) -> new Glaive(material, settings), 4, -2.6f, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, 2f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"i", "i", "p"}),
            new WeaponType("greatsword", (material, settings, id) -> new GreatSword(material, settings), 7, -3.2f, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, 2f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" i ", " i ", "isi"}),
            new WeaponType("halberd", (material, settings, id) -> new Halberd(material, settings), 6, -3.0f, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, 2f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" i", "ii", "is"}),
            new WeaponType("katana", (material, settings, id) -> new Katana(material, settings), 4, -2.3f, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, 1f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" i", "ii", "s "}),
            new WeaponType("mace", (material, settings, id) -> new Mace(material, settings), 9, -3.2f, false, null, null, null, new String[]{"b", "i", "s"}),
            new WeaponType("rapier", (material, settings, id) -> new Rapier(material, settings), 1, -2.0f, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, 0.5f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"  i", " i ", "s  "}),
            new WeaponType("scythe", (material, settings, id) -> new Scythe(material, settings), 6, -3.0f, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, 1.5f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" ii", "iip", "i s"}),
            new WeaponType("spear", (material, settings, id) -> new Spear(material, settings), 5, -3.0f, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, 2.5f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"i", "p"}),
            new WeaponType("kunai", (material, settings, id) ->
                    new Kunai(material, () -> (EntityType<KunaiEntity>) EntityInit.ENTITY_TYPES.get(SpartanWeaponsMain.identifierOf(id)), settings), 0, -1.8f, true, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, -0.5f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"xi", "s "}),
            new WeaponType("javelin", (material, settings, id) ->
                    new Javelin(material, () -> (EntityType<JavelinEntity>) EntityInit.ENTITY_TYPES.get(SpartanWeaponsMain.identifierOf(id)), settings), 2, -2.4f, true, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, 1.0f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{"i", "x", "p"}),
            new WeaponType("cutlass", (material, settings, id) -> new Cutlass(material, settings), 5, -2.8f, false, EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE, new EntityAttributeModifier(ATTACK_BONUS_MODIFIER_ID, 1.0f, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND, new String[]{" ii", "i  ", "s  "})
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
            register(woodHandle + "_spear", new Spear(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(Spear.createAttributeModifiers(ToolMaterials.WOOD, 3, -3.0f))));
            register(woodHandle + "_club", new Club(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(Club.createAttributeModifiers(ToolMaterials.WOOD, 4, -3.2f))));
            register(woodHandle + "_staff", new Staff(ToolMaterials.WOOD, new Item.Settings().attributeModifiers(Staff.createAttributeModifiers(ToolMaterials.WOOD, 2, -2.4f))));
        }

        Registry.register(Registries.ITEM_GROUP, SPARTANWEAPONS_ITEM_GROUP,
                FabricItemGroup.builder().icon(() -> new ItemStack(OAK_POLE)).displayName(Text.translatable("item.spartanweapons.item_group")).build());

        for (Identifier id : ITEMS.keySet()) {
            Registry.register(Registries.ITEM, id, ITEMS.get(id));
            ItemGroupEvents.modifyEntriesEvent(SPARTANWEAPONS_ITEM_GROUP).register(entries -> entries.add(ITEMS.get(id)));
        }
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

        return new Item.Settings().attributeModifiers(modifiers);
    }

    private static void registerWeaponEntity(String id, Item item) {
        EntityType type = null;
        if (item instanceof Javelin) {
            type = EntityType.Builder.<JavelinEntity>create(
                    (entity, world) -> new JavelinEntity(entity, world, (Javelin) item),
                    SpawnGroup.MISC).dimensions(0.5F, 0.5F).build();
        } else if (item instanceof Kunai) {
            type = EntityType.Builder.<KunaiEntity>create(
                    (entity, world) -> new KunaiEntity(entity, world, (Kunai) item),
                    SpawnGroup.MISC).dimensions(0.5F, 0.5F).build();
        }
        EntityInit.register(id, type);
    }

}
