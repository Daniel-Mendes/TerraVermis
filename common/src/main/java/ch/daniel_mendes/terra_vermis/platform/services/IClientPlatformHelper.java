package ch.daniel_mendes.terra_vermis.platform.services;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface IClientPlatformHelper {

    <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider);

    <T extends Block> void registerRenderLayer(Supplier<T> block, RenderType type);

    void registerBlockColor(Block block, BlockColorProvider colorProvider);

    interface BlockColorProvider {
        int getColor(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockAndTintGetter getter, net.minecraft.core.BlockPos pos, int tintIndex);
    }
}
