package net.digitalpear.gipples_galore.init.tags;

import net.digitalpear.gipples_galore.GipplesGalore;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class GGItemTags {
    public static final TagKey<Item> GIPPLE_FOOD = of("gipple_food");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(GipplesGalore.MOD_ID, id));
    }

}
