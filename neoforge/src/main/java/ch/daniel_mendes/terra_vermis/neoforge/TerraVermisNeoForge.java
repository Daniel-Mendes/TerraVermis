package ch.daniel_mendes.terra_vermis.neoforge;


import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.TerraVermisCommon;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class TerraVermisNeoForge {

    public TerraVermisNeoForge(IEventBus eventBus) {
        TerraVermisCommon.init();
    }
}