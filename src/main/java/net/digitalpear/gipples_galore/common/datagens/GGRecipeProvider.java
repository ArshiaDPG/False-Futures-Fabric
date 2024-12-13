package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class GGRecipeProvider extends FabricRecipeProvider {


    public GGRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new GGRecipeGenerator(registryLookup, exporter);
    }



    @Override
    public String getName() {
        return "recipe";
    }

    public class GGRecipeGenerator extends RecipeGenerator {
        public RegistryEntryLookup<Item> registryEntryLookup = registries.getOrThrow(RegistryKeys.ITEM);
        protected GGRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
            super(registries, exporter);
        }

        @Override
        public void generate() {
            offerReversibleCompactingRecipes(RecipeCategory.FOOD, GGItems.GELATIN, RecipeCategory.DECORATIONS, GGBlocks.GELATIN_BLOCK);
        /*
            This map is used to generate the recipes and English translations of all Jellys.
         */
            GGBlocks.JELLY.forEach(this::offerJellyRecipe);

            createGelatinStoneMaking(Blocks.COBBLESTONE, GGBlocks.GELATITE);
            createGelatinStoneMaking(Blocks.COBBLED_DEEPSLATE, GGBlocks.AMOEBALITH);

            ShapedRecipeJsonBuilder.create(registryEntryLookup, RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICKS, 4)
                    .input('X', GGBlocks.GELATITE)
                    .pattern("XX")
                    .pattern("XX")
                    .criterion(hasItem(GGBlocks.GELATITE), conditionsFromItem(GGBlocks.GELATITE)).offerTo(exporter);

            ShapedRecipeJsonBuilder.create(registryEntryLookup, RecipeCategory.BUILDING_BLOCKS, GGBlocks.CHISELED_GELATITE_BRICKS, 1)
                    .input('X', GGBlocks.GELATITE_BRICK_SLAB)
                    .pattern("X")
                    .pattern("X")
                    .criterion(hasItem(GGBlocks.GELATITE), conditionsFromItem(GGBlocks.GELATITE)).offerTo(exporter);


            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICKS, GGBlocks.GELATITE);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICK_STAIRS, GGBlocks.GELATITE);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICK_SLAB, GGBlocks.GELATITE, 2);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.GELATITE_BRICK_WALL, GGBlocks.GELATITE);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.CHISELED_GELATITE_BRICKS, GGBlocks.GELATITE);

            ShapedRecipeJsonBuilder.create(registryEntryLookup, RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICKS, 4)
                    .input('X', GGBlocks.AMOEBALITH)
                    .pattern("XX")
                    .pattern("XX")
                    .criterion(hasItem(GGBlocks.AMOEBALITH), conditionsFromItem(GGBlocks.AMOEBALITH)).offerTo(exporter);

            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICK_STAIRS, GGBlocks.AMOEBALITH);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICK_SLAB, GGBlocks.AMOEBALITH, 2);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICK_WALL, GGBlocks.AMOEBALITH);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.CHISELED_AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH);

            ShapedRecipeJsonBuilder.create(registryEntryLookup, RecipeCategory.BUILDING_BLOCKS, GGBlocks.CHISELED_AMOEBALITH_BRICKS)
                    .input('X', GGBlocks.AMOEBALITH_BRICK_SLAB)
                    .pattern("X")
                    .pattern("X")
                    .criterion(hasItem(GGBlocks.AMOEBALITH), conditionsFromItem(GGBlocks.AMOEBALITH)).offerTo(exporter);

            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, GGBlocks.AMOEBALITH_BRICKS, GGBlocks.CHISELED_AMOEBALITH_BRICKS);

            makeStoneRecipes(GGBlocks.GELATITE, GGBlocks.GELATITE_STAIRS, GGBlocks.GELATITE_SLAB, GGBlocks.GELATITE_BUTTON,
                    GGBlocks.GELATITE_PRESSURE_PLATE, GGBlocks.GELATITE_WALL);
            makeStoneRecipes(GGBlocks.GELATITE_BRICKS, GGBlocks.GELATITE_BRICK_STAIRS, GGBlocks.GELATITE_BRICK_SLAB, GGBlocks.GELATITE_BRICK_WALL);
            makeStoneRecipes(GGBlocks.AMOEBALITH, GGBlocks.AMOEBALITH_STAIRS, GGBlocks.AMOEBALITH_SLAB, GGBlocks.AMOEBALITH_BUTTON,
                    GGBlocks.AMOEBALITH_PRESSURE_PLATE, GGBlocks.AMOEBALITH_WALL);
            makeStoneRecipes(GGBlocks.AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH_BRICK_STAIRS, GGBlocks.AMOEBALITH_BRICK_SLAB,
                    GGBlocks.AMOEBALITH_BRICK_WALL);
        }

        public void createGelatinStoneMaking(ItemConvertible stone, ItemConvertible output){
            ShapedRecipeJsonBuilder.create(registryEntryLookup, RecipeCategory.BUILDING_BLOCKS, output)
                    .criterion(hasItem(GGItems.GELATIN), conditionsFromItem(GGItems.GELATIN))
                    .pattern("SG")
                    .pattern("GS")
                    .input('S', stone).input('G', GGItems.GELATIN)
                    .offerTo(exporter);
        }


        public void offerJellyRecipe(ItemConvertible output, ItemConvertible input) {
            ShapedRecipeJsonBuilder.create(registryEntryLookup, RecipeCategory.FOOD, output, 2)
                    .input('X', input)
                    .input('#', GGItems.GELATIN)
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("###")
                    .group("jellies")
                    .criterion("has_gelatin", conditionsFromItem(GGItems.GELATIN)).offerTo(exporter);
        }

        public void makeButton(ItemConvertible input, ItemConvertible output, String criterion){
            ShapelessRecipeJsonBuilder.create(registryEntryLookup, RecipeCategory.REDSTONE,output)
                    .input(input)
                    .criterion(criterion, conditionsFromItem(input)).offerTo(exporter);
            offerStonecuttingRecipe(RecipeCategory.REDSTONE, output, input);
        }


        public void makeStoneRecipes(ItemConvertible input, ItemConvertible stairs, ItemConvertible slab, ItemConvertible button, ItemConvertible pressurePlate, ItemConvertible wall){
            String criteria = "has_" + Registries.ITEM.getId(input.asItem()).getPath();

            makeStoneRecipes(input, stairs, slab, wall);

            makeButton(input, button, criteria);

            offerPressurePlateRecipe(pressurePlate, input);
            offerStonecuttingRecipe(RecipeCategory.REDSTONE, pressurePlate, input);
        }
        public void makeStoneRecipes(ItemConvertible input, ItemConvertible stairs, ItemConvertible slab, ItemConvertible wall){
            String criteria = "has_" + Registries.ITEM.getId(input.asItem()).getPath();

            createStairsRecipe(stairs, Ingredient.ofItems(input)).criterion(criteria, conditionsFromItem(input)).offerTo(exporter);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stairs, input);

            offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, slab, input);
            offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, slab, input, 2);

            offerWallRecipe(RecipeCategory.DECORATIONS, wall, input);
            offerStonecuttingRecipe(RecipeCategory.DECORATIONS, wall, input);
        }
    }

}
