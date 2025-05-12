package ch.daniel_mendes.terra_vermis.mixin;

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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHookRenderer.class)
public class FishingHookRendererMixin {
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/item/earthworm.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutout(TEXTURE_LOCATION);

    @Inject(method = "render(Lnet/minecraft/client/renderer/entity/state/FishingHookRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    private void renderBait(FishingHookRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null) return;

        Item heldItem = player.getMainHandItem().getItem();

        if (heldItem == ItemsRegistry.FISHING_ROD_WITH_BAIT.get()) {
            ItemStack bait = ItemsRegistry.EARTHWORM.get().getDefaultInstance();

            if (bait != null) {
                // Render the bait item
                poseStack.pushPose();
                poseStack.translate(0, 0, 0); // Adjust the position as needed
                VertexConsumer vertexConsumer = bufferSource.getBuffer(RENDER_TYPE);
                //ItemRenderer.renderItem(bait, ItemDisplayContext.FIXED, combinedLight, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, 0);
                poseStack.popPose();
            }
        }
    }
}
