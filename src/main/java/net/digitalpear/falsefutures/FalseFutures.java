package net.digitalpear.falsefutures;

import com.kyanite.paragon.Paragon;
import com.kyanite.paragon.api.ConfigRegistry;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFItems;
import net.digitalpear.falsefutures.init.FFSoundEvents;
import net.digitalpear.falsefutures.init.tags.FFBiomeTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;

public class FalseFutures implements ModInitializer {

    public static final String MOD_ID = "falsefutures";

    @Override
    public void onInitialize() {
        FFItems.init();
        FFBlocks.init();
        FFEntities.init();
        FFSoundEvents.init();

        ConfigRegistry.register(new FalseFuturesConfig());

        BiomeModifications.addSpawn(BiomeSelectors.tag(FFBiomeTags.GIPPLE_HABITATS),
                SpawnGroup.UNDERGROUND_WATER_CREATURE, FFEntities.GIPPLE, 2, 1, 3);
    }
}
