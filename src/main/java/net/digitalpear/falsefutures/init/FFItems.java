package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class FFItems {

    public static Item register(String itemID, Item item){
        return Registry.register(Registry.ITEM, new Identifier(FalseFutures.MOD_ID, itemID), item);
    }
    public static Item createSpawnEgg(EntityType<? extends MobEntity> type, int PrimaryColor, int SecondaryColor){
        return register(Registry.ENTITY_TYPE.getId(type).getPath() + "_spawn_egg", new SpawnEggItem(type, PrimaryColor, SecondaryColor, new Item.Settings().group(ItemGroup.MISC)));
    }
    public static Item createInvisibleSpawnEgg(EntityType<? extends MobEntity> type, int PrimaryColor, int SecondaryColor){
        return register(Registry.ENTITY_TYPE.getId(type).getPath() + "_spawn_egg", new SpawnEggItem(type, PrimaryColor, SecondaryColor, new Item.Settings()));
    }
    public static Item createDisc(String name, int output, SoundEvent sound, int length){
        return register("music_disc_" + name, new MusicDiscItem(output, sound, new Item.Settings().rarity(Rarity.RARE).maxCount(1).group(ItemGroup.MISC), length));
    }
    public static Item createBucketedMob(EntityType<?> type){
        return register(Registry.ENTITY_TYPE.getId(type).getPath() + "_bucket", new EntityBucketItem(type, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, (new Item.Settings()).maxCount(1).group(ItemGroup.MISC)));
    }

    public static final Item GIPPLE_BUCKET = createBucketedMob(FFEntities.GIPPLE);

    public static final Item GIPPLE_SPAWN_EGG = createSpawnEgg(FFEntities.GIPPLE, 13558777, 11642584);
    public static final Item SOMETHING_SPAWN_EGG = createInvisibleSpawnEgg(FFEntities.SOMETHING, 13558777, 9669861);

    public static final Item GELATIN = register("gelatin", new Item(new Item.Settings().food(FFFoodComponents.GELATIN).group(ItemGroup.FOOD)));

    public static final Item MUSIC_DISC_GIPPLECORE = createDisc("gipplecore", 13, FFSoundEvents.MUSIC_DISC_GIPPLECORE, 113);

    public static final Item GIPPLEPAD = register("gipplepad", new PlaceableOnWaterItem(FFBlocks.GIPPLEPAD, new Item.Settings()));


    public static void init(){
    }
}
