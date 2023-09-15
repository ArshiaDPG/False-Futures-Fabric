package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.*;
import net.digitalpear.falsefutures.common.blocks.jelly.*;
import net.digitalpear.falsefutures.init.data.sets.StoneSets;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
public class FFBlocks {

    public static final Map<Block, Item> JELLY = new HashMap<>();

    public static Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }
    public static BlockItem createBlockItem(String blockID, Block block){
        return Registry.register(Registries.ITEM, new Identifier(FalseFutures.MOD_ID, blockID), new BlockItem(block, new Item.Settings()));
    }
    public static Block createJellyBlockWithItem(String blockID, Block block, Item ingredient){
        createBlockItem(blockID, block);
        Block jelly = Registry.register(Registries.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
        JELLY.put(jelly,ingredient);
        return jelly;
    }


    public static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }

    public static AbstractBlock.Settings jellySettings(MapColor color){
        return AbstractBlock.Settings.create().mapColor(color).strength(0.1f, 0.2f).sounds(BlockSoundGroup.SLIME).pistonBehavior(PistonBehavior.DESTROY).nonOpaque();
    }
    public static AbstractBlock.Settings jellySettings(MapColor color, int luminance){
        return jellySettings(color).luminance((state) -> {
            if(state.get(JellyBlock.HALVED)) return (int)(luminance / 1.5);
            return luminance;
        });
    }

    public static Block makePottedPlant(Block base){
        return createBlockWithoutItem("potted_" + Registries.BLOCK.getId(base).getPath(), new FlowerPotBlock(base, AbstractBlock.Settings.copy(Blocks.POTTED_WARPED_FUNGUS).luminance(state -> base.getDefaultState().getLuminance()).mapColor(base.getDefaultMapColor())));
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


    public static final Block GIPPLEPAD = createBlockWithoutItem("gipplepad", new LilyPadBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_PURPLE).breakInstantly().sounds(BlockSoundGroup.LILY_PAD).nonOpaque()));

    public static final Block GELATIN_LAYER = createBlockWithItem("gelatin_layer",
            new GelatinLayerBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.PALE_PURPLE)));

    public static final Block GELATITE = createBlockWithItem("gelatite",
            new GelatiteBlock(AbstractBlock.Settings.copy(Blocks.STONE)
                    .mapColor(MapColor.CYAN)
                    .sounds(BlockSoundGroup.NETHER_GOLD_ORE)));

    public static final Block GELATITE_STAIRS = StoneSets.stoneStairs(GELATITE);
    public static final Block GELATITE_SLAB = StoneSets.stoneSlab(GELATITE);
    public static final Block GELATITE_WALL = StoneSets.stoneWall(GELATITE);
    public static final Block GELATITE_PRESSURE_PLATE = StoneSets.stonePressurePlate(GELATITE);
    public static final Block GELATITE_BUTTON = StoneSets.stoneButton(GELATITE);

    public static final Block GELATITE_BRICKS = createBlockWithItem("gelatite_bricks",
            new Block(AbstractBlock.Settings.copy(GELATITE)));
    public static final Block GELATITE_BRICK_STAIRS = StoneSets.stoneStairs(GELATITE_BRICKS);
    public static final Block GELATITE_BRICK_SLAB = StoneSets.stoneSlab(GELATITE_BRICKS);
    public static final Block GELATITE_BRICK_WALL = StoneSets.stoneWall(GELATITE_BRICKS);

    public static final Block CHISELED_GELATITE_BRICKS = createBlockWithItem("chiseled_gelatite_bricks",
            new Block(AbstractBlock.Settings.copy(GELATITE)));


    public static final Block BRINESHALE = createBlockWithItem("brineshale",
            new GelatiteBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
            .sounds(BlockSoundGroup.NETHERRACK)
            .mapColor(MapColor.DARK_CRIMSON)));

    public static final Block BRINESHALE_STAIRS = StoneSets.stoneStairs(BRINESHALE);
    public static final Block BRINESHALE_SLAB = StoneSets.stoneSlab(BRINESHALE);
    public static final Block BRINESHALE_WALL = StoneSets.stoneWall(BRINESHALE);
    public static final Block BRINESHALE_PRESSURE_PLATE = StoneSets.stonePressurePlate(BRINESHALE);
    public static final Block BRINESHALE_BUTTON = StoneSets.stoneButton(BRINESHALE);

    public static final Block CHISELED_BRINESHALE_BRICKS = createBlockWithItem("chiseled_brineshale_bricks",
            new Block(AbstractBlock.Settings.copy(BRINESHALE)));

    public static final Block BRINESHALE_BRICKS = createBlockWithItem("brineshale_bricks",
            new GelatiteBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
                    .sounds(BlockSoundGroup.NETHERRACK)
                    .mapColor(MapColor.DARK_CRIMSON)));
    public static final Block BRINESHALE_BRICK_STAIRS = StoneSets.stoneStairs(BRINESHALE_BRICKS);
    public static final Block BRINESHALE_BRICK_SLAB = StoneSets.stoneSlab(BRINESHALE_BRICKS);
    public static final Block BRINESHALE_BRICK_WALL = StoneSets.stoneWall(BRINESHALE_BRICKS);

    public static final Block JELLYROOT = createBlockWithItem("jellyroot", new JellyrootBlock(AbstractBlock.Settings.copy(Blocks.GRASS)
            .mapColor(MapColor.LIGHT_BLUE).sounds(BlockSoundGroup.WEEPING_VINES).luminance(state -> 4)));
    public static final Block POTTED_JELLYROOT = makePottedPlant(JELLYROOT);

    public static final Block TALL_JELLYROOT = createBlockWithItem("tall_jellyroot", new TallJellyrootBlock(AbstractBlock.Settings.copy(Blocks.GRASS)
            .mapColor(MapColor.LIGHT_BLUE).sounds(BlockSoundGroup.WEEPING_VINES).luminance(state -> 8)));

    public static final Block HIBERNATING_GIPPLE = createBlockWithItem("hibernating_gipple", new HibernatingGippleBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK).mapColor(GELATIN_LAYER.getDefaultMapColor()).hardness(0.1f).pistonBehavior(PistonBehavior.DESTROY).dropsNothing()));


    public static void init(){

    }
}
