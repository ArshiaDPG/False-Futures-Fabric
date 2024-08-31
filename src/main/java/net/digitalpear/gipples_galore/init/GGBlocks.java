package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.common.blocks.GelatinBlock;
import net.digitalpear.gipples_galore.common.blocks.GelatinLayerBlock;
import net.digitalpear.gipples_galore.common.blocks.GelatinousGrowthBlock;
import net.digitalpear.gipples_galore.common.blocks.HibernatingGippleBlock;
import net.digitalpear.gipples_galore.common.blocks.jelly.*;
import net.digitalpear.gipples_galore.init.data.GGBlockSoundGroups;
import net.digitalpear.gipples_galore.init.data.sets.StoneSet;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
public class GGBlocks {

    public static final Map<Block, Item> JELLY = new HashMap<>();

    public static BlockItem createBlockItem(String blockID, Block block){
        return Registry.register(Registries.ITEM, GipplesGalore.id(blockID), new BlockItem(block, new Item.Settings()));
    }
    public static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registries.BLOCK, GipplesGalore.id(blockID), block);
    }
    public static Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        return createBlockWithoutItem(blockID, block);
    }

    public static Block createJellyBlockWithItem(String blockID, Block block, Item ingredient){
        createBlockItem(blockID, block);
        Block jelly = createBlockWithoutItem(blockID, block);
        JELLY.put(jelly,ingredient);
        return jelly;
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
    public static final Block WARP_JELLY = createJellyBlockWithItem("warp_jelly", new WarpJellyBlock(jellySettings(MapColor.CYAN)), Items.ENDER_PEARL);
    public static final Block FOAMY_JELLY = createJellyBlockWithItem("foamy_jelly", new JellyBlock(jellySettings(MapColor.PURPLE)), Items.DRAGON_BREATH);
    public static final Block ENCHANTING_JELLY = createJellyBlockWithItem("enchanting_jelly", new JellyBlock(jellySettings(MapColor.BLUE)), Items.LAPIS_LAZULI);
    public static final Block COCOA_JELLY = createJellyBlockWithItem("cocoa_jelly", new SweetJellyBlock(jellySettings(MapColor.BROWN)), Items.COCOA_BEANS);
    public static final Block PRICKLY_JELLY = createJellyBlockWithItem("prickly_jelly", new BouncyJellyBlock(jellySettings(MapColor.GREEN)), Items.CACTUS);
    public static final Block FRUITY_JELLY = createJellyBlockWithItem("fruity_jelly", new SweetJellyBlock(jellySettings(MapColor.BRIGHT_RED)), Items.MELON_SLICE);
    public static final Block INKY_JELLY = createJellyBlockWithItem( "inky_jelly", new InkyJellyBlock(jellySettings(MapColor.BLACK)), Items.INK_SAC);


    public static final Block GIPPLEPAD = createBlockWithoutItem("gipplepad", new LilyPadBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_PURPLE).breakInstantly().sounds(BlockSoundGroup.LILY_PAD).nonOpaque()));

    public static final Block GELATIN_LAYER = createBlockWithItem("gelatin_layer",
            new GelatinLayerBlock(AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.PALE_PURPLE)));

    public static final StoneSet GELATITE_SET = new StoneSet(GipplesGalore.id("gelatite"),
            new Block(AbstractBlock.Settings.copy(Blocks.STONE)
                    .mapColor(MapColor.CYAN)
                    .sounds(GGBlockSoundGroups.GELATITE))).stairs().slab().wall().pressurePlate().button();

    public static final Block GELATITE = GELATITE_SET.getBase();
    public static final Block GELATITE_STAIRS = GELATITE_SET.getStairs();
    public static final Block GELATITE_SLAB = GELATITE_SET.getSlab();
    public static final Block GELATITE_WALL = GELATITE_SET.getWall();
    public static final Block GELATITE_PRESSURE_PLATE = GELATITE_SET.getPressurePlate();
    public static final Block GELATITE_BUTTON = GELATITE_SET.getButton();



    public static final StoneSet GELATITE_BRICK_SET = new StoneSet("gelatite_brick",
            new Block(AbstractBlock.Settings.copy(GELATITE))).stairs().slab().wall().chiseled();

    public static final Block GELATITE_BRICKS = GELATITE_BRICK_SET.getBase();
    public static final Block GELATITE_BRICK_STAIRS = GELATITE_BRICK_SET.getStairs();
    public static final Block GELATITE_BRICK_SLAB = GELATITE_BRICK_SET.getSlab();
    public static final Block GELATITE_BRICK_WALL = GELATITE_BRICK_SET.getWall();
    public static final Block CHISELED_GELATITE_BRICKS = GELATITE_BRICK_SET.getChiseled();



    public static final StoneSet AMOEBALITH_SET = new StoneSet("amoebalith",
            new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
                    .sounds(GGBlockSoundGroups.AMOEBALITH)
                    .mapColor(MapColor.PURPLE))).stairs().slab().wall().pressurePlate().button();

    public static final Block AMOEBALITH = AMOEBALITH_SET.getBase();

    public static final Block AMOEBALITH_STAIRS = AMOEBALITH_SET.getStairs();
    public static final Block AMOEBALITH_SLAB = AMOEBALITH_SET.getSlab();
    public static final Block AMOEBALITH_WALL = AMOEBALITH_SET.getWall();
    public static final Block AMOEBALITH_PRESSURE_PLATE = AMOEBALITH_SET.getPressurePlate();
    public static final Block AMOEBALITH_BUTTON = AMOEBALITH_SET.getButton();



    public static final StoneSet AMOEBALITH_BRICK_SET = new StoneSet("amoebalith_brick",
            new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
                    .sounds(GGBlockSoundGroups.AMOEBALITH)
                    .mapColor(MapColor.PURPLE))).stairs().slab().wall().chiseled();

    public static final Block AMOEBALITH_BRICKS = AMOEBALITH_BRICK_SET.getBase();
    public static final Block AMOEBALITH_BRICK_STAIRS = AMOEBALITH_BRICK_SET.getStairs();
    public static final Block AMOEBALITH_BRICK_SLAB = AMOEBALITH_BRICK_SET.getSlab();
    public static final Block AMOEBALITH_BRICK_WALL = AMOEBALITH_BRICK_SET.getWall();
    public static final Block CHISELED_AMOEBALITH_BRICKS = AMOEBALITH_BRICK_SET.getChiseled();



    public static final Block HIBERNATING_GIPPLE = createBlockWithItem("hibernating_gipple",
            new HibernatingGippleBlock(AbstractBlock.Settings.create()
                .sounds(BlockSoundGroup.HONEY)
                .nonOpaque()
                .mapColor(GELATIN_LAYER.getDefaultMapColor())
                .hardness(0.8f)
                .pistonBehavior(PistonBehavior.DESTROY)
                .dropsNothing()
                .ticksRandomly()
            ));

    public static final Block GELATIN_BLOCK = createBlockWithItem("gelatin_block",
            new GelatinBlock(AbstractBlock.Settings.create()
                    .hardness(0.4f)
                    .mapColor(GELATIN_LAYER.getDefaultMapColor())
                    .sounds(BlockSoundGroup.HONEY)

            ));

    public static final Block GELATINOUS_GROWTH = createBlockWithItem("gelatinous_growth",
            new GelatinousGrowthBlock(AbstractBlock.Settings.copy(Blocks.SHORT_GRASS)
                    .mapColor(GELATIN_LAYER.getDefaultMapColor())
                    .sounds(BlockSoundGroup.HONEY)
            ));

    public static final Block POTTED_GELATINOUS_GROWTH = createBlockWithoutItem("potted_gelatinous_growth",new FlowerPotBlock(GELATINOUS_GROWTH, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));


    public static void init(){

    }
}
