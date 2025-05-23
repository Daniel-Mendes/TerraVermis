package ch.daniel_mendes.terra_vermis.registry;

import ch.daniel_mendes.terra_vermis.Constants;
import ch.daniel_mendes.terra_vermis.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class CreativeModeTabsRegistry {
    public static void init() {}

    public static final Component MAIN_TITLE = Component.translatable("itemGroup." + Constants.MOD_ID + ".main");

    public static final Supplier<CreativeModeTab> TERRA_VERMIS_TAB = Services.COMMON.registerCreativeModeTab(Constants.MOD_ID, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(MAIN_TITLE)
            .icon(() -> new ItemStack(ItemsRegistry.EARTHWORM.get()))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ItemsRegistry.EARTHWORM.get());
                output.accept(ItemsRegistry.FISHING_ROD_WITH_BAIT.get());
                output.accept(ItemsRegistry.EARTHWORM_STEW.get());
                output.accept(BlocksRegistry.WORMY_DIRT.get());
                output.accept(BlocksRegistry.WORMY_GRASS_BLOCK.get());
                output.accept(BlocksRegistry.WORMY_FARMLAND.get());
                output.accept(BlocksRegistry.WORMY_PODZOL.get());
                output.accept(BlocksRegistry.WORMY_MYCELIUM.get());
            })
            .build());
}
