package net.digitalpear.gipples_galore.common.datagens.tags;

import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.data.sets.StoneSet;
import net.digitalpear.gipples_galore.init.tags.GGBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class GGBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public static final Map<BlockFamily.Variant, TagKey<Block>> TAG_KEY_MAP = Map.of(
            BlockFamily.Variant.WALL, BlockTags.WALLS,
            BlockFamily.Variant.BUTTON, BlockTags.STONE_BUTTONS,
            BlockFamily.Variant.PRESSURE_PLATE, BlockTags.STONE_PRESSURE_PLATES,
            BlockFamily.Variant.STAIRS, BlockTags.STAIRS,
            BlockFamily.Variant.SLAB, BlockTags.SLABS);

    public GGBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getTagBuilder(GGBlockTags.GIPPLE_COLONY_REPLACEABLE)
                .addOptionalTag(BlockTags.BASE_STONE_OVERWORLD.id())
                .addOptionalTag(BlockTags.BASE_STONE_NETHER.id())
                .addOptionalTag(BlockTags.DIRT.id())
                .add(getId(Blocks.DRIPSTONE_BLOCK))
                .add(getId(Blocks.POINTED_DRIPSTONE))
                .add(getId(Blocks.WATER));

        getTagBuilder(GGBlockTags.GELATINOUS_GROWTH_SUPPORTING)
                .add(getId(GGBlocks.GELATIN_BLOCK))
                .add(getId(GGBlocks.HIBERNATING_GIPPLE))
                .add(getId(GGBlocks.GELATITE))
                .add(getId(GGBlocks.AMOEBALITH))
                .addTag(GGBlockTags.JELLIES.id());

        getTagBuilder(BlockTags.STONE_BRICKS)
                .add(getId(GGBlocks.AMOEBALITH_BRICKS))
                .add(getId(GGBlocks.GELATITE_BRICKS));

        getTagBuilder(ConventionalBlockTags.STONES)
                .add(getId(GGBlocks.AMOEBALITH))
                .add(getId(GGBlocks.GELATITE));

        var jellies = getTagBuilder(GGBlockTags.JELLIES);
        for(Block jelly : GGBlocks.JELLY.keySet()) {
            jellies.add(getId(jelly));
        }

        getTagBuilder(GGBlockTags.GIPPLE_FOOD)
                .add(getId(Blocks.GLOW_LICHEN))
                .addOptional(Identifier.of("galosphere", "lichen_roots"))
                .addOptional(Identifier.of("galosphere", "bowl_lichen"))
                .addOptional(Identifier.of("galosphere", "lichen_shelf"))
        ;

        getTagBuilder(BlockTags.FLOWER_POTS).add(getId(GGBlocks.POTTED_GELATINOUS_GROWTH));

        StoneSet.ALL_SETS.forEach(stoneSet -> {
            stoneSet.getBlockFamily().getVariants().forEach((variant, block) -> {
                getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(getId(block));
            });
            TAG_KEY_MAP.forEach((variant, tagKey) -> {
                if (stoneSet.getBlockFamily().getVariants().containsKey(variant)){
                    getTagBuilder(tagKey).add(getId(stoneSet.getBlockFamily().getVariants().get(variant)));
                }
            });
        });

        getTagBuilder(BlockTags.HOE_MINEABLE).add(getId(GGBlocks.GELATIN_BLOCK));
    }

    public static Identifier getId(Block block){
        return Registries.BLOCK.getId(block);
    }
}
