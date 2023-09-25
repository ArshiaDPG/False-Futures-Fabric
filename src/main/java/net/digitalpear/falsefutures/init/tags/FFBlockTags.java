package net.digitalpear.falsefutures.init.tags;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FFBlockTags {

    public static final TagKey<Block> JELLIES = of("jellies");
    public static final TagKey<Block> GIPPLE_FOOD = of("gipple_food");
    public static final TagKey<Block> GIPPLE_COLONY_REPLACEABLE = of("gipple_colony_replaceable");
    public static final TagKey<Block> GELATINOUS_GROWTH_SUPPORTING = of("gelatinous_growth_supporting");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(FalseFutures.MOD_ID, id));
    }


}
