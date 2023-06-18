package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.tags.FFBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class FFBiomeTagGen extends FabricTagProvider.DynamicRegistryTagProvider<Biome> {

    /**
     * Construct a new {@link DynamicRegistryTagProvider}.
     *
     * @param dataGenerator The data generator instance
     * @throws IllegalArgumentException if the registry is static registry
     */
    public FFBiomeTagGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.BIOME_KEY);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(FFBiomeTags.GIPPLE_HABITATS).add(BiomeKeys.DRIPSTONE_CAVES);
    }
}
