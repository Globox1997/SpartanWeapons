package net.spartanweapons.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.Vec3d;
import net.spartanweapons.init.ConfigInit;

public class Dagger extends SwordItem {

    public Dagger(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        Entity attacker = damageSource.getAttacker();
        if (attacker != null && target instanceof LivingEntity livingTarget) {
            Vec3d facing = Vec3d.fromPolar(0.0F, livingTarget.getBodyYaw());
            Vec3d toAttacker = attacker.getPos().subtract(target.getPos()).multiply(1.0D, 0.0D, 1.0D).normalize();
            if (facing.dotProduct(toAttacker) < -0.5D) {
                return baseAttackDamage * ConfigInit.CONFIG.dagger_backstab_bonus;
            }
        }
        return 0.0F;
    }

}
