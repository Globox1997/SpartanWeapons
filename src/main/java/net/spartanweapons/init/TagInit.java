package net.spartanweapons.init;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagInit {

    // Item
    public static final TagKey<Item> STICKS = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "sticks"));
    public static final TagKey<Item> POLES = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "poles"));

    public static void init() {
    }

}
