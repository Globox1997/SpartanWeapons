package net.spartanweapons.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.spartanweapons.init.RecipeInit;
import net.spartanweapons.init.TagInit;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

//    private static final ItemStack STICK = new ItemStack(Items.STICK);

    // @Inject(method = "apply", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/recipe/RecipeManager;deserialize(Lnet/minecraft/util/Identifier;Lcom/google/gson/JsonObject;)Lnet/minecraft/recipe/Recipe;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    // private void applyMixin(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info, Map map2, ImmutableMap.Builder builder, Iterator var6,
    //         Map.Entry entry, Identifier identifier, Recipe<?> recipe) {

    //     if (!recipe.getId().toString().equals("minecraft:stick")) {
    //         if (!recipe.getId().getNamespace().equals("spartanweapons") || !recipe.getId().getPath().contains("pole")) {
    //             for (int i = 0; i < recipe.getIngredients().size(); i++) {
    //                 if (recipe.getIngredients().get(i).test(STICK)) {
    //                     // ItemStack[] matchingStacks = recipe.getIngredients().get(i).getMatchingStacks();
    //                     // ArrayList<ItemStack> sticks = new ArrayList<ItemStack>();
    //                     // sticks.addAll(Arrays.asList(matchingStacks));
    //                     // sticks.addAll(OTHER_STICKS);
    //                     // recipe.getIngredients().set(i, Ingredient.ofStacks(sticks.stream()));
    //                     recipe.getIngredients().set(i, Ingredient.fromTag(TagInit.STICKS));
    //                 }
    //             }
    //         }
    //     }
    // }

    // @Inject(method = "apply", at = @At("HEAD"))
    // private void applyMixin(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
    //     for (Identifier id : RecipeInit.RECIPES.keySet()) {
    //         map.put(id, RecipeInit.RECIPES.get(id));
    //     }
    // }

    // @Inject(method = "apply", at = @At("HEAD"))
    // private void applyMixin(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
    // for (Identifier id : CompatRecipes.RECIPES.keySet()) {
    // // map.put(id, CompatRecipes.RECIPES.get(id));
    // }
    // }

}