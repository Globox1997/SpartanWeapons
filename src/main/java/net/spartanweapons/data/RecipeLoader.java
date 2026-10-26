package net.spartanweapons.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.init.ItemInit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeLoader extends FabricRecipeProvider {

    public RecipeLoader(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
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
    public void buildRecipes(RecipeOutput exporter) {
        for (String woodHandle : ItemInit.WOOD_MATERIALS) {
            Item stickItem = getHandleItem(woodHandle, "stick");
            Item planksItem = BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(woodHandle + "_planks"));

            for (BasicRecipe recipe : WOODEN_BASE_ITEMS) {
                Item resultItem = BuiltInRegistries.ITEM.get(SpartanWeaponsMain.identifierOf(woodHandle + "_" + recipe.suffix()));

                ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.MISC, resultItem)
                        .define('s', stickItem);

                if (recipe.needsLeather()) {
                    builder.define('l', Items.LEATHER);
                }else{
                    builder.define('b', planksItem);
                }

                for (String line : recipe.pattern()) {
                    builder.pattern(line);
                }

                builder.unlockedBy(getHasName(stickItem), has(stickItem))
                        .save(exporter);
            }
        }
        for (Tiers toolMaterial : Tiers.values()) {
            if (toolMaterial == Tiers.WOOD || toolMaterial == Tiers.STONE) {
                continue;
            }
            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (ItemInit.WeaponType type : ItemInit.TYPES) {
                    if (type.pattern() == null) {
                        continue;
                    }
                    String fullName = ItemInit.getFormattedName(toolMaterial, woodHandle, type.suffix());
                    Item resultItem = BuiltInRegistries.ITEM.get(SpartanWeaponsMain.identifierOf(fullName));

                    if (toolMaterial == Tiers.NETHERITE) {
                        String baseName = ItemInit.getFormattedName(Tiers.DIAMOND, woodHandle, type.suffix());
                        Item baseItem = BuiltInRegistries.ITEM.get(SpartanWeaponsMain.identifierOf(baseName));

                        SmithingTransformRecipeBuilder.smithing(
                                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                                        Ingredient.of(baseItem),
                                        Ingredient.of(Items.NETHERITE_INGOT),
                                        RecipeCategory.COMBAT,
                                        resultItem
                                ).unlocks(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                                .save(exporter, SpartanWeaponsMain.identifierOf(fullName + "_smithing"));
                    } else {
                        generateShapedRecipe(exporter, toolMaterial, woodHandle, type, resultItem);
                    }
                }
            }
        }
    }

    private void generateShapedRecipe(RecipeOutput exporter, Tiers toolMaterial, String woodHandle, ItemInit.WeaponType type, Item resultItem) {
        Item ingotItem = getBaseIngredient(toolMaterial);
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, resultItem);

        String fullPattern = String.join("", type.pattern());

        if (fullPattern.contains("i")) {
            builder.define('i', ingotItem);
        }
        if (fullPattern.contains("b")) {
            builder.define('b', getBlockItem(toolMaterial));
        }
        if (fullPattern.contains("s")) {
            builder.define('s', getHandleItem(woodHandle, "stick"));
        }
        if (fullPattern.contains("p")) {
            builder.define('p', getHandleItem(woodHandle, "pole"));
        }
        if (fullPattern.contains("x")) {
            builder.define('x', Items.SLIME_BALL);
        }

        for (String line : type.pattern()) {
            builder.pattern(line);
        }

        builder.unlockedBy(getHasName(ingotItem), has(ingotItem)).save(exporter);
    }

    private Item getBaseIngredient(Tiers material) {
        if (material == Tiers.IRON) {
            return Items.IRON_INGOT;
        }
        if (material == Tiers.GOLD) {
            return Items.GOLD_INGOT;
        }
        if (material == Tiers.DIAMOND) {
            return Items.DIAMOND;
        }
        if (material == Tiers.NETHERITE) {
            return Items.NETHERITE_INGOT;
        }
        return BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(material.name().toLowerCase() + "_ingot"));
    }

    private Item getBlockItem(Tiers mat) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.withDefaultNamespace(mat.name().toLowerCase() + "_block"));
    }

    private Item getHandleItem(String wood, String type) {
        ResourceLocation id = wood.equals("oak") && type.equals("stick") ? ResourceLocation.withDefaultNamespace(type) : SpartanWeaponsMain.identifierOf(wood + "_" + type);
        return BuiltInRegistries.ITEM.get(id);
    }

}
