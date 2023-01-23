package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFBlockTagGen extends FabricTagProvider<Block> {
    public FFBlockTagGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.BLOCK);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(FFBlockTags.BRINE_POOL_CANNOT_REPLACE)
                .forceAddTag(BlockTags.FEATURES_CANNOT_REPLACE)
                .add(FFBlocks.JELLYROOT)
                .add(FFBlocks.TALL_JELLYROOT);

        var jellies = getOrCreateTagBuilder(FFBlockTags.JELLIES);
            for(Block jelly : FFBlocks.JELLY.keySet()) {
                jellies.add(jelly);
            }

        getOrCreateTagBuilder(FFBlockTags.GIPPLE_FOOD)
                .add(Blocks.GLOW_LICHEN)
                .add(FFBlocks.JELLYROOT)
                .add(FFBlocks.TALL_JELLYROOT)
                .addOptional(new Identifier("galosphere", "lichen_moss"))
                .addOptional(new Identifier("galosphere", "lichen_roots"))
                .addOptional(new Identifier("galosphere", "lichen_shelf"));

        getOrCreateTagBuilder(FFBlockTags.JELLYROOT_PLANTABLES)
                .add(FFBlocks.GELATITE)
                .add(FFBlocks.BRINESHALE)
                .add(FFBlocks.CHISELED_GELATITE_BRICKS)
                .add(FFBlocks.CHISELED_BRINESHALE_BRICKS)
                .add(FFBlocks.GELATITE_BRICKS)
                .add(FFBlocks.BRINESHALE_BRICKS)
                .forceAddTag(BlockTags.DIRT)
                .addOptionalTag(BlockTags.BASE_STONE_OVERWORLD)
                .addOptionalTag(BlockTags.BASE_STONE_NETHER)
                .add(Blocks.END_STONE);

        getOrCreateTagBuilder(BlockTags.BUTTONS)
                .add(FFBlocks.GELATITE_BUTTON)
                .add(FFBlocks.BRINESHALE_BUTTON);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(FFBlocks.GELATITE_WALL)
                .add(FFBlocks.BRINESHALE_WALL)
                .add(FFBlocks.GELATITE_BRICK_WALL)
                .add(FFBlocks.BRINESHALE_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(FFBlocks.GELATITE_STAIRS)
                .add(FFBlocks.GELATITE_BRICK_STAIRS)
                .add(FFBlocks.BRINESHALE_STAIRS)
                .add(FFBlocks.BRINESHALE_BRICK_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(FFBlocks.GELATITE_SLAB)
                .add(FFBlocks.GELATITE_BRICK_SLAB)
                .add(FFBlocks.BRINESHALE_SLAB)
                .add(FFBlocks.BRINESHALE_BRICK_SLAB);

        getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES)
                .add(FFBlocks.GELATITE_PRESSURE_PLATE)
                .add(FFBlocks.BRINESHALE_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(FFBlocks.GELATITE)
                .add(FFBlocks.GELATITE_STAIRS)
                .add(FFBlocks.GELATITE_SLAB)
                .add(FFBlocks.GELATITE_WALL)
                .add(FFBlocks.GELATITE_BUTTON)
                .add(FFBlocks.GELATITE_PRESSURE_PLATE)

                .add(FFBlocks.CHISELED_GELATITE_BRICKS)

                .add(FFBlocks.GELATITE_BRICKS)
                .add(FFBlocks.GELATITE_BRICK_STAIRS)
                .add(FFBlocks.GELATITE_BRICK_SLAB)
                .add(FFBlocks.GELATITE_BRICK_WALL)

                .add(FFBlocks.BRINESHALE)
                .add(FFBlocks.BRINESHALE_STAIRS)
                .add(FFBlocks.BRINESHALE_SLAB)
                .add(FFBlocks.BRINESHALE_WALL)
                .add(FFBlocks.BRINESHALE_BUTTON)
                .add(FFBlocks.BRINESHALE_PRESSURE_PLATE)

                .add(FFBlocks.CHISELED_BRINESHALE_BRICKS)

                .add(FFBlocks.BRINESHALE_BRICKS)
                .add(FFBlocks.BRINESHALE_BRICK_STAIRS)
                .add(FFBlocks.BRINESHALE_BRICK_SLAB)
                .add(FFBlocks.BRINESHALE_BRICK_WALL);
    }
}
