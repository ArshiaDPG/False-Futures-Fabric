package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.blocks.jelly.JellyBlock;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;


public class GGModelProvider extends FabricModelProvider {


    public static final Model JELLY_HALF_SIDE = new Model(Optional.of(new Identifier(GipplesGalore.MOD_ID, "block/" + "jelly_half_side")), Optional.of("_half_side"), TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.INSIDE);
    public static final Model JELLY_HALF_UPPER = new Model(Optional.of(new Identifier(GipplesGalore.MOD_ID, "block/" + "jelly_half_upper")), Optional.of("_half_upper"), TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.INSIDE);
    public static final Model JELLY_HALF_LOWER = new Model(Optional.of(new Identifier(GipplesGalore.MOD_ID, "block/" + "jelly_half_lower")), Optional.of("_half_lower"), TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.INSIDE);


    public GGModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerGipplePad(blockStateModelGenerator);
        registerGelatinLayers(blockStateModelGenerator);
        registerAllJellies(blockStateModelGenerator);

        registerBlockSet(blockStateModelGenerator, GGBlocks.GELATITE, GGBlocks.GELATITE_STAIRS, GGBlocks.GELATITE_SLAB,
                GGBlocks.GELATITE_WALL, GGBlocks.GELATITE_PRESSURE_PLATE, GGBlocks.GELATITE_BUTTON);
        blockStateModelGenerator.registerSimpleCubeAll(GGBlocks.CHISELED_GELATITE_BRICKS);
        registerBrickBlockSet(blockStateModelGenerator, GGBlocks.GELATITE_BRICKS, GGBlocks.GELATITE_BRICK_STAIRS, GGBlocks.GELATITE_BRICK_SLAB,
                GGBlocks.GELATITE_BRICK_WALL);
        registerBlockSet(blockStateModelGenerator, GGBlocks.AMOEBALITH, GGBlocks.AMOEBALITH_STAIRS, GGBlocks.AMOEBALITH_SLAB,
                GGBlocks.AMOEBALITH_WALL, GGBlocks.AMOEBALITH_PRESSURE_PLATE, GGBlocks.AMOEBALITH_BUTTON);
        blockStateModelGenerator.registerSimpleCubeAll(GGBlocks.CHISELED_AMOEBALITH_BRICKS);
        registerBrickBlockSet(blockStateModelGenerator, GGBlocks.AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH_BRICK_STAIRS, GGBlocks.AMOEBALITH_BRICK_SLAB,
                GGBlocks.AMOEBALITH_BRICK_WALL);


        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(GGBlocks.HIBERNATING_GIPPLE, TextureMap.sideEnd(GGBlocks.HIBERNATING_GIPPLE));

        blockStateModelGenerator.registerParentedItemModel(GGItems.GIPPLE_SPAWN_EGG, new Identifier("item/template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(GGItems.ANEUPLOIDIAN_SPAWN_EGG, new Identifier("item/template_spawn_egg"));

        blockStateModelGenerator.registerSingleton(GGBlocks.GELATIN_BLOCK, (new TextureMap()).put(TextureKey.ALL, TextureMap.getId(GGBlocks.HIBERNATING_GIPPLE).withSuffixedPath("_top")), Models.CUBE_ALL);

        blockStateModelGenerator.registerFlowerPotPlant(GGBlocks.GELATINOUS_GROWTH, GGBlocks.POTTED_GELATINOUS_GROWTH, BlockStateModelGenerator.TintType.NOT_TINTED);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(GGItems.GELATIN, Models.GENERATED);
        itemModelGenerator.register(GGItems.GAPPLE, Models.GENERATED);
        itemModelGenerator.register(GGItems.GIPPLE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(GGItems.MUSIC_DISC_GIPPLECORE, Models.GENERATED);
        itemModelGenerator.register(GGItems.GIPPLE_BANNER_PATTERN, Models.GENERATED);
    }

    private void registerAllJellies(BlockStateModelGenerator blockStateModelGenerator) {
        for (Block jelly : GGBlocks.JELLY.keySet()) {
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
        Identifier identifier = Models.CUBE_ALL.upload(new Identifier(GipplesGalore.MOD_ID, "block/gelatin_height16"), TextureMap.all(new Identifier(GipplesGalore.MOD_ID, "block/gelatin")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(GGBlocks.GELATIN_LAYER).coordinate(BlockStateVariantMap.create(Properties.LAYERS).register((height) -> {
            BlockStateVariant blockStateVariant = BlockStateVariant.create();
            VariantSetting variantSettings = VariantSettings.MODEL;
            Identifier stateName;
            if (height < 8) {
                Block block = GGBlocks.GELATIN_LAYER;
                int currentHeight = height;
                stateName = ModelIds.getBlockSubModelId(block, "_height" + currentHeight * 2);
            } else {
                stateName = identifier;
            }
            return blockStateVariant.put(variantSettings, stateName);
        })));
        blockStateModelGenerator.registerParentedItemModel(GGBlocks.GELATIN_LAYER, ModelIds.getBlockSubModelId(GGBlocks.GELATIN_LAYER, "_height2"));
    }

    private void registerGipplePad(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerItemModel(GGBlocks.GIPPLEPAD);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(GGBlocks.GIPPLEPAD,
                ModelIds.getBlockModelId(GGBlocks.GIPPLEPAD)));
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
                SLAB, SLAB_TOP, new Identifier(GipplesGalore.MOD_ID, "block/" + Registries.BLOCK.getId(textureBase).getPath())));
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
