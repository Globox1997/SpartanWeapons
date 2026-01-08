package net.spartanweapons.init;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.spartanweapons.SpartanWeaponsMain;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntityInit {
    // Map
    public static final Map<Identifier, EntityType> ENTITY_TYPES = new LinkedHashMap<>();
    // Damage
    public static final RegistryKey<DamageType> JAVELIN = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, SpartanWeaponsMain.identifierOf("javelin"));
    public static final RegistryKey<DamageType> KUNAI = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, SpartanWeaponsMain.identifierOf("kunai"));

    public static void init() {
        for (Identifier id : ENTITY_TYPES.keySet()) {
            Registry.register(Registries.ENTITY_TYPE, id, ENTITY_TYPES.get(id));
        }
    }

    public static <T extends EntityType<?>> T register(String name, T type) {
        ENTITY_TYPES.put(SpartanWeaponsMain.identifierOf(name), type);
        return type;
    }

}
