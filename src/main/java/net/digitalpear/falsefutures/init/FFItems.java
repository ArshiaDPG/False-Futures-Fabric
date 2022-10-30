package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class FFItems {
    public static Item register(String itemID, Item item){
        return Registry.register(Registry.ITEM, new Identifier(FalseFutures.MOD_ID, itemID), item);
    }
    public static Item createSpawnEgg(EntityType<? extends MobEntity> type, int PrimaryColor, int SecondaryColor){
        return register(type.getTranslationKey().split("\\.")[2] + "_spawn_egg", new SpawnEggItem(type, PrimaryColor, SecondaryColor, new Item.Settings().group(ItemGroup.MISC)));
    }
    public static Item createDisc(String name, int output, SoundEvent sound, int length){
        return register("music_disc_" + name, new MusicDiscItem(output, sound, new Item.Settings().rarity(Rarity.RARE).maxCount(1).group(ItemGroup.MISC), length));
    }
    public static final Item GIPPLE_SPAWN_EGG = createSpawnEgg(FFEntities.GIPPLE, 13558777, 11642584);
    public static final Item SOMETHING_SPAWN_EGG = createSpawnEgg(FFEntities.SOMETHING, 13558777, 11642584);
    public static final Item GELATIN = register("gelatin", new Item(new Item.Settings().food(FFFoodComponents.GELATIN).group(ItemGroup.FOOD)));
    public static final Item MUSIC_DISC_GIPPLECORE = createDisc("gipplecore", 13, FFSoundEvents.MUSIC_DISC_GIPPLECORE, 113);



    public static void init(){}
}
