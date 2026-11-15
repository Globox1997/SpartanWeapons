package net.spartanweapons.neoforge.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.spartanweapons.init.ConfigInit;
import net.spartanweapons.item.Scythe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public abstract class PlayerSweepMixin {

    @ModifyVariable(method = "doSweepAttack(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/damagesource/DamageSource;FLnet/minecraft/world/phys/AABB;)V", at = @At("HEAD"), argsOnly = true)
    private AABB doSweepAttackMixin(AABB sweepHitBox) {
        if (((Player) (Object) this).getMainHandItem().getItem() instanceof Scythe) {
            double extraRange = ConfigInit.CONFIG.scythe_sweep_range_multiplier - 1.0D;
            return sweepHitBox.inflate(extraRange, 0.0D, extraRange);
        }
        return sweepHitBox;
    }
}
