package net.digitalpear.falsefutures.common.entities.gipple;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GippleEntityRenderer<T extends GippleEntity> extends GeoEntityRenderer<T> {

    public GippleEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GippleEntityModel<>());
    }

    @Override
    public void preRender(MatrixStack poseStack, T animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);


        if (animatable.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
            this.shadowRadius *= 0.6F;

        } else if (animatable.isLuminous()) {
            poseStack.scale(1.3F, 1.3F, 1.3F);
            this.shadowRadius *= 1.3F;
        }
    }

    @Override
    public void actuallyRender(MatrixStack poseStack, T animatable, BakedGeoModel model, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.push();
        if (animatable.isLuminous()) {
            packedLight = 255;
            packedOverlay = OverlayTexture.DEFAULT_UV;
            red = 1;
            green = 1;
            blue = 1;
            alpha = 1;
        }
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        poseStack.pop();
    }
}