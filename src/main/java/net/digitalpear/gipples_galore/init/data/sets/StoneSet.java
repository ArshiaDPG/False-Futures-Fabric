package net.digitalpear.gipples_galore.init.data.sets;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.block.*;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class StoneSet {
    public static List<StoneSet> ALL_SETS = new ArrayList<>();

    private final BlockFamily.Builder family;
    private final Block base;
    private Block stairs;
    private Block slab;
    private Block wall;
    private Block button;
    private Block pressurePlate;
    private Block chiseled;

    private final Identifier setName;

    private final AbstractBlock.Settings properties;

    private StoneSet(String name, AbstractBlock.Settings baseProperties){
        this(GipplesGalore.id(name), baseProperties);
    }
    private StoneSet(Identifier name, AbstractBlock.Settings baseProperties){
        setName = name;
        properties = baseProperties;
        base = baseBlock();
        family = new BlockFamily.Builder(base);
    }
    private Block baseBlock(){
        Block block = Blocks.register(Builder.keyOf(formatName(setName)), properties);
        Items.register(block);
        return block;
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
    public static class Builder{
        private final StoneSet stoneSet;
        public Builder(String name, AbstractBlock.Settings baseProperties){
            this(GipplesGalore.id(name), baseProperties);
        }
        public Builder(Identifier name, AbstractBlock.Settings baseProperties){
            this.stoneSet = new StoneSet(name, baseProperties);
        }

        private static RegistryKey<Block> keyOf(String id) {
            return keyOf(GipplesGalore.id(id));
        }
        private static RegistryKey<Block> keyOf(Identifier id) {
            return RegistryKey.of(RegistryKeys.BLOCK, id);
        }


        public Builder stairs(){
            return stairs(settings -> new StairsBlock(stoneSet.base.getDefaultState(), settings));
        }
        public Builder stairs(Function<AbstractBlock.Settings, Block> factory){
            stoneSet.stairs = Blocks.register(keyOf(stoneSet.setName.withSuffixedPath("_stairs")), factory, stoneSet.properties);
            Items.register(stoneSet.stairs);
            stoneSet.family.stairs(stoneSet.stairs);
            return this;
        }

        public Builder slab(){
            return slab(SlabBlock::new);
        }
        public Builder slab(Function<AbstractBlock.Settings, Block> factory){
            stoneSet.slab = Blocks.register(keyOf(stoneSet.setName.withSuffixedPath("_slab")), factory, stoneSet.properties);
            Items.register(stoneSet.slab);
            stoneSet.family.slab(stoneSet.slab);
            return this;
        }

        public Builder wall(){
            return wall(WallBlock::new);
        }
        public Builder wall(Function<AbstractBlock.Settings, Block> factory){
            stoneSet.wall = Blocks.register(keyOf(stoneSet.setName.withSuffixedPath("_wall")), factory, stoneSet.properties);
            Items.register(stoneSet.wall);
            stoneSet.family.wall(stoneSet.wall);
            return this;
        }

        public Builder button(){
            return button(settings -> new ButtonBlock(BlockSetType.STONE, 20, settings));
        }
        public Builder button(Function<AbstractBlock.Settings, Block> factory){
            stoneSet.button = Blocks.register(keyOf(stoneSet.setName.withSuffixedPath("_button")), factory, stoneSet.properties);
            Items.register(stoneSet.button);
            stoneSet.family.button(stoneSet.button);
            return this;
        }
        public Builder pressurePlate(){
            return pressurePlate(settings -> new PressurePlateBlock(BlockSetType.STONE, settings));
        }
        public Builder pressurePlate(Function<AbstractBlock.Settings, Block> factory){
            stoneSet.pressurePlate = Blocks.register(keyOf(stoneSet.setName.withSuffixedPath("_pressure_plate")), factory, stoneSet.properties);
            Items.register(stoneSet.pressurePlate);
            stoneSet.family.pressurePlate(stoneSet.pressurePlate);
            return this;
        }

        public Builder chiseled(){
            stoneSet.chiseled = Blocks.register(keyOf(stoneSet.formatName(stoneSet.setName).withPrefixedPath("chiseled_")), stoneSet.properties);
            Items.register(stoneSet.chiseled);
            stoneSet.family.chiseled(stoneSet.chiseled);
            return this;
        }

        public StoneSet build(){
            ALL_SETS.add(stoneSet);
            return stoneSet;
        }
    }
}
