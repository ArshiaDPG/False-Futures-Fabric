package net.digitalpear.gipples_galore.common.entities.gipple;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GippleEntityRenderer<T extends GippleEntity> extends GeoEntityRenderer<T> {

    public GippleEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GippleEntityModel<>());
    }

    @Override
    public void preRender(MatrixStack poseStack, T animatable, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        if (animatable.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
            this.shadowRadius *= 0.6F;

        } else if (animatable.isLuminous()) {
            poseStack.scale(1.3F, 1.3F, 1.3F);
            this.shadowRadius *= 1.3F;
        }
    }


    @Override
    public void actuallyRender(MatrixStack poseStack, T animatable, BakedGeoModel model, @Nullable RenderLayer renderType, VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        poseStack.push();
        if (animatable.isLuminous()) {
            packedLight = 255;
            packedOverlay = OverlayTexture.DEFAULT_UV;
            colour = 16777215;
        }
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        poseStack.pop();
    }
}