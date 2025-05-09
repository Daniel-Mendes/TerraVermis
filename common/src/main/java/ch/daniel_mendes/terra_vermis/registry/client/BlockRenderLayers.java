package ch.daniel_mendes.terra_vermis.registry.client;

import ch.daniel_mendes.terra_vermis.platform.Services;
import ch.daniel_mendes.terra_vermis.registry.BlocksRegistry;
import net.minecraft.client.renderer.RenderType;

public class BlockRenderLayers {
    public static void init() {
        Services.CLIENT.registerRenderLayer(BlocksRegistry.WORMY_DIRT, RenderType.cutout());
        Services.CLIENT.registerRenderLayer(BlocksRegistry.WORMY_GRASS_BLOCK, RenderType.cutout());
    }
}
