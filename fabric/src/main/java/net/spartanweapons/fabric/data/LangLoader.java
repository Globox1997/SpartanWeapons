package net.spartanweapons.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.spartanweapons.init.EntityInit;
import net.spartanweapons.init.ItemInit;
import net.spartanweapons.init.TagInit;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class LangLoader extends FabricLanguageProvider {

    public LangLoader(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
        for (Identifier identifier : ItemInit.ITEMS.keySet()) {
            String displayName = getDisplayName(identifier.getPath());
            translationBuilder.add(BuiltInRegistries.ITEM.getValue(identifier), displayName);
            if (EntityInit.ENTITY_TYPES.containsKey(identifier)) {
                translationBuilder.add(EntityInit.ENTITY_TYPES.get(identifier), displayName);
            }
        }
        translationBuilder.add(TagInit.STICKS, "Sticks");
        translationBuilder.add(TagInit.POLES, "Poles");
        translationBuilder.add(TagInit.ENCHANTABLE_JAVELIN, "Enchantable Javelins");
        translationBuilder.add(TagInit.JAVELIN_ENCHANTMENTS, "Javelin Enchantments");
        try {
            Path existingFilePath = packOutput.getModContainer().findPath("assets/spartanweapons/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }

    private static String getDisplayName(String path) {
        for (String toolMaterial : ItemInit.WEAPON_MATERIALS.keySet()) {
            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                String prefix = ItemInit.getFormattedName(toolMaterial, woodHandle, "");
                if (path.startsWith(prefix)) {
                    return capitalize(prefix.substring(0, prefix.indexOf("_")) + "_" + path.substring(prefix.length()));
                }
            }
        }
        return capitalize(path);
    }

    private static String capitalize(String path) {
        StringBuilder result = new StringBuilder();
        for (String word : path.split("_")) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return result.toString().trim();
    }
}
