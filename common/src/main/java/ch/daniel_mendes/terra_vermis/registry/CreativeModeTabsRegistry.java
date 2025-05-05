package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.RegistrationProvider;
import ch.daniel_mendes.terra_vermis.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeModeTabsRegistry {
    public static void init() {}

    public static final Component MAIN_TITLE = Component.translatable("itemGroup." + Constants.MOD_ID + ".main");

    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static final RegistryObject<CreativeModeTab, CreativeModeTab> TERRA_VERMIS_TAB = CREATIVE_MODE_TABS.register(Constants.MOD_ID, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(MAIN_TITLE)
            .icon(() -> new ItemStack(ItemsRegistry.EARTHWORM.get()))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ItemsRegistry.EARTHWORM.get());
                output.accept(ItemsRegistry.EARTHWORM_FISHING_ROD.get());
                output.accept(BlocksRegistry.EARTHWORM_DIRT.get());
                output.accept(BlocksRegistry.EARTHWORM_GRASS_BLOCK.get());
                output.accept(BlocksRegistry.EARTHWORM_FARMLAND.get());
            })
            .build());
}
