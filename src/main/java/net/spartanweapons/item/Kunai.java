package net.spartanweapons.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.spartanweapons.entity.KunaiEntity;

import java.util.function.Supplier;

public class Kunai extends SwordItem {

    private final Supplier<EntityType<KunaiEntity>> typeSupplier;
    private EntityType<KunaiEntity> cachedType = null;

    public Kunai(ToolMaterial toolMaterial, Supplier<EntityType<KunaiEntity>> typeSupplier, Settings settings) {
        super(toolMaterial, settings);
        this.typeSupplier = typeSupplier;
    }

    public EntityType<KunaiEntity> getType() {
        if (cachedType == null) {
            cachedType = typeSupplier.get();
        }
        return cachedType;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
            if (i >= 10) {
                if (!world.isClient()) {
                    stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));
                    KunaiEntity kunaiEntity = new KunaiEntity(world, playerEntity, stack);
                    kunaiEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 1.5F, 1.0F);
                    if (playerEntity.isCreative()) {
                        kunaiEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    }
                    world.spawnEntity(kunaiEntity);
                    world.playSoundFromEntity(null, kunaiEntity, SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    if (!playerEntity.isCreative()) {
                        playerEntity.getInventory().removeOne(stack);
                    }
                }

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPYGLASS;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }
}
