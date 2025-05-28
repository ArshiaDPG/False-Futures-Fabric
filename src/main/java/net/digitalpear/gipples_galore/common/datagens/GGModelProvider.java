package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.blocks.jelly.JellyBlock;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGItems;
import net.digitalpear.gipples_galore.init.data.sets.StoneSet;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.client.render.model.json.ModelVariantOperator;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.AxisRotation;
import net.minecraft.util.math.Direction;

import java.util.Optional;


public class GGModelProvider extends FabricModelProvider {

    public static final Model JELLY_HALF_SIDE = new Model(Optional.of(GipplesGalore.id("block/" + "jelly_half_side")), Optional.of("_half_side"), TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.INSIDE);
    public static final Model JELLY_HALF_UPPER = new Model(Optional.of(GipplesGalore.id("block/" + "jelly_half_upper")), Optional.of("_half_upper"), TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.INSIDE);
    public static final Model JELLY_HALF_LOWER = new Model(Optional.of(GipplesGalore.id("block/" + "jelly_half_lower")), Optional.of("_half_lower"), TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE, TextureKey.INSIDE);

    public GGModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerGipplePad(blockStateModelGenerator);
        registerGelatinLayers(blockStateModelGenerator, GGBlocks.GELATIN_LAYER);
        registerAllJellies(blockStateModelGenerator);

        registerStoneSet(blockStateModelGenerator, GGBlocks.GELATITE_SET);
        registerStoneSet(blockStateModelGenerator, GGBlocks.GELATITE_BRICK_SET);
        registerStoneSet(blockStateModelGenerator, GGBlocks.AMOEBALITH_SET);
        registerStoneSet(blockStateModelGenerator, GGBlocks.AMOEBALITH_BRICK_SET);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(GGBlocks.HIBERNATING_GIPPLE, TextureMap.sideEnd(GGBlocks.HIBERNATING_GIPPLE));

        blockStateModelGenerator.registerSingleton(GGBlocks.GELATIN_BLOCK, TexturedModel.makeFactory(block -> new TextureMap().put(TextureKey.ALL, TextureMap.getId(GGBlocks.HIBERNATING_GIPPLE).withSuffixedPath("_top")), Models.CUBE_ALL));

        blockStateModelGenerator.registerFlowerPotPlant(GGBlocks.GELATINOUS_GROWTH, GGBlocks.POTTED_GELATINOUS_GROWTH, BlockStateModelGenerator.CrossType.NOT_TINTED);
        blockStateModelGenerator.registerItemModel(GGBlocks.GELATINOUS_GROWTH);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        GGItems.EGG_COLORS.forEach((item, integerIntegerPair) -> {
            itemModelGenerator.register(item, Models.GENERATED);
        });

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

    private AxisRotation rotationOf(Direction d) {
        return switch (d) {
            case EAST -> AxisRotation.R90;
            case SOUTH -> AxisRotation.R180;
            case WEST -> AxisRotation.R270;
            default -> AxisRotation.R0;
        };
    }

    private void registerJelly(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        TextureMap horizontalMap = new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside")).put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));
        TextureMap verticalMap = new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.INSIDE, TextureMap.getSubId(block, "_inside_vertical")).put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));
        WeightedVariant half_side = BlockStateModelGenerator.createWeightedVariant(JELLY_HALF_SIDE.upload(block,horizontalMap, blockStateModelGenerator.modelCollector));
        WeightedVariant half_upper = BlockStateModelGenerator.createWeightedVariant(JELLY_HALF_UPPER.upload(block,verticalMap, blockStateModelGenerator.modelCollector));
        WeightedVariant half_lower = BlockStateModelGenerator.createWeightedVariant(JELLY_HALF_LOWER.upload(block,verticalMap, blockStateModelGenerator.modelCollector));
        WeightedVariant full = BlockStateModelGenerator.createWeightedVariant(Models.CUBE_BOTTOM_TOP.upload(block,horizontalMap,blockStateModelGenerator.modelCollector));
        BlockStateVariantMap.DoubleProperty<WeightedVariant, Direction, Boolean> stateMap = BlockStateVariantMap.models(Properties.FACING, JellyBlock.HALVED);
        for(Direction direction : Direction.values()) {
            stateMap.register(direction, Boolean.FALSE, full);
            if (direction == Direction.DOWN) {
                stateMap.register(direction, Boolean.TRUE, half_upper);
            }
            else if (direction == Direction.UP) {
                stateMap.register(direction, Boolean.TRUE, half_lower);
            }
            else {
                stateMap.register(direction, Boolean.TRUE, half_side.apply(ModelVariantOperator.ROTATION_Y.withValue(rotationOf(direction))));
            }
        }

        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockModelDefinitionCreator.of(block).with(stateMap)
        );
    }


    private void registerGelatinLayers(BlockStateModelGenerator blockStateModelGenerator, Block layerBlock) {
        WeightedVariant weightedVariant = BlockStateModelGenerator.createWeightedVariant(Models.CUBE_ALL.upload(ModelIds.getBlockSubModelId(layerBlock, "_height16"), TextureMap.all(GipplesGalore.id("block/gelatin")), blockStateModelGenerator.modelCollector));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(layerBlock).with(BlockStateVariantMap.models(Properties.LAYERS).generate((integer) -> {
            WeightedVariant var2;
            if (integer < 8) {
                var2 = BlockStateModelGenerator.createWeightedVariant(ModelIds.getBlockSubModelId(layerBlock, "_height" + integer * 2));
            } else {
                var2 = weightedVariant;
            }
            return var2;
        })));
        blockStateModelGenerator.registerParentedItemModel(layerBlock, ModelIds.getBlockSubModelId(layerBlock, "_height2"));
    }

    private void registerGipplePad(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerItemModel(GGBlocks.GIPPLEPAD);
        ModelVariant modelVariant = BlockStateModelGenerator.createModelVariant(ModelIds.getBlockModelId(Blocks.LILY_PAD));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(GGBlocks.GIPPLEPAD, BlockStateModelGenerator.modelWithYRotation(modelVariant)));
    }

    public static void registerStoneSet(BlockStateModelGenerator blockStateModelGenerator, StoneSet stoneSet){
        blockStateModelGenerator.registerCubeAllModelTexturePool(stoneSet.getBase()).family(stoneSet.getBlockFamily());
    }
}
