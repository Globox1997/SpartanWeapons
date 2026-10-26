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
import net.spartanweapons.entity.KunaiEntity;

@SuppressWarnings("deprecation")
@Environment(EnvType.CLIENT)
public class KunaiEntityRenderer extends EntityRenderer<KunaiEntity> {

    private final ItemRenderer itemRenderer;

    public KunaiEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(KunaiEntity kunaiEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        matrixStack.pushPose();

        matrixStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(g, kunaiEntity.yRotO, kunaiEntity.getYRot()) - 90.0F));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(g, kunaiEntity.xRotO, kunaiEntity.getXRot()) + -45.0F));

        matrixStack.translate(-0.3D, -0.15D, 0.0D);
        matrixStack.scale(0.85f,0.85f,0.85f);
        // Use GUI type cause it isn't used for this type
        this.itemRenderer.renderStatic(kunaiEntity.getItem(), ItemDisplayContext.GUI, i, OverlayTexture.NO_OVERLAY, matrixStack, vertexConsumerProvider, kunaiEntity.level(),
                kunaiEntity.getId());
        matrixStack.popPose();
        super.render(kunaiEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTextureLocation(KunaiEntity kunaiEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

}