package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GGRecipeProvider extends FabricRecipeProvider {


    public GGRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, GGItems.GELATIN, RecipeCategory.DECORATIONS, GGBlocks.GELATIN_BLOCK);
        /*
            This map is used to generate the recipes and English translations of all Jellys.
         */
        GGBlocks.JELLY.forEach((jelly, ingredient) -> offerJellyRecipe(exporter, jelly, ingredient));

        createGelatinStoneMaking(exporter, Blocks.COBBLESTONE, GGBlocks.GELATITE);
        createGelatinStoneMaking(exporter, Blocks.COBBLED_DEEPSLATE, GGBlocks.AMOEBALITH);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICKS, 4)
                .input('X', GGBlocks.GELATITE)
                .pattern("XX")
                .pattern("XX")
                .criterion(hasItem(GGBlocks.GELATITE), conditionsFromItem(GGBlocks.GELATITE)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, GGBlocks.CHISELED_GELATITE_BRICKS, 1)
                .input('X', GGBlocks.GELATITE_BRICK_SLAB)
                .pattern("X")
                .pattern("X")
                .criterion(hasItem(GGBlocks.GELATITE), conditionsFromItem(GGBlocks.GELATITE)).offerTo(exporter);


        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICKS, GGBlocks.GELATITE);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICK_STAIRS, GGBlocks.GELATITE);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICK_SLAB, GGBlocks.GELATITE, 2);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICK_WALL, GGBlocks.GELATITE);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.CHISELED_GELATITE_BRICKS, GGBlocks.GELATITE);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICKS, 4)
                .input('X', GGBlocks.AMOEBALITH)
                .pattern("XX")
                .pattern("XX")
                .criterion(hasItem(GGBlocks.AMOEBALITH), conditionsFromItem(GGBlocks.AMOEBALITH)).offerTo(exporter);

        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICK_STAIRS, GGBlocks.AMOEBALITH);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICK_SLAB, GGBlocks.AMOEBALITH, 2);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICK_WALL, GGBlocks.AMOEBALITH);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.CHISELED_AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, GGBlocks.CHISELED_AMOEBALITH_BRICKS)
                .input('X', GGBlocks.AMOEBALITH_BRICK_SLAB)
                .pattern("X")
                .pattern("X")
                .criterion(hasItem(GGBlocks.AMOEBALITH), conditionsFromItem(GGBlocks.AMOEBALITH)).offerTo(exporter);

        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICKS, GGBlocks.CHISELED_AMOEBALITH_BRICKS);

        makeStoneRecipes(exporter, GGBlocks.GELATITE, GGBlocks.GELATITE_STAIRS, GGBlocks.GELATITE_SLAB, GGBlocks.GELATITE_BUTTON,
                GGBlocks.GELATITE_PRESSURE_PLATE, GGBlocks.GELATITE_WALL);
        makeStoneRecipes(exporter, GGBlocks.GELATITE_BRICKS, GGBlocks.GELATITE_BRICK_STAIRS, GGBlocks.GELATITE_BRICK_SLAB, GGBlocks.GELATITE_BRICK_WALL);
        makeStoneRecipes(exporter, GGBlocks.AMOEBALITH, GGBlocks.AMOEBALITH_STAIRS, GGBlocks.AMOEBALITH_SLAB, GGBlocks.AMOEBALITH_BUTTON,
                GGBlocks.AMOEBALITH_PRESSURE_PLATE, GGBlocks.AMOEBALITH_WALL);
        makeStoneRecipes(exporter, GGBlocks.AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH_BRICK_STAIRS, GGBlocks.AMOEBALITH_BRICK_SLAB,
                GGBlocks.AMOEBALITH_BRICK_WALL);
    }

    public void createGelatinStoneMaking(RecipeExporter exporter, ItemConvertible stone, ItemConvertible output){
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output)
                .criterion(hasItem(GGItems.GELATIN), conditionsFromItem(GGItems.GELATIN))
                .pattern("SG")
                .pattern("GS")
                .input('S', stone).input('G', GGItems.GELATIN)
                .offerTo(exporter);
    }


    public static void offerJellyRecipe(RecipeExporter exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, output, 2)
                .input('X', input)
                .input('#', GGItems.GELATIN)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .group("jellies")
                .criterion("has_gelatin", conditionsFromItem(GGItems.GELATIN)).offerTo(exporter);
    }

    public static void makeButton(RecipeExporter exporter, ItemConvertible input, ItemConvertible output, String criterion){
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE,output)
                .input(input)
                .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.REDSTONE, output, input);
    }


    public static void makeStoneRecipes(RecipeExporter exporter, ItemConvertible input, ItemConvertible stairs, ItemConvertible slab, ItemConvertible button, ItemConvertible pressurePlate, ItemConvertible wall){
        String criteria = "has_" + Registries.ITEM.getId(input.asItem()).getPath();

        makeStoneRecipes(exporter, input, stairs, slab, wall);

        makeButton(exporter, input, button, criteria);

        FabricRecipeProvider.offerPressurePlateRecipe(exporter, pressurePlate, input);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.REDSTONE, pressurePlate, input);
    }
    public static void makeStoneRecipes(RecipeExporter exporter, ItemConvertible input, ItemConvertible stairs, ItemConvertible slab, ItemConvertible wall){
        String criteria = "has_" + Registries.ITEM.getId(input.asItem()).getPath();

        FabricRecipeProvider.createStairsRecipe(stairs, Ingredient.ofItems(input)).criterion(criteria, conditionsFromItem(input)).offerTo(exporter);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, stairs, input);

        FabricRecipeProvider.offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, slab, input);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS, slab, input, 2);

        FabricRecipeProvider.offerWallRecipe(exporter, RecipeCategory.DECORATIONS, wall, input);
        FabricRecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, wall, input);
    }
}
