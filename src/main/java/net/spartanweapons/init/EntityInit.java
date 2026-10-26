package net.spartanweapons.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.spartanweapons.SpartanWeaponsMain;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntityInit {
    // Map
    public static final Map<ResourceLocation, EntityType> ENTITY_TYPES = new LinkedHashMap<>();
    // Damage
    public static final ResourceKey<DamageType> JAVELIN = ResourceKey.create(Registries.DAMAGE_TYPE, SpartanWeaponsMain.identifierOf("javelin"));
    public static final ResourceKey<DamageType> KUNAI = ResourceKey.create(Registries.DAMAGE_TYPE, SpartanWeaponsMain.identifierOf("kunai"));

    public static void init() {
        for (ResourceLocation id : ENTITY_TYPES.keySet()) {
            Registry.register(BuiltInRegistries.ENTITY_TYPE, id, ENTITY_TYPES.get(id));
        }
    }

    public static <T extends EntityType<?>> T register(String name, T type) {
        ENTITY_TYPES.put(SpartanWeaponsMain.identifierOf(name), type);
        return type;
    }

}
