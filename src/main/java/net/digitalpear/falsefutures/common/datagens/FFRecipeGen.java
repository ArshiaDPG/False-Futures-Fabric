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
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.registry.Registry;

import java.util.function.Consumer;

public class FFRecipeGen extends FabricRecipeProvider {
    /*
        This map is used to generate the recipes and English translations of all Jellys.
     */
    public FFRecipeGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {

        FFBlocks.JELLY.forEach((jelly, ingredient) -> offerJellyRecipe(exporter, jelly, ingredient));

        ShapelessRecipeJsonBuilder.create(FFBlocks.GELATITE)
                .input(Blocks.COBBLESTONE)
                .input(FFItems.GELATIN)
                .criterion("has_gelatin", conditionsFromItem(FFItems.GELATIN)).offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(FFBlocks.BRINESHALE)
                .input(Blocks.COBBLED_DEEPSLATE)
                .input(FFItems.GELATIN)
                .criterion("has_gelatin", conditionsFromItem(FFItems.GELATIN)).offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(FFItems.GIPPLE_BANNER_PATTERN)
                .input(Items.PAPER)
                .input(FFItems.GIPPLE_BUCKET)
                .criterion("has_gipple_bucket", conditionsFromItem(FFItems.GIPPLE_BUCKET)).offerTo(exporter);


        ShapedRecipeJsonBuilder.create(FFBlocks.GELATITE_BRICKS, 4)
                .input('X', FFBlocks.GELATITE)
                .pattern("XX")
                .pattern("XX")
                .criterion("has_gelatite", conditionsFromItem(FFBlocks.GELATITE)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, FFBlocks.GELATITE, FFBlocks.GELATITE_BRICKS);

        ShapedRecipeJsonBuilder.create(FFBlocks.CHISELED_GELATITE_BRICKS, 1)
                .input('X', FFBlocks.GELATITE_BRICK_SLAB)
                .pattern("X")
                .pattern("X")
                .criterion("has_gelatite", conditionsFromItem(FFBlocks.GELATITE)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, FFBlocks.GELATITE_BRICKS, FFBlocks.CHISELED_GELATITE_BRICKS);
        
        ShapedRecipeJsonBuilder.create(FFBlocks.BRINESHALE_BRICKS, 4)
                .input('X', FFBlocks.BRINESHALE)
                .pattern("XX")
                .pattern("XX")
                .criterion("has_brineshale", conditionsFromItem(FFBlocks.BRINESHALE)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, FFBlocks.BRINESHALE, FFBlocks.BRINESHALE_BRICKS);

        ShapedRecipeJsonBuilder.create(FFBlocks.CHISELED_BRINESHALE_BRICKS, 1)
                .input('X', FFBlocks.BRINESHALE_BRICK_SLAB)
                .pattern("X")
                .pattern("X")
                .criterion("has_brineshale", conditionsFromItem(FFBlocks.BRINESHALE)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, FFBlocks.BRINESHALE_BRICKS, FFBlocks.CHISELED_BRINESHALE_BRICKS);

        makeStoneRecipes(exporter, FFBlocks.GELATITE, FFBlocks.GELATITE_STAIRS, FFBlocks.GELATITE_SLAB, FFBlocks.GELATITE_BUTTON,
                FFBlocks.GELATITE_PRESSURE_PLATE, FFBlocks.GELATITE_WALL);
        makeStoneRecipes(exporter, FFBlocks.GELATITE_BRICKS, FFBlocks.GELATITE_BRICK_STAIRS, FFBlocks.GELATITE_BRICK_SLAB, FFBlocks.GELATITE_BRICK_WALL);
        makeStoneRecipes(exporter, FFBlocks.BRINESHALE, FFBlocks.BRINESHALE_STAIRS, FFBlocks.BRINESHALE_SLAB, FFBlocks.BRINESHALE_BUTTON,
                FFBlocks.BRINESHALE_PRESSURE_PLATE, FFBlocks.BRINESHALE_WALL);
        makeStoneRecipes(exporter, FFBlocks.BRINESHALE_BRICKS, FFBlocks.BRINESHALE_BRICK_STAIRS, FFBlocks.BRINESHALE_BRICK_SLAB,
                FFBlocks.BRINESHALE_BRICK_WALL);
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
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void makeSlab(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        FabricRecipeProvider.createStairsRecipe(output, Ingredient.ofItems(input)).criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, output, input, 2);
    }
    public static void makeWall(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapedRecipeJsonBuilder.create(output, 6)
                .input('C', input)
                .pattern("CCC")
                .pattern("CCC")
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void makeButton(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapelessRecipeJsonBuilder.create(output)
                .input(input)
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void makePressurePlate(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapedRecipeJsonBuilder.create(output)
                .input('C', input)
                .pattern("CC")
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }

    public static void makeStoneRecipes(Consumer<RecipeJsonProvider> exporter, Block input, Block stairs, Block slab, Block button, Block pressurePlate, Block wall){
        String criteria = "has_" + Registry.BLOCK.getId(input).getPath();
        makeStairs(exporter, input, stairs, criteria);
        makeSlab(exporter, input, slab, criteria);
        makeButton(exporter, input, button, criteria);
        makePressurePlate(exporter, input, pressurePlate, criteria);
        makeWall(exporter, input, wall, criteria);
    }
    public static void makeStoneRecipes(Consumer<RecipeJsonProvider> exporter, Block input, Block stairs, Block slab, Block wall){
        String criteria = "has_" + Registry.BLOCK.getId(input).getPath();
        makeStairs(exporter, input, stairs, criteria);
        makeSlab(exporter, input, slab, criteria);
        makeWall(exporter, input, wall, criteria);
    }
}
