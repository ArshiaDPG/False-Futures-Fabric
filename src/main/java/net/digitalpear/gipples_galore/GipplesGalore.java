package net.digitalpear.gipples_galore;

import net.digitalpear.gipples_galore.common.features.GGFeatures;
import net.digitalpear.gipples_galore.init.*;
import net.digitalpear.gipples_galore.init.data.GGData;
import net.digitalpear.gipples_galore.init.features.GGPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GipplesGalore implements ModInitializer {

    public static final String MOD_ID = "gipples_galore";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        GGItems.init();
        GGBlocks.init();
        GGEntities.init();
        GGSoundEvents.init();
        GGFeatures.init();
        GGPlacedFeatures.init();
        GGBannerPatterns.init();
        GGGameRules.init();
        GGData.init();
        GGStatusEffects.init();


        LOGGER.info("False Futures has finished registering successfully.");
    }
}
