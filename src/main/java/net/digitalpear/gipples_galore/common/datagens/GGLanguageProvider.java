package net.digitalpear.gipples_galore.common.datagens;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.digitalpear.gipples_galore.init.GGBlocks;
import net.digitalpear.gipples_galore.init.GGEntityTypes;
import net.digitalpear.gipples_galore.init.GGItems;
import net.digitalpear.gipples_galore.init.GGJukeboxSongs;
import net.digitalpear.gipples_galore.init.tags.GGBiomeTags;
import net.digitalpear.gipples_galore.init.tags.GGBlockTags;
import net.digitalpear.gipples_galore.init.tags.GGItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class GGLanguageProvider extends FabricLanguageProvider {
    public GGLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

        for(Block jelly : GGBlocks.JELLY.keySet()) {
            autoName(translationBuilder, jelly);
        }

        translationBuilder.add(GGBlocks.GELATIN_BLOCK, "Block of Gelatin");
        autoName(translationBuilder, GGBlocks.GELATINOUS_GROWTH);

        autoName(translationBuilder, GGBlocks.GELATITE);
        autoName(translationBuilder, GGBlocks.GELATITE_STAIRS);
        autoName(translationBuilder, GGBlocks.GELATITE_SLAB);
        autoName(translationBuilder, GGBlocks.GELATITE_WALL);
        autoName(translationBuilder, GGBlocks.GELATITE_PRESSURE_PLATE);
        autoName(translationBuilder, GGBlocks.GELATITE_BUTTON);

        autoName(translationBuilder, GGBlocks.GELATITE_BRICKS);
        autoName(translationBuilder, GGBlocks.GELATITE_BRICK_STAIRS);
        autoName(translationBuilder, GGBlocks.GELATITE_BRICK_SLAB);
        autoName(translationBuilder, GGBlocks.GELATITE_BRICK_WALL);
        autoName(translationBuilder, GGBlocks.CHISELED_GELATITE_BRICKS);

        autoName(translationBuilder, GGBlocks.AMOEBALITH);
        autoName(translationBuilder, GGBlocks.AMOEBALITH_STAIRS);
        autoName(translationBuilder, GGBlocks.AMOEBALITH_SLAB);
        autoName(translationBuilder, GGBlocks.AMOEBALITH_WALL);
        autoName(translationBuilder, GGBlocks.AMOEBALITH_PRESSURE_PLATE);
        autoName(translationBuilder, GGBlocks.AMOEBALITH_BUTTON);

        autoName(translationBuilder, GGBlocks.AMOEBALITH_BRICKS);
        autoName(translationBuilder, GGBlocks.AMOEBALITH_BRICK_STAIRS);
        autoName(translationBuilder, GGBlocks.AMOEBALITH_BRICK_SLAB);
        autoName(translationBuilder, GGBlocks.AMOEBALITH_BRICK_WALL);
        autoName(translationBuilder, GGBlocks.CHISELED_AMOEBALITH_BRICKS);

        autoName(translationBuilder, GGBlocks.GELATIN_LAYER);

        autoName(translationBuilder, GGBlocks.GIPPLEPAD);

        autoName(translationBuilder, GGEntityTypes.GIPPLE);
        autoName(translationBuilder, GGEntityTypes.ANEUPLOIDIAN);

        autoName(translationBuilder, GGBlocks.HIBERNATING_GIPPLE);
        translationBuilder.add(GGBlocks.HIBERNATING_GIPPLE.getTranslationKey() + ".description", "Contains a hibernating gipple which can be woken up by breaking the block.");

        autoName(translationBuilder, GGItems.GELATIN);

        autoName(translationBuilder, GGItems.GAPPLE);

        translationBuilder.add(GGItems.GIPPLE_BUCKET, "Bucket of Gipple");

        translationBuilder.add(GGItems.GIPPLE_SPAWN_EGG, "Gipple Spawn Egg");
        translationBuilder.add(GGItems.ANEUPLOIDIAN_SPAWN_EGG, "Aneuploidian Spawn Egg");

        translationBuilder.add(GGItems.MUSIC_DISC_GIPPLECORE, "Music Disc");
        jukeboxSongTranslation(translationBuilder, GGJukeboxSongs.GIPPLECORE, "Axoladdy - gipplecore");

        makeBannerTranslation(translationBuilder, GGItems.GIPPLE_BANNER_PATTERN, "Gipple");

        translationBuilder.add("advancements.husbandry.jellies.title", "Sing a rainbow!");
        translationBuilder.add("advancements.husbandry.jellies.description", "Have one of all jelly flavours in your inventory.");

        translationBuilder.add("advancements.husbandry.affordable_housing.title", "Affordable Housing!");
        translationBuilder.add("advancements.husbandry.affordable_housing.description", "Have the Infested, Oozing and Gipple status effects applied at the same time.");


        translationBuilder.add("subtitles.gipples_galore.gipple.ambient", "Gipple vibrates");
        translationBuilder.add("subtitles.gipples_galore.gipple.hurt", "Gipple hurts");
        translationBuilder.add("subtitles.gipples_galore.gipple.burp", "Gipple consumes");
        translationBuilder.add("subtitles.gipples_galore.gipple.death", "Gipple dies");

        translationBuilder.add("subtitles.gipples_galore.aneuploidian.ambient", "Aneuploidian vibrates");
        translationBuilder.add("subtitles.gipples_galore.aneuploidian.hurt", "Aneuploidian hurts");
        translationBuilder.add("subtitles.gipples_galore.aneuploidian.death", "Aneuploidian dies");


        translationBuilder.add("death.attack.gippleEffect", "%s was gipplified");
        translationBuilder.add("death.attack.gippleEffect.player", "%s became a Gipple to get away from %s");


        translationBuilder.add("effect.gipples_galore.gipple", "Gipple");
        translationBuilder.add("effect.gipples_galore.gipple.description", "Will slowly turn the host into a gipple. Side effects may include auditory hallucinations.");


        translationBuilder.add("painting.gipples_galore.gipple.title", "Gipple");
        translationBuilder.add("painting.gipples_galore.gipple.author", "Yapettoshen");

        /*
            Tag translations for EMI compat
         */
        nameTag(translationBuilder, GGBiomeTags.EXTRA_GIPPLE_HABITATS);
        nameTag(translationBuilder, GGBlockTags.GELATINOUS_GROWTH_SUPPORTING);
        nameTag(translationBuilder, GGBlockTags.GIPPLE_FOOD);
        nameTag(translationBuilder, GGItemTags.GIPPLE_FOOD);
        nameTag(translationBuilder, GGBlockTags.JELLIES);


        translationBuilder.add("gamerule.doApplyJellyEffects", "Should apply jelly effects when eaten.");
        translationBuilder.add("gamerule.gippleMutationPercentage", "Percentage chance for gipples to mutate when splitting.");

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/" + GipplesGalore.MOD_ID + "/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }

    private void makeBannerTranslation(TranslationBuilder translationBuilder, Item item, String name){
        translationBuilder.add(item, "Banner Pattern");
        translationBuilder.add(item.getTranslationKey() + ".desc", name);
        makeColoredBannerPatterns(translationBuilder, name);
    }


    private void makeColoredBannerPatterns(TranslationBuilder translationBuilder, String name){
        for (int i = 0; i < 16; i++){
            translationBuilder.add("block.minecraft.banner."  + GipplesGalore.MOD_ID +  "." + name.toLowerCase() + "." + DyeColor.byId(i),
                    autoNameInner(DyeColor.byId(i).getName()) + " " + name);
        }
    }
    private static void jukeboxSongTranslation(TranslationBuilder translationBuilder, RegistryKey<JukeboxSong> song, String value){
        translationBuilder.add("jukebox_song." + song.getValue().toTranslationKey(), value);
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

    private void nameTag(TranslationBuilder translationBuilder, TagKey<?> tTagKey){
        translationBuilder.add(tTagKey, autoNameInner(tTagKey.id().getPath()));
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
