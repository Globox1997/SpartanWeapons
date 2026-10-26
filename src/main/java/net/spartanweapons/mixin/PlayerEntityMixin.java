package net.spartanweapons.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.spartanweapons.init.ConfigInit;
import net.spartanweapons.item.Scythe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @ModifyArg(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Box;expand(DDD)Lnet/minecraft/util/math/Box;"), index = 0)
    private double attackSweepWidthXMixin(double x) {
        return x * getSweepRangeMultiplier();
    }

    @ModifyArg(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Box;expand(DDD)Lnet/minecraft/util/math/Box;"), index = 2)
    private double attackSweepWidthZMixin(double z) {
        return z * getSweepRangeMultiplier();
    }

    @ModifyConstant(method = "attack", constant = @Constant(doubleValue = 9.0D))
    private double attackSweepDistanceMixin(double squaredDistance) {
        double multiplier = getSweepRangeMultiplier();
        return squaredDistance * multiplier * multiplier;
    }

    @Unique
    private double getSweepRangeMultiplier() {
        return ((PlayerEntity) (Object) this).getMainHandStack().getItem() instanceof Scythe ? ConfigInit.CONFIG.scythe_sweep_range_multiplier : 1.0D;
    }
}
