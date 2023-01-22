package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.nio.file.Path;

public class FFLanguageProvider extends FabricLanguageProvider {
    public FFLanguageProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        FFBlocks.JELLY.forEach((jelly, ingredient) ->
                translationBuilder.add(jelly, capitalize(Registry.BLOCK.getId(jelly).getPath().split("_")[0]) + " Jelly"));

        makeTranslation(translationBuilder, FFItems.GELATIN);

        translationBuilder.add(FFItems.GIPPLE_BUCKET, "Bucket of Gipple");

        translationBuilder.add(FFItems.GIPPLE_SPAWN_EGG, "Gipple Spawn Egg");
        translationBuilder.add(FFItems.SOMETHING_SPAWN_EGG, "Something Spawn Egg");

        translationBuilder.add(FFItems.MUSIC_DISC_GIPPLECORE, "Music Disc");
        translationBuilder.add("item.falsefutures.music_disc_gipplecore.desc", "Axoladdy - gipplecore");

        makeTranslation(translationBuilder, FFEntities.GIPPLE);
        makeTranslation(translationBuilder, FFEntities.SOMETHING);

        translationBuilder.add(FFBlocks.GIPPLEPAD, "Gipplepad");

        translationBuilder.add("advancements.husbandry.jellies.title", "Sing a rainbow!");
        translationBuilder.add("advancements.husbandry.jellies.description", "Have one of all jelly flavours in your inventory.");


        translationBuilder.add("subtitles.falsefutures.gipple_ambient", "Gipple vibrates");
        translationBuilder.add("subtitles.falsefutures.gipple_hurt", "Gipple hurts");
        translationBuilder.add("subtitles.falsefutures.gipple_burp", "Gipple consumes");
        translationBuilder.add("subtitles.falsefutures.gipple_death", "Gipple dies");

        translationBuilder.add(FFBlocks.GELATITE, "Gelatite");
        translationBuilder.add(FFBlocks.GELATITE_STAIRS, "Gelatite Stairs");
        translationBuilder.add(FFBlocks.GELATITE_SLAB, "Gelatite Slab");
        translationBuilder.add(FFBlocks.GELATITE_WALL, "Gelatite Wall");
        translationBuilder.add(FFBlocks.GELATITE_PRESSURE_PLATE, "Gelatite Pressure Plate");
        translationBuilder.add(FFBlocks.GELATITE_BUTTON, "Gelatite Button");

        translationBuilder.add(FFBlocks.GELATITE_BRICKS, "Gelatite Bricks");
        translationBuilder.add(FFBlocks.GELATITE_BRICK_STAIRS, "Gelatite Brick Stairs");
        translationBuilder.add(FFBlocks.GELATITE_BRICK_SLAB, "Gelatite Brick Slab");
        translationBuilder.add(FFBlocks.GELATITE_BRICK_WALL, "Gelatite Brick Wall");
        translationBuilder.add(FFBlocks.GELATITE_BRICK_PRESSURE_PLATE, "Gelatite Brick Pressure Plate");
        translationBuilder.add(FFBlocks.GELATITE_BRICK_BUTTON, "Gelatite Brick Button");

        translationBuilder.add(FFBlocks.DEEP_GELATITE, "Deep Gelatite");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_STAIRS, "Deep Gelatite Stairs");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_SLAB, "Deep Gelatite Slab");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_WALL, "Deep Gelatite Wall");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_PRESSURE_PLATE, "Deep Gelatite Pressure Plate");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_BUTTON, "Deep Gelatite Button");

        translationBuilder.add(FFBlocks.GELATIN_LAYER, "Gelatin Layer");
        translationBuilder.add(FFBlocks.JELLYROOT, "Jellyroot");
        translationBuilder.add(FFBlocks.POTTED_JELLYROOT, "Potted Jellyroot");
        translationBuilder.add(FFBlocks.TALL_JELLYROOT, "Tall Jellyroot");

        translationBuilder.add(FFBlocks.DEEP_GELATITE_BRICKS, "Deep Gelatite Bricks");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_BRICK_STAIRS, "Deep Gelatite Brick Stairs");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_BRICK_SLAB, "Deep Gelatite Brick Slab");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_BRICK_WALL, "Deep Gelatite Brick Wall");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_BRICK_PRESSURE_PLATE, "Deep Gelatite Brick Pressure Plate");
        translationBuilder.add(FFBlocks.DEEP_GELATITE_BRICK_BUTTON, "Deep Gelatite Brick Button");

        translationBuilder.add(FFBlocks.INFESTED_GELATITE, "Infested Gelatite");
        translationBuilder.add(FFBlocks.INFESTED_GELATITE_BRICKS, "Infested Gelatite Bricks");
        translationBuilder.add(FFBlocks.INFESTED_DEEP_GELATITE, "Infested Deep Gelatite");
        translationBuilder.add(FFBlocks.INFESTED_DEEP_GELATITE_BRICKS, "Infested Deep Gelatite Bricks");

        try {
            Path existingFilePath = dataGenerator.getModContainer().findPath("assets/" + FalseFutures.MOD_ID + "/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
    public static void makeTranslation(TranslationBuilder translationBuilder, Item item){
        translationBuilder.add(item, capitalize(Registry.ITEM.getId(item).getPath()));
    }
    public static void makeTranslation(TranslationBuilder translationBuilder, EntityType<?> entityType){
        translationBuilder.add(entityType, capitalize(Registry.ENTITY_TYPE.getId(entityType).getPath()));
    }

    public static String capitalize(String inputString) {

        // get the first character of the inputString
        char firstLetter = inputString.charAt(0);

        // return the output string by updating
        //the first char of the input string
        return inputString.replaceFirst(String.valueOf(firstLetter), String.valueOf(firstLetter).toUpperCase());
    }
}
