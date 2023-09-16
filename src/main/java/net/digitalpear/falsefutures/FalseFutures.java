package net.digitalpear.falsefutures;

import net.digitalpear.falsefutures.common.features.FFFeatures;
import net.digitalpear.falsefutures.init.*;
import net.digitalpear.falsefutures.init.data.FFData;
import net.digitalpear.falsefutures.init.features.FFPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FalseFutures implements ModInitializer {

    public static final String MOD_ID = "falsefutures";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        FFItems.init();
        FFBlocks.init();
        FFEntities.init();
        FFSoundEvents.init();
        FFFeatures.init();
        FFPlacedFeatures.init();
        FFBannerPatterns.init();
        FFGameRules.init();
        FFData.init();

        LOGGER.info("False Futures has finished registering successfully.");
    }
}
