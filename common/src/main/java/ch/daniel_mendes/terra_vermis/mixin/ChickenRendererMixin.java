package ch.daniel_mendes.terra_vermis.mixin;

import ch.daniel_mendes.terra_vermis.client.renderer.entity.state.ChickenRenderStateDuck;
import ch.daniel_mendes.terra_vermis.entity.animal.ChickenDuck;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import net.minecraft.world.entity.animal.Chicken;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChickenRenderer.class)
public class ChickenRendererMixin {

    @Inject(method = "extractRenderState*", at = @At("TAIL"))
    public void onExtractRenderState(Chicken chicken, ChickenRenderState state, float partialTicks, CallbackInfo ci) {
        ChickenRenderStateDuck stateDuck = (ChickenRenderStateDuck) state;
        ChickenDuck chickenDuck = (ChickenDuck) chicken;

        stateDuck.terra_vermis$setHeadEatPositionScale(chickenDuck.terra_vermis$getHeadEatPositionScale(partialTicks));
        stateDuck.terra_vermis$setHeadEatAngleScale(chickenDuck.terra_vermis$getHeadEatAngleScale(partialTicks));
    }
}
