package net.digitalpear.falsefutures.init.tags;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class FFBlockTags {

    public static final TagKey<Block> JELLIES = of("jellies");
    public static final TagKey<Block> GIPPLE_FOOD = of("gipple_food");
    public static final TagKey<Block> JELLYROOT_PLANTABLES = of("jellyroot_plantables");

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(FalseFutures.MOD_ID, id));
    }


    public static void makeBlockTagList(TagKey<Block> tagKey, List<Block> list){
        Registry.BLOCK.forEach(block -> {
            if (block.getDefaultState().isIn(tagKey)){
                list.add(block);
            }
        });
    }

}
