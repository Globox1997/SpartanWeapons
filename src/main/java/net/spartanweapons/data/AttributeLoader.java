package net.spartanweapons.data;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.spartanweapons.init.ItemInit;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AttributeLoader implements DataProvider {

    private final FabricDataOutput dataOutput;
    private final CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    public AttributeLoader(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        this.dataOutput = dataOutput;
        this.registryLookup = registryLookup;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {

        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (Identifier identifier : ItemInit.ITEMS.keySet()) {
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

    private CompletableFuture<?> saveAttribute(DataWriter writer, String weaponId, String parentValue) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", parentValue);

        Identifier id = Identifier.of(dataOutput.getModId(), weaponId);
        Path path = dataOutput.getResolver(net.minecraft.data.DataOutput.OutputType.DATA_PACK, "weapon_attributes").resolveJson(id);

        return DataProvider.writeToPath(writer, json, path);
    }

    @Override
    public String getName() {
        return "Attribute Definitions";
    }
}
