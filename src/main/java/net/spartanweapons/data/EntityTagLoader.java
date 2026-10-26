package net.spartanweapons.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.spartanweapons.init.EntityInit;

import java.util.concurrent.CompletableFuture;

public class EntityTagLoader extends FabricTagProvider.EntityTypeTagProvider {

    public EntityTagLoader(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        for(EntityType type : EntityInit.ENTITY_TYPES.values()){
            getOrCreateTagBuilder(EntityTypeTags.IMPACT_PROJECTILES).add(type);
        }
    }
}
