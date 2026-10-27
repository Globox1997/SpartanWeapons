package net.spartanweapons.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.spartanweapons.SpartanWeaponsMain;

public class TagInit {

    // Item
    public static final TagKey<Item> STICKS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "sticks"));
    public static final TagKey<Item> POLES = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "poles"));
    public static final TagKey<Item> ENCHANTABLE_JAVELIN = TagKey.create(Registries.ITEM, SpartanWeaponsMain.identifierOf("enchantable/javelin"));
    // Enchantment
    public static final TagKey<Enchantment> JAVELIN_ENCHANTMENTS = TagKey.create(Registries.ENCHANTMENT, SpartanWeaponsMain.identifierOf("javelin_enchantments"));

    public static void init() {
    }

}
