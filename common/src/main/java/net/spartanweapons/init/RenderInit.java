package net.spartanweapons.init;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.spartanweapons.entity.JavelinEntity;
import net.spartanweapons.entity.KunaiEntity;
import net.spartanweapons.entity.renderer.JavelinEntityRenderer;
import net.spartanweapons.entity.renderer.KunaiEntityRenderer;

import java.util.Map;

public class RenderInit {

    @FunctionalInterface
    public interface RendererRegistrar {
        <E extends Entity> void register(EntityType<? extends E> entityType, EntityRendererProvider<E> provider);
    }

    @SuppressWarnings("unchecked")
    public static void init(RendererRegistrar registrar) {

        for (Map.Entry<Identifier, EntityType> entry : EntityInit.ENTITY_TYPES.entrySet()) {
            if (entry.getKey().getPath().contains("javelin")) {
                registrar.register((EntityType<JavelinEntity>) entry.getValue(), JavelinEntityRenderer::new);
            } else if (entry.getKey().getPath().contains("kunai")) {
                registrar.register((EntityType<KunaiEntity>) entry.getValue(), KunaiEntityRenderer::new);
            }
        }
    }

}
