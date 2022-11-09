package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.jelly.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFBlocks {

    public static BlockItem createBlockItem(String blockID, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(FalseFutures.MOD_ID, blockID), new BlockItem(block, new Item.Settings().group(group)));
    }

    public static Block createBlockWithItem(String blockID, Block block, ItemGroup group){
        createBlockItem(blockID, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }
    public static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }
    public static AbstractBlock.Settings jellySettings(MapColor color){
        return AbstractBlock.Settings.of(Material.CAKE, color).strength(0.1f, 0.2f).sounds(BlockSoundGroup.SLIME);
    }

    public static final Block PLAIN_JELLY = createBlockWithItem( "plain_jelly", new JellyBlock(jellySettings(MapColor.LIGHT_BLUE)), ItemGroup.FOOD);
    public static final Block WEIRD_JELLY = createBlockWithItem("weird_jelly", new WeirdJellyBlock(jellySettings(MapColor.LIME)), ItemGroup.FOOD);
    public static final Block MILKY_JELLY = createBlockWithItem("milky_jelly", new MilkyJellyBlock(jellySettings(MapColor.WHITE)), ItemGroup.FOOD);
    public static final Block FLORAL_JELLY = createBlockWithItem("floral_jelly", new FloralJellyBlock(jellySettings(MapColor.YELLOW)), ItemGroup.FOOD);
    public static final Block SYMPHONIC_JELLY = createBlockWithItem("symphonic_jelly", new SymphonicJellyBlock(jellySettings(MapColor.PURPLE)), ItemGroup.FOOD);
    public static final Block SWEET_JELLY = createBlockWithItem("sweet_jelly", new SweetJellyBlock(jellySettings(MapColor.PINK)), ItemGroup.FOOD);
    public static final Block FRUITY_JELLY = createBlockWithItem("fruity_jelly", new SweetJellyBlock(jellySettings(MapColor.BRIGHT_RED)), ItemGroup.FOOD);


    public static void init(){

    }
}
