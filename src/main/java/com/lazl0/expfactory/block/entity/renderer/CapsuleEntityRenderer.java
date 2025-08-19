package com.lazl0.expfactory.block.entity.renderer;

import com.lazl0.expfactory.block.entity.CapsuleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class CapsuleEntityRenderer implements BlockEntityRenderer<CapsuleEntity> {
    public CapsuleEntityRenderer(BlockEntityRendererProvider.Context context){

    }

    @Override
    public void render(CapsuleEntity capsuleEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = capsuleEntity.inventory.getStackInSlot(0);
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.36f, 0.5f);
        poseStack.scale(0.25f, 0.25f, 0.25f);
        //This tells it to rotate around the Y = y-axis, P = positive
        poseStack.mulPose(Axis.YP.rotationDegrees(capsuleEntity.getRenderRotation()));

        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(capsuleEntity.getLevel(), capsuleEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, poseStack, bufferSource, capsuleEntity.getLevel(), 1);
        poseStack.popPose();
    }
    private int getLightLevel(Level level, BlockPos blockPos){
        int blockLight = level.getBrightness(LightLayer.BLOCK, blockPos);
        int skyLight = level.getBrightness(LightLayer.SKY, blockPos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
