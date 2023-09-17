package net.digitalpear.falsefutures.common.datagens;

import net.digitalpear.falsefutures.FalseFutures;
import net.digitalpear.falsefutures.init.FFBlocks;
import net.digitalpear.falsefutures.init.FFEntities;
import net.digitalpear.falsefutures.init.FFItems;
import net.digitalpear.falsefutures.init.tags.FFBiomeTags;
import net.digitalpear.falsefutures.init.tags.FFBlockTags;
import net.digitalpear.falsefutures.init.tags.FFItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;

import java.nio.file.Path;

public class FFLanguageProvider extends FabricLanguageProvider {


    public FFLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        for(Block jelly : FFBlocks.JELLY.keySet()) {
            autoName(translationBuilder, jelly);
        }

        translationBuilder.add(FFBlocks.GELATIN_BLOCK, "Block of Gelatin");

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

        autoName(translationBuilder, FFBlocks.AMOEBALITH);
        autoName(translationBuilder, FFBlocks.AMOEBALITH_STAIRS);
        autoName(translationBuilder, FFBlocks.AMOEBALITH_SLAB);
        autoName(translationBuilder, FFBlocks.AMOEBALITH_WALL);
        autoName(translationBuilder, FFBlocks.AMOEBALITH_PRESSURE_PLATE);
        autoName(translationBuilder, FFBlocks.AMOEBALITH_BUTTON);

        autoName(translationBuilder, FFBlocks.AMOEBALITH_BRICKS);
        autoName(translationBuilder, FFBlocks.AMOEBALITH_BRICK_STAIRS);
        autoName(translationBuilder, FFBlocks.AMOEBALITH_BRICK_SLAB);
        autoName(translationBuilder, FFBlocks.AMOEBALITH_BRICK_WALL);
        autoName(translationBuilder, FFBlocks.CHISELED_AMOEBALITH_BRICKS);

        autoName(translationBuilder, FFBlocks.GELATIN_LAYER);
        autoName(translationBuilder, FFBlocks.JELLYROOT);
        autoName(translationBuilder, FFBlocks.POTTED_JELLYROOT);
        autoName(translationBuilder, FFBlocks.TALL_JELLYROOT);

        autoName(translationBuilder, FFBlocks.GIPPLEPAD);

        autoName(translationBuilder, FFEntities.GIPPLE);
        autoName(translationBuilder, FFEntities.ANEUPLOIDIAN);

        autoName(translationBuilder, FFBlocks.HIBERNATING_GIPPLE);

        autoName(translationBuilder, FFItems.GELATIN);

        translationBuilder.add(FFItems.GIPPLE_BUCKET, "Bucket of Gipple");

        translationBuilder.add(FFItems.GIPPLE_SPAWN_EGG, "Gipple Spawn Egg");
        translationBuilder.add(FFItems.ANEUPLOIDIAN_SPAWN_EGG, "Something Spawn Egg");

        translationBuilder.add(FFItems.MUSIC_DISC_GIPPLECORE, "Music Disc");
        translationBuilder.add("item.falsefutures.music_disc_gipplecore.desc", "Axoladdy - gipplecore");

        makeBannerStuff(translationBuilder, FFItems.GIPPLE_BANNER_PATTERN, "Gipple");

        translationBuilder.add("advancements.husbandry.jellies.title", "Sing a rainbow!");
        translationBuilder.add("advancements.husbandry.jellies.description", "Have one of all jelly flavours in your inventory.");

        translationBuilder.add("subtitles.falsefutures.gipple_ambient", "Gipple vibrates");
        translationBuilder.add("subtitles.falsefutures.gipple_hurt", "Gipple hurts");
        translationBuilder.add("subtitles.falsefutures.gipple_burp", "Gipple consumes");
        translationBuilder.add("subtitles.falsefutures.gipple_death", "Gipple dies");

        /*
            Tag translations for EMI compat
         */
        biomeTag(translationBuilder, FFBiomeTags.GIPPLE_HABITATS);
        blackTag(translationBuilder, FFBlockTags.GIPPLE_COLONY_REPLACEABLE);
        blackTag(translationBuilder, FFBlockTags.GIPPLE_FOOD);
        itemTag(translationBuilder, FFItemTags.GIPPLE_FOOD);
        blackTag(translationBuilder, FFBlockTags.JELLYROOT_PLANTABLES);
        blackTag(translationBuilder, FFBlockTags.JELLIES);


        translationBuilder.add("gamerule.doApplyJellyEffects", "Should apply jelly effects when eaten");

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/" + FalseFutures.MOD_ID + "/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }

    private void makeBannerStuff(TranslationBuilder translationBuilder, Item item, String name){
        translationBuilder.add(item, "Banner Pattern");
        translationBuilder.add(item.getTranslationKey() + ".desc", name);
        makeColoredBannerPatterns(translationBuilder, name);
    }


    private void makeColoredBannerPatterns(TranslationBuilder translationBuilder, String name){
        for (int i = 0; i < 16; i++){
            translationBuilder.add("block.minecraft.banner."  + FalseFutures.MOD_ID +  "." + name.toLowerCase() + "." + DyeColor.byId(i),
                    autoNameInner(DyeColor.byId(i).getName()) + " " + name);
        }
    }

    private void autoName(TranslationBuilder translationBuilder, Block block) {
        translationBuilder.add(block, autoNameInner(Registries.BLOCK.getId(block).getPath()));
    }
    private void autoName(TranslationBuilder translationBuilder, Item item) {
        translationBuilder.add(item, autoNameInner(Registries.ITEM.getId(item).getPath()));
    }
    private void autoName(TranslationBuilder translationBuilder, EntityType<?> entityType) {
        translationBuilder.add(entityType, autoNameInner(Registries.ENTITY_TYPE.getId(entityType).getPath()));
    }

    private void blackTag(TranslationBuilder translationBuilder, TagKey<?> tTagKey){
        nameTag(translationBuilder, tTagKey, "block");
    }
    private void itemTag(TranslationBuilder translationBuilder, TagKey<?> tTagKey){
        nameTag(translationBuilder, tTagKey, "item");
    }
    private void biomeTag(TranslationBuilder translationBuilder, TagKey<?> tTagKey){
        nameTag(translationBuilder, tTagKey, "biome");
    }

    private void nameTag(TranslationBuilder translationBuilder, TagKey<?> tTagKey, String registry){
        translationBuilder.add("tag." + registry + "." + tTagKey.id().getNamespace() + "." + tTagKey.id().getPath(), autoNameInner(tTagKey.id().getPath()));
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
