package net.digitalpear.falsefutures.common.entities.aneuploidian;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class AneuploidianEntityModel extends DefaultedEntityGeoModel<AneuploidianEntity> {

    public AneuploidianEntityModel() {
        super(new Identifier(FalseFutures.MOD_ID, "aneuploidian"));
    }

    @Override
    public Identifier getAnimationResource(AneuploidianEntity entity) {
        return new Identifier(FalseFutures.MOD_ID, "animations/aneuploidian.animation.json");
    }

    @Override
    public Identifier getModelResource(AneuploidianEntity entity) {
        return new Identifier(FalseFutures.MOD_ID, "geo/aneuploidian.geo.json");
    }

    @Override
    public Identifier getTextureResource(AneuploidianEntity entity) {
        return new Identifier(FalseFutures.MOD_ID, "textures/entity/aneuploidian.png");
    }

}