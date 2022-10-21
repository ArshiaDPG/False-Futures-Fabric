package net.digitalpear.falsefutures.init.tags;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FFItemTags {
    public static final TagKey<Item> GIPPLE_FOOD = of("gipple_food");

    private static TagKey<Item> of(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(FalseFutures.MOD_ID, id));
    }
}
