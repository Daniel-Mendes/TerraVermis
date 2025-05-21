package ch.daniel_mendes.terra_vermis.neoforge;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.TerraVermisCommon;
import ch.daniel_mendes.terra_vermis.platform.neoforge.NeoForgeCommonPlatformHelper;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class TerraVermisNeoForge {

    public TerraVermisNeoForge(IEventBus bus) {
        NeoForgeCommonPlatformHelper.ITEMS.register(bus);
        NeoForgeCommonPlatformHelper.BLOCKS.register(bus);
        NeoForgeCommonPlatformHelper.CREATIVE_MOD_TAB.register(bus);

        TerraVermisCommon.init();
    }
}