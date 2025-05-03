package ch.daniel_mendes.terra_vermis.registry.worldgen;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.registry.FeaturesRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ConfiguredFeatureRegistry {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_EARTHWORM_CONFIGURED_KEY = createConfiguredFeatureKey("patch_earthworm");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        register(context, PATCH_EARTHWORM_CONFIGURED_KEY, FeaturesRegistry.PATCH_EARTHWORM.get(), NoneFeatureConfiguration.INSTANCE);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeatureKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key,
                                                                                          F feature,
                                                                                          FC featureConfig) {
        context.register(key, new ConfiguredFeature<>(feature, featureConfig));
    }
}
