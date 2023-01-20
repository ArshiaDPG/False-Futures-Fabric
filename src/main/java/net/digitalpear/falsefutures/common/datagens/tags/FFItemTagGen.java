package net.digitalpear.falsefutures.common.datagens.tags;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.digitalpear.falsefutures.init.tags.FFBiomeTags;
import net.digitalpear.falsefutures.init.tags.FFItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFItemTagGen extends FabricTagProvider<Item> {
    public FFItemTagGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.ITEM);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(FFItemTags.GIPPLE_FOOD)
                .add(Items.GLOW_LICHEN)
                .addOptional(new Identifier("galosphere", "lichen_cordyceps"));



        getOrCreateTagBuilder(ItemTags.MUSIC_DISCS).add(FFItems.MUSIC_DISC_GIPPLECORE);
        getOrCreateTagBuilder(ItemTags.WALLS)
                .add(FFBlocks.GELASTONE_WALL.asItem())
                .add(FFBlocks.GELASTONE_BRICK_WALL.asItem())
                .add(FFBlocks.DEEP_GELASTONE_WALL.asItem())
                .add(FFBlocks.DEEP_GELASTONE_BRICK_WALL.asItem());
        getOrCreateTagBuilder(ItemTags.STAIRS)
                .add(FFBlocks.GELASTONE_STAIRS.asItem())
                .add(FFBlocks.GELASTONE_BRICK_STAIRS.asItem())
                .add(FFBlocks.DEEP_GELASTONE_STAIRS.asItem())
                .add(FFBlocks.DEEP_GELASTONE_BRICK_STAIRS.asItem());
        getOrCreateTagBuilder(ItemTags.SLABS)
                .add(FFBlocks.GELASTONE_SLAB.asItem())
                .add(FFBlocks.GELASTONE_BRICK_SLAB.asItem())
                .add(FFBlocks.DEEP_GELASTONE_SLAB.asItem())
                .add(FFBlocks.DEEP_GELASTONE_BRICK_SLAB.asItem());
        getOrCreateTagBuilder(ItemTags.BUTTONS)
                .add(FFBlocks.GELASTONE_BUTTON.asItem())
                .add(FFBlocks.GELASTONE_BRICK_BUTTON.asItem())
                .add(FFBlocks.DEEP_GELASTONE_BUTTON.asItem())
                .add(FFBlocks.DEEP_GELASTONE_BRICK_BUTTON.asItem());
    }
}
