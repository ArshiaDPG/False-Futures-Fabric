package net.digitalpear.falsefutures.init.tags;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;

public class FFItemTags {
    public static final TagKey<Item> GIPPLE_FOOD = of("gipple_food");

    private static TagKey<Item> of(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(FalseFutures.MOD_ID, id));
    }

    public static void makeItemTagList(TagKey<Item> tagKey, List<Item> list){
        Registry.ITEM.forEach(item -> {
            if (new ItemStack(item).isIn(tagKey)){
                list.add(item);
            }
        });
    }
}
