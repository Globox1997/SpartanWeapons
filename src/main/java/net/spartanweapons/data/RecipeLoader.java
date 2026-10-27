package net.spartanweapons.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.init.ItemInit;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeLoader extends FabricRecipeProvider {

    public RecipeLoader(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
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
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                    Item stickItem = getHandleItem(woodHandle, "stick");
                    Item planksItem = BuiltInRegistries.ITEM.getValue(Identifier.withDefaultNamespace(woodHandle + "_planks"));

                    for (BasicRecipe recipe : WOODEN_BASE_ITEMS) {
                        Item resultItem = BuiltInRegistries.ITEM.getValue(SpartanWeaponsMain.identifierOf(woodHandle + "_" + recipe.suffix()));

                        ShapedRecipeBuilder builder = this.shaped(RecipeCategory.MISC, resultItem)
                                .define('s', stickItem);

                        if (recipe.needsLeather()) {
                            builder.define('l', Items.LEATHER);
                        }else{
                            builder.define('b', planksItem);
                        }

                        for (String line : recipe.pattern()) {
                            builder.pattern(line);
                        }

                        builder.unlockedBy(getHasName(stickItem), this.has(stickItem))
                                .save(this.output);
                    }
                }
                for (String toolMaterial : ItemInit.WEAPON_MATERIALS.keySet()) {
                    for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                        for (ItemInit.WeaponType type : ItemInit.TYPES) {
                            if (type.pattern() == null) {
                                continue;
                            }
                            String fullName = ItemInit.getFormattedName(toolMaterial, woodHandle, type.suffix());
                            Item resultItem = BuiltInRegistries.ITEM.getValue(SpartanWeaponsMain.identifierOf(fullName));

                            if (toolMaterial.equals("netherite")) {
                                String baseName = ItemInit.getFormattedName("diamond", woodHandle, type.suffix());
                                Item baseItem = BuiltInRegistries.ITEM.getValue(SpartanWeaponsMain.identifierOf(baseName));

                                SmithingTransformRecipeBuilder.smithing(
                                                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                                                Ingredient.of(baseItem),
                                                Ingredient.of(Items.NETHERITE_INGOT),
                                                RecipeCategory.COMBAT,
                                                resultItem
                                        ).unlocks(getHasName(Items.NETHERITE_INGOT), this.has(Items.NETHERITE_INGOT))
                                        .save(this.output, ResourceKey.create(Registries.RECIPE, SpartanWeaponsMain.identifierOf(fullName + "_smithing")));
                            } else {
                                generateShapedRecipe(this, this.output, toolMaterial, woodHandle, type, resultItem);
                            }
                        }
                    }
                }
            }
        };
    }

    private static void generateShapedRecipe(RecipeProvider provider, RecipeOutput output, String toolMaterial, String woodHandle, ItemInit.WeaponType type, Item resultItem) {
        Item ingotItem = getBaseIngredient(toolMaterial);
        ShapedRecipeBuilder builder = provider.shaped(RecipeCategory.COMBAT, resultItem);

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

        builder.unlockedBy(RecipeProvider.getHasName(ingotItem), provider.has(ingotItem)).save(output);
    }

    private static Item getBaseIngredient(String material) {
        return switch (material) {
            case "iron" -> Items.IRON_INGOT;
            case "gold" -> Items.GOLD_INGOT;
            case "diamond" -> Items.DIAMOND;
            case "netherite" -> Items.NETHERITE_INGOT;
            default -> BuiltInRegistries.ITEM.getValue(Identifier.withDefaultNamespace(material + "_ingot"));
        };
    }

    private static Item getBlockItem(String mat) {
        return BuiltInRegistries.ITEM.getValue(Identifier.withDefaultNamespace(mat + "_block"));
    }

    private static Item getHandleItem(String wood, String type) {
        Identifier id = wood.equals("oak") && type.equals("stick") ? Identifier.withDefaultNamespace(type) : SpartanWeaponsMain.identifierOf(wood + "_" + type);
        return BuiltInRegistries.ITEM.getValue(id);
    }

    @Override
    public String getName() {
        return "Recipes";
    }

}
