package net.spartanweapons.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.spartanweapons.init.ConfigInit;
import net.spartanweapons.item.Rapier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("HEAD"), cancellable = true)
    private void applyArmorToDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Float> info) {
        if (source.is(DamageTypes.PLAYER_ATTACK) || source.is(DamageTypes.MOB_ATTACK)) {
            ItemStack weaponStack = source.getWeaponItem();
            if (weaponStack != null && weaponStack.getItem() instanceof Rapier && ((LivingEntity) (Object) this).getRandom().nextFloat() < ConfigInit.CONFIG.rapier_armor_ignore_chance) {
                info.setReturnValue(amount);
            }
        }
    }
}
