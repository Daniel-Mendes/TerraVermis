package ch.daniel_mendes.terra_vermis.registry.client;

import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;

public class BlockColorsRegistry {
    public static void init() {
        ColorProviderRegistry.BLOCK.register(
                (state, blockAndTintGetter, pos, tintIndex) -> {
                    if(blockAndTintGetter != null && pos != null) {
                        return BiomeColors.getAverageGrassColor(blockAndTintGetter, pos);
                    }

                    return -1;
                },
                BlocksRegistry.EARTHWORM_GRASS_BLOCK.get()
        );
    }
}