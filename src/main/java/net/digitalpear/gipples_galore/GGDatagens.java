package net.digitalpear.gipples_galore;

import net.digitalpear.gipples_galore.common.datagens.*;
import net.digitalpear.gipples_galore.common.datagens.providers.GGConfiguredFeatureProvider;
import net.digitalpear.gipples_galore.common.datagens.providers.GGDamageTypeProvider;
import net.digitalpear.gipples_galore.common.datagens.providers.GGPlacedFeatureProvider;
import net.digitalpear.gipples_galore.common.datagens.tags.*;
import net.digitalpear.gipples_galore.init.GGDamageTypes;
import net.digitalpear.gipples_galore.init.GGPaintings;
import net.digitalpear.gipples_galore.init.features.GGConfiguredFeatures;
import net.digitalpear.gipples_galore.init.features.GGPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class GGDatagens implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        /*
            Tag datagens
         */
        pack.addProvider(GGItemTagProvider::new);
        pack.addProvider(GGBlockTagProvider::new);
        pack.addProvider(GGEntityTypeTagProvider::new);
        pack.addProvider(GGBannerPatternTagProvider::new);
        pack.addProvider(GGBiomeTagProvider::new);
        pack.addProvider(GGDamageTypeTagProvider::new);
        pack.addProvider(GGPaintingTagProvider::new);


        /*
            Everything else
         */
        pack.addProvider(GGAdvancementProvider::new);
        pack.addProvider(GGBlockLootTableProvider::new);
        pack.addProvider(GGEntityLootTableProvider::new);
        pack.addProvider(GGModelProvider::new);
        pack.addProvider(GGRecipeProvider::new);
        pack.addProvider(GGLanguageProvider::new);
        pack.addProvider(GGDamageTypeProvider::new);


        pack.addProvider(GGConfiguredFeatureProvider::new);
        pack.addProvider(GGPlacedFeatureProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, GGConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, GGPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, GGDamageTypes::bootstrap);
    }
}
