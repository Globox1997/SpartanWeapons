package net.spartanweapons.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.spartanweapons.init.ConfigInit;

public class Katana extends SwordItem {

    private static final float CHARGED_DAMAGE_FACTOR = 0.2F + 0.9F * 0.9F * 0.8F;

    public Katana(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        if (damageSource.getAttacker() instanceof PlayerEntity player && player.isOnGround() && !player.isSprinting()
                && baseAttackDamage > player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * CHARGED_DAMAGE_FACTOR) {
            return baseAttackDamage * ConfigInit.CONFIG.katana_charged_sweep_bonus;
        }
        return 0.0F;
    }

}
