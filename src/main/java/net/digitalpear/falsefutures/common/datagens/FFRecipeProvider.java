package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;

import java.util.function.Consumer;

public class FFRecipeProvider extends FabricRecipeProvider {


    public FFRecipeProvider(FabricDataOutput output) {
        super(output);
    }



    public static void offerJellyRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, output, 2)
                .input('X', input)
                .input('#', FFItems.GELATIN)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .group("jellies")
                .criterion("has_gelatin", conditionsFromItem(FFItems.GELATIN)).offerTo(exporter);
    }
    public static void makeStairs(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .input('C', input)
                .pattern("C  ")
                .pattern("CC ")
                .pattern("CCC")
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, output, input);
    }
    public static void makeSlab(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        FabricRecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, output, Ingredient.ofItems(input)).criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, output, input, 2);
    }
    public static void makeWall(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, output, 6)
                .input('C', input)
                .pattern("CCC")
                .pattern("CCC")
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, output, input);
    }

    public static void makeButton(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE,output)
                .input(input)
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.REDSTONE, output, input);
    }
    public static void makePressurePlate(Consumer<RecipeJsonProvider> exporter, Block input, Block output, String criterion){
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE,output)
                .input('C', input)
                .pattern("CC")
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.REDSTONE, output, input);
    }

    public static void makeStoneRecipes(Consumer<RecipeJsonProvider> exporter, Block input, Block stairs, Block slab, Block button, Block pressurePlate, Block wall){
        String criteria = "has_" + Registries.BLOCK.getId(input).getPath();
        makeStairs(exporter, input, stairs, criteria);
        makeSlab(exporter, input, slab, criteria);
        makeButton(exporter, input, button, criteria);
        makePressurePlate(exporter, input, pressurePlate, criteria);
        makeWall(exporter, input, wall, criteria);
    }
    public static void makeStoneRecipes(Consumer<RecipeJsonProvider> exporter, Block input, Block stairs, Block slab, Block wall){
        String criteria = "has_" + Registries.BLOCK.getId(input).getPath();
        makeStairs(exporter, input, stairs, criteria);
        makeSlab(exporter, input, slab, criteria);
        makeWall(exporter, input, wall, criteria);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        offerReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, FFItems.GELATIN, RecipeCategory.DECORATIONS, FFBlocks.GELATIN_BLOCK);
        /*
            This map is used to generate the recipes and English translations of all Jellys.
         */
        FFBlocks.JELLY.forEach((jelly, ingredient) -> offerJellyRecipe(exporter, jelly, ingredient));

        createGelatinStoneMaking(exporter, Blocks.COBBLESTONE, FFBlocks.GELATITE);
        createGelatinStoneMaking(exporter, Blocks.COBBLED_DEEPSLATE, FFBlocks.AMOEBALITH);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, FFBlocks.GELATITE_BRICKS, 4)
                .input('X', FFBlocks.GELATITE)
                .pattern("XX")
                .pattern("XX")
                .criterion(hasItem(FFBlocks.GELATITE), conditionsFromItem(FFBlocks.GELATITE)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, FFBlocks.CHISELED_GELATITE_BRICKS, 1)
                .input('X', FFBlocks.GELATITE_BRICK_SLAB)
                .pattern("X")
                .pattern("X")
                .criterion(hasItem(FFBlocks.GELATITE), conditionsFromItem(FFBlocks.GELATITE)).offerTo(exporter);


        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.GELATITE_BRICKS, FFBlocks.GELATITE);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.GELATITE_BRICK_STAIRS, FFBlocks.GELATITE);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.GELATITE_BRICK_SLAB, FFBlocks.GELATITE, 2);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.GELATITE_BRICK_WALL, FFBlocks.GELATITE);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.CHISELED_GELATITE_BRICKS, FFBlocks.GELATITE);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, FFBlocks.AMOEBALITH_BRICKS, 4)
                .input('X', FFBlocks.AMOEBALITH)
                .pattern("XX")
                .pattern("XX")
                .criterion(hasItem(FFBlocks.AMOEBALITH), conditionsFromItem(FFBlocks.AMOEBALITH)).offerTo(exporter);

        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.AMOEBALITH_BRICKS, FFBlocks.AMOEBALITH);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.AMOEBALITH_BRICK_STAIRS, FFBlocks.AMOEBALITH);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.AMOEBALITH_BRICK_SLAB, FFBlocks.AMOEBALITH, 2);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.AMOEBALITH_BRICK_WALL, FFBlocks.AMOEBALITH);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.CHISELED_AMOEBALITH_BRICKS, FFBlocks.AMOEBALITH);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, FFBlocks.CHISELED_AMOEBALITH_BRICKS)
                .input('X', FFBlocks.AMOEBALITH_BRICK_SLAB)
                .pattern("X")
                .pattern("X")
                .criterion(hasItem(FFBlocks.AMOEBALITH), conditionsFromItem(FFBlocks.AMOEBALITH)).offerTo(exporter);

        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, FFBlocks.AMOEBALITH_BRICKS, FFBlocks.CHISELED_AMOEBALITH_BRICKS);
        makeStoneRecipes(exporter, FFBlocks.GELATITE, FFBlocks.GELATITE_STAIRS, FFBlocks.GELATITE_SLAB, FFBlocks.GELATITE_BUTTON,
                FFBlocks.GELATITE_PRESSURE_PLATE, FFBlocks.GELATITE_WALL);
        makeStoneRecipes(exporter, FFBlocks.GELATITE_BRICKS, FFBlocks.GELATITE_BRICK_STAIRS, FFBlocks.GELATITE_BRICK_SLAB, FFBlocks.GELATITE_BRICK_WALL);
        makeStoneRecipes(exporter, FFBlocks.AMOEBALITH, FFBlocks.AMOEBALITH_STAIRS, FFBlocks.AMOEBALITH_SLAB, FFBlocks.AMOEBALITH_BUTTON,
                FFBlocks.AMOEBALITH_PRESSURE_PLATE, FFBlocks.AMOEBALITH_WALL);
        makeStoneRecipes(exporter, FFBlocks.AMOEBALITH_BRICKS, FFBlocks.AMOEBALITH_BRICK_STAIRS, FFBlocks.AMOEBALITH_BRICK_SLAB,
                FFBlocks.AMOEBALITH_BRICK_WALL);
    }

    public void createGelatinStoneMaking(Consumer<RecipeJsonProvider> exporter, ItemConvertible stone, ItemConvertible output){
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output)
                .criterion(hasItem(FFItems.GELATIN), conditionsFromItem(FFItems.GELATIN))
                .input(stone).input(FFItems.GELATIN)
                .offerTo(exporter);
    }
}
