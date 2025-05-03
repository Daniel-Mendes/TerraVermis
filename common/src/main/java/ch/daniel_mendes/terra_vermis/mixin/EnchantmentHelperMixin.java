package ch.daniel_mendes.terra_vermis.mixin;

import ch.daniel_mendes.terra_vermis.registry.ItemsRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Unique
    private static final int EARTHWORM_FISHING_TIME_REDUCTION = 100;
    @Unique
    private static final int EARTHWORM_FISHING_LUCK_BONUS = 1;

    @Inject(method = "getFishingLuckBonus", at = @At("RETURN"), cancellable = true)
    private static void modifyFishingLuck(ServerLevel level, ItemStack stack, Entity entity, CallbackInfoReturnable<Integer> cir) {
        if (stack.is(ItemsRegistry.EARTHWORM_FISHING_ROD.get())) {
            cir.setReturnValue(cir.getReturnValueI() + EARTHWORM_FISHING_LUCK_BONUS);
        }
    }

    @Inject(method = "getFishingTimeReduction", at = @At("RETURN"), cancellable = true)
    private static void modifyFishingTimeReduction(ServerLevel level, ItemStack stack, Entity entity, CallbackInfoReturnable<Float> cir) {
        if (stack.is(ItemsRegistry.EARTHWORM_FISHING_ROD.get())) {
            float original = cir.getReturnValueF();
            float newReduction = original + (EARTHWORM_FISHING_TIME_REDUCTION / 20.0f); // Minecraft time is in seconds, 20 ticks = 1 sec
            cir.setReturnValue(newReduction);
        }
    }
}
