package ch.daniel_mendes.terra_vermis.mixin;

import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ComposterBlock.class)
public class ComposterBlockMixin {

    @Unique
    private static final int EARTHWORM_DROP_CHANCE = 5;

    @Inject(method = "extractProduce", at = @At("HEAD"))
    private static void dropEarthwormFarmChance(Entity entity, BlockState state, Level level, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        if (!level.isClientSide && level.random.nextInt(100) < EARTHWORM_DROP_CHANCE) {
            Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5).offsetRandom(level.random, 0.7F);
            ItemStack earthwormFarm = new ItemStack(BlocksRegistry.EARTHWORM_DIRT.get());
            ItemEntity drop = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(), earthwormFarm);
            drop.setDefaultPickUpDelay();
            level.addFreshEntity(drop);
        }
    }
}
