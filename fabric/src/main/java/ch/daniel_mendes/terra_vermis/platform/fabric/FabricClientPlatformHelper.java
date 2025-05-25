package ch.daniel_mendes.terra_vermis.platform.fabric;

import ch.daniel_mendes.terra_vermis.platform.services.IClientPlatformHelper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class FabricClientPlatformHelper implements IClientPlatformHelper {
    @Override
    public <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
        EntityRendererRegistry.register(type.get(), renderProvider);
    }

    @Override
    public <T extends Block> void registerRenderLayer(Supplier<T> block, RenderType type) {
        BlockRenderLayerMap.INSTANCE.putBlock(block.get(), type);
    }

    @Override
    public void registerBlockColor(Block block, BlockColorProvider colorProvider) {
        ColorProviderRegistry.BLOCK.register(
                colorProvider::getColor, block);
    }
}
