package ch.daniel_mendes.terra_vermis.worldgen.feature;

import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import ch.daniel_mendes.terra_vermis.registry.TagsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class EarthwormPatchFeature extends Feature<NoneFeatureConfiguration> {
    private static final TagKey<Biome> HAS_WORMY_MYCELIUM = TagsRegistry.BiomeTags.HAS_WORMY_MYCELIUM;
    private static final TagKey<Biome> HAS_WORMY_PODZOL = TagsRegistry.BiomeTags.HAS_WORMY_PODZOL;

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

        for (int i = 0; i < veinSize; i++) {
            BlockPos target = origin.offset(
                    random.nextInt(6) - 3,
                    random.nextInt(4) - 2,  // Random offset for y (vertical) up to 4 blocks below origin
                    random.nextInt(6) - 3
            );

            if (level.getBlockState(target).is(BlockTags.DIRT)) {
                level.setBlock(target, BlocksRegistry.WORMY_DIRT.get().defaultBlockState(), 2);
                veinBlocks.add(target);
            }
        }

        for (int i = 0; i < random.nextInt(3) + 1; i++) {
            if (veinBlocks.isEmpty()) break;

            BlockPos veinBlock = veinBlocks.get(random.nextInt(veinBlocks.size()));
            BlockPos above = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, veinBlock);
            Block targetBlock = level.getBlockState(above).getBlock();
            ResourceKey<Biome> currentBiome = level.getBiome(origin).unwrapKey().orElse(null);

            if (currentBiome == null) continue;

            if (currentBiome.isFor(HAS_WORMY_MYCELIUM.registry())) {
                if (targetBlock == Blocks.MYCELIUM) {
                    level.setBlock(above, BlocksRegistry.WORMY_MYCELIUM.get().defaultBlockState(), 2);
                }
            } else if (currentBiome.isFor(HAS_WORMY_PODZOL.registry())) {
                if (targetBlock == Blocks.PODZOL) {
                    level.setBlock(above, BlocksRegistry.WORMY_PODZOL.get().defaultBlockState(), 2);
                }
            } else {
                if (targetBlock == Blocks.GRASS_BLOCK) {
                    level.setBlock(above, BlocksRegistry.WORMY_GRASS_BLOCK.get().defaultBlockState(), 2);
                }
            }
        }

        return true;
    }
}
