package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;

public class FFBlockTagGeneration extends FabricTagProvider<Block> {
    public FFBlockTagGeneration(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.BLOCK);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(FFBlockTags.JELLIES)
                .add(FFBlocks.PLAIN_JELLY)
                .add(FFBlocks.FLORAL_JELLY)
                .add(FFBlocks.FRUITY_JELLY)
                .add(FFBlocks.MILKY_JELLY)
                .add(FFBlocks.SWEET_JELLY)
                .add(FFBlocks.SYMPHONIC_JELLY)
                .add(FFBlocks.WEIRD_JELLY);

        getOrCreateTagBuilder(FFBlockTags.GIPPLE_FOOD).add(Blocks.GLOW_LICHEN);
    }
}
