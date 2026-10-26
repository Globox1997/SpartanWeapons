package net.spartanweapons.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.init.ItemInit;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModelLoader extends FabricModelProvider {

    public static final ModelTemplate HANDHELD_TWO_LAYERS = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("item/handheld")), Optional.empty(), TextureSlot.LAYER0, TextureSlot.LAYER1);

    private static final String itemModelPath = "item/";

    public ModelLoader(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

        for (Item stick : ItemInit.STICKS) {
            ModelTemplates.FLAT_HANDHELD_ITEM.create(SpartanWeaponsMain.identifierOf(itemModelPath + BuiltInRegistries.ITEM.getKey(stick).getPath()), TextureMapping.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "sticks/" + BuiltInRegistries.ITEM.getKey(stick).getPath())), itemModelGenerator.output);
        }
        for (String woodHandle : ItemInit.WOOD_MATERIALS) {
            ModelTemplates.FLAT_HANDHELD_ITEM.create(SpartanWeaponsMain.identifierOf(itemModelPath + woodHandle + "_pole"), TextureMapping.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "poles/" + woodHandle + "_pole")), itemModelGenerator.output);
            ModelTemplates.FLAT_HANDHELD_ITEM.create(SpartanWeaponsMain.identifierOf(itemModelPath + woodHandle + "_club"), TextureMapping.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "clubs/" + woodHandle + "_club")), itemModelGenerator.output);
            ModelTemplates.FLAT_HANDHELD_ITEM.create(SpartanWeaponsMain.identifierOf(itemModelPath + woodHandle + "_spear"), TextureMapping.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "spears/" + woodHandle + "_spear")), itemModelGenerator.output, (id, textures) -> createPredicateJson(id, textures, "spear", "", "", false, false, false, false));
            ModelTemplates.FLAT_HANDHELD_ITEM.create(SpartanWeaponsMain.identifierOf(itemModelPath + woodHandle + "_staff"), TextureMapping.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "staffs/" + woodHandle + "_staff")), itemModelGenerator.output, (id, textures) -> createPredicateJson(id, textures, "staff", "", "", false, false, false, false));
        }

        for (Tiers toolMaterial : Tiers.values()) {
            if (toolMaterial.equals(Tiers.WOOD) || toolMaterial.equals(Tiers.STONE)) {
                continue;
            }
            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (ItemInit.WeaponType type : ItemInit.TYPES) {
                    String name = toolMaterial.name().toLowerCase() + (toolMaterial.name().toLowerCase().equals("gold") ? "en" : "") + "_" + woodHandle + "_" + type.suffix();
                    ModelTemplate model = getItemModel(type.suffix(), toolMaterial.name().toLowerCase(), woodHandle, itemModelGenerator.output);
                    if (model != null) {
                        itemModelGenerator.generateFlatItem(BuiltInRegistries.ITEM.get(SpartanWeaponsMain.identifierOf(name)), model);
                    }
                }
            }
        }
    }


    @Nullable
    private static ModelTemplate getItemModel(String item, String material, String woodHandle, BiConsumer<ResourceLocation, Supplier<JsonElement>> writer) {
        String name = material + (material.equals("gold") ? "en" : "") + "_" + woodHandle + "_" + item;
        String layer0 = material + (material.equals("gold") ? "en" : "") + "_" + item;
        String layer1 = "alt_wood/" + item + "_" + woodHandle;
        String path = itemModelPath + item.replace("battle_axe", "battleaxe") + (item.equals("cutlass") ? "es" : "s") + "/";

        switch (item) {
            case "cutlass":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "dagger":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "glaive":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "greatsword":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "halberd":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "katana":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "mace":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "rapier":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "scythe":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "spear":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "battle_axe":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "kunai":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "javelin":
                HANDHELD_TWO_LAYERS.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, true, false, false));
                new ModelTemplate(Optional.of(SpartanWeaponsMain.identifierOf(itemModelPath + item + "_throwing")), Optional.empty(), TextureSlot.LAYER0, TextureSlot.LAYER1).create(SpartanWeaponsMain.identifierOf(itemModelPath + name + "_throwing"), TextureMapping.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer);
                return null;
            default:
                return null;
        }
    }

    private static JsonObject createPredicateJson(ResourceLocation id, Map<TextureSlot, ResourceLocation> textures, String item, String material, String woodHandle, boolean gui, boolean throwing, boolean extraLayer, boolean bettercombat) {
        JsonObject jsonObject;
        if (extraLayer) {
            jsonObject = new ModelTemplate(Optional.of(SpartanWeaponsMain.identifierOf("item/" + item)), Optional.empty(), TextureSlot.LAYER0, TextureSlot.LAYER1).createBaseTemplate(id, textures);
        } else {
            jsonObject = new ModelTemplate(Optional.of(SpartanWeaponsMain.identifierOf("item/" + item)), Optional.empty(), TextureSlot.LAYER0).createBaseTemplate(id, textures);
        }

        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject2;
        JsonObject jsonObject3;

        material = material + (material.equals("gold") ? "en" : "");

        if (throwing) {
            jsonObject2 = new JsonObject();
            jsonObject3 = new JsonObject();
            jsonObject3.addProperty("throwing", 1.0f);
            jsonObject2.add("predicate", jsonObject3);
            jsonObject2.addProperty("model", "spartanweapons:item/" + material + "_" + woodHandle + "_" + item + "_throwing");
            jsonArray.add(jsonObject2);
        }
        jsonObject.add("overrides", jsonArray);
        return jsonObject;
    }


}
