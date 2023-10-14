package net.digitalpear.gipples_galore.common.datagens.tags;

import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.tags.GGBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class GGBlockTagProvider extends FabricTagProvider<Block> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance

     * @param registriesFuture the backing registry for the tag type
     */
    public GGBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(GGBlockTags.GIPPLE_COLONY_REPLACEABLE)
                .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
                .forceAddTag(BlockTags.BASE_STONE_NETHER)
                .forceAddTag(BlockTags.DIRT)
                .add(Blocks.DRIPSTONE_BLOCK)
                .add(Blocks.POINTED_DRIPSTONE)
                .add(Blocks.WATER);

        getOrCreateTagBuilder(GGBlockTags.GELATINOUS_GROWTH_SUPPORTING)
                .add(GGBlocks.GELATIN_BLOCK)
                .add(GGBlocks.HIBERNATING_GIPPLE)
                .add(GGBlocks.GELATITE)
                .add(GGBlocks.AMOEBALITH)
                .forceAddTag(GGBlockTags.JELLIES);

        var jellies = getOrCreateTagBuilder(GGBlockTags.JELLIES);
        for(Block jelly : GGBlocks.JELLY.keySet()) {
            jellies.add(jelly);
        }

        getOrCreateTagBuilder(GGBlockTags.GIPPLE_FOOD)
                .add(Blocks.GLOW_LICHEN)
                .addOptional(new Identifier("galosphere", "lichen_roots"))
                .addOptional(new Identifier("galosphere", "bowl_lichen"))
                .addOptional(new Identifier("galosphere", "lichen_shelf"))
        ;

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(GGBlocks.POTTED_GELATINOUS_GROWTH);

        getOrCreateTagBuilder(BlockTags.BUTTONS)
                .add(GGBlocks.GELATITE_BUTTON)
                .add(GGBlocks.AMOEBALITH_BUTTON);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(GGBlocks.GELATITE_WALL)
                .add(GGBlocks.AMOEBALITH_WALL)
                .add(GGBlocks.GELATITE_BRICK_WALL)
                .add(GGBlocks.AMOEBALITH_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(GGBlocks.GELATITE_STAIRS)
                .add(GGBlocks.GELATITE_BRICK_STAIRS)
                .add(GGBlocks.AMOEBALITH_STAIRS)
                .add(GGBlocks.AMOEBALITH_BRICK_STAIRS);

        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(GGBlocks.GELATITE_SLAB)
                .add(GGBlocks.GELATITE_BRICK_SLAB)
                .add(GGBlocks.AMOEBALITH_SLAB)
                .add(GGBlocks.AMOEBALITH_BRICK_SLAB);

        getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES)
                .add(GGBlocks.GELATITE_PRESSURE_PLATE)
                .add(GGBlocks.AMOEBALITH_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(GGBlocks.GELATITE)
                .add(GGBlocks.GELATITE_STAIRS)
                .add(GGBlocks.GELATITE_SLAB)
                .add(GGBlocks.GELATITE_WALL)
                .add(GGBlocks.GELATITE_BUTTON)
                .add(GGBlocks.GELATITE_PRESSURE_PLATE)

                .add(GGBlocks.CHISELED_GELATITE_BRICKS)

                .add(GGBlocks.GELATITE_BRICKS)
                .add(GGBlocks.GELATITE_BRICK_STAIRS)
                .add(GGBlocks.GELATITE_BRICK_SLAB)
                .add(GGBlocks.GELATITE_BRICK_WALL)

                .add(GGBlocks.AMOEBALITH)
                .add(GGBlocks.AMOEBALITH_STAIRS)
                .add(GGBlocks.AMOEBALITH_SLAB)
                .add(GGBlocks.AMOEBALITH_WALL)
                .add(GGBlocks.AMOEBALITH_BUTTON)
                .add(GGBlocks.AMOEBALITH_PRESSURE_PLATE)

                .add(GGBlocks.CHISELED_AMOEBALITH_BRICKS)

                .add(GGBlocks.AMOEBALITH_BRICKS)
                .add(GGBlocks.AMOEBALITH_BRICK_STAIRS)
                .add(GGBlocks.AMOEBALITH_BRICK_SLAB)
                .add(GGBlocks.AMOEBALITH_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(GGBlocks.GELATIN_BLOCK);
    }
}
