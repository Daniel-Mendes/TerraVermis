package ch.daniel_mendes.terra_vermis.mixin.block;

import ch.daniel_mendes.terra_vermis.block.WormyFarmBlock;
import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public class CropBlockMixin {
    @Unique
    private static final float FASTER_GROWTH_RATE = 5.0f;

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        CropBlock cropBlock = (CropBlock) (Object) this;

        if (level.getBlockState(pos.below()).getBlock() instanceof WormyFarmBlock) {
            int age = cropBlock.getAge(state);

            if (age < cropBlock.getMaxAge()) {
                float growthSpeed = CropBlockAccessor.callGetGrowthSpeed(cropBlock, level, pos);
                growthSpeed *= FASTER_GROWTH_RATE;

                if (random.nextInt((int) (25 / growthSpeed) + 1) == 0) {
                    level.setBlock(pos, cropBlock.getStateForAge(age + 1), 2);
                }
            }

            // Cancel original vanilla tick logic
            ci.cancel();
        }
    }

    @Inject(method = "mayPlaceOn", at = @At("HEAD"), cancellable = true)
    protected void mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(BlocksRegistry.WORMY_FARMLAND.get())) {
            cir.setReturnValue(true);
        }
    }
}
