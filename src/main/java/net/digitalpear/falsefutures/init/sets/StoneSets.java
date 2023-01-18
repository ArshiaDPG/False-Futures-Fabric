package net.digitalpear.falsefutures.init.sets;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.GippleInfestedBlock;
import net.digitalpear.falsefutures.common.datagens.FFBlockModelGen;
import net.fabricmc.fabric.impl.item.group.ItemGroupExtensions;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class StoneSets {

    public static Block createBlockWithItem(String blockID, Block block, ItemGroup group){
        createBlockItem(blockID, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }
    public static BlockItem createBlockItem(String blockID, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(FalseFutures.MOD_ID, blockID), new BlockItem(block, new Item.Settings().group(group)));
    }

    public static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }


    public static Block fullStoneSet(String name, AbstractBlock.Settings settings){
        Block baseBlock = createBlockWithItem(name, new Block(settings), ItemGroup.BUILDING_BLOCKS);
        stoneStairs(baseBlock);
        stoneSlab(baseBlock);
        stoneWall(baseBlock);
        stoneButton(baseBlock);
        stonePressurePlate(baseBlock);
        return baseBlock;
    }

    public static Block infestedBlock(Block block){
        return createBlockWithItem("infested_" + getName(block), new GippleInfestedBlock(block, AbstractBlock.Settings.copy(block)), ItemGroup.DECORATIONS);
    }

    public static Block stoneStairs(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_stairs",
                new StairsBlock(baseBlock.getDefaultState(), AbstractBlock.Settings.copy(baseBlock)), ItemGroup.BUILDING_BLOCKS);
    }
    public static Block stoneSlab(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_slab",
                new SlabBlock(AbstractBlock.Settings.copy(baseBlock)), ItemGroup.BUILDING_BLOCKS);
    }
    public static Block stoneWall(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_wall",
                new WallBlock(AbstractBlock.Settings.copy(baseBlock)), ItemGroup.DECORATIONS);
    }
    public static Block stonePressurePlate(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_pressure_plate",
                new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, AbstractBlock.Settings.copy(baseBlock)), ItemGroup.REDSTONE);
    }
    public static Block stoneButton(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_button",
                new StoneButtonBlock(AbstractBlock.Settings.copy(baseBlock)), ItemGroup.REDSTONE);
    }

    //If name ends with "Bricks" then shorten to "Brick" for use
    public static String getName(Block base){
        String name = Registry.BLOCK.getId(base).getPath();
        return name.endsWith("bricks") ? name.substring(0, name.length() - 1) : name;
    }
    public static String getName(String base){
        return base.endsWith("bricks") ? base.substring(0, base.length() - 1) : base;
    }
}
