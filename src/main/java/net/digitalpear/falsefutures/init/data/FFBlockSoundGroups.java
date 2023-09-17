package net.digitalpear.falsefutures.init.data;

import net.digitalpear.falsefutures.init.FFSoundEvents;
import net.minecraft.sound.BlockSoundGroup;

public class FFBlockSoundGroups {

    public static final BlockSoundGroup AMOEBALITH = new BlockSoundGroup(1.0F, 1.0F,
            FFSoundEvents.BLOCK_AMOEBALITH_BREAK,
            FFSoundEvents.BLOCK_AMOEBALITH_STEP,
            FFSoundEvents.BLOCK_AMOEBALITH_PLACE,
            FFSoundEvents.BLOCK_AMOEBALITH_HIT,
            FFSoundEvents.BLOCK_AMOEBALITH_FALL);

    public static final BlockSoundGroup GELATITE = new BlockSoundGroup(1.0F, 1.0F,
            FFSoundEvents.BLOCK_GELATITE_BREAK,
            FFSoundEvents.BLOCK_GELATITE_STEP,
            FFSoundEvents.BLOCK_GELATITE_PLACE,
            FFSoundEvents.BLOCK_GELATITE_HIT,
            FFSoundEvents.BLOCK_GELATITE_FALL);

}
