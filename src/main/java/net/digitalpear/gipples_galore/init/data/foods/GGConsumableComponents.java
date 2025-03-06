package net.digitalpear.gipples_galore.init.data.foods;

import net.digitalpear.gipples_galore.init.GGStatusEffects;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundEvents;

public class GGConsumableComponents{
    public static ConsumableComponent.Builder food() {
        return ConsumableComponent.builder().consumeSeconds(ConsumableComponent.DEFAULT_CONSUME_SECONDS).useAction(UseAction.EAT).sound(SoundEvents.ENTITY_GENERIC_EAT).consumeParticles(true);
    }
    public static final ConsumableComponent GAPPLE = food().consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(GGStatusEffects.GIPPLE,1200,0),1)).build();
    public static final ConsumableComponent GELATIN = food().consumeSeconds(ConsumableComponent.DEFAULT_CONSUME_SECONDS/2).build();
}