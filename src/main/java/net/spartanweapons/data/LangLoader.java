package net.spartanweapons.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.spartanweapons.init.ItemInit;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LangLoader extends FabricLanguageProvider {

    private static final List<String> WOODEN_ITEMS = List.of("pole", "club", "staff", "spear");

    public LangLoader(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        for (Identifier identifier : ItemInit.ITEMS.keySet()) {
            String fullName = identifier.getPath();

            String[] words = fullName.split("_");
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                if (words.length >= 3 && i == 1) {
                    continue;
                }

                String word = words[i];
                if (!word.isEmpty()) {
                    result.append(Character.toUpperCase(word.charAt(0)))
                            .append(word.substring(1).toLowerCase())
                            .append(" ");
                }
            }
            translationBuilder.add(Registries.ITEM.get(identifier), result.toString().trim());
            if(words[0].equals("javelin")){
                translationBuilder.add(Registries.ENTITY_TYPE.get(identifier), result.toString().trim());
            }
        }
        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/spartanweapons/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
