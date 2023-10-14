package net.digitalpear.gipples_galore.common.datagens.tags;

import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGItems;
import net.digitalpear.gipples_galore.init.tags.GGItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class GGItemTagProvider extends FabricTagProvider<Item> {


    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public GGItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(GGItemTags.GIPPLE_FOOD)
                .add(Items.GLOW_LICHEN)
                .addOptional(new Identifier("galosphere", "lichen_roots"))
                .addOptional(new Identifier("galosphere", "bowl_lichen"))
                .addOptional(new Identifier("galosphere", "lichen_shelf"))
                .addOptional(new Identifier("galosphere", "lichen_cordyceps"));


        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS).add(GGItems.MUSIC_DISC_GIPPLECORE);

        getOrCreateTagBuilder(ItemTags.WALLS)
                .add(GGBlocks.GELATITE_WALL.asItem())
                .add(GGBlocks.GELATITE_BRICK_WALL.asItem())
                .add(GGBlocks.AMOEBALITH_WALL.asItem())
                .add(GGBlocks.AMOEBALITH_BRICK_WALL.asItem());
        getOrCreateTagBuilder(ItemTags.STAIRS)
                .add(GGBlocks.GELATITE_STAIRS.asItem())
                .add(GGBlocks.GELATITE_BRICK_STAIRS.asItem())
                .add(GGBlocks.AMOEBALITH_STAIRS.asItem())
                .add(GGBlocks.AMOEBALITH_BRICK_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.SLABS)
                .add(GGBlocks.GELATITE_SLAB.asItem())
                .add(GGBlocks.GELATITE_BRICK_SLAB.asItem())
                .add(GGBlocks.AMOEBALITH_SLAB.asItem())
                .add(GGBlocks.AMOEBALITH_BRICK_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.BUTTONS)
                .add(GGBlocks.GELATITE_BUTTON.asItem())
                .add(GGBlocks.AMOEBALITH_BUTTON.asItem());
    }
}
