package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.GippleInfestedBlock;
import net.digitalpear.falsefutures.common.blocks.jelly.JellyBlock;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

import java.util.Optional;


public class FFModelGen extends FabricModelProvider {
    public FFModelGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    public static final Model JELLY_HALF_SIDE = new Model(Optional.of(new Identifier(FalseFutures.MOD_ID, "block/" + "jelly_half_side")), Optional.of("_half_side"), TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.INSIDE);
    public static final Model JELLY_HALF_UPPER = new Model(Optional.of(new Identifier(FalseFutures.MOD_ID, "block/" + "jelly_half_upper")), Optional.of("_half_upper"), TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.INSIDE);
    public static final Model JELLY_HALF_LOWER = new Model(Optional.of(new Identifier(FalseFutures.MOD_ID, "block/" + "jelly_half_lower")), Optional.of("_half_lower"), TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.INSIDE);

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerGipplePad(blockStateModelGenerator);
        registerGelatinLayers(blockStateModelGenerator);
        registerAllJellies(blockStateModelGenerator);

        registerBlockSet(blockStateModelGenerator, FFBlocks.GELATITE, FFBlocks.GELATITE_STAIRS, FFBlocks.GELATITE_SLAB,
                FFBlocks.GELATITE_WALL, FFBlocks.GELATITE_PRESSURE_PLATE, FFBlocks.GELATITE_BUTTON);
        blockStateModelGenerator.registerSimpleCubeAll(FFBlocks.CHISELED_GELATITE_BRICKS);
        registerBrickBlockSet(blockStateModelGenerator, FFBlocks.GELATITE_BRICKS, FFBlocks.GELATITE_BRICK_STAIRS, FFBlocks.GELATITE_BRICK_SLAB,
                FFBlocks.GELATITE_BRICK_WALL);
        registerBlockSet(blockStateModelGenerator, FFBlocks.BRINESHALE, FFBlocks.BRINESHALE_STAIRS, FFBlocks.BRINESHALE_SLAB,
                FFBlocks.BRINESHALE_WALL, FFBlocks.BRINESHALE_PRESSURE_PLATE, FFBlocks.BRINESHALE_BUTTON);
        blockStateModelGenerator.registerSimpleCubeAll(FFBlocks.CHISELED_BRINESHALE_BRICKS);
        registerBrickBlockSet(blockStateModelGenerator, FFBlocks.BRINESHALE_BRICKS, FFBlocks.BRINESHALE_BRICK_STAIRS, FFBlocks.BRINESHALE_BRICK_SLAB,
                FFBlocks.BRINESHALE_BRICK_WALL);


