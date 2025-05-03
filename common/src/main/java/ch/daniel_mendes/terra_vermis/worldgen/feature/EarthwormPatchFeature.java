package ch.daniel_mendes.terra_vermis.worldgen.feature;

import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class EarthwormPatchFeature extends Feature<NoneFeatureConfiguration> {
    public EarthwormPatchFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int veinSize = 8 + random.nextInt(5);
        List<BlockPos> veinBlocks = new ArrayList<>();

        // Place EARTHWORM_DIRT blocks
        for (int i = 0; i < veinSize; i++) {
            BlockPos target = origin.offset(
                    random.nextInt(6) - 3,
                    random.nextInt(4) - 2,  // Random offset for y (vertical) up to 4 blocks below origin
                    random.nextInt(6) - 3
            );

            // Only replace dirt blocks with EARTHWORM_DIRT
            if (level.getBlockState(target).is(BlockTags.DIRT)) {
                level.setBlock(target, BlocksRegistry.EARTHWORM_DIRT.get().defaultBlockState(), 2);
                veinBlocks.add(target);
            }
        }

        // Place EARTHWORM_GRASS_BLOCK blocks on the surface (up to 3 blocks)
        for (int i = 0; i < random.nextInt(3) + 1; i++) {
            if (veinBlocks.isEmpty()) break;

            BlockPos veinBlock = veinBlocks.get(random.nextInt(veinBlocks.size()));
            BlockPos above = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, veinBlock);

            // Only place EARTHWORM_GRASS_BLOCK if the block above is a GRASS_BLOCK
            if (level.getBlockState(above).getBlock() == Blocks.GRASS_BLOCK) {
                level.setBlock(above, BlocksRegistry.EARTHWORM_GRASS_BLOCK.get().defaultBlockState(), 2);
            }
        }

        return true;
    }
}
