package net.digitalpear.gipples_galore.common.status;

import net.digitalpear.gipples_galore.common.entities.gipple.GippleEntity;
import net.digitalpear.gipples_galore.init.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class GippleStatusEffect extends StatusEffect {
    public GippleStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xc6d1f2, GGParticleTypes.GIPPLE);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        int duration = entity.getStatusEffect(GGStatusEffects.GIPPLE).getDuration();
        if(entity.getRandom().nextInt((duration/4)+1) < 6 || entity.getRandom().nextInt(10) == 1) {
            entity.playSound(GGSoundEvents.ENTITY_GIPPLE_AMBIENT, 1 / ((duration+1)/60F), 0.9F + entity.getRandom().nextFloat() / 5F);
        }
        if(duration <= 1 && duration > -1) {
            entity.playSound(GGSoundEvents.ENTITY_GIPPLE_HURT, 1, 1);
            if (world != null) {
                if(!world.isClient()) {
                    GippleEntity gipple = GGEntityTypes.GIPPLE.create(world, SpawnReason.BREEDING);
                    if(gipple != null) {
                        gipple.setPosition(entity.getPos());
                        gipple.setCustomName(entity.getName());
                        gipple.setPitch(entity.getPitch());
                        gipple.setYaw(entity.getYaw());
                        gipple.setHeadYaw(entity.getHeadYaw());
                        gipple.setPersistent();
                        world.spawnEntity(gipple);
                    }
                }
                entity.damage(world, GGDamageTypes.gippleEffect(world), entity.getMaxHealth());
            }
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 10 == 0 || duration <= 1;
    }
}
