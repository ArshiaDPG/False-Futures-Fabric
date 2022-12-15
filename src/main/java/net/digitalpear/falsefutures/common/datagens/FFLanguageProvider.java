package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.nio.file.Path;

public class FFLanguageProvider extends FabricLanguageProvider {
    public FFLanguageProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        FFRecipeGen.JELLY.forEach((item, item2) -> {
            translationBuilder.add(item, capitalize(item.getTranslationKey().split("\\.")[2]).split("_")[0] + " Jelly");
        });


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

        try {
            Path existingFilePath = dataGenerator.getModContainer().findPath("assets/" + FalseFutures.MOD_ID + "/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
    public static void makeTranslation(TranslationBuilder translationBuilder, Item item){
        translationBuilder.add(item, capitalize(item.getTranslationKey().split("\\.")[2]));
    }
    public static void makeTranslation(TranslationBuilder translationBuilder, EntityType<?> entityType){
        translationBuilder.add(entityType, capitalize(entityType.getTranslationKey().split("\\.")[2]));
    }

    public static String capitalize(String inputString) {

        // get the first character of the inputString
        char firstLetter = inputString.charAt(0);

        // convert it to an UpperCase letter
        char capitalFirstLetter = Character.toUpperCase(firstLetter);

        // return the output string by updating
        //the first char of the input string
        return inputString.replace(inputString.charAt(0), capitalFirstLetter);
    }
}
