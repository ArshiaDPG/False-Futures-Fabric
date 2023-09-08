package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class FFSoundEvents {
    public static final SoundEvent ENTITY_GIPPLE_AMBIENT = register("entity.falsefutures.gipple_ambient");
    public static final SoundEvent ENTITY_GIPPLE_HURT = register("entity.falsefutures.gipple_hurt");
    public static final SoundEvent ENTITY_GIPPLE_DEATH = register("entity.falsefutures.gipple_death");
    public static final SoundEvent ENTITY_GIPPLE_BURP = register("entity.falsefutures.gipple_burp");
    public static final SoundEvent MUSIC_DISC_GIPPLECORE = register("music.record.gipplecore");

    private static SoundEvent register(String id) {
        return Registry.register(Registries.SOUND_EVENT, new Identifier(FalseFutures.MOD_ID + ":" + id), SoundEvent.of(new Identifier(FalseFutures.MOD_ID + ":" + id)));
    }

    public static void init(){

    }
}
