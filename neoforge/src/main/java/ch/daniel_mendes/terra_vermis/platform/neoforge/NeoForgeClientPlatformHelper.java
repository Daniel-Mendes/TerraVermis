package ch.daniel_mendes.terra_vermis.platform.neoforge;

import ch.daniel_mendes.terra_vermis.platform.services.IClientPlatformHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class NeoForgeClientPlatformHelper implements IClientPlatformHelper {
    @Override
    public <T extends Block> void registerRenderLayer(Supplier<T> block, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), type);
    }

    @Override
    public void registerBlockColor(Block block, BlockColorProvider colorProvider) {

    }
}
