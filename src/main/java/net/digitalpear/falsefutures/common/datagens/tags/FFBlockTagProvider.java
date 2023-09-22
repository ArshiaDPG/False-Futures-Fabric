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

        getOrCreateTagBuilder(FFBlockTags.GELATINOUS_GROWTH_SUPPORTING)
                .add(FFBlocks.GELATIN_BLOCK)
                .add(FFBlocks.HIBERNATING_GIPPLE)
                .add(FFBlocks.GELATITE)
                .add(FFBlocks.AMOEBALITH)
                .forceAddTag(FFBlockTags.JELLIES);

        var jellies = getOrCreateTagBuilder(FFBlockTags.JELLIES);
        for(Block jelly : FFBlocks.JELLY.keySet()) {
            jellies.add(jelly);
        }

        getOrCreateTagBuilder(FFBlockTags.GIPPLE_FOOD)
                .add(Blocks.GLOW_LICHEN)
                .addOptional(new Identifier("galosphere", "lichen_moss"))
                .addOptional(new Identifier("galosphere", "lichen_roots"))
                .addOptional(new Identifier("galosphere", "lichen_shelf"));

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(FFBlocks.POTTED_GELATINOUS_GROWTH);

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

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(FFBlocks.GELATIN_BLOCK);
    }
}
