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
                .add(FFBlocks.PLAIN_JELLO_CAKE)
                .add(FFBlocks.FLORAL_JELLO_CAKE)
                .add(FFBlocks.FRUITY_JELLO_CAKE)
                .add(FFBlocks.MILKY_JELLO_CAKE)
                .add(FFBlocks.SWEET_JELLO_CAKE)
                .add(FFBlocks.SYMPHONIC_JELLO_CAKE)
                .add(FFBlocks.WEIRD_JELLO_CAKE);

        getOrCreateTagBuilder(FFBlockTags.GIPPLE_FOOD).add(Blocks.GLOW_LICHEN);
    }
}
