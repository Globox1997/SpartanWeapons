package net.spartanweapons.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.spartanweapons.init.ItemInit;
import net.spartanweapons.init.TagInit;

import java.util.concurrent.CompletableFuture;

public class ItemTagLoader extends FabricTagProvider.ItemTagProvider {

    public ItemTagLoader(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        for (Item entry : ItemInit.ITEMS.values()) {
            if (entry instanceof SwordItem) {
                getOrCreateTagBuilder(ItemTags.SWORDS).add(entry);
            }
        }
        for (Item stick : ItemInit.STICKS) {
            getOrCreateTagBuilder(TagInit.STICKS).add(stick);
        }
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.ACACIA_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.BAMBOO_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.BIRCH_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.CHERRY_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.CRIMSON_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.DARK_OAK_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.JUNGLE_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.MANGROVE_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.OAK_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.SPRUCE_POLE);
        getOrCreateTagBuilder(TagInit.POLES).add(ItemInit.WARPED_POLE);
    }
}
