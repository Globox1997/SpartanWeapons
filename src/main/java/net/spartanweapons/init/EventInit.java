package net.spartanweapons.init;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import java.util.HashMap;
import java.util.Map;

public class EventInit {

    public static void init() {
        Map<ResourceLocation, Item> leavesSticks = new HashMap<>();
        for (Item stick : ItemInit.STICKS) {
            String stickPath = BuiltInRegistries.ITEM.getKey(stick).getPath();
            leavesSticks.put(ResourceLocation.withDefaultNamespace("blocks/" + stickPath.replace("_stick", "_leaves")), stick);
        }

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            Item stick = leavesSticks.get(key.location());
            if (stick != null) {
                tableBuilder.apply(createStickReplacement(stick, registries));
            }
        });

        EnchantmentEvents.ALLOW_ENCHANTING.register((enchantment, target, enchantingContext) ->
                target.is(TagInit.ENCHANTABLE_JAVELIN) && enchantment.is(TagInit.JAVELIN_ENCHANTMENTS) ? TriState.TRUE : TriState.DEFAULT);
    }

    private static LootItemFunction createStickReplacement(Item stick, HolderLookup.Provider registries) {
        JsonObject itemFilter = new JsonObject();
        itemFilter.addProperty("items", "minecraft:stick");

        JsonObject setItem = new JsonObject();
        setItem.addProperty("function", "minecraft:set_item");
        setItem.addProperty("item", BuiltInRegistries.ITEM.getKey(stick).toString());

        JsonObject filtered = new JsonObject();
        filtered.addProperty("function", "minecraft:filtered");
        filtered.add("item_filter", itemFilter);
        filtered.add("modifier", setItem);

        return LootItemFunctions.ROOT_CODEC.parse(registries.createSerializationContext(JsonOps.INSTANCE), filtered).getOrThrow();
    }
}
