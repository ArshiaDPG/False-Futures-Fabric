package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FFRecipeGen extends FabricRecipeProvider {
    /*
        This map is used to generate the recipes and English translations of all Jellys.
     */
    public static final Map<Block, Item> JELLY = new HashMap<>();
    public FFRecipeGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
        JELLY.put(FFBlocks.WEIRD_JELLY, Items.SLIME_BALL);
        JELLY.put(FFBlocks.PLAIN_JELLY, FFItems.GELATIN);
        JELLY.put(FFBlocks.FLORAL_JELLY, Items.HONEY_BOTTLE);
        JELLY.put(FFBlocks.FRUITY_JELLY, Items.MELON_SLICE);
        JELLY.put(FFBlocks.MILKY_JELLY, Items.MILK_BUCKET);
        JELLY.put(FFBlocks.SWEET_JELLY, Items.SWEET_BERRIES);
        JELLY.put(FFBlocks.SYMPHONIC_JELLY, Items.CHORUS_FRUIT);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        JELLY.forEach((jelly, ingredient) -> offerJellyRecipe(exporter, jelly, ingredient));

        ShapelessRecipeJsonBuilder.create(FFBlocks.GELASTONE)
                .input(Blocks.COBBLESTONE)
                .input(FFItems.GELATIN)
                .criterion("has_gelatin", conditionsFromItem(FFItems.GELATIN)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(FFBlocks.DEEP_GELASTONE)
                .input(Blocks.COBBLED_DEEPSLATE)
                .input(FFItems.GELATIN)
                .criterion("has_gelatin", conditionsFromItem(FFItems.GELATIN)).offerTo(exporter);



        ShapedRecipeJsonBuilder.create(FFBlocks.GELASTONE_BRICKS, 4)
                .input('X', FFBlocks.GELASTONE)
                .pattern("XX")
                .pattern("XX")
                .criterion("has_gelastone", conditionsFromItem(FFBlocks.GELASTONE)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, FFBlocks.GELASTONE, FFBlocks.GELASTONE_BRICKS);

        ShapedRecipeJsonBuilder.create(FFBlocks.DEEP_GELASTONE_BRICKS, 4)
                .input('X', FFBlocks.DEEP_GELASTONE)
                .pattern("XX")
                .pattern("XX")
                .criterion("has_deep_gelastone", conditionsFromItem(FFBlocks.DEEP_GELASTONE)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, FFBlocks.DEEP_GELASTONE, FFBlocks.DEEP_GELASTONE_BRICKS);

        makeStoneRecipes(exporter, FFBlocks.GELASTONE, FFBlocks.GELASTONE_STAIRS, FFBlocks.GELASTONE_SLAB, FFBlocks.GELASTONE_BUTTON,
                FFBlocks.GELASTONE_PRESSURE_PLATE, FFBlocks.GELASTONE_WALL);
        makeStoneRecipes(exporter, FFBlocks.GELASTONE_BRICKS, FFBlocks.GELASTONE_BRICK_STAIRS, FFBlocks.GELASTONE_BRICK_SLAB,
                FFBlocks.GELASTONE_BRICK_BUTTON, FFBlocks.GELASTONE_BRICK_PRESSURE_PLATE, FFBlocks.GELASTONE_BRICK_WALL);
        makeStoneRecipes(exporter, FFBlocks.DEEP_GELASTONE, FFBlocks.DEEP_GELASTONE_STAIRS, FFBlocks.DEEP_GELASTONE_SLAB, FFBlocks.DEEP_GELASTONE_BUTTON,
                FFBlocks.DEEP_GELASTONE_PRESSURE_PLATE, FFBlocks.DEEP_GELASTONE_WALL);
        makeStoneRecipes(exporter, FFBlocks.DEEP_GELASTONE_BRICKS, FFBlocks.DEEP_GELASTONE_BRICK_STAIRS, FFBlocks.DEEP_GELASTONE_BRICK_SLAB,
                FFBlocks.DEEP_GELASTONE_BRICK_WALL, FFBlocks.DEEP_GELASTONE_BRICK_PRESSURE_PLATE, FFBlocks.DEEP_GELASTONE_BRICK_BUTTON);
    }


    public static void offerJellyRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(output, 2)
                .input('X', input)
                .input('#', FFItems.GELATIN)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .group("jellies")
                .criterion("has_gelatin", conditionsFromItem(FFItems.GELATIN)).offerTo(exporter);
    }
    public static void makeStairs(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapedRecipeJsonBuilder.create(output, 4)
                .input('C', input)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, input, output);
    }
    public static void makeSlab(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        FabricRecipeProvider.createStairsRecipe(output, Ingredient.ofItems(input)).criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, input, output, 2);
    }
    public static void makeWall(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapedRecipeJsonBuilder.create(output, 6)
                .input('C', input)
                .pattern("CCC")
                .pattern("CCC")
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, input, output);
    }
    public static void makeButton(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapelessRecipeJsonBuilder.create(output)
                .input(input)
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, input, output);
    }
    public static void makePressurePlate(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapedRecipeJsonBuilder.create(output)
                .input('C', input)
                .pattern("CC")
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, input, output);
    }

    public static void makeStoneRecipes(Consumer<RecipeJsonProvider> exporter, Block input, Block stairs, Block slab, Block button, Block pressurePlate, Block wall){
        String criteria = "has_" + Registry.BLOCK.getId(input).getPath();
        makeStairs(exporter, input, stairs, criteria);
        makeSlab(exporter, input, slab, criteria);
        makeButton(exporter, input, button, criteria);
        makePressurePlate(exporter, input, pressurePlate, criteria);
        makeWall(exporter, input, wall, criteria);
    }
}
