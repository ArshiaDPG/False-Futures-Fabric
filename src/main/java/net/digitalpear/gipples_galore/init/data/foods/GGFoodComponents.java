package net.digitalpear.gipples_galore.init.data.foods;

import net.minecraft.component.type.FoodComponent;


public class GGFoodComponents {
    public static final FoodComponent GELATIN = new FoodComponent.Builder().nutrition(1).saturationModifier(0.3F).build();
    public static final FoodComponent GAPPLE = new FoodComponent.Builder().nutrition(5).saturationModifier(0.375F).alwaysEdible().build();
}
