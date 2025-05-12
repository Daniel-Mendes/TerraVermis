package ch.daniel_mendes.terra_vermis.registry.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class BiomeModificationRegistry {
    public static void register() {
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.VEGETAL_DECORATION,
            PlacedFeatureRegistry.createPlacedFeatureKey("disk_earthworm")
        );
    }
}
