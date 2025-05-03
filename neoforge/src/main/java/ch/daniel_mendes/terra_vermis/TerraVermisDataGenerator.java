package ch.daniel_mendes.terra_vermis;

import ch.daniel_mendes.terra_vermis.worldgen.WorldGenProviderNeoForge;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class TerraVermisDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        try {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();

            generator.addProvider(true, new WorldGenProviderNeoForge(output, event.getLookupProvider()));

        } catch (RuntimeException e) {
            Constants.LOG.error("Failed to generate data", e);
        }
    }
}
