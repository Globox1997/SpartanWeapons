package net.spartanweapons;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.spartanweapons.init.ConfigInit;
import net.spartanweapons.init.EntityInit;
import net.spartanweapons.init.EventInit;
import net.spartanweapons.init.ItemInit;
import net.spartanweapons.init.TagInit;

public class SpartanWeaponsMain implements ModInitializer {

    public static ResourceLocation identifierOf(String path) {
        return ResourceLocation.fromNamespaceAndPath("spartanweapons", path);
    }

    @Override
    public void onInitialize() {
        ConfigInit.init();
        ItemInit.init();
        EntityInit.init();
        TagInit.init();
        EventInit.init();
    }

}
