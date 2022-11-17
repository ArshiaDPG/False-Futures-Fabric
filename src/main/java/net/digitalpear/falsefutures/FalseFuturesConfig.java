package net.digitalpear.falsefutures;

import com.kyanite.paragon.api.ConfigGroup;
import com.kyanite.paragon.api.ConfigOption;
import com.kyanite.paragon.api.interfaces.configtypes.JSONModConfig;

public class FalseFuturesConfig implements JSONModConfig {
    public static final ConfigOption<Boolean> CAN_PET_GIPPLE = new ConfigOption<>("can_pet_gipples", true);

    public static final ConfigOption<Boolean> JELLY_SPECIAL_EFFECTS = new ConfigOption<>("jelly_special_effects", true);
    public static final ConfigOption<Integer> JELLY_FOOD_VALUE = new ConfigOption<>("jelly_food_value", 2);
    public static final ConfigOption<Float> JELLY_SATURATION_VALUE = new ConfigOption<>("jelly_saturation_value", 0.8f);

//    public static final ConfigGroup GIPPLES = new ConfigGroup("gipples", CAN_PET_GIPPLE);
//    public static final ConfigGroup JELLY = new ConfigGroup("jelly", JELLY_SPECIAL_EFFECTS, JELLY_FOOD_VALUE, JELLY_SATURATION_VALUE);

    @Override
    public String getModId() {
        return FalseFutures.MOD_ID;
    }
}
