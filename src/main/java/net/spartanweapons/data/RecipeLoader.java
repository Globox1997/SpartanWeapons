package net.spartanweapons.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.init.ItemInit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeLoader extends FabricRecipeProvider {

    public RecipeLoader(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    private record BasicRecipe(String suffix, String[] pattern, boolean needsLeather) {}

    private static final List<BasicRecipe> WOODEN_BASE_ITEMS = List.of(
            new BasicRecipe("pole", new String[]{"s  ", " l ", "  s"}, true),
            new BasicRecipe("spear", new String[]{"s  ", " sb", " bb"}, false),
            new BasicRecipe("club", new String[]{"s  ", " bs", " sb"}, false),
            new BasicRecipe("staff", new String[]{"s", "b", "s"}, false)
    );

    @Override
    public void generate(RecipeExporter exporter) {
        for (String woodHandle : ItemInit.WOOD_MATERIALS) {
            Item stickItem = getHandleItem(woodHandle, "stick");
            Item planksItem = Registries.ITEM.get(Identifier.ofVanilla(woodHandle + "_planks"));

            for (BasicRecipe recipe : WOODEN_BASE_ITEMS) {
                Item resultItem = Registries.ITEM.get(SpartanWeaponsMain.identifierOf(woodHandle + "_" + recipe.suffix()));

                ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, resultItem)
                        .input('s', stickItem);

                if (recipe.needsLeather()) {
                    builder.input('l', Items.LEATHER);
                }else{
                    builder.input('b', planksItem);
                }

                for (String line : recipe.pattern()) {
                    builder.pattern(line);
                }

                builder.criterion(hasItem(stickItem), conditionsFromItem(stickItem))
                        .offerTo(exporter);
            }
        }
        for (ToolMaterials toolMaterial : ToolMaterials.values()) {
            if (toolMaterial == ToolMaterials.WOOD || toolMaterial == ToolMaterials.STONE) {
                continue;
            }
            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (ItemInit.WeaponType type : ItemInit.TYPES) {
                    if (type.pattern() == null) {
                        continue;
                    }
                    String fullName = ItemInit.getFormattedName(toolMaterial, woodHandle, type.suffix());
                    Item resultItem = Registries.ITEM.get(SpartanWeaponsMain.identifierOf(fullName));

                    if (toolMaterial == ToolMaterials.NETHERITE) {
                        String baseName = ItemInit.getFormattedName(ToolMaterials.DIAMOND, woodHandle, type.suffix());
                        Item baseItem = Registries.ITEM.get(SpartanWeaponsMain.identifierOf(baseName));

                        SmithingTransformRecipeJsonBuilder.create(
                                        Ingredient.ofItems(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                                        Ingredient.ofItems(baseItem),
                                        Ingredient.ofItems(Items.NETHERITE_INGOT),
                                        RecipeCategory.COMBAT,
                                        resultItem
                                ).criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                                .offerTo(exporter, SpartanWeaponsMain.identifierOf(fullName + "_smithing"));
                    } else {
                        generateShapedRecipe(exporter, toolMaterial, woodHandle, type, resultItem);
                    }
                }
            }
        }
    }

    private void generateShapedRecipe(RecipeExporter exporter, ToolMaterials toolMaterial, String woodHandle, ItemInit.WeaponType type, Item resultItem) {
        Item ingotItem = getBaseIngredient(toolMaterial);
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, resultItem);

        String fullPattern = String.join("", type.pattern());

        if (fullPattern.contains("i")) {
            builder.input('i', ingotItem);
        }
        if (fullPattern.contains("b")) {
            builder.input('b', getBlockItem(toolMaterial));
        }
        if (fullPattern.contains("s")) {
            builder.input('s', getHandleItem(woodHandle, "stick"));
        }
        if (fullPattern.contains("p")) {
            builder.input('p', getHandleItem(woodHandle, "pole"));
        }
        if (fullPattern.contains("x")) {
            builder.input('x', Items.SLIME_BALL);
        }

        for (String line : type.pattern()) {
            builder.pattern(line);
        }

        builder.criterion(hasItem(ingotItem), conditionsFromItem(ingotItem)).offerTo(exporter);
    }

    private Item getBaseIngredient(ToolMaterials material) {
        if (material == ToolMaterials.IRON) {
            return Items.IRON_INGOT;
        }
        if (material == ToolMaterials.GOLD) {
            return Items.GOLD_INGOT;
        }
        if (material == ToolMaterials.DIAMOND) {
            return Items.DIAMOND;
        }
        if (material == ToolMaterials.NETHERITE) {
            return Items.NETHERITE_INGOT;
        }
        return Registries.ITEM.get(Identifier.ofVanilla(material.name().toLowerCase() + "_ingot"));
    }

    private Item getBlockItem(ToolMaterials mat) {
        return Registries.ITEM.get(Identifier.ofVanilla(mat.name().toLowerCase() + "_block"));
    }

    private Item getHandleItem(String wood, String type) {
        Identifier id = wood.equals("oak") && type.equals("stick") ? Identifier.ofVanilla(type) : SpartanWeaponsMain.identifierOf(wood + "_" + type);
        return Registries.ITEM.get(id);
    }

}
