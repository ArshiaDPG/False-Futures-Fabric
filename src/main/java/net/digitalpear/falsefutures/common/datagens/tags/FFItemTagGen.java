package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.digitalpear.falsefutures.init.tags.FFItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class FFItemTagGen extends FabricTagProvider<Item> {


    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public FFItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(FFItemTags.GIPPLE_FOOD)
                .add(Items.GLOW_LICHEN)
                .addOptional(new Identifier("galosphere", "lichen_cordyceps"));



        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS).add(FFItems.MUSIC_DISC_GIPPLECORE);
        getOrCreateTagBuilder(ItemTags.WALLS)
                .add(FFBlocks.GELATITE_WALL.asItem())
                .add(FFBlocks.GELATITE_BRICK_WALL.asItem())
                .add(FFBlocks.BRINESHALE_WALL.asItem())
                .add(FFBlocks.BRINESHALE_BRICK_WALL.asItem());
        getOrCreateTagBuilder(ItemTags.STAIRS)
                .add(FFBlocks.GELATITE_STAIRS.asItem())
                .add(FFBlocks.GELATITE_BRICK_STAIRS.asItem())
                .add(FFBlocks.BRINESHALE_STAIRS.asItem())
                .add(FFBlocks.BRINESHALE_BRICK_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.SLABS)
                .add(FFBlocks.GELATITE_SLAB.asItem())
                .add(FFBlocks.GELATITE_BRICK_SLAB.asItem())
                .add(FFBlocks.BRINESHALE_SLAB.asItem())
                .add(FFBlocks.BRINESHALE_BRICK_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.BUTTONS)
                .add(FFBlocks.GELATITE_BUTTON.asItem())
                .add(FFBlocks.BRINESHALE_BUTTON.asItem());
    }
}
