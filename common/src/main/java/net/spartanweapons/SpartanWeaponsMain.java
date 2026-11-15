package net.spartanweapons;

import net.minecraft.resources.Identifier;
import net.spartanweapons.init.ConfigInit;
import net.spartanweapons.init.TagInit;

public class SpartanWeaponsMain {

    public static final String MOD_ID = "spartanweapons";

    public static Identifier identifierOf(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static void init() {
        ConfigInit.init();
        TagInit.init();
    }

}
