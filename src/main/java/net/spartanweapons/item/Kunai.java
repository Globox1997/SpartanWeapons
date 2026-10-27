package net.spartanweapons.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.spartanweapons.entity.KunaiEntity;

import java.util.function.Supplier;

public class Kunai extends Item {

    private final Supplier<EntityType<KunaiEntity>> typeSupplier;
    private EntityType<KunaiEntity> cachedType = null;

    public Kunai(Supplier<EntityType<KunaiEntity>> typeSupplier, Properties settings) {
        super(settings);
        this.typeSupplier = typeSupplier;
    }

    public EntityType<KunaiEntity> getType() {
        if (cachedType == null) {
            cachedType = typeSupplier.get();
        }
        return cachedType;
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof Player playerEntity) {
            int i = this.getUseDuration(stack, user) - remainingUseTicks;
            if (i >= 10) {
                if (!world.isClientSide()) {
                    stack.hurtAndBreak(1, playerEntity, user.getUsedItemHand());
                    KunaiEntity kunaiEntity = new KunaiEntity(world, playerEntity, stack);
                    kunaiEntity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 1.5F, 1.0F);
                    if (playerEntity.isCreative()) {
                        kunaiEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                    world.addFreshEntity(kunaiEntity);
                    world.playSound(null, kunaiEntity, SoundEvents.ENDER_PEARL_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!playerEntity.isCreative()) {
                        playerEntity.getInventory().removeItem(stack);
                    }
                }

                playerEntity.awardStat(Stats.ITEM_USED.get(this));
                return true;
            }
        }
        return false;
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if (itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1) {
            return InteractionResult.FAIL;
        } else {
            user.startUsingItem(hand);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.SPYGLASS;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return 72000;
    }
}
