package net.digitalpear.falsefutures.common.entities.aneuploidian;

import net.digitalpear.falsefutures.FalseFutures;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;

public class SomethingEntityModel extends AnimatedTickingGeoModel<SomethingEntity> {
    @Override
    public Identifier getAnimationResource(SomethingEntity entity) {
        return new Identifier(FalseFutures.MOD_ID, "animations/something.animation.json");
    }

    @Override
    public Identifier getModelResource(SomethingEntity entity) {
        return new Identifier(FalseFutures.MOD_ID, "geo/something.geo.json");
    }

    @Override
    public Identifier getTextureResource(SomethingEntity entity) {
        return new Identifier(FalseFutures.MOD_ID, "textures/entity/something.png");
    }

}