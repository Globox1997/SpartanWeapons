package net.spartanweapons.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.spartanweapons.init.ItemInit;
import net.spartanweapons.init.TagInit;
import net.spartanweapons.item.Javelin;

import java.util.concurrent.CompletableFuture;

public class ItemTagLoader extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagLoader(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        for (Item entry : ItemInit.ITEMS.values()) {
            if (entry.getClass() != Item.class) {
                builder(ItemTags.SWORDS).add(entry.builtInRegistryHolder().key());
            }
            if (entry instanceof Javelin) {
                builder(TagInit.ENCHANTABLE_JAVELIN).add(entry.builtInRegistryHolder().key());
            }
        }
        for (Item stick : ItemInit.STICKS) {
            builder(TagInit.STICKS).add(stick.builtInRegistryHolder().key());
        }
        builder(TagInit.POLES).add(ItemInit.ACACIA_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.BAMBOO_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.BIRCH_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.CHERRY_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.CRIMSON_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.DARK_OAK_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.JUNGLE_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.MANGROVE_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.OAK_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.SPRUCE_POLE.builtInRegistryHolder().key());
        builder(TagInit.POLES).add(ItemInit.WARPED_POLE.builtInRegistryHolder().key());
    }
}
