package ch.daniel_mendes.terra_vermis;

import ch.daniel_mendes.terra_vermis.registry.worldgen.ConfiguredFeatureRegistry;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TerraVermisDataGeneratorFabric implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        //pack.addProvider(ConfiguredFeatureRegistry::bootstrap);
    }
}
