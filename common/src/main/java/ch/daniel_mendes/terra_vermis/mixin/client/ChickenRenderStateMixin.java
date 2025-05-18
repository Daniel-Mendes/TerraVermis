package ch.daniel_mendes.terra_vermis.mixin.client;

import ch.daniel_mendes.terra_vermis.client.renderer.entity.state.ChickenRenderStateDuck;
import net.minecraft.client.renderer.entity.state.ChickenRenderState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChickenRenderState.class)
public class ChickenRenderStateMixin implements ChickenRenderStateDuck {
    private float headEatPositionScale;
    private float headEatAngleScale;

    @Override
    public float terra_vermis$getHeadEatPositionScale() {
        return headEatPositionScale;
    }

    @Override
    public void terra_vermis$setHeadEatPositionScale(float value) {
        this.headEatPositionScale = value;
    }

    @Override
    public float terra_vermis$getHeadEatAngleScale() {
        return headEatAngleScale;
    }

    @Override
    public void terra_vermis$setHeadEatAngleScale(float value) {
        this.headEatAngleScale = value;
    }
}
