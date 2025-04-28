package ch.daniel_mendes.terra_vermis.event;

import ch.daniel_mendes.terra_vermis.WormDropLogic;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class FabricBlockBreakListener {
    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((level, player, blockPos, blockState, blockEntity) -> {
            WormDropLogic.dropWorms(level, blockPos, blockState, player);
        });
    }
}