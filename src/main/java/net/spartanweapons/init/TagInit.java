package net.spartanweapons.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.spartanweapons.SpartanWeaponsMain;

public class TagInit {

    // Item
    public static final TagKey<Item> STICKS = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "sticks"));
    public static final TagKey<Item> POLES = TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "poles"));
    public static final TagKey<Item> ENCHANTABLE_JAVELIN = TagKey.of(RegistryKeys.ITEM, SpartanWeaponsMain.identifierOf("enchantable/javelin"));
    // Enchantment
    public static final TagKey<Enchantment> JAVELIN_ENCHANTMENTS = TagKey.of(RegistryKeys.ENCHANTMENT, SpartanWeaponsMain.identifierOf("javelin_enchantments"));

    public static void init() {
    }

}
