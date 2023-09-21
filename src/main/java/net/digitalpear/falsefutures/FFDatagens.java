package net.digitalpear.falsefutures;

import net.digitalpear.falsefutures.common.datagens.*;
import net.digitalpear.falsefutures.common.datagens.providers.FFConfiguredFeatureProvider;
import net.digitalpear.falsefutures.common.datagens.providers.FFDamageTypeProvider;
import net.digitalpear.falsefutures.common.datagens.providers.FFPlacedFeatureProvider;
import net.digitalpear.falsefutures.common.datagens.tags.*;
import net.digitalpear.falsefutures.init.FFDamageTypes;
import net.digitalpear.falsefutures.init.features.FFConfiguredFeatures;
import net.digitalpear.falsefutures.init.features.FFPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class FFDatagens implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        /*
            Tag datagens
         */
        pack.addProvider(FFItemTagProvider::new);
        pack.addProvider(FFBlockTagProvider::new);
        pack.addProvider(FFEntityTypeTagProvider::new);
        pack.addProvider(FFBannerPatternTagProvider::new);
        pack.addProvider(FFBiomeTagProvider::new);
        pack.addProvider(FFDamageTypeTagProvider::new);

        /*
            Everything else
         */
        pack.addProvider(FFAdvancementProvider::new);
        pack.addProvider(FFBlockLootTableProvider::new);
        pack.addProvider(FabricEntityLootTableProvider::new);
        pack.addProvider(FFModelProvider::new);
        pack.addProvider(FFRecipeProvider::new);
        pack.addProvider(FFLanguageProvider::new);

        pack.addProvider(FFConfiguredFeatureProvider::new);
        pack.addProvider(FFPlacedFeatureProvider::new);
        pack.addProvider(FFDamageTypeProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, FFConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, FFPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, FFDamageTypes::bootstrap);
    }
}
