package net.spartanweapons.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.DataTracker.Builder;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.spartanweapons.init.EntityInit;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.spartanweapons.item.Kunai;
import org.jetbrains.annotations.Nullable;

public class KunaiEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    private static final TrackedData<Byte> LOYALTY;
    private static final TrackedData<Boolean> ENCHANTMENT_GLINT;
    private ItemStack kunai;
    private final Set<UUID> piercedEntities = new HashSet<>();
    public int returnTimer;
    private boolean dealtDamage;

    public KunaiEntity(EntityType<? extends KunaiEntity> entityType, World world, Kunai item) {
        super(entityType, world);
        this.kunai = new ItemStack(item);
    }

    public KunaiEntity(World world, LivingEntity owner, ItemStack stack) {
        super(((Kunai) stack.getItem()).getType(), owner, world, stack, null);
        this.kunai = stack;
        this.dataTracker.set(LOYALTY, this.getLoyalty(stack));
        this.dataTracker.set(ENCHANTMENT_GLINT, stack.hasGlint());
    }

    public KunaiEntity(World world, double x, double y, double z, ItemStack stack) {
        super(((Kunai) stack.getItem()).getType(), x, y, z, world, stack, stack);
        this.kunai = stack;
        this.dataTracker.set(LOYALTY, this.getLoyalty(stack));
        this.dataTracker.set(ENCHANTMENT_GLINT, stack.hasGlint());
    }

    @Override
    protected void initDataTracker(Builder builder) {
        super.initDataTracker(builder);
        builder.add(LOYALTY, (byte) 0);
        builder.add(ENCHANTMENT_GLINT, false);
    }

    @Override
    protected ItemStack asItemStack() {
        return this.kunai.copy();
    }

    @Environment(EnvType.CLIENT)
    public boolean enchantingGlint() {
        return this.dataTracker.get(ENCHANTMENT_GLINT);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity hitEntity = entityHitResult.getEntity();
        if (this.piercedEntities.contains(hitEntity.getUuid()) || this.piercedEntities.size() > this.getPierceLevel()) {
            return;
        }
        this.piercedEntities.add(hitEntity.getUuid());
        float damage = ((Kunai) this.kunai.getItem()).getMaterial().getAttackDamage() * 2.35F;
        this.dealtDamage = true;

        Entity owner = this.getOwner();

        DamageSource damageSource = createDamageSource(this, owner == null ? this : owner);
        if (hitEntity.damage(damageSource, damage)) {
            if (hitEntity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (this.getWorld() instanceof ServerWorld serverWorld) {
                EnchantmentHelper.onTargetDamaged(serverWorld, hitEntity, damageSource, this.getWeaponStack());
            }
            if (hitEntity instanceof LivingEntity livingEntity) {
                this.knockback(livingEntity, damageSource);
                this.onHit(livingEntity);
            }
        }

        if (this.piercedEntities.size() > this.getPierceLevel()) {
            this.setVelocity(this.getVelocity().multiply(-0.01D, -0.1D, -0.01D));
        } else {
            this.setVelocity(this.getVelocity().multiply(0.75));
        }
        this.playSound(SoundEvents.ITEM_TRIDENT_HIT, 1.0f, 1.0f);
    }

    @Override
    @Nullable
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
        Entity entity = this.getOwner();
        byte i = this.dataTracker.get(LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoClip()) && entity != null) {
            if (!this.isOwnerAlive()) {
                if (!this.getWorld().isClient() && this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1f);
                }
                this.discard();
            } else {
                this.setNoClip(true);
                Vec3d vec3d = entity.getEyePos().subtract(this.getPos());
                this.setPos(this.getX(), this.getY() + vec3d.y * 0.015 * (double) i, this.getZ());
                if (this.getWorld().isClient()) {
                    this.lastRenderY = this.getY();
                }
                double d = 0.05 * (double) i;
                this.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(d)));
                if (this.returnTimer == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0f, 1.0f);
                }
                ++this.returnTimer;
            }
        }
        super.tick();
    }

    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        Entity entity = this.getOwner();
        if (entity == null || entity.getUuid() == player.getUuid()) {
            super.onPlayerCollision(player);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("kunai", 10)) {
            this.kunai = ItemStack.fromNbt(this.getRegistryManager(), nbt.getCompound("kunai")).orElse(this.getDefaultItemStack());
            this.dataTracker.set(ENCHANTMENT_GLINT, this.kunai.hasGlint());
        }

        this.piercedEntities.clear();
        if (nbt.contains("kunai_hit", 9)) {
            for (NbtElement hitEntity : nbt.getList("kunai_hit", 10)) {
                this.piercedEntities.add(((NbtCompound) hitEntity).getUuid("UUID"));
            }
        }
        this.dealtDamage = nbt.getBoolean("DealtDamage");
        this.dataTracker.set(LOYALTY, this.getLoyalty(this.getItemStack()));
    }

    private byte getLoyalty(ItemStack stack) {
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            return (byte) MathHelper.clamp(EnchantmentHelper.getTridentReturnAcceleration(serverWorld, stack, this), 0, 127);
        }
        return 0;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (!this.kunai.isEmpty()) {
            nbt.put("kunai", this.kunai.encode(this.getRegistryManager()));

            NbtList tags = new NbtList();
            for (UUID uuid : this.piercedEntities) {
                NbtCompound c = new NbtCompound();
                c.putUuid("UUID", uuid);
                tags.add(c);
            }
            nbt.putBoolean("DealtDamage", this.dealtDamage);
            nbt.put("kunai_hit", tags);
        }
    }

    @Override
    public void age() {
        int i = (Byte) this.dataTracker.get(LOYALTY);
        if (this.pickupType != PersistentProjectileEntity.PickupPermission.ALLOWED || i <= 0) {
            super.age();
        }

    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    @Override
    public ItemStack getStack() {
        return this.kunai;
    }

    static {
        LOYALTY = DataTracker.registerData(KunaiEntity.class, TrackedDataHandlerRegistry.BYTE);
        ENCHANTMENT_GLINT = DataTracker.registerData(KunaiEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    private DamageSource createDamageSource(Entity source, Entity attacker) {
        return attacker.getDamageSources().create(EntityInit.KUNAI, source, attacker);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return this.kunai;
    }

}
