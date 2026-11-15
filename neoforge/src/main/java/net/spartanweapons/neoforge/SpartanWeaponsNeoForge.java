package net.spartanweapons.neoforge;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.init.EntityInit;
import net.spartanweapons.init.ItemInit;
import net.spartanweapons.neoforge.loot.LeavesStickLootModifier;

@Mod(SpartanWeaponsMain.MOD_ID)
public class SpartanWeaponsNeoForge {

    public SpartanWeaponsNeoForge(IEventBus modBus) {
        SpartanWeaponsMain.init();
        modBus.addListener(RegisterEvent.class, SpartanWeaponsNeoForge::register);
    }

    private static void register(RegisterEvent event) {
        event.register(Registries.ITEM, helper -> ItemInit.register(helper::register));
        event.register(Registries.ENTITY_TYPE, helper -> EntityInit.register(helper::register));
        event.register(Registries.CREATIVE_MODE_TAB, helper -> helper.register(ItemInit.SPARTANWEAPONS_ITEM_GROUP.identifier(),
                ItemInit.createItemGroup(CreativeModeTab.builder().displayItems((parameters, output) -> ItemInit.ITEMS.values().forEach(output::accept)))));
        event.register(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> helper.register(SpartanWeaponsMain.identifierOf("leaves_sticks"), LeavesStickLootModifier.CODEC));
    }

}
