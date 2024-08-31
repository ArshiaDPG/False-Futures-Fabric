package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.init.tags.GGBannerPatternItemTags;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;

@SuppressWarnings("unused")
public class GGItems {

    public static Item register(String itemID, Item item){
        return Registry.register(Registries.ITEM, GipplesGalore.id(itemID), item);
    }
    public static Item createSpawnEgg(EntityType<? extends MobEntity> type, int PrimaryColor, int SecondaryColor){
        return register(Registries.ENTITY_TYPE.getId(type).getPath() + "_spawn_egg", new SpawnEggItem(type, PrimaryColor, SecondaryColor, new Item.Settings()));
    }
    public static Item createDisc(String name, RegistryKey<JukeboxSong> song){
        return register("music_disc_" + name, new Item(new Item.Settings().rarity(Rarity.RARE).maxCount(1).jukeboxPlayable(song)));
    }
    public static Item createBucketedMob(EntityType<?> type){
        return register(Registries.ENTITY_TYPE.getId(type).getPath() + "_bucket", new EntityBucketItem(type, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).recipeRemainder(Items.WATER_BUCKET).maxCount(1)));
    }

    public static final Item GIPPLE_BUCKET = createBucketedMob(GGEntityTypes.GIPPLE);
    public static final Item GIPPLE_SPAWN_EGG = createSpawnEgg(GGEntityTypes.GIPPLE, 13558777, 11642584);
    public static final Item ANEUPLOIDIAN_SPAWN_EGG = createSpawnEgg(GGEntityTypes.ANEUPLOIDIAN, 13558777, 9669861);
    public static final Item GELATIN = register("gelatin", new Item(new Item.Settings().component(DataComponentTypes.FOOD, GGFoodComponents.GELATIN)));
    public static final Item GAPPLE = register("gapple", new Item(new Item.Settings().component(DataComponentTypes.FOOD, GGFoodComponents.GAPPLE)));
    public static final Item MUSIC_DISC_GIPPLECORE = createDisc("gipplecore", GGJukeboxSongs.GIPPLECORE);
    public static final Item GIPPLEPAD = register("gipplepad", new PlaceableOnWaterItem(GGBlocks.GIPPLEPAD, new Item.Settings()));
    public static final Item GIPPLE_BANNER_PATTERN = register("gipple_banner_pattern", new BannerPatternItem(GGBannerPatternItemTags.GIPPLE_PATTERN_ITEM, new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.HONEY_BOTTLE, GELATIN);
            entries.addAfter(Items.ENCHANTED_GOLDEN_APPLE, GAPPLE);
            entries.addAfter(GELATIN, GGBlocks.PLAIN_JELLY, GGBlocks.MILKY_JELLY, GGBlocks.BLAST_JELLY, GGBlocks.MUDDY_JELLY, GGBlocks.INKY_JELLY,
                    GGBlocks.COCOA_JELLY, GGBlocks.FRUITY_JELLY, GGBlocks.BRIGHT_JELLY, GGBlocks.FLORAL_JELLY, GGBlocks.BOUNCY_JELLY, GGBlocks.PRICKLY_JELLY,
                    GGBlocks.WARP_JELLY, GGBlocks.LUMINESCENT_JELLY, GGBlocks.ENCHANTING_JELLY, GGBlocks.FOAMY_JELLY, GGBlocks.SYMPHONIC_JELLY, GGBlocks.SWEET_JELLY
            );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.addAfter(Items.PINK_BANNER, GGBlocks.PLAIN_JELLY, GGBlocks.MILKY_JELLY, GGBlocks.BLAST_JELLY, GGBlocks.MUDDY_JELLY, GGBlocks.INKY_JELLY,
                    GGBlocks.COCOA_JELLY, GGBlocks.FRUITY_JELLY, GGBlocks.BRIGHT_JELLY, GGBlocks.FLORAL_JELLY, GGBlocks.BOUNCY_JELLY, GGBlocks.PRICKLY_JELLY,
                    GGBlocks.WARP_JELLY, GGBlocks.LUMINESCENT_JELLY, GGBlocks.ENCHANTING_JELLY, GGBlocks.FOAMY_JELLY, GGBlocks.SYMPHONIC_JELLY, GGBlocks.SWEET_JELLY
            );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(Items.MUSIC_DISC_RELIC, MUSIC_DISC_GIPPLECORE);
            entries.addAfter(Items.TADPOLE_BUCKET, GIPPLE_BUCKET);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.PIGLIN_BANNER_PATTERN, GIPPLE_BANNER_PATTERN);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.addAfter(Items.ALLAY_SPAWN_EGG, ANEUPLOIDIAN_SPAWN_EGG);
            entries.addAfter(Items.GLOW_SQUID_SPAWN_EGG, GIPPLE_SPAWN_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.DEEPSLATE, GGBlocks.GELATITE, GGBlocks.AMOEBALITH);
            entries.addAfter(Items.SNOW, GGBlocks.GELATIN_LAYER);
            entries.addAfter(Items.GLOW_LICHEN, GGBlocks.GELATINOUS_GROWTH);
            entries.addBefore(Items.SCULK, GGBlocks.GELATIN_BLOCK , GGBlocks.HIBERNATING_GIPPLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.REINFORCED_DEEPSLATE,
                    GGBlocks.GELATITE, GGBlocks.GELATITE_STAIRS, GGBlocks.GELATITE_SLAB, GGBlocks.GELATITE_WALL, GGBlocks.GELATITE_BUTTON,
                    GGBlocks.GELATITE_BRICKS, GGBlocks.GELATITE_BRICK_STAIRS, GGBlocks.GELATITE_BRICK_SLAB, GGBlocks.GELATITE_BRICK_WALL, GGBlocks.CHISELED_GELATITE_BRICKS,
                    GGBlocks.AMOEBALITH, GGBlocks.AMOEBALITH_STAIRS, GGBlocks.AMOEBALITH_SLAB, GGBlocks.AMOEBALITH_WALL, GGBlocks.AMOEBALITH_BUTTON,
                    GGBlocks.AMOEBALITH_BRICKS, GGBlocks.AMOEBALITH_BRICK_STAIRS, GGBlocks.AMOEBALITH_BRICK_SLAB, GGBlocks.AMOEBALITH_BRICK_WALL, GGBlocks.CHISELED_AMOEBALITH_BRICKS
            );
        });

    }
}
