package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.blocks.jelly.JellyBlock;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGItems;
import net.digitalpear.gipples_galore.init.data.sets.StoneSet;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
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
        registerGelatinLayers(blockStateModelGenerator);
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
            itemModelGenerator.registerSpawnEgg(item, integerIntegerPair.getLeft(), integerIntegerPair.getRight());
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
        Identifier identifier = Models.CUBE_ALL.upload(GipplesGalore.id("block/gelatin_height16"), TextureMap.all(GipplesGalore.id("block/gelatin")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(GGBlocks.GELATIN_LAYER).coordinate(BlockStateVariantMap.create(Properties.LAYERS).register((height) -> {
            BlockStateVariant blockStateVariant = BlockStateVariant.create();
            VariantSetting<Identifier> variantSettings = VariantSettings.MODEL;
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
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(GGBlocks.GIPPLEPAD, ModelIds.getBlockModelId(GGBlocks.GIPPLEPAD)));
    }

    public static void registerStoneSet(BlockStateModelGenerator blockStateModelGenerator, StoneSet stoneSet){
        blockStateModelGenerator.registerCubeAllModelTexturePool(stoneSet.getBase()).family(stoneSet.getBlockFamily());
    }
}
