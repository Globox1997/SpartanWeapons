package net.spartanweapons;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.spartanweapons.data.*;

public class SpartanWeaponsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModelLoader::new);
        pack.addProvider(RecipeLoader::new);
        pack.addProvider(LangLoader::new);
        pack.addProvider(AttributeLoader::new);
        pack.addProvider(TagLoader::new);
    }
}
