package net.spartanweapons.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.spartanweapons.init.ConfigInit;
import net.spartanweapons.item.Club;
import net.spartanweapons.item.Mace;
import net.spartanweapons.item.Rapier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "disablesShield", at = @At("RETURN"), cancellable = true)
    private void disablesShieldMixin(CallbackInfoReturnable<Boolean> info) {
        Item item = ((LivingEntity) (Object) this).getWeaponStack().getItem();
        if ((item instanceof Mace && ConfigInit.CONFIG.mace_disables_shield) || (item instanceof Club && ConfigInit.CONFIG.club_disables_shield)) {
            info.setReturnValue(true);
        }
    }

    @Inject(method = "applyArmorToDamage", at = @At("HEAD"), cancellable = true)
    private void applyArmorToDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Float> info) {
        if (source.isOf(DamageTypes.PLAYER_ATTACK) || source.isOf(DamageTypes.MOB_ATTACK)) {
            ItemStack weaponStack = source.getWeaponStack();
            if (weaponStack != null && weaponStack.getItem() instanceof Rapier && ((LivingEntity) (Object) this).getRandom().nextFloat() < ConfigInit.CONFIG.rapier_armor_ignore_chance) {
                info.setReturnValue(amount);
            }
        }
    }
}
