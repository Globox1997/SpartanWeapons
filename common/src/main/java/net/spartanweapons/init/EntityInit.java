package net.spartanweapons.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.spartanweapons.SpartanWeaponsMain;
import net.spartanweapons.entity.JavelinEntity;
import net.spartanweapons.entity.KunaiEntity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class EntityInit {
    // Map
    public static final Map<Identifier, EntityType> ENTITY_TYPES = new LinkedHashMap<>();
    // Damage
    public static final ResourceKey<DamageType> JAVELIN = ResourceKey.create(Registries.DAMAGE_TYPE, SpartanWeaponsMain.identifierOf("javelin"));
    public static final ResourceKey<DamageType> KUNAI = ResourceKey.create(Registries.DAMAGE_TYPE, SpartanWeaponsMain.identifierOf("kunai"));

    public static void register(BiConsumer<Identifier, EntityType<?>> registrar) {
        for (String toolMaterial : ItemInit.WEAPON_MATERIALS.keySet()) {
            for (String woodHandle : ItemInit.WOOD_MATERIALS) {
                for (ItemInit.WeaponType type : ItemInit.TYPES) {
                    if (type.hasEntity()) {
                        Identifier id = SpartanWeaponsMain.identifierOf(ItemInit.getFormattedName(toolMaterial, woodHandle, type.suffix()));
                        ENTITY_TYPES.put(id, createWeaponEntity(type.suffix(), ResourceKey.create(Registries.ENTITY_TYPE, id)));
                    }
                }
            }
        }
        ENTITY_TYPES.forEach(registrar::accept);
    }

    private static EntityType<?> createWeaponEntity(String suffix, ResourceKey<EntityType<?>> key) {
        if (suffix.equals("javelin")) {
            return EntityType.Builder.<JavelinEntity>of(
                    JavelinEntity::new,
                    MobCategory.MISC).sized(0.5F, 0.5F).build(key);
        }
        return EntityType.Builder.<KunaiEntity>of(
                KunaiEntity::new,
                MobCategory.MISC).sized(0.5F, 0.5F).build(key);
    }

}
