package ch.daniel_mendes.terra_vermis.registry.worldgen;

import ch.daniel_mendes.terra_vermis.Constants;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PlacedFeatureRegistry {
    public static final ResourceKey<PlacedFeature> PATCH_EARTHWORM_PLACED_KEY = createPlacedFeatureKey("patch_earthworm");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, PATCH_EARTHWORM_PLACED_KEY,
                configuredFeatures.getOrThrow(ConfiguredFeatureRegistry.PATCH_EARTHWORM_CONFIGURED_KEY),
                List.of(
                    CountPlacement.of(6),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(1), VerticalAnchor.aboveBottom(5)),
                    BiomeFilter.biome()
                ));
    }

    public static ResourceKey<PlacedFeature> createPlacedFeatureKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context,
                                 ResourceKey<PlacedFeature> key,
                                Holder<ConfiguredFeature<?, ?>> config,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
