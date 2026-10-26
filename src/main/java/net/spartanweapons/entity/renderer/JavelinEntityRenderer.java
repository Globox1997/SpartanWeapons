package net.spartanweapons.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.spartanweapons.entity.JavelinEntity;

@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public class JavelinEntityRenderer extends EntityRenderer<JavelinEntity> {

    private final ItemRenderer itemRenderer;

    public JavelinEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(JavelinEntity javelinEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        matrixStack.pushPose();

        matrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(g, javelinEntity.yRotO, javelinEntity.getYRot()) - 90.0F));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(g, javelinEntity.xRotO, javelinEntity.getXRot()) + -45.0F));

        matrixStack.translate(-0.3D, -0.15D, 0.0D);
        matrixStack.scale(1.47f,1.47f,0.85f);
        // Use GUI type cause it isn't used for this type
        this.itemRenderer.renderStatic(javelinEntity.getItem(), ItemDisplayContext.GUI, i, OverlayTexture.NO_OVERLAY, matrixStack, vertexConsumerProvider, javelinEntity.level(),
                javelinEntity.getId());
        matrixStack.popPose();
        super.render(javelinEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTextureLocation(JavelinEntity javelinEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

}