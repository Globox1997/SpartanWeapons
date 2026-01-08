package net.spartanweapons.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.init.ItemInit;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModelLoader extends FabricModelProvider {

    public static final Model HANDHELD_TWO_LAYERS = new Model(Optional.of(Identifier.ofVanilla("item/handheld")), Optional.empty(), TextureKey.LAYER0, TextureKey.LAYER1);

    private static final String itemModelPath = "item/";

    public ModelLoader(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        for (Item stick : ItemInit.STICKS) {
            Models.HANDHELD.upload(SpartanWeaponsMain.identifierOf(itemModelPath + Registries.ITEM.getId(stick).getPath()), TextureMap.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "sticks/" + Registries.ITEM.getId(stick).getPath())), itemModelGenerator.writer);
        }
        for (String woodHandle : ItemInit.WOOD_MATERIALS) {
            Models.HANDHELD.upload(SpartanWeaponsMain.identifierOf(itemModelPath + woodHandle + "_pole"), TextureMap.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "poles/" + woodHandle + "_pole")), itemModelGenerator.writer);
            Models.HANDHELD.upload(SpartanWeaponsMain.identifierOf(itemModelPath + woodHandle + "_club"), TextureMap.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "clubs/" + woodHandle + "_club")), itemModelGenerator.writer);
            Models.HANDHELD.upload(SpartanWeaponsMain.identifierOf(itemModelPath + woodHandle + "_spear"), TextureMap.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "spears/" + woodHandle + "_spear")), itemModelGenerator.writer, (id, textures) -> createPredicateJson(id, textures, "spear", "", "", false, false, false, false));
            Models.HANDHELD.upload(SpartanWeaponsMain.identifierOf(itemModelPath + woodHandle + "_staff"), TextureMap.layer0(SpartanWeaponsMain.identifierOf(itemModelPath + "staffs/" + woodHandle + "_staff")), itemModelGenerator.writer, (id, textures) -> createPredicateJson(id, textures, "staff", "", "", false, false, false, false));
        }

        for (ToolMaterials toolMaterial : ToolMaterials.values()) {
            if (toolMaterial.equals(ToolMaterials.WOOD) || toolMaterial.equals(ToolMaterials.STONE)) {
                continue;
            }
            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (ItemInit.WeaponType type : ItemInit.TYPES) {
                    String name = toolMaterial.name().toLowerCase() + (toolMaterial.name().toLowerCase().equals("gold") ? "en" : "") + "_" + woodHandle + "_" + type.suffix();
                    Model model = getItemModel(type.suffix(), toolMaterial.name().toLowerCase(), woodHandle, itemModelGenerator.writer);
                    if (model != null) {
                        itemModelGenerator.register(Registries.ITEM.get(SpartanWeaponsMain.identifierOf(name)), model);
                    }
                }
            }
        }
    }


    @Nullable
    private static Model getItemModel(String item, String material, String woodHandle, BiConsumer<Identifier, Supplier<JsonElement>> writer) {
        String name = material + (material.equals("gold") ? "en" : "") + "_" + woodHandle + "_" + item;
        String layer0 = material + (material.equals("gold") ? "en" : "") + "_" + item;
        String layer1 = "alt_wood/" + item + "_" + woodHandle;
        String path = itemModelPath + item.replace("battle_axe", "battleaxe") + (item.equals("cutlass") ? "es" : "s") + "/";

        switch (item) {
            case "cutlass":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "dagger":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "glaive":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "greatsword":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "halberd":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "katana":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "mace":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "rapier":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "scythe":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "spear":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "battle_axe":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "kunai":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, false, true, false));
                return null;
            case "javelin":
                HANDHELD_TWO_LAYERS.upload(SpartanWeaponsMain.identifierOf(itemModelPath + name), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer, (id, textures) -> createPredicateJson(id, textures, item, material, woodHandle, true, true, false, false));
                new Model(Optional.of(SpartanWeaponsMain.identifierOf(itemModelPath + item + "_throwing")), Optional.empty(), TextureKey.LAYER0, TextureKey.LAYER1).upload(SpartanWeaponsMain.identifierOf(itemModelPath + name + "_throwing"), TextureMap.layered(SpartanWeaponsMain.identifierOf(path + layer0), SpartanWeaponsMain.identifierOf(path + layer1)), writer);
                return null;
            default:
                return null;
        }
    }

    private static JsonObject createPredicateJson(Identifier id, Map<TextureKey, Identifier> textures, String item, String material, String woodHandle, boolean gui, boolean throwing, boolean extraLayer, boolean bettercombat) {
        JsonObject jsonObject;
        if (extraLayer) {
            jsonObject = new Model(Optional.of(SpartanWeaponsMain.identifierOf("item/" + item)), Optional.empty(), TextureKey.LAYER0, TextureKey.LAYER1).createJson(id, textures);
        } else {
            jsonObject = new Model(Optional.of(SpartanWeaponsMain.identifierOf("item/" + item)), Optional.empty(), TextureKey.LAYER0).createJson(id, textures);
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
