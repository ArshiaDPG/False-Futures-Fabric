package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.FFBannerPatterns;
import net.digitalpear.falsefutures.init.tags.FFBannerPatternItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;


import java.util.concurrent.CompletableFuture;

public class FFBannerPatternTagProvider extends FabricTagProvider<BannerPattern> {


    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public FFBannerPatternTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BANNER_PATTERN, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getTagBuilder(FFBannerPatternItemTags.GIPPLE_PATTERN_ITEM)
                .add(FFBannerPatterns.GIPPLE.getValue());
    }
}
