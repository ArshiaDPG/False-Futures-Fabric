package net.digitalpear.falsefutures;

import com.kyanite.paragon.api.ConfigRegistry;
import net.digitalpear.falsefutures.init.*;
import net.digitalpear.falsefutures.init.features.FFConfiguredFeatures;
import net.digitalpear.falsefutures.init.features.FFPlacedFeatures;
import net.digitalpear.falsefutures.init.tags.FFBiomeTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
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

        ConfigRegistry.register(new FalseFuturesConfig());


        LOGGER.info("False Futures has finished registering successfully.");
    }
}
