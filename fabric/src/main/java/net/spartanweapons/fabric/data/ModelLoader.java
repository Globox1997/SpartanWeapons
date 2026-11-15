package net.spartanweapons.fabric.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.init.ItemInit;

import java.util.Optional;

public class ModelLoader extends FabricModelProvider {

    private static final String itemModelPath = "item/";

    public ModelLoader(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

        for (Item stick : ItemInit.STICKS) {
            String name = BuiltInRegistries.ITEM.getKey(stick).getPath();
            generateItem(itemModelGenerator, name, ModelTemplates.FLAT_HANDHELD_ITEM, TextureMapping.layer0(texture("sticks/" + name)));
        }
        for (String woodHandle : ItemInit.WOOD_MATERIALS) {
            generateItem(itemModelGenerator, woodHandle + "_pole", ModelTemplates.FLAT_HANDHELD_ITEM, TextureMapping.layer0(texture("poles/" + woodHandle + "_pole")));
            generateItem(itemModelGenerator, woodHandle + "_club", ModelTemplates.FLAT_HANDHELD_ITEM, TextureMapping.layer0(texture("clubs/" + woodHandle + "_club")));
            generateItem(itemModelGenerator, woodHandle + "_spear", parentTemplate("spear", TextureSlot.LAYER0), TextureMapping.layer0(texture("spears/" + woodHandle + "_spear")));
            generateItem(itemModelGenerator, woodHandle + "_staff", parentTemplate("staff", TextureSlot.LAYER0), TextureMapping.layer0(texture("staffs/" + woodHandle + "_staff")));
        }

        for (String material : ItemInit.WEAPON_MATERIALS.keySet()) {
            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (ItemInit.WeaponType type : ItemInit.TYPES) {
                    generateWeapon(itemModelGenerator, type.suffix(), material, woodHandle);
                }
            }
        }
    }

    private static void generateWeapon(ItemModelGenerators itemModelGenerator, String item, String material, String woodHandle) {
        String name = ItemInit.getFormattedName(material, woodHandle, item);
        String layer0 = (material.equals("gold") ? "golden" : material) + "_" + item;
        String layer1 = "alt_wood/" + item + "_" + woodHandle;
        String path = item.replace("battle_axe", "battleaxe") + (item.equals("cutlass") ? "es" : "s") + "/";
        TextureMapping textures = TextureMapping.layered(texture(path + layer0), texture(path + layer1));

        Identifier model = parentTemplate(item, TextureSlot.LAYER0, TextureSlot.LAYER1).create(SpartanWeaponsMain.identifierOf(itemModelPath + name), textures, itemModelGenerator.modelOutput);
        Item weapon = BuiltInRegistries.ITEM.getValue(SpartanWeaponsMain.identifierOf(name));
        if (item.equals("javelin")) {
            Identifier throwingModel = parentTemplate(item + "_throwing", TextureSlot.LAYER0, TextureSlot.LAYER1).create(SpartanWeaponsMain.identifierOf(itemModelPath + name + "_throwing"), textures, itemModelGenerator.modelOutput);
            itemModelGenerator.itemModelOutput.accept(weapon, ItemModelUtils.conditional(ItemModelUtils.isUsingItem(), ItemModelUtils.plainModel(throwingModel), ItemModelUtils.plainModel(model)));
        } else {
            itemModelGenerator.itemModelOutput.accept(weapon, ItemModelUtils.plainModel(model));
        }
    }

    private static void generateItem(ItemModelGenerators itemModelGenerator, String name, ModelTemplate template, TextureMapping textures) {
        Identifier model = template.create(SpartanWeaponsMain.identifierOf(itemModelPath + name), textures, itemModelGenerator.modelOutput);
        itemModelGenerator.itemModelOutput.accept(BuiltInRegistries.ITEM.getValue(SpartanWeaponsMain.identifierOf(name)), ItemModelUtils.plainModel(model));
    }

    private static ModelTemplate parentTemplate(String parent, TextureSlot... slots) {
        return new ModelTemplate(Optional.of(SpartanWeaponsMain.identifierOf(itemModelPath + parent)), Optional.empty(), slots);
    }

    private static Material texture(String path) {
        return new Material(SpartanWeaponsMain.identifierOf(itemModelPath + path));
    }

}
