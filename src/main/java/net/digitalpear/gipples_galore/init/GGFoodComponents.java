package net.digitalpear.gipples_galore.init;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;


public class GGFoodComponents {
    public static final FoodComponent GELATIN = new FoodComponent.Builder().nutrition(1).saturationModifier(0.3F).snack().build();
    public static final FoodComponent GAPPLE = new FoodComponent.Builder().nutrition(5).saturationModifier(0.375F).statusEffect(new StatusEffectInstance(GGStatusEffects.GIPPLE,1200,0),1).alwaysEdible().build();
}
