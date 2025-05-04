package ch.daniel_mendes.terra_vermis.mixin;

import ch.daniel_mendes.terra_vermis.client.renderer.entity.state.ChickenRenderStateDuck;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChickenModel.class)
public abstract class ChickenModelMixin {
    @Shadow
    private ModelPart head;

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/ChickenRenderState;)V", at = @At("TAIL"))
    public void onSetupAnim(ChickenRenderState renderState, CallbackInfo ci) {
        ChickenRenderStateDuck renderStateDuck = (ChickenRenderStateDuck) renderState;

        this.head.y = this.head.y + renderStateDuck.terra_vermis$getHeadEatPositionScale() * renderState.ageScale;
        this.head.xRot = renderStateDuck.terra_vermis$getHeadEatAngleScale();
    }
}