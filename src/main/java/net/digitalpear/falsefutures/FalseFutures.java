package net.digitalpear.falsefutures;

import com.kyanite.paragon.api.ConfigManager;
import net.digitalpear.falsefutures.init.*;
import net.digitalpear.falsefutures.init.features.FFConfiguredFeatures;
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
        FFConfiguredFeatures.init();
        FFPlacedFeatures.init();
        FFBannerPatterns.init();

        ConfigManager.register(MOD_ID, new FalseFuturesConfig());

        LOGGER.info("False Futures has finished registering successfully.");
    }
}
