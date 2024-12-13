package net.digitalpear.gipples_galore.init.data.sets;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;


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

    private AbstractBlock.Settings properties;

    public StoneSet(String name, AbstractBlock.Settings baseProperties){
        setName = GipplesGalore.id(name);
        properties = baseProperties;
        base = baseBlock();
        family = new BlockFamily.Builder(base);
    }
    public StoneSet(Identifier name, AbstractBlock.Settings baseProperties){
        setName = name;
        properties = baseProperties;
        base = baseBlock();
        family = new BlockFamily.Builder(base);
    }
    private static RegistryKey<Block> keyOf(String id) {
        return keyOf(GipplesGalore.id(id));
    }
    private static RegistryKey<Block> keyOf(Identifier id) {
        return RegistryKey.of(RegistryKeys.BLOCK, id);
    }
    private Block baseBlock(){
        Block block = Blocks.register(keyOf(formatName(setName)), properties);
        Items.register(block);
        return block;
    }

    public StoneSet stairs(){
        return stairs(settings -> new StairsBlock(base.getDefaultState(), settings));
    }
    public StoneSet stairs(Function<AbstractBlock.Settings, Block> factory){
        stairs = Blocks.register(keyOf(setName.withSuffixedPath("_stairs")), factory, properties);
        Items.register(stairs);
        family.stairs(stairs);
        return this;
    }

    public StoneSet slab(){
        return slab(SlabBlock::new);
    }
    public StoneSet slab(Function<AbstractBlock.Settings, Block> factory){
        slab = Blocks.register(keyOf(setName.withSuffixedPath("_slab")), factory, properties);
        Items.register(slab);
        family.slab(slab);
        return this;
    }

    public StoneSet wall(){
        return wall(WallBlock::new);
    }
    public StoneSet wall(Function<AbstractBlock.Settings, Block> factory){
        wall = Blocks.register(keyOf(setName.withSuffixedPath("_wall")), factory, properties);
        Items.register(wall);
        family.wall(wall);
        return this;
    }

    public StoneSet button(){
        return button(settings -> new ButtonBlock(BlockSetType.STONE, 20, settings));
    }
    public StoneSet button(Function<AbstractBlock.Settings, Block> factory){
        button = Blocks.register(keyOf(setName.withSuffixedPath("_button")), factory, properties);
        Items.register(button);
        family.button(button);
        return this;
    }
    public StoneSet pressurePlate(){
        return pressurePlate(settings -> new PressurePlateBlock(BlockSetType.STONE, settings));
    }
    public StoneSet pressurePlate(Function<AbstractBlock.Settings, Block> factory){
        pressurePlate = Blocks.register(keyOf(setName.withSuffixedPath("_pressure_plate")), factory, properties);
        Items.register(pressurePlate);
        family.pressurePlate(pressurePlate);
        return this;
    }

    public StoneSet chiseled(){
        chiseled = Blocks.register(keyOf(formatName(setName).withPrefixedPath("chiseled_")), properties);
        Items.register(chiseled);
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

    private Identifier formatName(Identifier id){
        if (id.getPath().endsWith("brick")){
            return id.withSuffixedPath("s");
        }
        return id;
    }
}
