package ch.daniel_mendes.terra_vermis.platform.services;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface IClientPlatformHelper {

    <T extends Block> void registerRenderLayer(Supplier<T> block, RenderType type);

    void registerBlockColor(Block block, BlockColorProvider colorProvider);

    interface BlockColorProvider {
        int getColor(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockAndTintGetter getter, net.minecraft.core.BlockPos pos, int tintIndex);
    }
}
