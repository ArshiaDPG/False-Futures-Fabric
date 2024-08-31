package net.digitalpear.gipples_galore.init.data.sets;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class StoneSet {
    private BlockFamily.Builder family;
    private Block base;
    private Block stairs;
    private Block slab;
    private Block wall;
    private Block button;
    private Block pressurePlate;
    private Block chiseled;

    private Identifier setName;

    private Block properties;

    public StoneSet(String name, Block baseProperties){
        setName = GipplesGalore.id(name);
        properties = baseProperties;
        base = createBlockWithItem(formatName(setName), properties);
        family = new BlockFamily.Builder(base);
    }
    public StoneSet(Identifier name, Block baseProperties){
        setName = name;
        properties = baseProperties;
        base = createBlockWithItem(formatName(setName), properties);
        family = new BlockFamily.Builder(base);
    }

    public StoneSet stairs(){
        return stairs(new StairsBlock(base.getDefaultState(), AbstractBlock.Settings.copy(properties)));
    }
    public StoneSet stairs(Block properties){
        stairs = createBlockWithItem(setName.withSuffixedPath("_stairs"), properties);
        family.stairs(stairs);
        return this;
    }

    public StoneSet slab(){
        return slab(new SlabBlock(AbstractBlock.Settings.copy(properties)));
    }
    public StoneSet slab(Block properties){
        slab = createBlockWithItem(setName.withSuffixedPath("_slab"), properties);
        family.slab(slab);
        return this;
    }

    public StoneSet wall(){
        return wall(new WallBlock(AbstractBlock.Settings.copy(properties)));
    }
    public StoneSet wall(Block properties){
        wall = createBlockWithItem(setName.withSuffixedPath("_wall"), properties);
        family.wall(wall);
        return this;
    }

    public StoneSet button(){
        return button(new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(properties)));
    }
    public StoneSet button(Block properties){
        button = createBlockWithItem(setName.withSuffixedPath("_button"), properties);
        family.button(button);
        return this;
    }
    public StoneSet pressurePlate(){
        return pressurePlate(new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(properties)));
    }
    public StoneSet pressurePlate(Block properties){
        pressurePlate = createBlockWithItem(setName.withSuffixedPath("_pressure_plate"), properties);
        family.pressurePlate(pressurePlate);
        return this;
    }

    public StoneSet chiseled(){
        return chiseled(new Block(AbstractBlock.Settings.copy(properties)));
    }
    public StoneSet chiseled(Block properties){
        chiseled = createBlockWithItem(formatName(setName).withPrefixedPath("chiseled_"), properties);
        family.chiseled(chiseled);
        return this;
    }

    public Block getBase() {
        return base;
    }

    public Block getStairs() {
        return stairs;
    }

    public Block getSlab() {
        return slab;
    }

    public Block getWall() {
        return wall;
    }

    public Block getPressurePlate() {
        return pressurePlate;
    }

    public Block getButton() {
        return button;
    }

    public Block getChiseled() {
        return chiseled;
    }

    public BlockFamily getBlockFamily() {
        return family.build();
    }

    private static Block createBlockWithItem(Identifier blockID, Block block){
        createBlockItem(blockID, block);
        return createBlockWithoutItem(blockID, block);
    }
    private static BlockItem createBlockItem(Identifier blockID, Block block){
        return Registry.register(Registries.ITEM, blockID, new BlockItem(block, new Item.Settings()));
    }

    private static Block createBlockWithoutItem(Identifier blockID, Block block){
        return Registry.register(Registries.BLOCK, blockID, block);
    }

    private Identifier formatName(Identifier id){
        if (id.getPath().endsWith("brick")){
            return id.withSuffixedPath("s");
        }
        return id;
    }
}
