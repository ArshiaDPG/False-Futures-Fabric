package net.digitalpear.falsefutures.init.tags;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFBlockTags {
    public static final TagKey<Block> JELLY = of("jelly");

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(FalseFutures.MOD_ID, id));
    }
}
