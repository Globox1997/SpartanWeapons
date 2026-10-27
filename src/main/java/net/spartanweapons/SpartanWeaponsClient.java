package net.spartanweapons;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.spartanweapons.init.RenderInit;

@Environment(EnvType.CLIENT)
public class SpartanWeaponsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RenderInit.init();
    }

}
