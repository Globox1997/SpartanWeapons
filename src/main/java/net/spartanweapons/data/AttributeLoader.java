package net.spartanweapons.data;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.spartanweapons.init.ItemInit;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AttributeLoader implements DataProvider {

    private final FabricDataOutput dataOutput;
    private final CompletableFuture<HolderLookup.Provider> registryLookup;

    public AttributeLoader(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        this.dataOutput = dataOutput;
        this.registryLookup = registryLookup;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput writer) {

        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (ResourceLocation identifier : ItemInit.ITEMS.keySet()) {
            String path = identifier.getPath();

            if (path.contains("kunai")) {
                futures.add(saveAttribute(writer, path, "bettercombat:dagger"));
            } else if (path.contains("dagger")) {
                futures.add(saveAttribute(writer, path, "spartanweapons:dagger"));
            } else if (path.contains("javelin")) {
                futures.add(saveAttribute(writer, path, "bettercombat:trident"));
            } else if (path.contains("staff")) {
                futures.add(saveAttribute(writer, path, "bettercombat:battlestaff"));
            } else if (path.contains("battle_axe")) {
                futures.add(saveAttribute(writer, path, "bettercombat:heavy_axe"));
            } else if (path.contains("club")) {
                futures.add(saveAttribute(writer, path, "bettercombat:mace"));
            }
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    private CompletableFuture<?> saveAttribute(CachedOutput writer, String weaponId, String parentValue) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parentValue);

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(dataOutput.getModId(), weaponId);
        Path path = dataOutput.createPathProvider(net.minecraft.data.PackOutput.Target.DATA_PACK, "weapon_attributes").json(id);

        return DataProvider.saveStable(writer, json, path);
    }

    @Override
    public String getName() {
        return "Attribute Definitions";
    }
}
