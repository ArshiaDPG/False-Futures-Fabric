package net.digitalpear.gipples_galore.common.datagens.tags;

import net.digitalpear.gipples_galore.init.tags.GGBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.tag.TagProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterList;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class GGBiomeTagProvider extends FabricTagProvider<Biome> {


    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public GGBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(GGBiomeTags.NO_GIPPLE_HABITATS);

        getOrCreateTagBuilder(GGBiomeTags.EXTRA_GIPPLE_HABITATS)
                .forceAddTag(BiomeTags.IS_OCEAN)
                .forceAddTag(BiomeTags.IS_DEEP_OCEAN)
                .forceAddTag(BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE)
                .addOptional(Identifier.tryParse("galosphere:lichen_caves"));

    }
}
