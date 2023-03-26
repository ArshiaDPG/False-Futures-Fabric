package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.FFBannerPatterns;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
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

        for(Block jelly : FFBlocks.JELLY.keySet()) {
            autoName(translationBuilder, jelly);
        }

        autoName(translationBuilder, FFBlocks.GELATITE);
        autoName(translationBuilder, FFBlocks.GELATITE_STAIRS);
        autoName(translationBuilder, FFBlocks.GELATITE_SLAB);
        autoName(translationBuilder, FFBlocks.GELATITE_WALL);
        autoName(translationBuilder, FFBlocks.GELATITE_PRESSURE_PLATE);
        autoName(translationBuilder, FFBlocks.GELATITE_BUTTON);

        autoName(translationBuilder, FFBlocks.GELATITE_BRICKS);
        autoName(translationBuilder, FFBlocks.GELATITE_BRICK_STAIRS);
        autoName(translationBuilder, FFBlocks.GELATITE_BRICK_SLAB);
        autoName(translationBuilder, FFBlocks.GELATITE_BRICK_WALL);
        autoName(translationBuilder, FFBlocks.CHISELED_GELATITE_BRICKS);

        autoName(translationBuilder, FFBlocks.BRINESHALE);
        autoName(translationBuilder, FFBlocks.BRINESHALE_STAIRS);
        autoName(translationBuilder, FFBlocks.BRINESHALE_SLAB);
        autoName(translationBuilder, FFBlocks.BRINESHALE_WALL);
        autoName(translationBuilder, FFBlocks.BRINESHALE_PRESSURE_PLATE);
        autoName(translationBuilder, FFBlocks.BRINESHALE_BUTTON);

        autoName(translationBuilder, FFBlocks.BRINESHALE_BRICKS);
        autoName(translationBuilder, FFBlocks.BRINESHALE_BRICK_STAIRS);
        autoName(translationBuilder, FFBlocks.BRINESHALE_BRICK_SLAB);
        autoName(translationBuilder, FFBlocks.BRINESHALE_BRICK_WALL);
        autoName(translationBuilder, FFBlocks.CHISELED_BRINESHALE_BRICKS);

        autoName(translationBuilder, FFBlocks.INFESTED_GELATITE);
        autoName(translationBuilder, FFBlocks.INFESTED_GELATITE_BRICKS);
        autoName(translationBuilder, FFBlocks.INFESTED_BRINESHALE);
        autoName(translationBuilder, FFBlocks.INFESTED_BRINESHALE_BRICKS);

        autoName(translationBuilder, FFBlocks.GELATIN_LAYER);
        autoName(translationBuilder, FFBlocks.JELLYROOT);
        autoName(translationBuilder, FFBlocks.POTTED_JELLYROOT);
        autoName(translationBuilder, FFBlocks.TALL_JELLYROOT);

        autoName(translationBuilder, FFBlocks.GIPPLEPAD);


        autoName(translationBuilder, FFEntities.GIPPLE);
        autoName(translationBuilder, FFEntities.SOMETHING);


        autoName(translationBuilder, FFItems.GELATIN);

        translationBuilder.add(FFItems.GIPPLE_BUCKET, "Bucket of Gipple");

        translationBuilder.add(FFItems.GIPPLE_SPAWN_EGG, "Gipple Spawn Egg");
        translationBuilder.add(FFItems.SOMETHING_SPAWN_EGG, "Something Spawn Egg");

        translationBuilder.add(FFItems.MUSIC_DISC_GIPPLECORE, "Music Disc");
        translationBuilder.add("item.falsefutures.music_disc_gipplecore.desc", "Axoladdy - gipplecore");

        translationBuilder.add(FFItems.GIPPLE_BANNER_PATTERN, "Banner Pattern");
        translationBuilder.add(FFItems.GIPPLE_BANNER_PATTERN.getTranslationKey() + ".desc", "Gipple");


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

    private void autoName(TranslationBuilder translationBuilder, Block block) {
        translationBuilder.add(block, autoNameInner(Registry.BLOCK.getId(block).getPath()));
    }
    private void autoName(TranslationBuilder translationBuilder, Item item) {
        translationBuilder.add(item, autoNameInner(Registry.ITEM.getId(item).getPath()));
    }
    private void autoName(TranslationBuilder translationBuilder, EntityType<?> entityType) {
        translationBuilder.add(entityType, autoNameInner(Registry.ENTITY_TYPE.getId(entityType).getPath()));
    }

    private String autoNameInner(String id) {
        StringBuilder name = new StringBuilder();
        String[] split = id.split("_");
        for(String str : split) {
            if(!name.isEmpty()) {
                name.append(" ");
            }
            name.append(capitalize(str));
        }
        return name.toString();
    }

    public static String capitalize(String inputString) {

        // get the first character of the inputString
        char firstLetter = inputString.charAt(0);

        // return the output string by updating
        //the first char of the input string
        return inputString.replaceFirst(String.valueOf(firstLetter), String.valueOf(firstLetter).toUpperCase());
    }
}
