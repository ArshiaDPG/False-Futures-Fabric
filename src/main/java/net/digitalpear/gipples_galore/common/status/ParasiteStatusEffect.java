package net.digitalpear.gipples_galore.common.status;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

import java.util.Objects;
public class ParasiteStatusEffect<T extends MobEntity> extends StatusEffect {
    private final EntityType<T> entityType;
    private final RegistryKey<DamageType> damageType;
    private final SoundEvent hallucination;
    private final SoundEvent deathSound;

    public ParasiteStatusEffect(EntityType<T> entityType, RegistryKey<DamageType> damageType, int color, ParticleEffect particleEffect, SoundEvent hallucination, SoundEvent deathSound) {
        super(StatusEffectCategory.HARMFUL, color, particleEffect);
        this.entityType = entityType;
        this.damageType = damageType;
        this.hallucination = hallucination;
        this.deathSound = deathSound;
    }

    public EntityType<T> getEntityType() {
        return entityType;
    }

    public RegistryKey<DamageType> getDamageType() {
        return damageType;
    }

    public SoundEvent getHallucination() {
        return hallucination;
    }

    public SoundEvent getDeathSound() {
        return deathSound;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        int duration = Objects.requireNonNull(entity.getStatusEffect(Registries.STATUS_EFFECT.getEntry(this))).getDuration();
        if(duration <= 1 && duration > -1) {
            if (!world.isClient()) {
                spawnCreature(world, entity, amplifier);
            }
            entity.damage(world, damageSource(world), entity.getMaxHealth());
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }
    public void spawnCreature(ServerWorld serverWorld, LivingEntity entity, int amplifier){
        T burster = entityType.create(serverWorld, SpawnReason.BREEDING);
        if (burster != null) {
            burster.setPosition(entity.getPos());
            burster.setCustomName(entity.getName());
            burster.setPitch(entity.getPitch());
            burster.setYaw(entity.getYaw());
            burster.setHeadYaw(entity.getHeadYaw());
            burster.setPersistent();
            serverWorld.spawnEntity(burster);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 10 == 0 || duration <= 1;
    }
    public DamageSource damageSource(World world) {
        return new DamageSource(world.getRegistryManager().getOrThrow(RegistryKeys.DAMAGE_TYPE).getEntry(damageType.getValue()).get());
    }
}
