package net.spartanweapons.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.spartanweapons.init.ConfigInit;

public class Katana extends SwordItem {

    private static final float CHARGED_DAMAGE_FACTOR = 0.2F + 0.9F * 0.9F * 0.8F;

    public Katana(Tier toolMaterial, Properties settings) {
        super(toolMaterial, settings);
    }

    @Override
    public float getAttackDamageBonus(Entity target, float baseAttackDamage, DamageSource damageSource) {
        if (damageSource.getEntity() instanceof Player player && player.onGround() && !player.isSprinting()
                && baseAttackDamage > player.getAttributeValue(Attributes.ATTACK_DAMAGE) * CHARGED_DAMAGE_FACTOR) {
            return baseAttackDamage * ConfigInit.CONFIG.katana_charged_sweep_bonus;
        }
        return 0.0F;
    }

}
