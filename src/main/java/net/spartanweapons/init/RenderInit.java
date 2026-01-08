package net.spartanweapons.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.spartanweapons.entity.renderer.JavelinEntityRenderer;
import net.spartanweapons.entity.renderer.KunaiEntityRenderer;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class RenderInit {

    public static void init() {

        for (Map.Entry<Identifier, EntityType> entry : EntityInit.ENTITY_TYPES.entrySet()) {
            if (entry.getKey().getPath().contains("javelin")) {
                EntityRendererRegistry.register(entry.getValue(), JavelinEntityRenderer::new);
            } else if (entry.getKey().getPath().contains("kunai")) {
                EntityRendererRegistry.register(entry.getValue(), KunaiEntityRenderer::new);
            }
        }
    }

}
