package net.spartanweapons.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.Vec3;
import net.spartanweapons.init.ConfigInit;

public class Dagger extends SwordItem {

    public Dagger(Tier toolMaterial, Properties settings) {
        super(toolMaterial, settings);
    }

    @Override
    public float getAttackDamageBonus(Entity target, float baseAttackDamage, DamageSource damageSource) {
        Entity attacker = damageSource.getEntity();
        if (attacker != null && target instanceof LivingEntity livingTarget) {
            Vec3 facing = Vec3.directionFromRotation(0.0F, livingTarget.getVisualRotationYInDegrees());
            Vec3 toAttacker = attacker.position().subtract(target.position()).multiply(1.0D, 0.0D, 1.0D).normalize();
            if (facing.dot(toAttacker) < -0.5D) {
                return baseAttackDamage * ConfigInit.CONFIG.dagger_backstab_bonus;
            }
        }
        return 0.0F;
    }

}
