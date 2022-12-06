package net.digitalpear.falsefutures;

import com.kyanite.paragon.api.ConfigOption;
import com.kyanite.paragon.api.interfaces.configtypes.JSONModConfig;

public class FalseFuturesConfig implements JSONModConfig {
    public static final ConfigOption<Boolean> CAN_PET_GIPPLE = new ConfigOption<>("can_pet_gipples", "Can players pet gipples by right clicking them?", true);
    public static final ConfigOption<Integer> GIPPLE_SPAWNING_WEIGHT = new ConfigOption<>("gipple_spawning_weight", "Spawning frequency of the gipple.", 2);
    public static final ConfigOption<Integer> SOMETHING_SPAWNING_PERCENTAGE = new ConfigOption<>("something_spawning_percentage", "Set to 0 to disable.", 10);

    public static final ConfigOption<Boolean> JELLY_SPECIAL_EFFECTS = new ConfigOption<>("jelly_special_effects", "Will jelly give special effects upon being eaten?", true);
    public static final ConfigOption<Integer> JELLY_FOOD_VALUE = new ConfigOption<>("jelly_food_value", "How much food is restored. [Larger than 20 is redundant.]", 2);
    public static final ConfigOption<Float> JELLY_SATURATION_VALUE = new ConfigOption<>("jelly_saturation_value", "How much saturation is restored. [Larger than 1 is redundant.]", 0.8f);


    @Override
    public String getModId() {
        return FalseFutures.MOD_ID;
    }
}
