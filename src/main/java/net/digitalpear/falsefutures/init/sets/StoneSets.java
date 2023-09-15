package net.digitalpear.falsefutures.init.sets;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class StoneSets {

    public static Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        return Registry.register(Registries.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }
    public static BlockItem createBlockItem(String blockID, Block block){
        return Registry.register(Registries.ITEM, new Identifier(FalseFutures.MOD_ID, blockID), new BlockItem(block, new Item.Settings()));
    }

    public static Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(FalseFutures.MOD_ID, blockID), block);
    }


    public static Block fullStoneSet(String name, AbstractBlock.Settings settings) {
        Block baseBlock = createBlockWithItem(name, new Block(settings));
        stoneStairs(baseBlock);
        stoneSlab(baseBlock);
        stoneWall(baseBlock);
        stoneButton(baseBlock);
        stonePressurePlate(baseBlock);
        return baseBlock;
    }

    public static Block stoneStairs(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_stairs",
                new StairsBlock(baseBlock.getDefaultState(), AbstractBlock.Settings.copy(baseBlock)));
    }
    public static Block stoneSlab(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_slab",
                new SlabBlock(AbstractBlock.Settings.copy(baseBlock)));
    }
    public static Block stoneWall(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_wall",
                new WallBlock(AbstractBlock.Settings.copy(baseBlock)));
    }
    public static Block stonePressurePlate(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_pressure_plate",
                new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, AbstractBlock.Settings.copy(baseBlock), BlockSetType.STONE));
    }
    public static Block stoneButton(Block baseBlock){
        return createBlockWithItem(getName(baseBlock) + "_button",
                new ButtonBlock(AbstractBlock.Settings.create().noCollision().strength(0.5F).pistonBehavior(PistonBehavior.DESTROY), BlockSetType.STONE, 20, false));
    }

    //If name ends with "Bricks" then shorten to "Brick" for use
    public static String getName(Block base){
        String name = Registries.BLOCK.getId(base).getPath();
        return name.endsWith("bricks") ? name.substring(0, name.length() - 1) : name;
    }
    public static String getName(String base){
        return base.endsWith("bricks") ? base.substring(0, base.length() - 1) : base;
    }
}
