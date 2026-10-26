package net.spartanweapons.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.spartanweapons.init.ConfigInit;
import net.spartanweapons.init.EntityInit;
import net.spartanweapons.item.Kunai;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

public class KunaiEntity extends AbstractArrow implements ItemSupplier {
    private static final EntityDataAccessor<Byte> LOYALTY;
    private static final EntityDataAccessor<ItemStack> STACK;
    private final Set<UUID> piercedEntities = new HashSet<>();
    public int returnTimer;
    private boolean dealtDamage;

    public KunaiEntity(EntityType<? extends KunaiEntity> entityType, Level world) {
        super(entityType, world);
    }

    public KunaiEntity(Level world, LivingEntity owner, ItemStack stack) {
        super(((Kunai) stack.getItem()).getType(), owner, world, stack, stack);
        this.entityData.set(LOYALTY, this.getLoyalty(stack));
        this.entityData.set(STACK, this.getPickupItemStackOrigin().copy());
    }

    public KunaiEntity(Level world, double x, double y, double z, ItemStack stack) {
        super(((Kunai) stack.getItem()).getType(), x, y, z, world, stack, stack);
        this.entityData.set(LOYALTY, this.getLoyalty(stack));
        this.entityData.set(STACK, this.getPickupItemStackOrigin().copy());
    }

    @Override
    protected void defineSynchedData(Builder builder) {
        super.defineSynchedData(builder);
        builder.define(LOYALTY, (byte) 0);
        builder.define(STACK, this.getDefaultPickupItem());
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity hitEntity = entityHitResult.getEntity();
        if (this.dealtDamage || this.piercedEntities.contains(hitEntity.getUUID())) {
            return;
        }
        this.piercedEntities.add(hitEntity.getUUID());
        boolean pierceExhausted = this.piercedEntities.size() > this.getPierceLevel();

        Entity owner = this.getOwner();
        DamageSource damageSource = createDamageSource(this, owner == null ? this : owner);
        float damage = getThrowDamage(this.getPickupItemStackOrigin(), ConfigInit.CONFIG.kunai_throw_damage_multiplier);
        if (this.level() instanceof ServerLevel serverWorld) {
            damage = EnchantmentHelper.modifyDamage(serverWorld, this.getWeaponItem(), hitEntity, damageSource, damage);
        }
        if (pierceExhausted) {
            this.dealtDamage = true;
        }

        if (hitEntity.hurt(damageSource, damage)) {
            if (hitEntity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (this.level() instanceof ServerLevel serverWorld) {
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverWorld, hitEntity, damageSource, this.getWeaponItem());
            }
            if (hitEntity instanceof LivingEntity livingEntity) {
                this.doKnockback(livingEntity, damageSource);
                this.doPostHurtEffects(livingEntity);
            }
        }

        if (pierceExhausted) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        } else {
            this.setDeltaMovement(this.getDeltaMovement().scale(0.75));
        }
        this.playSound(SoundEvents.ARROW_HIT_PLAYER, 1.0f, 1.0f);
    }

    private static float getThrowDamage(ItemStack stack, float multiplier) {
        double[] damage = {1.0D};
        stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY).forEach(EquipmentSlot.MAINHAND, (attribute, modifier) -> {
            if (attribute.equals(Attributes.ATTACK_DAMAGE) && modifier.operation() == AttributeModifier.Operation.ADD_VALUE) {
                damage[0] += modifier.amount();
            }
        });
        return (float) damage[0] * multiplier;
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return super.canHitEntity(entity) && !this.piercedEntities.contains(entity.getUUID());
    }

    @Override
    @Nullable
    protected EntityHitResult findHitEntity(Vec3 currentPosition, Vec3 nextPosition) {
        return this.dealtDamage ? null : super.findHitEntity(currentPosition, nextPosition);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
        Entity entity = this.getOwner();
        byte i = this.entityData.get(LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isOwnerAlive()) {
                if (!this.level().isClientSide() && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1f);
                }
                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3d = entity.getEyePosition().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3d.y * 0.015 * (double) i, this.getZ());
                if (this.level().isClientSide()) {
                    this.yOld = this.getY();
                }
                double d = 0.05 * (double) i;
                this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3d.normalize().scale(d)));
                if (this.returnTimer == 0) {
                    this.playSound(SoundEvents.TRIDENT_RETURN, 10.0f, 1.0f);
                }
                ++this.returnTimer;
            }
        }
        super.tick();
    }

    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    @Override
    protected boolean tryPickup(Player player) {
        return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
    }

    @Override
    public void playerTouch(Player player) {
        if (this.ownedBy(player) || this.getOwner() == null) {
            super.playerTouch(player);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.entityData.set(STACK, this.getPickupItemStackOrigin().copy());

        this.piercedEntities.clear();
        if (nbt.contains("kunai_hit", Tag.TAG_LIST)) {
            for (Tag hitEntity : nbt.getList("kunai_hit", Tag.TAG_COMPOUND)) {
                this.piercedEntities.add(((CompoundTag) hitEntity).getUUID("UUID"));
            }
        }
        this.dealtDamage = nbt.getBoolean("DealtDamage");
        this.entityData.set(LOYALTY, this.getLoyalty(this.getPickupItemStackOrigin()));
    }

    private byte getLoyalty(ItemStack stack) {
        if (this.level() instanceof ServerLevel serverWorld) {
            return (byte) Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverWorld, stack, this), 0, 127);
        }
        return 0;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        ListTag tags = new ListTag();
        for (UUID uuid : this.piercedEntities) {
            CompoundTag c = new CompoundTag();
            c.putUUID("UUID", uuid);
            tags.add(c);
        }
        nbt.putBoolean("DealtDamage", this.dealtDamage);
        nbt.put("kunai_hit", tags);
    }

    @Override
    public void tickDespawn() {
        int i = this.entityData.get(LOYALTY);
        if (this.pickup != AbstractArrow.Pickup.ALLOWED || i <= 0) {
            super.tickDespawn();
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    @Override
    public ItemStack getItem() {
        return this.entityData.get(STACK);
    }

    @Override
    public ItemStack getWeaponItem() {
        return this.getPickupItemStackOrigin();
    }

    static {
        LOYALTY = SynchedEntityData.defineId(KunaiEntity.class, EntityDataSerializers.BYTE);
        STACK = SynchedEntityData.defineId(KunaiEntity.class, EntityDataSerializers.ITEM_STACK);
    }

    private DamageSource createDamageSource(Entity source, Entity attacker) {
        return attacker.damageSources().source(EntityInit.KUNAI, source, attacker);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(BuiltInRegistries.ITEM.get(BuiltInRegistries.ENTITY_TYPE.getKey(this.getType())));
    }

}
