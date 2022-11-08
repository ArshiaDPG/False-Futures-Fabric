package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FFRecipeGeneration extends FabricRecipeProvider {
    public static final Map<Item, Item> JELLY = new HashMap<>();
    public FFRecipeGeneration(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
        JELLY.put(FFBlocks.FRUITY_JELLO_CAKE.asItem(), Items.MELON_SLICE);
        JELLY.put(FFBlocks.WEIRD_JELLO_CAKE.asItem(), Items.SEAGRASS);
        JELLY.put(FFBlocks.PLAIN_JELLO_CAKE.asItem(), FFItems.GELATIN);
        JELLY.put(FFBlocks.FLORAL_JELLO_CAKE.asItem(), Items.SUNFLOWER);
        JELLY.put(FFBlocks.FRUITY_JELLO_CAKE.asItem(), Items.MELON_SLICE);
        JELLY.put(FFBlocks.MILKY_JELLO_CAKE.asItem(), Items.MILK_BUCKET);
        JELLY.put(FFBlocks.SWEET_JELLO_CAKE.asItem(), Items.MELON_SLICE);
        JELLY.put(FFBlocks.SYMPHONIC_JELLO_CAKE.asItem(), Items.CHORUS_FRUIT);

    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
//        offerJellyRecipe(exporter, FFBlocks.FRUITY_JELLO_CAKE, Items.MELON_SLICE);
//        offerJellyRecipe(exporter, FFBlocks.WEIRD_JELLO_CAKE, Items.SEAGRASS);
//        offerJellyRecipe(exporter, FFBlocks.PLAIN_JELLO_CAKE, FFItems.GELATIN);
//        offerJellyRecipe(exporter, FFBlocks.FLORAL_JELLO_CAKE, Items.SUNFLOWER);
        JELLY.forEach((jelly, item) -> {
            offerJellyRecipe(exporter, jelly, item);
        });
    }
    public static void offerJellyRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(output, 1)
                .input('#', input)
                .input('X', FFItems.GELATIN)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .group("jelly")
                .criterion("has_gelatin",
                        conditionsFromItem(FFItems.GELATIN)).offerTo(exporter);
    }
}
