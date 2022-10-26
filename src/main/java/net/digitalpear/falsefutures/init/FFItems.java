package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFItems {
    public static Item register(String itemID, Item item){
        return Registry.register(Registry.ITEM, new Identifier(FalseFutures.MOD_ID, itemID), item);
    }
    public static Item createSpawnEgg(EntityType<? extends MobEntity> type, int PrimaryColor, int SecondaryColor){
        return register(type.getTranslationKey().split("\\.")[2] + "_spawn_egg", new SpawnEggItem(type, PrimaryColor, SecondaryColor, new Item.Settings().group(ItemGroup.MISC)));
    }
    public static final Item GIPPLE_SPAWN_EGG = createSpawnEgg(FFEntities.GIPPLE, 13558777, 11642584);
    public static final Item GELATIN = register("gelatin", new Item(new Item.Settings().food(FFFoodComponents.GELATIN).group(ItemGroup.FOOD)));


    public static void init(){}
}
