package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFBlockTagGen extends FabricTagProvider<Block> {
    public FFBlockTagGen(FabricDataGenerator dataGenerator) {
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

        getOrCreateTagBuilder(FFBlockTags.GIPPLE_FOOD)
                .add(Blocks.GLOW_LICHEN)
                .add(FFBlocks.JELLYROOT)
                .add(FFBlocks.TALL_JELLYROOT)
                .addOptional(new Identifier("galosphere", "lichen_moss"))
                .addOptional(new Identifier("galosphere", "lichen_roots"))
                .addOptional(new Identifier("galosphere", "lichen_shelf"));

        getOrCreateTagBuilder(FFBlockTags.JELLYROOT_PLANTABLES)
                .add(FFBlocks.GELASTONE)
                .add(FFBlocks.DEEP_GELASTONE)
                .add(FFBlocks.GELASTONE_BRICKS)
                .add(FFBlocks.DEEP_GELASTONE_BRICKS)
                .forceAddTag(BlockTags.DIRT)
                .addOptionalTag(BlockTags.BASE_STONE_OVERWORLD)
                .addOptionalTag(BlockTags.BASE_STONE_NETHER)
                .add(Blocks.END_STONE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(FFBlocks.GELASTONE_WALL)
                .add(FFBlocks.DEEP_GELASTONE_WALL)
                .add(FFBlocks.GELASTONE_BRICK_WALL)
                .add(FFBlocks.DEEP_GELASTONE_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(FFBlocks.GELASTONE_STAIRS)
                .add(FFBlocks.GELASTONE_BRICK_STAIRS)
                .add(FFBlocks.DEEP_GELASTONE_STAIRS)
                .add(FFBlocks.DEEP_GELASTONE_BRICK_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(FFBlocks.GELASTONE_SLAB)
                .add(FFBlocks.GELASTONE_BRICK_SLAB)
                .add(FFBlocks.DEEP_GELASTONE_SLAB)
                .add(FFBlocks.DEEP_GELASTONE_BRICK_SLAB);

        getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES)
                .add(FFBlocks.GELASTONE_PRESSURE_PLATE)
                .add(FFBlocks.DEEP_GELASTONE_PRESSURE_PLATE)
                .add(FFBlocks.GELASTONE_BRICK_PRESSURE_PLATE)
                .add(FFBlocks.DEEP_GELASTONE_BRICK_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(FFBlocks.GELASTONE)
                .add(FFBlocks.GELASTONE_STAIRS)
                .add(FFBlocks.GELASTONE_SLAB)
                .add(FFBlocks.GELASTONE_WALL)
                .add(FFBlocks.GELASTONE_BUTTON)
                .add(FFBlocks.GELASTONE_PRESSURE_PLATE)

                .add(FFBlocks.GELASTONE_BRICKS)
                .add(FFBlocks.GELASTONE_BRICK_STAIRS)
                .add(FFBlocks.GELASTONE_BRICK_SLAB)
                .add(FFBlocks.GELASTONE_BRICK_WALL)
                .add(FFBlocks.GELASTONE_BRICK_BUTTON)
                .add(FFBlocks.GELASTONE_BRICK_PRESSURE_PLATE)

                .add(FFBlocks.DEEP_GELASTONE)
                .add(FFBlocks.DEEP_GELASTONE_STAIRS)
                .add(FFBlocks.DEEP_GELASTONE_SLAB)
                .add(FFBlocks.DEEP_GELASTONE_WALL)
                .add(FFBlocks.DEEP_GELASTONE_BUTTON)
                .add(FFBlocks.DEEP_GELASTONE_PRESSURE_PLATE)

                .add(FFBlocks.DEEP_GELASTONE_BRICKS)
                .add(FFBlocks.DEEP_GELASTONE_BRICK_STAIRS)
                .add(FFBlocks.DEEP_GELASTONE_BRICK_SLAB)
                .add(FFBlocks.DEEP_GELASTONE_BRICK_WALL)
                .add(FFBlocks.DEEP_GELASTONE_BRICK_BUTTON)
                .add(FFBlocks.DEEP_GELASTONE_BRICK_PRESSURE_PLATE);
    }
}
