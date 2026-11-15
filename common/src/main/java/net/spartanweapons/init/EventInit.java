package net.spartanweapons.init;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class EventInit {

    private static final Map<Identifier, Item> LEAVES_STICKS = new HashMap<>();

    @Nullable
    public static Item getLeavesStick(Identifier lootTable) {
        if (LEAVES_STICKS.isEmpty()) {
            for (Item stick : ItemInit.STICKS) {
                String stickPath = BuiltInRegistries.ITEM.getKey(stick).getPath();
                LEAVES_STICKS.put(Identifier.withDefaultNamespace("blocks/" + stickPath.replace("_stick", "_leaves")), stick);
            }
        }
        return LEAVES_STICKS.get(lootTable);
    }

    public static boolean isJavelinEnchantment(ItemStack target, Holder<Enchantment> enchantment) {
        return target.is(TagInit.ENCHANTABLE_JAVELIN) && enchantment.is(TagInit.JAVELIN_ENCHANTMENTS);
    }

    public static LootItemFunction createStickReplacement(Item stick, HolderLookup.Provider registries) {
        JsonObject itemFilter = new JsonObject();
        itemFilter.addProperty("items", "minecraft:stick");

        JsonObject setItem = new JsonObject();
        setItem.addProperty("type", "minecraft:set_item");
        setItem.addProperty("item", BuiltInRegistries.ITEM.getKey(stick).toString());

        JsonObject filtered = new JsonObject();
        filtered.addProperty("type", "minecraft:filtered");
        filtered.add("item_filter", itemFilter);
        filtered.add("on_pass", setItem);

        return LootItemFunctions.DIRECT_CODEC.parse(registries.createSerializationContext(JsonOps.INSTANCE), filtered).getOrThrow();
    }
}
