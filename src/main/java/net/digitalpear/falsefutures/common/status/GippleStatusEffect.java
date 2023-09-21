package net.digitalpear.falsefutures.common.status;

import net.digitalpear.falsefutures.common.entities.gipple.GippleEntity;
import net.digitalpear.falsefutures.init.FFDamageTypes;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFSoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.world.World;

public class GippleStatusEffect extends StatusEffect {
    public GippleStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xc6d1f2);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        int duration = entity.getStatusEffect(this).getDuration();
        if(entity.getRandom().nextInt((duration/4)+1) < 6 || entity.getRandom().nextInt(10) == 1) {
            entity.playSound(FFSoundEvents.ENTITY_GIPPLE_AMBIENT, 1 / ((duration+1)/60F), 0.9F + entity.getRandom().nextFloat() / 5F);
        }
        if(duration <= 1) {
            entity.playSound(FFSoundEvents.ENTITY_GIPPLE_HURT, 1, 1);
            World world = entity.getWorld();
            if (world != null) {
                if(!world.isClient()) {
                    GippleEntity gipple = FFEntities.GIPPLE.create(world);
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
                entity.damage(FFDamageTypes.gippleEffect(world), entity.getMaxHealth());
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 10 == 0 || duration <= 1;
    }
}
