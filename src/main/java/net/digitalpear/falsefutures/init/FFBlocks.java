package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.GelatinLayerBlock;
import net.digitalpear.falsefutures.common.blocks.jelly.*;
import net.digitalpear.falsefutures.init.sets.StoneSets;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFBlocks {

    public static Block createBlockWithItem(String blockID, Block block, ItemGroup group){
        createBlockItem(blockID, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }
    public static BlockItem createBlockItem(String blockID, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(FalseFutures.MOD_ID, blockID), new BlockItem(block, new Item.Settings().group(group)));
    }
    public static Block createJellyBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block, ItemGroup.FOOD);
        return Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }


    public static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }

    public static AbstractBlock.Settings jellySettings(MapColor color){
        return AbstractBlock.Settings.of(Material.CAKE, color).strength(0.1f, 0.2f).sounds(BlockSoundGroup.SLIME);
    }

    public static final Block PLAIN_JELLY = createJellyBlockWithItem( "plain_jelly", new JellyBlock(jellySettings(MapColor.LIGHT_BLUE)));
    public static final Block WEIRD_JELLY = createJellyBlockWithItem("weird_jelly", new WeirdJellyBlock(jellySettings(MapColor.LIME)));
    public static final Block MILKY_JELLY = createJellyBlockWithItem("milky_jelly", new MilkyJellyBlock(jellySettings(MapColor.WHITE)));
    public static final Block FLORAL_JELLY = createJellyBlockWithItem("floral_jelly", new FloralJellyBlock(jellySettings(MapColor.YELLOW)));
    public static final Block SYMPHONIC_JELLY = createJellyBlockWithItem("symphonic_jelly", new SymphonicJellyBlock(jellySettings(MapColor.PURPLE)));
    public static final Block SWEET_JELLY = createJellyBlockWithItem("sweet_jelly", new SweetJellyBlock(jellySettings(MapColor.PINK)));
    public static final Block FRUITY_JELLY = createJellyBlockWithItem("fruity_jelly", new SweetJellyBlock(jellySettings(MapColor.BRIGHT_RED)));

    public static final Block GIPPLEPAD = createBlockWithoutItem("gipplepad", new LilyPadBlock(AbstractBlock.Settings.of(Material.PLANT,
            MapColor.PALE_PURPLE).breakInstantly().sounds(BlockSoundGroup.LILY_PAD).nonOpaque()));

    public static final Block GELATIN_LAYERS = createBlockWithItem("gelatin_layer",
            new GelatinLayerBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK)
                    .mapColor(MapColor.LIGHT_BLUE)), ItemGroup.DECORATIONS);

    public static final Block GELASTONE = createBlockWithItem("gelastone",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE)
                    .mapColor(MapColor.CYAN)
                    .sounds(BlockSoundGroup.NETHER_GOLD_ORE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GELASTONE_STAIRS = StoneSets.stoneStairs(GELASTONE);
    public static final Block GELASTONE_SLAB = StoneSets.stoneSlab(GELASTONE);
    public static final Block GELASTONE_WALL = StoneSets.stoneWall(GELASTONE);
    public static final Block GELASTONE_PRESSURE_PLATE = StoneSets.stonePressurePlate(GELASTONE);
    public static final Block GELASTONE_BUTTON = StoneSets.stoneButton(GELASTONE);


    public static final Block DEEP_GELASTONE = createBlockWithItem("deep_gelastone",
            new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
            .sounds(BlockSoundGroup.NETHERRACK)
            .mapColor(MapColor.DARK_CRIMSON)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DEEP_GELASTONE_STAIRS = StoneSets.stoneStairs(DEEP_GELASTONE);
    public static final Block DEEP_GELASTONE_SLAB = StoneSets.stoneSlab(DEEP_GELASTONE);
    public static final Block DEEP_GELASTONE_WALL = StoneSets.stoneWall(DEEP_GELASTONE);
    public static final Block DEEP_GELASTONE_PRESSURE_PLATE = StoneSets.stonePressurePlate(DEEP_GELASTONE);
    public static final Block DEEP_GELASTONE_BUTTON = StoneSets.stoneButton(DEEP_GELASTONE);


    public static void init(){
    }
}
