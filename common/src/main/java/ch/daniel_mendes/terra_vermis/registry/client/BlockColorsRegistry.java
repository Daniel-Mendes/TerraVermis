package ch.daniel_mendes.terra_vermis.registry.client;

import ch.daniel_mendes.terra_vermis.platform.Services;
import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import net.minecraft.client.renderer.BiomeColors;

public class BlockColorsRegistry {
    public static void init() {
        Services.CLIENT.registerBlockColor(
            BlocksRegistry.WORMY_GRASS_BLOCK.get(),
            (state, blockAndTintGetter, pos, tintIndex) -> BiomeColors.getAverageGrassColor(blockAndTintGetter, pos));
    }
}
