package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class FFBlockTagProvider extends FabricTagProvider<Block> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance

     * @param registriesFuture the backing registry for the tag type
     */
    public FFBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(FFBlockTags.GIPPLE_COLONY_REPLACEABLE)
                .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
                .forceAddTag(BlockTags.BASE_STONE_NETHER)
                .forceAddTag(BlockTags.DIRT)
                .add(Blocks.DRIPSTONE_BLOCK)
                .add(Blocks.POINTED_DRIPSTONE)
                .add(Blocks.WATER);

        var jellies = getOrCreateTagBuilder(FFBlockTags.JELLIES);
        for(Block jelly : FFBlocks.JELLY.keySet()) {
            jellies.add(jelly);
        }

        getOrCreateTagBuilder(FFBlockTags.GIPPLE_SPAWNABLES).add(FFBlocks.GELATITE).add(FFBlocks.AMOEBALITH);

        getOrCreateTagBuilder(FFBlockTags.GIPPLE_FOOD)
                .add(Blocks.GLOW_LICHEN)
                .add(FFBlocks.JELLYROOT)
                .add(FFBlocks.TALL_JELLYROOT)
                .addOptional(new Identifier("galosphere", "lichen_moss"))
                .addOptional(new Identifier("galosphere", "lichen_roots"))
                .addOptional(new Identifier("galosphere", "lichen_shelf"));

        getOrCreateTagBuilder(FFBlockTags.JELLYROOT_PLANTABLES)
                .add(FFBlocks.GELATITE)
                .add(FFBlocks.AMOEBALITH)
                .add(FFBlocks.CHISELED_GELATITE_BRICKS)
                .add(FFBlocks.CHISELED_AMOEBALITH_BRICKS)
                .add(FFBlocks.GELATITE_BRICKS)
                .add(FFBlocks.AMOEBALITH_BRICKS)
                .forceAddTag(BlockTags.DIRT)
                .add(Blocks.FARMLAND)
                .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
                .forceAddTag(BlockTags.BASE_STONE_NETHER)
                .add(Blocks.END_STONE);

        getOrCreateTagBuilder(BlockTags.BUTTONS)
                .add(FFBlocks.GELATITE_BUTTON)
                .add(FFBlocks.AMOEBALITH_BUTTON);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(FFBlocks.GELATITE_WALL)
                .add(FFBlocks.AMOEBALITH_WALL)
                .add(FFBlocks.GELATITE_BRICK_WALL)
                .add(FFBlocks.AMOEBALITH_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(FFBlocks.GELATITE_STAIRS)
                .add(FFBlocks.GELATITE_BRICK_STAIRS)
                .add(FFBlocks.AMOEBALITH_STAIRS)
                .add(FFBlocks.AMOEBALITH_BRICK_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(FFBlocks.GELATITE_SLAB)
                .add(FFBlocks.GELATITE_BRICK_SLAB)
                .add(FFBlocks.AMOEBALITH_SLAB)
                .add(FFBlocks.AMOEBALITH_BRICK_SLAB);

        getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES)
                .add(FFBlocks.GELATITE_PRESSURE_PLATE)
                .add(FFBlocks.AMOEBALITH_PRESSURE_PLATE);

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

                .add(FFBlocks.AMOEBALITH)
                .add(FFBlocks.AMOEBALITH_STAIRS)
                .add(FFBlocks.AMOEBALITH_SLAB)
                .add(FFBlocks.AMOEBALITH_WALL)
                .add(FFBlocks.AMOEBALITH_BUTTON)
                .add(FFBlocks.AMOEBALITH_PRESSURE_PLATE)

                .add(FFBlocks.CHISELED_AMOEBALITH_BRICKS)

                .add(FFBlocks.AMOEBALITH_BRICKS)
                .add(FFBlocks.AMOEBALITH_BRICK_STAIRS)
                .add(FFBlocks.AMOEBALITH_BRICK_SLAB)
                .add(FFBlocks.AMOEBALITH_BRICK_WALL);
    }
}
