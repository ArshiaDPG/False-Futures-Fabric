package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.GelastoneBlock;
import net.digitalpear.falsefutures.common.blocks.GelatinLayerBlock;
import net.digitalpear.falsefutures.common.blocks.JellyrootBlock;
import net.digitalpear.falsefutures.common.blocks.TallJellyrootBlock;
import net.digitalpear.falsefutures.common.blocks.jelly.*;
import net.digitalpear.falsefutures.init.sets.StoneSets;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class FFBlocks {

    public static final Map<Block, Item> JELLY = new HashMap<>();

    public static Block createBlockWithItem(String blockID, Block block, ItemGroup group){
        createBlockItem(blockID, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }
    public static BlockItem createBlockItem(String blockID, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(FalseFutures.MOD_ID, blockID), new BlockItem(block, new Item.Settings().group(group)));
    }
    public static Block createJellyBlockWithItem(String blockID, Block block, Item ingredient){
        createBlockItem(blockID, block, ItemGroup.FOOD);
        Block jelly = Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
        JELLY.put(jelly,ingredient);
        return jelly;
    }


    public static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }

    public static AbstractBlock.Settings jellySettings(MapColor color){
        return AbstractBlock.Settings.of(Material.CAKE, color).strength(0.1f, 0.2f).sounds(BlockSoundGroup.SLIME).nonOpaque();
    }
    public static AbstractBlock.Settings jellySettings(MapColor color, int luminance){
        return jellySettings(color).luminance((state) -> {
            if(state.get(JellyBlock.HALVED)) return (int)(luminance / 1.5);
            return luminance;
        });
    }

    public static final Block PLAIN_JELLY = createJellyBlockWithItem("plain_jelly", new JellyBlock(jellySettings(MapColor.PALE_PURPLE)),Items.SUGAR);
    public static final Block MILKY_JELLY = createJellyBlockWithItem("milky_jelly", new MilkyJellyBlock(jellySettings(MapColor.WHITE)), Items.MILK_BUCKET);
    public static final Block BRIGHT_JELLY = createJellyBlockWithItem( "bright_jelly", new BrightJellyBlock(jellySettings(MapColor.ORANGE,14)),Items.GLOW_BERRIES);
    public static final Block SYMPHONIC_JELLY = createJellyBlockWithItem("symphonic_jelly", new SymphonicJellyBlock(jellySettings(MapColor.PURPLE)), Items.CHORUS_FRUIT);
    public static final Block LUMINESCENT_JELLY = createJellyBlockWithItem( "luminescent_jelly", new LuminescentJellyBlock(jellySettings(MapColor.LIGHT_BLUE,12)), Items.GLOW_INK_SAC);
    public static final Block FLORAL_JELLY = createJellyBlockWithItem("floral_jelly", new FloralJellyBlock(jellySettings(MapColor.YELLOW)), Items.HONEY_BOTTLE);
    public static final Block BOUNCY_JELLY = createJellyBlockWithItem("bouncy_jelly", new BouncyJellyBlock(jellySettings(MapColor.LIME)), Items.SLIME_BALL);
    public static final Block SWEET_JELLY = createJellyBlockWithItem("sweet_jelly", new SweetJellyBlock(jellySettings(MapColor.PINK)), Items.SWEET_BERRIES);
    public static final Block MUDDY_JELLY = createJellyBlockWithItem("muddy_jelly", new JellyBlock(jellySettings(MapColor.GRAY)), Items.MUD);
    public static final Block BLAST_JELLY = createJellyBlockWithItem("blast_jelly", new JellyBlock(jellySettings(MapColor.LIGHT_GRAY)), Items.GUNPOWDER);
    public static final Block WARP_JELLY = createJellyBlockWithItem("warp_jelly", new JellyBlock(jellySettings(MapColor.CYAN)), Items.ENDER_PEARL);
    public static final Block FOAMY_JELLY = createJellyBlockWithItem("foamy_jelly", new JellyBlock(jellySettings(MapColor.PURPLE)), Items.DRAGON_BREATH);
    public static final Block ENCHANTING_JELLY = createJellyBlockWithItem("enchanting_jelly", new JellyBlock(jellySettings(MapColor.BLUE)), Items.LAPIS_LAZULI);
    public static final Block COCOA_JELLY = createJellyBlockWithItem("cocoa_jelly", new SweetJellyBlock(jellySettings(MapColor.BROWN)), Items.COCOA_BEANS);
    public static final Block PRICKLY_JELLY = createJellyBlockWithItem("prickly_jelly", new BouncyJellyBlock(jellySettings(MapColor.GREEN)), Items.CACTUS);
    public static final Block FRUITY_JELLY = createJellyBlockWithItem("fruity_jelly", new SweetJellyBlock(jellySettings(MapColor.BRIGHT_RED)), Items.MELON_SLICE);
    public static final Block INKY_JELLY = createJellyBlockWithItem( "inky_jelly", new InkyJellyBlock(jellySettings(MapColor.BLACK)), Items.INK_SAC);

    public static final Block GIPPLEPAD = createBlockWithoutItem("gipplepad", new LilyPadBlock(AbstractBlock.Settings.of(Material.PLANT,
            MapColor.PALE_PURPLE).breakInstantly().sounds(BlockSoundGroup.LILY_PAD).nonOpaque()));

    public static final Block GELATIN_LAYER = createBlockWithItem("gelatin_layer",
            new GelatinLayerBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.PALE_PURPLE)), ItemGroup.DECORATIONS);

    public static final Block GELASTONE = createBlockWithItem("gelastone",
            new GelastoneBlock(AbstractBlock.Settings.copy(Blocks.STONE)
                    .mapColor(MapColor.CYAN)
                    .sounds(BlockSoundGroup.NETHER_GOLD_ORE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GELASTONE_STAIRS = StoneSets.stoneStairs(GELASTONE);
    public static final Block GELASTONE_SLAB = StoneSets.stoneSlab(GELASTONE);
    public static final Block GELASTONE_WALL = StoneSets.stoneWall(GELASTONE);
    public static final Block GELASTONE_PRESSURE_PLATE = StoneSets.stonePressurePlate(GELASTONE);
    public static final Block GELASTONE_BUTTON = StoneSets.stoneButton(GELASTONE);

    public static final Block GELASTONE_BRICKS = createBlockWithItem("gelastone_bricks",
            new Block(AbstractBlock.Settings.copy(GELASTONE)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GELASTONE_BRICK_STAIRS = StoneSets.stoneStairs(GELASTONE_BRICKS);
    public static final Block GELASTONE_BRICK_SLAB = StoneSets.stoneSlab(GELASTONE_BRICKS);
    public static final Block GELASTONE_BRICK_WALL = StoneSets.stoneWall(GELASTONE_BRICKS);
    public static final Block GELASTONE_BRICK_PRESSURE_PLATE = StoneSets.stonePressurePlate(GELASTONE_BRICKS);
    public static final Block GELASTONE_BRICK_BUTTON = StoneSets.stoneButton(GELASTONE_BRICKS);


    public static final Block DEEP_GELASTONE = createBlockWithItem("deep_gelastone",
            new GelastoneBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
            .sounds(BlockSoundGroup.NETHERRACK)
            .mapColor(MapColor.DARK_CRIMSON)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DEEP_GELASTONE_STAIRS = StoneSets.stoneStairs(DEEP_GELASTONE);
    public static final Block DEEP_GELASTONE_SLAB = StoneSets.stoneSlab(DEEP_GELASTONE);
    public static final Block DEEP_GELASTONE_WALL = StoneSets.stoneWall(DEEP_GELASTONE);
    public static final Block DEEP_GELASTONE_PRESSURE_PLATE = StoneSets.stonePressurePlate(DEEP_GELASTONE);
    public static final Block DEEP_GELASTONE_BUTTON = StoneSets.stoneButton(DEEP_GELASTONE);


    public static final Block DEEP_GELASTONE_BRICKS = createBlockWithItem("deep_gelastone_bricks",
            new GelastoneBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
                    .sounds(BlockSoundGroup.NETHERRACK)
                    .mapColor(MapColor.DARK_CRIMSON)), ItemGroup.BUILDING_BLOCKS);
    public static final Block DEEP_GELASTONE_BRICK_STAIRS = StoneSets.stoneStairs(DEEP_GELASTONE_BRICKS);
    public static final Block DEEP_GELASTONE_BRICK_SLAB = StoneSets.stoneSlab(DEEP_GELASTONE_BRICKS);
    public static final Block DEEP_GELASTONE_BRICK_WALL = StoneSets.stoneWall(DEEP_GELASTONE_BRICKS);
    public static final Block DEEP_GELASTONE_BRICK_PRESSURE_PLATE = StoneSets.stonePressurePlate(DEEP_GELASTONE_BRICKS);
    public static final Block DEEP_GELASTONE_BRICK_BUTTON = StoneSets.stoneButton(DEEP_GELASTONE_BRICKS);

    public static final Block JELLYROOT = createBlockWithItem("jellyroot", new JellyrootBlock(AbstractBlock.Settings.copy(Blocks.GRASS)
            .mapColor(MapColor.LIGHT_BLUE).sounds(BlockSoundGroup.WEEPING_VINES).luminance(state -> 4)), ItemGroup.DECORATIONS);
    public static final Block POTTED_JELLYROOT = makePottedPlant(JELLYROOT);
    public static final Block TALL_JELLYROOT = createBlockWithItem("tall_jellyroot", new TallJellyrootBlock(AbstractBlock.Settings.copy(Blocks.GRASS)
            .mapColor(MapColor.LIGHT_BLUE).sounds(BlockSoundGroup.WEEPING_VINES).luminance(state -> 8)), ItemGroup.DECORATIONS);

    public static final Block INFESTED_GELASTONE = StoneSets.infestedBlock(GELASTONE);
    public static final Block INFESTED_DEEP_GELASTONE = StoneSets.infestedBlock(DEEP_GELASTONE);
    public static final Block INFESTED_GELASTONE_BRICKS = StoneSets.infestedBlock(GELASTONE_BRICKS);
    public static final Block INFESTED_DEEP_GELASTONE_BRICKS = StoneSets.infestedBlock(DEEP_GELASTONE_BRICKS);


    public static Block makePottedPlant(Block base){
        return createBlockWithoutItem("potted_" + Registry.BLOCK.getId(base).getPath(), new FlowerPotBlock(base, AbstractBlock.Settings.copy(Blocks.POTTED_WARPED_FUNGUS).luminance(state -> base.getDefaultState().getLuminance()).mapColor(base.getDefaultMapColor())));
    }

    public static void init(){
    }
}