        blockStateModelGenerator.registerFlowerPotPlant(FFBlocks.JELLYROOT, FFBlocks.POTTED_JELLYROOT, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(FFBlocks.TALL_JELLYROOT, BlockStateModelGenerator.TintType.NOT_TINTED);

        GippleInfestedBlock.REGULAR_TO_INFESTED_BLOCK.forEach(blockStateModelGenerator::registerParented);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(FFItems.GELATIN, Models.GENERATED);
        itemModelGenerator.register(FFItems.GIPPLE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(FFItems.MUSIC_DISC_GIPPLECORE, Models.GENERATED);
        itemModelGenerator.register(FFItems.GIPPLE_BANNER_PATTERN, Models.GENERATED);
    }

    private void registerAllJellies(BlockStateModelGenerator blockStateModelGenerator) {
        for (Block jelly : FFBlocks.JELLY.keySet()) {
            registerJelly(blockStateModelGenerator, jelly);
        }
    }

    private VariantSettings.Rotation rotationOf(Direction d) {
        return switch (d) {
            case EAST -> VariantSettings.Rotation.R90;
            case SOUTH -> VariantSettings.Rotation.R180;
            case WEST -> VariantSettings.Rotation.R270;
            default -> VariantSettings.Rotation.R0;
        };
    }

    private void registerJelly(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        TextureMap horizontalMap = new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside")).put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));
        TextureMap verticalMap = new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside_vertical")).put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));
        Identifier half_side = JELLY_HALF_SIDE.upload(block,horizontalMap, blockStateModelGenerator.modelCollector);
        Identifier half_upper = JELLY_HALF_UPPER.upload(block,verticalMap, blockStateModelGenerator.modelCollector);
        Identifier half_lower = JELLY_HALF_LOWER.upload(block,verticalMap, blockStateModelGenerator.modelCollector);
        Identifier full = Models.CUBE_BOTTOM_TOP.upload(block,horizontalMap,blockStateModelGenerator.modelCollector);
        var stateMap = BlockStateVariantMap.create(Properties.FACING,JellyBlock.HALVED);
        for(Direction d : Direction.values()) {

            stateMap.register(d, Boolean.FALSE, BlockStateVariant.create().put(VariantSettings.MODEL, full));

            if (d == Direction.DOWN) {
                stateMap.register(d, Boolean.TRUE, BlockStateVariant.create().put(VariantSettings.MODEL, half_upper));
            }
            else if (d == Direction.UP) {
                stateMap.register(d, Boolean.TRUE, BlockStateVariant.create().put(VariantSettings.MODEL, half_lower));
            }
            else {
                stateMap.register(d, Boolean.TRUE, BlockStateVariant.create().put(VariantSettings.MODEL, half_side).put(VariantSettings.Y, rotationOf(d)));
            }
        }
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, full)).coordinate(stateMap));
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

    public static void registerBrickBlockSet(BlockStateModelGenerator blockStateModelGenerator, Block base, Block stairs, Block slab, Block wall){
        blockStateModelGenerator.registerSimpleCubeAll(base);
        createStairs(blockStateModelGenerator, base, stairs);
        createSlab(blockStateModelGenerator, base, slab);
        createWall(blockStateModelGenerator, base, wall);
    }

    public static void registerBlockSet(BlockStateModelGenerator blockStateModelGenerator, Block base, Block stairs, Block slab, Block wall, Block pressurePlate, Block button){
        blockStateModelGenerator.registerSimpleCubeAll(base);
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
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs,
                INNER_STAIRS, STAIRS, OUTER_STAIRS));
    }

    public static void createSlab(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block slab){
        Identifier SLAB = Models.SLAB.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier SLAB_TOP = Models.SLAB_TOP.upload(slab, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab,
                SLAB, SLAB_TOP, new Identifier(FalseFutures.MOD_ID, "block/" + Registry.BLOCK.getId(textureBase).getPath())));
    }

    public static void createWall(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block wall){
        Identifier WALL_INVENTORY = Models.WALL_INVENTORY.upload(wall, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier TEMPLATE_WALL_POST = Models.TEMPLATE_WALL_POST.upload(wall, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier TEMPLATE_WALL_SIDE = Models.TEMPLATE_WALL_SIDE.upload(wall, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier TEMPLATE_WALL_SIDE_TALL = Models.TEMPLATE_WALL_SIDE_TALL.upload(wall, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wall,
                TEMPLATE_WALL_POST, TEMPLATE_WALL_SIDE, TEMPLATE_WALL_SIDE_TALL));
        blockStateModelGenerator.registerParentedItemModel(wall.asItem(), WALL_INVENTORY);
    }
    public static void makeButton(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block button){
        Identifier BUTTON = Models.BUTTON.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier BUTTON_PRESSED = Models.BUTTON_PRESSED.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier BUTTON_INVENTORY = Models.BUTTON_INVENTORY.upload(button, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(button,
                BUTTON, BUTTON_PRESSED));
        blockStateModelGenerator.registerParentedItemModel(button.asItem(), BUTTON_INVENTORY);
    }

    public static void makePressurePlate(BlockStateModelGenerator blockStateModelGenerator, Block textureBase, Block plate){
        Identifier PRESSURE_PLATE_DOWN = Models.PRESSURE_PLATE_DOWN.upload(plate, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        Identifier PRESSURE_PLATE_UP = Models.PRESSURE_PLATE_UP.upload(plate, TextureMap.all(textureBase), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(plate,
                PRESSURE_PLATE_UP, PRESSURE_PLATE_DOWN));
    }


}
