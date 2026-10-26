package net.spartanweapons.init;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.item.Item;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class EventInit {

    public static void init() {
        Map<Identifier, Item> leavesSticks = new HashMap<>();
        for (Item stick : ItemInit.STICKS) {
            String stickPath = Registries.ITEM.getId(stick).getPath();
            leavesSticks.put(Identifier.ofVanilla("blocks/" + stickPath.replace("_stick", "_leaves")), stick);
        }

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            Item stick = leavesSticks.get(key.getValue());
            if (stick != null) {
                tableBuilder.apply(createStickReplacement(stick, registries));
            }
        });

        EnchantmentEvents.ALLOW_ENCHANTING.register((enchantment, target, enchantingContext) ->
                target.isIn(TagInit.ENCHANTABLE_JAVELIN) && enchantment.isIn(TagInit.JAVELIN_ENCHANTMENTS) ? TriState.TRUE : TriState.DEFAULT);
    }

    private static LootFunction createStickReplacement(Item stick, RegistryWrapper.WrapperLookup registries) {
        JsonObject itemFilter = new JsonObject();
        itemFilter.addProperty("items", "minecraft:stick");

        JsonObject setItem = new JsonObject();
        setItem.addProperty("function", "minecraft:set_item");
        setItem.addProperty("item", Registries.ITEM.getId(stick).toString());

        JsonObject filtered = new JsonObject();
        filtered.addProperty("function", "minecraft:filtered");
        filtered.add("item_filter", itemFilter);
        filtered.add("modifier", setItem);

        return LootFunctionTypes.CODEC.parse(registries.getOps(JsonOps.INSTANCE), filtered).getOrThrow();
    }
}
