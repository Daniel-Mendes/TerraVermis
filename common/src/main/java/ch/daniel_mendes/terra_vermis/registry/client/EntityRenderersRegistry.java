package ch.daniel_mendes.terra_vermis.registry.client;

import ch.daniel_mendes.terra_vermis.client.renderer.entity.FishingHookWithBaitRenderer;
import ch.daniel_mendes.terra_vermis.platform.Services;
import ch.daniel_mendes.terra_vermis.registry.EntityTypesRegistry;

public class EntityRenderersRegistry {
    public static void init() {
        Services.CLIENT.registerEntityRenderer(EntityTypesRegistry.FISHING_BOBBER_WITH_BAIT, FishingHookWithBaitRenderer::new);
    }
}
