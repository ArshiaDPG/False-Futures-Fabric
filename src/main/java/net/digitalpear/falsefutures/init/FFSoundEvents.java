package net.digitalpear.falsefutures.init;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFSoundEvents {
    public static final SoundEvent ENTITY_GIPPLE_AMBIENT = register("entity.falsefutures.gipple_ambient");
    public static final SoundEvent ENTITY_GIPPLE_HURT = register("entity.falsefutures.gipple_hurt");
    public static final SoundEvent ENTITY_GIPPLE_DEATH = register("entity.falsefutures.gipple_death");

    private static SoundEvent register(String id) {
        return Registry.register(Registry.SOUND_EVENT, FalseFutures.MOD_ID + ":" + id, new SoundEvent(new Identifier(id)));
    }

    public static void init(){

    }
}
