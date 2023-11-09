package net.digitalpear.gipples_galore.common.datagens.tags;

import net.digitalpear.gipples_galore.init.GGPaintings;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.PaintingVariantTags;

import java.util.concurrent.CompletableFuture;

public class GGPaintingTagProvider extends FabricTagProvider<PaintingVariant> {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public GGPaintingTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.PAINTING_VARIANT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(PaintingVariantTags.PLACEABLE).add(GGPaintings.GIPPLE);
    }
}
