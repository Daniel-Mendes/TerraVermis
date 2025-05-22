package ch.daniel_mendes.terra_vermis.mixin.fabric;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CropBlock.class)
public interface CropBlockAccessorFabric {
    @Invoker("getGrowthSpeed")
    static float callGetGrowthSpeed(Block block, BlockGetter level, BlockPos pos) {
        throw new AssertionError(); // Mixin replaces this at runtime
    }
}
