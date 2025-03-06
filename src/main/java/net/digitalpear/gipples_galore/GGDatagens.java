package net.digitalpear.gipples_galore;

import net.digitalpear.gipples_galore.common.datagens.*;
import net.digitalpear.gipples_galore.common.datagens.providers.*;
import net.digitalpear.gipples_galore.common.datagens.tags.*;
import net.digitalpear.gipples_galore.init.*;
import net.digitalpear.gipples_galore.init.artsy_stuff.GGBannerPatterns;
import net.digitalpear.gipples_galore.init.artsy_stuff.GGJukeboxSongs;
import net.digitalpear.gipples_galore.init.artsy_stuff.GGPaintingVariants;
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


        pack.addProvider(GGAdvancementProvider::new);
        pack.addProvider(GGBlockLootTableProvider::new);
        pack.addProvider(GGEntityLootTableProvider::new);
        pack.addProvider(GGModelProvider::new);
        pack.addProvider(GGRecipeProvider::new);
        pack.addProvider(GGLanguageProvider::new);
        pack.addProvider(GGDamageTypeProvider::new);

        /*
            Worldgen
         */
        pack.addProvider(GGConfiguredFeatureProvider::new);
        pack.addProvider(GGPlacedFeatureProvider::new);

        /*
            Artsy Stuff
         */
        pack.addProvider(GGJukeboxSongProvider::new);
        pack.addProvider(GGBannerPatternProvider::new);
        pack.addProvider(GGPaintingVariantProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, GGConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, GGPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, GGDamageTypes::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.JUKEBOX_SONG, GGJukeboxSongs::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.BANNER_PATTERN, GGBannerPatterns::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PAINTING_VARIANT, GGPaintingVariants::bootstrap);
    }
}
