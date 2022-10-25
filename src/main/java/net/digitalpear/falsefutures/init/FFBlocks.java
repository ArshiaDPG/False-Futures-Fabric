package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.JelloCakeBlock;
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

    //Jello Stuff
    public static Block jelloCakeSettings(MapColor color){
        return new JelloCakeBlock(AbstractBlock.Settings.of(Material.CAKE, color).strength(0.1f, 0.2f).sounds(BlockSoundGroup.SLIME));
    }
    public static Block jelloCakeRegistry(String color_name,MapColor color){
        return createBlockWithItem(color_name + "_jelly", jelloCakeSettings(color), ItemGroup.FOOD);
    }

    public static final Block BLUE_JELLO_CAKE = jelloCakeRegistry("plain", MapColor.LIGHT_BLUE);
    public static final Block GREEN_JELLO_CAKE = jelloCakeRegistry("weird", MapColor.LIME);
    public static final Block WHITE_JELLO_CAKE = jelloCakeRegistry("milky", MapColor.WHITE);
    public static final Block YELLOW_JELLO_CAKE = jelloCakeRegistry("floral", MapColor.YELLOW);
    public static final Block PURPLE_JELLO_CAKE = jelloCakeRegistry("symphonic", MapColor.PURPLE);
    public static final Block PINK_JELLO_CAKE = jelloCakeRegistry("sweet", MapColor.PINK);
    public static final Block RED_JELLO_CAKE = jelloCakeRegistry("fruity", MapColor.BRIGHT_RED);


    public static void init(){}
}
