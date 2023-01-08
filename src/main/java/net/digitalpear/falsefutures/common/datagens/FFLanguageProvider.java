package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.common.blocks.GippleInfestedBlock;
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
        FFRecipeGen.JELLY.forEach((jelly, ingredient) ->
                translationBuilder.add(jelly, capitalize(Registry.BLOCK.getId(jelly).getPath()).split("_")[0] + " Jelly"));


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

        translationBuilder.add(FFBlocks.GELASTONE, "Gelastone");
        translationBuilder.add(FFBlocks.GELASTONE_STAIRS, "Gelastone Stairs");
        translationBuilder.add(FFBlocks.GELASTONE_SLAB, "Gelastone Slab");
        translationBuilder.add(FFBlocks.GELASTONE_WALL, "Gelastone Wall");
        translationBuilder.add(FFBlocks.GELASTONE_PRESSURE_PLATE, "Gelastone Pressure Plate");
        translationBuilder.add(FFBlocks.GELASTONE_BUTTON, "Gelastone Button");

        translationBuilder.add(FFBlocks.GELASTONE_BRICKS, "Gelastone Bricks");
        translationBuilder.add(FFBlocks.GELASTONE_BRICK_STAIRS, "Gelastone Brick Stairs");
        translationBuilder.add(FFBlocks.GELASTONE_BRICK_SLAB, "Gelastone Brick Slab");
        translationBuilder.add(FFBlocks.GELASTONE_BRICK_WALL, "Gelastone Brick Wall");
        translationBuilder.add(FFBlocks.GELASTONE_BRICK_PRESSURE_PLATE, "Gelastone Brick Pressure Plate");
        translationBuilder.add(FFBlocks.GELASTONE_BRICK_BUTTON, "Gelastone Brick Button");

        translationBuilder.add(FFBlocks.DEEP_GELASTONE, "Deep Gelastone");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_STAIRS, "Deep Gelastone Stairs");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_SLAB, "Deep Gelastone Slab");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_WALL, "Deep Gelastone Wall");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_PRESSURE_PLATE, "Deep Gelastone Pressure Plate");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_BUTTON, "Deep Gelastone Button");

        translationBuilder.add(FFBlocks.GELATIN_LAYER, "Gelatin Layer");
        translationBuilder.add(FFBlocks.JELLYROOT, "Jellyroot");
        translationBuilder.add(FFBlocks.POTTED_JELLYROOT, "Potted Jellyroot");
        translationBuilder.add(FFBlocks.TALL_JELLYROOT, "Tall Jellyroot");

        translationBuilder.add(FFBlocks.DEEP_GELASTONE_BRICKS, "Deep Gelastone Bricks");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_BRICK_STAIRS, "Deep Gelastone Brick Stairs");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_BRICK_SLAB, "Deep Gelastone Brick Slab");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_BRICK_WALL, "Deep Gelastone Brick Wall");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_BRICK_PRESSURE_PLATE, "Deep Gelastone Brick Pressure Plate");
        translationBuilder.add(FFBlocks.DEEP_GELASTONE_BRICK_BUTTON, "Deep Gelastone Brick Button");

        translationBuilder.add(FFBlocks.INFESTED_GELASTONE, "Infested Gelastone");
        translationBuilder.add(FFBlocks.INFESTED_GELASTONE_BRICKS, "Infested Gelastone Bricks");
        translationBuilder.add(FFBlocks.INFESTED_DEEP_GELASTONE, "Infested Deep Gelastone");
        translationBuilder.add(FFBlocks.INFESTED_DEEP_GELASTONE_BRICKS, "Infested Deep Gelastone Bricks");

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

        // convert it to an UpperCase letter
        char capitalFirstLetter = Character.toUpperCase(firstLetter);

        // return the output string by updating
        //the first char of the input string
        return inputString.replace(inputString.charAt(0), capitalFirstLetter);
    }
}
