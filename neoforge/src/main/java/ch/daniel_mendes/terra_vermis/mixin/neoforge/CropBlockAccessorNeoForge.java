package ch.daniel_mendes.terra_vermis.mixin.neoforge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CropBlock.class)
public interface CropBlockAccessorNeoForge {
    @Invoker("getGrowthSpeed")
    static float callGetGrowthSpeed(BlockState state, BlockGetter level, BlockPos pos) {
        throw new AssertionError(); // Mixin replaces this at runtime
    }
}
