package net.spartanweapons.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;
import net.spartanweapons.init.EntityInit;

import java.util.concurrent.CompletableFuture;

public class EntityTagLoader extends FabricTagProvider.EntityTypeTagProvider {

    public EntityTagLoader(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        for(EntityType type : EntityInit.ENTITY_TYPES.values()){
            getOrCreateTagBuilder(EntityTypeTags.IMPACT_PROJECTILES).add(type);
        }
    }
}
