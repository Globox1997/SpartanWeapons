package net.spartanweapons.fabric.mixin;

import net.minecraft.world.entity.player.Player;
import net.spartanweapons.init.ConfigInit;
import net.spartanweapons.item.Scythe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Player.class)
public abstract class PlayerEntityMixin {

    @ModifyArg(method = "doSweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;inflate(DDD)Lnet/minecraft/world/phys/AABB;"), index = 0)
    private double attackSweepWidthXMixin(double x) {
        return x * getSweepRangeMultiplier();
    }

    @ModifyArg(method = "doSweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;inflate(DDD)Lnet/minecraft/world/phys/AABB;"), index = 2)
    private double attackSweepWidthZMixin(double z) {
        return z * getSweepRangeMultiplier();
    }

    @ModifyConstant(method = "doSweepAttack", constant = @Constant(doubleValue = 9.0D))
    private double attackSweepDistanceMixin(double squaredDistance) {
        double multiplier = getSweepRangeMultiplier();
        return squaredDistance * multiplier * multiplier;
    }

    @Unique
    private double getSweepRangeMultiplier() {
        return ((Player) (Object) this).getMainHandItem().getItem() instanceof Scythe ? ConfigInit.CONFIG.scythe_sweep_range_multiplier : 1.0D;
    }
}
