package net.digitalpear.gipples_galore.common.datagens.tags;

import com.google.common.collect.ImmutableMap;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.data.sets.StoneSet;
import net.digitalpear.gipples_galore.init.tags.GGItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class GGItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public static final Map<BlockFamily.Variant, TagKey<Item>> TAG_KEY_MAP = Map.of(
            BlockFamily.Variant.WALL, ItemTags.WALLS,
            BlockFamily.Variant.BUTTON, ItemTags.STONE_BUTTONS,
            BlockFamily.Variant.STAIRS, ItemTags.STAIRS,
            BlockFamily.Variant.SLAB, ItemTags.SLABS);
    public GGItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture, new GGBlockTagProvider(output, completableFuture));
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getTagBuilder(GGItemTags.GIPPLE_FOOD)
                .add(getId(Items.GLOW_LICHEN))
                .addOptional(Identifier.of("galosphere", "lichen_roots"))
                .addOptional(Identifier.of("galosphere", "bowl_lichen"))
                .addOptional(Identifier.of("galosphere", "lichen_shelf"))
                .addOptional(Identifier.of("galosphere", "lichen_cordyceps"));

        StoneSet.ALL_SETS.forEach(stoneSet -> {
            TAG_KEY_MAP.forEach((variant, tagKey) -> {
                if (stoneSet.getBlockFamily().getVariants().containsKey(variant)){
                    getTagBuilder(tagKey).add(getId(stoneSet.getBlockFamily().getVariants().get(variant)));
                }
            });
        });

        getTagBuilder(ItemTags.STONE_BRICKS)
                .add(getId(GGBlocks.AMOEBALITH_BRICKS))
                .add(getId(GGBlocks.GELATITE_BRICKS));

        getTagBuilder(ConventionalItemTags.STONES)
                .add(getId(GGBlocks.AMOEBALITH))
                .add(getId(GGBlocks.GELATITE));
    }

    public static Identifier getId(ItemConvertible block){
        return Registries.ITEM.getId(block.asItem());
    }
}
