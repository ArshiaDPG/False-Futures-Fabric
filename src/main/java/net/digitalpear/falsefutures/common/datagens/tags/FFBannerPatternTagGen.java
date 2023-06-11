package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.FFBannerPatterns;
import net.digitalpear.falsefutures.init.tags.FFBannerPatternItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.util.registry.Registry;

public class FFBannerPatternTagGen extends FabricTagProvider<BannerPattern> {
    /**
     * Construct a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided. For example @see BlockTagProvider
     *
     * @param dataGenerator The data generator instance
     */
    public FFBannerPatternTagGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.BANNER_PATTERN);
    }

    @Override
    protected void generateTags() {
        getTagBuilder(FFBannerPatternItemTags.GIPPLE_PATTERN_ITEM)
                .add(FFBannerPatterns.GIPPLE.getValue());
    }
}
