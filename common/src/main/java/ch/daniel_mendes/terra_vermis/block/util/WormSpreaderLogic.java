package ch.daniel_mendes.terra_vermis.block.util;

import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class WormSpreaderLogic {

    public static void trySpreadingWormGrass(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        for (BlockPos adjacentPos : BlockPos.betweenClosed(pos.offset(-1, 0, -1), pos.offset(1, 0, 1))) {
            if (!adjacentPos.equals(pos)) {  // Avoid checking the block itself
                BlockState adjacentState = level.getBlockState(adjacentPos);
                if (adjacentState.is(BlocksRegistry.WORMY_DIRT.get()) && canPropagate(state, level, adjacentPos)) {
                    // Replace earthworm dirt with earthworm grass block
                    level.setBlockAndUpdate(adjacentPos, BlocksRegistry.WORMY_GRASS_BLOCK.get().defaultBlockState());
                }
            }
        }
    }

    private static boolean canBeEarthwormGrass(BlockState state, LevelReader levelReader, BlockPos pos) {
        BlockPos blockAbove = pos.above();
        BlockState aboveState = levelReader.getBlockState(blockAbove);

        // Check if the block above is air or a suitable block for grass growth
        return aboveState.isAir() || aboveState.is(Blocks.GRASS_BLOCK);
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeEarthwormGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }
}
