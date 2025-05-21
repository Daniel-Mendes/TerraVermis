package ch.daniel_mendes.terra_vermis.registry.worldgen;

import ch.daniel_mendes.terra_vermis.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.*;

public class PlacedFeatureRegistry {
    public static ResourceKey<PlacedFeature> createPlacedFeatureKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Constants.id(name));
    }
}
