package net.digitalpear.falsefutures.common.datagens;

import com.google.gson.JsonElement;
import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.BiConsumer;
import java.util.function.Supplier;


public class FFBlockModelGen extends FabricModelProvider {
    public FFBlockModelGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerGipplePad(blockStateModelGenerator);
        registerGelatinLayers(blockStateModelGenerator);

        registerBlockSetMirrorable(blockStateModelGenerator, FFBlocks.GELASTONE, FFBlocks.GELASTONE_STAIRS, FFBlocks.GELASTONE_SLAB,
                FFBlocks.GELASTONE_WALL, FFBlocks.GELASTONE_PRESSURE_PLATE, FFBlocks.GELASTONE_BUTTON);
        registerBrickBlockSet(blockStateModelGenerator, FFBlocks.GELASTONE_BRICKS, FFBlocks.GELASTONE_BRICK_STAIRS, FFBlocks.GELASTONE_BRICK_SLAB,
                FFBlocks.GELASTONE_BRICK_WALL, FFBlocks.GELASTONE_BRICK_PRESSURE_PLATE, FFBlocks.GELASTONE_BRICK_BUTTON);

        registerBlockSetMirrorable(blockStateModelGenerator, FFBlocks.DEEP_GELASTONE, FFBlocks.DEEP_GELASTONE_STAIRS, FFBlocks.DEEP_GELASTONE_SLAB,
                FFBlocks.DEEP_GELASTONE_WALL, FFBlocks.DEEP_GELASTONE_PRESSURE_PLATE, FFBlocks.DEEP_GELASTONE_BUTTON);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(FFItems.GELATIN, Models.GENERATED);
        itemModelGenerator.register(FFItems.GIPPLE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(FFItems.MUSIC_DISC_GIPPLECORE, Models.GENERATED);
    }




    private void registerGelatinLayers(BlockStateModelGenerator blockStateModelGenerator) {
        TextureMap textureMap = TextureMap.all(FFBlocks.GELATIN_LAYER);
        Identifier identifier = Models.CUBE_ALL.upload(FFBlocks.GELATIN_LAYER, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(FFBlocks.GELATIN_LAYER).coordinate(BlockStateVariantMap.create(Properties.LAYERS).register((height) -> {
            BlockStateVariant blockStateVariant = BlockStateVariant.create();
            VariantSetting variantSettings = VariantSettings.MODEL;
            Identifier stateName;
            if (height < 8) {
                Block block = FFBlocks.GELATIN_LAYER;
                int currentHeight = height;
                stateName = ModelIds.getBlockSubModelId(block, "_height" + currentHeight * 2);
            } else {
                stateName = identifier;
            }
            return blockStateVariant.put(variantSettings, stateName);
        })));
        blockStateModelGenerator.registerParentedItemModel(FFBlocks.GELATIN_LAYER, ModelIds.getBlockSubModelId(Blocks.SNOW, "_height2"));
    }



    private void registerGipplePad(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerItemModel(FFBlocks.GIPPLEPAD);
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(FFBlocks.GIPPLEPAD,
                ModelIds.getBlockModelId(FFBlocks.GIPPLEPAD)));
    }

    public static void registerBrickBlockSet(BlockStateModelGenerator blockStateModelGenerator, Block base, Block stairs, Block slab, Block wall, Block pressurePlate, Block button){
        registerSideMirrorable(blockStateModelGenerator, base);
        createStairs(blockStateModelGenerator, stairs);
        createSlab(blockStateModelGenerator, base, slab);
        createWall(blockStateModelGenerator, wall);
        makePressurePlate(blockStateModelGenerator, pressurePlate);
        makeButton(blockStateModelGenerator, button);
    }

    public static void registerBlockSetMirrorable(BlockStateModelGenerator blockStateModelGenerator, Block base, Block stairs, Block slab, Block wall, Block pressurePlate, Block button){
        blockStateModelGenerator.registerMirrorable(base);
        createStairs(blockStateModelGenerator, stairs);
        createSlab(blockStateModelGenerator, base, slab);
        createWall(blockStateModelGenerator, wall);
        makePressurePlate(blockStateModelGenerator, pressurePlate);
        makeButton(blockStateModelGenerator, button);
    }

    public static void createStairs(BlockStateModelGenerator blockStateModelGenerator, Block stairs){
        String stairs_name = Registry.BLOCK.getId(stairs).getPath();
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createStairsBlockState(stairs,
                new Identifier(FalseFutures.MOD_ID, stairs_name + "_inner"),
                new Identifier(FalseFutures.MOD_ID, stairs_name),
                new Identifier(FalseFutures.MOD_ID, stairs_name + "_outer")));
    }

    public static void createSlab(BlockStateModelGenerator blockStateModelGenerator, Block base, Block slab){
        String name = Registry.BLOCK.getId(slab).getPath();
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createSlabBlockState(slab,
                new Identifier(FalseFutures.MOD_ID, name),
                new Identifier(FalseFutures.MOD_ID, name + "_top"),
                new Identifier(FalseFutures.MOD_ID, Registry.BLOCK.getId(base).getPath())));
    }
    public static void createSlab(BlockStateModelGenerator blockStateModelGenerator, Block slab){
        String name = Registry.BLOCK.getId(slab).getPath();
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createSlabBlockState(slab,
                new Identifier(FalseFutures.MOD_ID, name),
                new Identifier(FalseFutures.MOD_ID, name + "_top"),
                new Identifier(FalseFutures.MOD_ID, slab + "_double")));
    }
    public static void createWall(BlockStateModelGenerator blockStateModelGenerator, Block wall){
        String name = Registry.BLOCK.getId(wall).getPath();
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createWallBlockState(wall,
                new Identifier(FalseFutures.MOD_ID, name + "_post"),
                new Identifier(FalseFutures.MOD_ID, name + "_side"),
                new Identifier(FalseFutures.MOD_ID, name + "_side_tall")));
    }
    public static void makeButton(BlockStateModelGenerator blockStateModelGenerator, Block button){
        String name = Registry.BLOCK.getId(button).getPath();
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createButtonBlockState(button,
                new Identifier(FalseFutures.MOD_ID, name),
                new Identifier(FalseFutures.MOD_ID, name + "_pressed")));
    }

    public static void makePressurePlate(BlockStateModelGenerator blockStateModelGenerator, Block plate){
        String name = Registry.BLOCK.getId(plate).getPath();
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createPressurePlateBlockState(plate, new Identifier(FalseFutures.MOD_ID, name), new Identifier(FalseFutures.MOD_ID, name + "_down")));
    }

    public static void registerSideMirrorable(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier identifier = TexturedModel.CUBE_ALL.upload(block, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.CUBE_NORTH_WEST_MIRRORED_ALL.upload(block, TextureMap.all(block), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createBlockStateWithTwoModelAndRandomInversion(block, identifier, identifier2));
    }
}
