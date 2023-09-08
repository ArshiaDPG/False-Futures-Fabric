package net.digitalpear.falsefutures;

import com.kyanite.paragon.api.ConfigOption;
import com.kyanite.paragon.api.interfaces.Config;

public class FalseFuturesConfig implements Config {
    public static final ConfigOption<Boolean> CAN_PET_GIPPLE = new ConfigOption<>("can_pet_gipples", true);
    public static final ConfigOption<Integer> SOMETHING_SPAWNING_PERCENTAGE = new ConfigOption<>("something_spawning_percentage", 10);

    public static final ConfigOption<Boolean> JELLY_SPECIAL_EFFECTS = new ConfigOption<>("jelly_special_effects", true);
    public static final ConfigOption<Integer> JELLY_FOOD_VALUE = new ConfigOption<>("jelly_food_value", 2);
    public static final ConfigOption<Float> JELLY_SATURATION_VALUE = new ConfigOption<>("jelly_saturation_value", 0.8f);

}
