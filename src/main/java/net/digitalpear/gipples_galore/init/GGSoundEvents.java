package net.digitalpear.gipples_galore.init;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class GGSoundEvents {
    public static final SoundEvent ENTITY_GIPPLE_AMBIENT = register("entity.gipple.ambient");
    public static final SoundEvent ENTITY_GIPPLE_HURT = register("entity.gipple.hurt");
    public static final SoundEvent ENTITY_GIPPLE_DEATH = register("entity.gipple.death");
    public static final SoundEvent ENTITY_GIPPLE_BURP = register("entity.gipple.burp");
    public static final SoundEvent MUSIC_DISC_GIPPLECORE = register("music.record.gipplecore");

    public static final SoundEvent BLOCK_GELATITE_BREAK = register("block.gelatite.break");
    public static final SoundEvent BLOCK_GELATITE_STEP = register("block.gelatite.step");
    public static final SoundEvent BLOCK_GELATITE_PLACE = register("block.gelatite.place");
    public static final SoundEvent BLOCK_GELATITE_HIT = register("block.gelatite.hit");
    public static final SoundEvent BLOCK_GELATITE_FALL = register("block.gelatite.fall");

    public static final SoundEvent BLOCK_AMOEBALITH_BREAK = register("block.amoebalith.break");
    public static final SoundEvent BLOCK_AMOEBALITH_STEP = register("block.amoebalith.step");
    public static final SoundEvent BLOCK_AMOEBALITH_PLACE = register("block.amoebalith.place");
    public static final SoundEvent BLOCK_AMOEBALITH_HIT = register("block.amoebalith.hit");
    public static final SoundEvent BLOCK_AMOEBALITH_FALL = register("block.amoebalith.fall");

    private static SoundEvent register(String id) {
        return Registry.register(Registries.SOUND_EVENT, new Identifier(GipplesGalore.MOD_ID + ":" + id), SoundEvent.of(new Identifier(GipplesGalore.MOD_ID + ":" + id)));
    }

    public static void init(){

    }
}
