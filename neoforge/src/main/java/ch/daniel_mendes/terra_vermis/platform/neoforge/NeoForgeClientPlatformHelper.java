package ch.daniel_mendes.terra_vermis.platform.neoforge;

import ch.daniel_mendes.terra_vermis.platform.services.IClientPlatformHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class NeoForgeClientPlatformHelper implements IClientPlatformHelper {
    private static final List<BlockColorRegistration> pendingBlockColors = new ArrayList<>();

    private static RegisterColorHandlersEvent.Block colorEvent = null;

    @Override
    public <T extends Entity> void registerEntityRenderer(Supplier<EntityType<T>> type, EntityRendererProvider<T> renderProvider) {
        EntityRenderers.register(type.get(), renderProvider);
    }

    @Override
    public <T extends Block> void registerRenderLayer(Supplier<T> block, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(block.get(), type);
    }

    @Override
    public void registerBlockColor(Block block, BlockColorProvider colorProvider) {
        if (colorEvent == null) {
            // Defer until the event is available
            pendingBlockColors.add(new BlockColorRegistration(block, colorProvider));
        } else {
            colorEvent.register(
                colorProvider::getColor,
                block
            );
        }
    }

    public static void injectBlockColors(RegisterColorHandlersEvent.Block event) {
        colorEvent = event;

        for (BlockColorRegistration reg : pendingBlockColors) {
            event.register(
                reg.colorProvider::getColor,
                reg.block
            );
        }

        pendingBlockColors.clear();
    }

    private record BlockColorRegistration(Block block, BlockColorProvider colorProvider) {}
}
