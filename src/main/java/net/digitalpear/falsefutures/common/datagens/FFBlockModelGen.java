package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


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
        registerBrickBlockSet(blockStateModelGenerator, FFBlocks.DEEP_GELASTONE_BRICKS, FFBlocks.DEEP_GELASTONE_BRICK_STAIRS, FFBlocks.DEEP_GELASTONE_BRICK_SLAB,
                FFBlocks.DEEP_GELASTONE_BRICK_WALL, FFBlocks.DEEP_GELASTONE_BRICK_PRESSURE_PLATE, FFBlocks.DEEP_GELASTONE_BRICK_BUTTON);


        blockStateModelGenerator.registerFlowerPotPlant(FFBlocks.JELLYROOT, FFBlocks.POTTED_JELLYROOT, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(FFBlocks.TALL_JELLYROOT, BlockStateModelGenerator.TintType.NOT_TINTED);
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
        blockStateModelGenerator.registerParentedItemModel(FFBlocks.GELATIN_LAYER, ModelIds.getBlockSubModelId(FFBlocks.GELATIN_LAYER, "_height2"));
    }



    private void registerGipplePad(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerItemModel(FFBlocks.GIPPLEPAD);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(FFBlocks.GIPPLEPAD,
                ModelIds.getBlockModelId(FFBlocks.GIPPLEPAD)));
    }

    public static void registerBrickBlockSet(BlockStateModelGenerator blockStateModelGenerator, Block base, Block stairs, Block slab, Block wall, Block pressurePlate, Block button){
        blockStateModelGenerator.registerSimpleCubeAll(base);
        createStairs(blockStateModelGenerator, base, stairs);
        createSlab(blockStateModelGenerator, base, slab);
        createWall(blockStateModelGenerator, base, wall);
        makePressurePlate(blockStateModelGenerator, base, pressurePlate);
        makeButton(blockStateModelGenerator, base, button);
    }

    public static void registerBlockSetMirrorable(BlockStateModelGenerator blockStateModelGenerator, Block base, Block stairs, Block slab, Block wall, Block pressurePlate, Block button){
        blockStateModelGenerator.registerMirrorable(base);
        createStairs(blockStateModelGenerator, base, stairs);
        createSlab(blockStateModelGenerator, base, slab);
        createWall(blockStateModelGenerator, base, wall);
        makePressurePlate(blockStateModelGenerator, base, pressurePlate);
        makeButton(blockStateModelGenerator, base, button);
    }

    public static void createStairs(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block stairs){
        Identifier STAIRS = Models.STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier INNER_STAIRS = Models.INNER_STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier OUTER_STAIRS = Models.OUTER_STAIRS.upload(stairs, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createStairsBlockState(stairs,
                INNER_STAIRS, STAIRS, OUTER_STAIRS));
    }

    public static void createSlab(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block slab){
        Identifier SLAB = Models.SLAB.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier SLAB_TOP = Models.SLAB_TOP.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createSlabBlockState(slab,
                SLAB, SLAB_TOP, Registry.BLOCK.getId(textureBase)));

    }
    public static void createWall(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block wall){
        Identifier WALL_INVENTORY = Models.WALL_INVENTORY.upload(wall, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier TEMPLATE_WALL_POST = Models.TEMPLATE_WALL_POST.upload(wall, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier TEMPLATE_WALL_SIDE = Models.TEMPLATE_WALL_SIDE.upload(wall, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier TEMPLATE_WALL_SIDE_TALL = Models.TEMPLATE_WALL_SIDE_TALL.upload(wall, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createWallBlockState(wall,
                TEMPLATE_WALL_POST, TEMPLATE_WALL_SIDE, TEMPLATE_WALL_SIDE_TALL));
        blockStateModelGenerator.registerParentedItemModel(wall.asItem(), WALL_INVENTORY);
    }
    public static void makeButton(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block button){
        Identifier BUTTON = Models.BUTTON.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier BUTTON_PRESSED = Models.BUTTON_PRESSED.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier BUTTON_INVENTORY = Models.BUTTON_INVENTORY.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createButtonBlockState(button,
                BUTTON, BUTTON_PRESSED));
        blockStateModelGenerator.registerParentedItemModel(button.asItem(), BUTTON_INVENTORY);
    }

    public static void makePressurePlate(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block plate){
        Identifier PRESSURE_PLATE_DOWN = Models.PRESSURE_PLATE_DOWN.upload(plate, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier PRESSURE_PLATE_UP = Models.PRESSURE_PLATE_UP.upload(plate, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(blockStateModelGenerator.createPressurePlateBlockState(plate,
                PRESSURE_PLATE_UP, PRESSURE_PLATE_DOWN));
    }
}
