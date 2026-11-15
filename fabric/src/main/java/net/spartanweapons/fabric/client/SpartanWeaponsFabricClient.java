package net.spartanweapons.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.spartanweapons.init.RenderInit;

@Environment(EnvType.CLIENT)
public class SpartanWeaponsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RenderInit.init(EntityRendererRegistry::register);
    }

}
