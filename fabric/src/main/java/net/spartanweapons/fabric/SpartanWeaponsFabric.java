package net.spartanweapons.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.init.EntityInit;
import net.spartanweapons.init.EventInit;
import net.spartanweapons.init.ItemInit;

public class SpartanWeaponsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        SpartanWeaponsMain.init();
        ItemInit.register((id, item) -> Registry.register(BuiltInRegistries.ITEM, id, item));
        EntityInit.register((id, type) -> Registry.register(BuiltInRegistries.ENTITY_TYPE, id, type));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ItemInit.SPARTANWEAPONS_ITEM_GROUP,
                ItemInit.createItemGroup(FabricCreativeModeTab.builder().displayItems((parameters, output) -> ItemInit.ITEMS.values().forEach(output::accept))));

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            Item stick = EventInit.getLeavesStick(key.identifier());
            if (stick != null) {
                tableBuilder.apply(EventInit.createStickReplacement(stick, registries));
            }
        });

        EnchantmentEvents.ALLOW_ENCHANTING.register((enchantment, target, enchantingContext) ->
                EventInit.isJavelinEnchantment(target, enchantment) ? TriState.TRUE : TriState.DEFAULT);
    }

}
