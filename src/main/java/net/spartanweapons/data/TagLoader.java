package net.spartanweapons.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.spartanweapons.init.ItemInit;

import java.util.concurrent.CompletableFuture;

public class TagLoader extends FabricTagProvider.ItemTagProvider {

    public TagLoader(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        for (Item entry : ItemInit.ITEMS.values()) {
            if (entry instanceof SwordItem) {
                getOrCreateTagBuilder(ItemTags.SWORDS).add(entry);
            }
        }
    }
}
