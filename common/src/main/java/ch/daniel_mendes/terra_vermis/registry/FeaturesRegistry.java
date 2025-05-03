package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.RegistrationProvider;
import ch.daniel_mendes.terra_vermis.RegistryObject;
import ch.daniel_mendes.terra_vermis.worldgen.feature.EarthwormPatchFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FeaturesRegistry {

    public static void init() {}

    public static final RegistrationProvider<Feature<?>> FEATURES = RegistrationProvider.get(BuiltInRegistries.FEATURE, Constants.MOD_ID);

    public static final RegistryObject<Feature<?>, Feature<NoneFeatureConfiguration>> PATCH_EARTHWORM  = FEATURES.register("patch_earthworm", EarthwormPatchFeature::new);
}