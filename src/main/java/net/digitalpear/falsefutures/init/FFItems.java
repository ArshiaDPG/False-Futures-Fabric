package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.tags.FFBannerPatternItemTags;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

@SuppressWarnings("unused")
public class FFItems {

    public static Item register(String itemID, Item item){
        return Registry.register(Registries.ITEM, new Identifier(FalseFutures.MOD_ID, itemID), item);
    }
    public static Item createSpawnEgg(EntityType<? extends MobEntity> type, int PrimaryColor, int SecondaryColor){
        return register(Registries.ENTITY_TYPE.getId(type).getPath() + "_spawn_egg", new SpawnEggItem(type, PrimaryColor, SecondaryColor, new Item.Settings()));
    }
    public static Item createInvisibleSpawnEgg(EntityType<? extends MobEntity> type, int PrimaryColor, int SecondaryColor){
        return register(Registries.ENTITY_TYPE.getId(type).getPath() + "_spawn_egg", new SpawnEggItem(type, PrimaryColor, SecondaryColor, new Item.Settings()));
    }
    public static Item createDisc(String name, int output, SoundEvent sound, int length){
        return register("music_disc_" + name, new MusicDiscItem(output, sound, new Item.Settings().rarity(Rarity.RARE).maxCount(1), length));
    }
    public static Item createBucketedMob(EntityType<?> type){
        return register(Registries.ENTITY_TYPE.getId(type).getPath() + "_bucket", new EntityBucketItem(type, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).recipeRemainder(Items.WATER_BUCKET).maxCount(1)));
    }

    public static final Item GIPPLE_BUCKET = createBucketedMob(FFEntities.GIPPLE);
    public static final Item GIPPLE_SPAWN_EGG = createSpawnEgg(FFEntities.GIPPLE, 13558777, 11642584);
    public static final Item ANEUPLOIDIAN_SPAWN_EGG = createInvisibleSpawnEgg(FFEntities.ANEUPLOIDIAN, 13558777, 9669861);
    public static final Item GELATIN = register("gelatin", new Item(new Item.Settings().food(FFFoodComponents.GELATIN)));
    public static final Item MUSIC_DISC_GIPPLECORE = createDisc("gipplecore", 13, FFSoundEvents.MUSIC_DISC_GIPPLECORE, 113);
    public static final Item GIPPLEPAD = register("gipplepad", new PlaceableOnWaterItem(FFBlocks.GIPPLEPAD, new Item.Settings()));
    public static final Item GIPPLE_BANNER_PATTERN = register("gipple_banner_pattern", new BannerPatternItem(FFBannerPatternItemTags.GIPPLE_PATTERN_ITEM, new Item.Settings().maxCount(1)));

    public static void init() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.HONEY_BOTTLE, GELATIN);
            entries.addAfter(GELATIN, FFBlocks.PLAIN_JELLY, FFBlocks.MILKY_JELLY, FFBlocks.BLAST_JELLY, FFBlocks.MUDDY_JELLY, FFBlocks.INKY_JELLY,
                    FFBlocks.COCOA_JELLY, FFBlocks.FRUITY_JELLY, FFBlocks.BRIGHT_JELLY, FFBlocks.FLORAL_JELLY, FFBlocks.BOUNCY_JELLY, FFBlocks.PRICKLY_JELLY,
                    FFBlocks.WARP_JELLY, FFBlocks.LUMINESCENT_JELLY, FFBlocks.ENCHANTING_JELLY, FFBlocks.FOAMY_JELLY, FFBlocks.SYMPHONIC_JELLY, FFBlocks.SWEET_JELLY

            );
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.addAfter(Items.PINK_BANNER, FFBlocks.PLAIN_JELLY, FFBlocks.MILKY_JELLY, FFBlocks.BLAST_JELLY, FFBlocks.MUDDY_JELLY, FFBlocks.INKY_JELLY,
                    FFBlocks.COCOA_JELLY, FFBlocks.FRUITY_JELLY, FFBlocks.BRIGHT_JELLY, FFBlocks.FLORAL_JELLY, FFBlocks.BOUNCY_JELLY, FFBlocks.PRICKLY_JELLY,
                    FFBlocks.WARP_JELLY, FFBlocks.LUMINESCENT_JELLY, FFBlocks.ENCHANTING_JELLY, FFBlocks.FOAMY_JELLY, FFBlocks.SYMPHONIC_JELLY, FFBlocks.SWEET_JELLY
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
            entries.addAfter(Items.GLOW_SQUID_SPAWN_EGG, GIPPLE_SPAWN_EGG, ANEUPLOIDIAN_SPAWN_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.DEEPSLATE, FFBlocks.GELATITE, FFBlocks.AMOEBALITH);
            entries.addAfter(Items.SMALL_DRIPLEAF, FFBlocks.JELLYROOT, FFBlocks.TALL_JELLYROOT);
            entries.addAfter(Items.SNOW, FFBlocks.GELATIN_LAYER);
            entries.add(FFBlocks.HIBERNATING_GIPPLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(FFBlocks.GELATIN_BLOCK);
            entries.addAfter(Items.REINFORCED_DEEPSLATE,
                    FFBlocks.GELATITE, FFBlocks.GELATITE_STAIRS, FFBlocks.GELATITE_SLAB, FFBlocks.GELATITE_WALL, FFBlocks.GELATITE_BUTTON,
                    FFBlocks.GELATITE_BRICKS, FFBlocks.GELATITE_BRICK_STAIRS, FFBlocks.GELATITE_BRICK_SLAB, FFBlocks.GELATITE_BRICK_WALL,
                    FFBlocks.AMOEBALITH, FFBlocks.AMOEBALITH_STAIRS, FFBlocks.AMOEBALITH_SLAB, FFBlocks.AMOEBALITH_WALL, FFBlocks.AMOEBALITH_BUTTON,
                    FFBlocks.AMOEBALITH_BRICKS, FFBlocks.AMOEBALITH_BRICK_STAIRS, FFBlocks.AMOEBALITH_BRICK_SLAB, FFBlocks.AMOEBALITH_BRICK_WALL
            );
        });

    }
}
