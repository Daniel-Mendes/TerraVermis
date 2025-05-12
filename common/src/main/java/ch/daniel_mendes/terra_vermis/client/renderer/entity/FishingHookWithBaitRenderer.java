package ch.daniel_mendes.terra_vermis.client.renderer.entity;

import ch.daniel_mendes.terra_vermis.registry.ItemsRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.entity.state.FishingHookRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;

public class FishingHookWithBaitRenderer extends FishingHookRenderer {
    public FishingHookWithBaitRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(FishingHookRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {



        super.render(renderState, poseStack, bufferSource, packedLight);
    }
}
