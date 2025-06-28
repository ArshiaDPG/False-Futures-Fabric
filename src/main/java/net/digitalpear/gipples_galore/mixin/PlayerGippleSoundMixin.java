package net.digitalpear.gipples_galore.mixin;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.status.ParasiteStatusEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class PlayerGippleSoundMixin {
    @Shadow public abstract Collection<StatusEffectInstance> getStatusEffects();

    @Shadow public abstract void playSound(@Nullable SoundEvent sound);

    @Inject(method = "tickStatusEffects", at = @At("HEAD"), cancellable = true)
    private void playEffectSound(CallbackInfo ci){
        List<StatusEffectInstance> statusEffectInstanceList = getStatusEffects().stream().filter(statusEffectInstance -> statusEffectInstance.getEffectType().value() instanceof ParasiteStatusEffect<?>).toList();
        if (!statusEffectInstanceList.isEmpty()){
            Random random = Random.create();
            StatusEffectInstance effect = Util.getRandom(statusEffectInstanceList, random);
            int duration = effect.getDuration();
            if (validDuration(duration) && effect.getEffectType().value() instanceof ParasiteStatusEffect<?> parasiteStatusEffect && !effect.isInfinite()){
                if(random.nextInt((duration/4)+1) < 6 || random.nextInt(10) == 1) {
                    this.playSound(parasiteStatusEffect.getHallucination());
                }
                if(duration <= 1 && duration > -1) {
                    this.playSound(parasiteStatusEffect.getDeathSound());
                }
            }
        }
    }
    @Unique
    private boolean validDuration(int duration){
        return duration % 10 == 0 || duration <= 1;
    }
}
