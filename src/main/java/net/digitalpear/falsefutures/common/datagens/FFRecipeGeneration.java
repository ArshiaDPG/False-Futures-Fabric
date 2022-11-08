package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FFRecipeGeneration extends FabricRecipeProvider {


    /*
        This map is used to generate the recipes and English translations of all Jellys.
     */
    public static final Map<Item, Item> JELLY = new HashMap<>();
    public FFRecipeGeneration(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
        JELLY.put(FFBlocks.FRUITY_JELLY.asItem(), Items.MELON_SLICE);
        JELLY.put(FFBlocks.WEIRD_JELLY.asItem(), Items.SLIME_BALL);
        JELLY.put(FFBlocks.PLAIN_JELLY.asItem(), FFItems.GELATIN);
        JELLY.put(FFBlocks.FLORAL_JELLY.asItem(), Items.HONEY_BOTTLE);
        JELLY.put(FFBlocks.FRUITY_JELLY.asItem(), Items.MELON_SLICE);
        JELLY.put(FFBlocks.MILKY_JELLY.asItem(), Items.MILK_BUCKET);
        JELLY.put(FFBlocks.SWEET_JELLY.asItem(), Items.SWEET_BERRIES);
        JELLY.put(FFBlocks.SYMPHONIC_JELLY.asItem(), Items.CHORUS_FRUIT);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        JELLY.forEach((jelly, item) -> {
            offerJellyRecipe(exporter, jelly, item);
        });
    }
    public static void offerJellyRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(output, 3)
                .input('#', input)
                .input('X', FFItems.GELATIN)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .group("jellies")
                .criterion("has_gelatin",
                        conditionsFromItem(FFItems.GELATIN)).offerTo(exporter);
    }
}
