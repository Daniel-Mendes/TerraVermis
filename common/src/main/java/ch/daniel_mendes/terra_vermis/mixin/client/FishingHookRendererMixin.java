package ch.daniel_mendes.terra_vermis.mixin.client;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.registry.ItemsRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.entity.state.FishingHookRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHookRenderer.class)
public class FishingHookRendererMixin {
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/fishing_hook_with_bait.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutout(TEXTURE_LOCATION);

    @Inject(method = "render(Lnet/minecraft/client/renderer/entity/state/FishingHookRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    private void renderBait(FishingHookRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null) return;

        Item heldItem = player.getMainHandItem().getItem();

        if (heldItem == ItemsRegistry.FISHING_ROD_WITH_BAIT.get()) {
            // Render the bait item
            poseStack.pushPose();
            poseStack.translate(0, 0, 0); // Adjust the position as needed
            VertexConsumer vertexConsumer = bufferSource.getBuffer(RENDER_TYPE);
            vertex(vertexConsumer, poseStack.last(), combinedLight, 0.0F, 0, 0, 1);
            poseStack.popPose();
        }
    }

    private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, int packedLight, float x, int y, int u, int v) {
        consumer.addVertex(pose, x - 0.5F, y - 0.5F, 0.0F)
                .setColor(-1)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}
