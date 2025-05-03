package ch.daniel_mendes.terra_vermis.mixin;

import ch.daniel_mendes.terra_vermis.registry.ItemsRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingHook.class)
public class FishingHookMixin {
    @Inject(method = "shouldStopFishing", at = @At("HEAD"), cancellable = true)
    private void modifyShouldStopFishing(Player player, CallbackInfoReturnable<Boolean> cir) {
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offhandItem = player.getOffhandItem();

        boolean hasEarthwormFishingRod = mainHandItem.is(ItemsRegistry.EARTHWORM_FISHING_ROD.get()) || offhandItem.is(ItemsRegistry.EARTHWORM_FISHING_ROD.get());

        if (hasEarthwormFishingRod) {
            if (!player.isRemoved() && player.isAlive()) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;awardStat(Lnet/minecraft/resources/ResourceLocation;I)V", shift = At.Shift.AFTER))
    private void replaceEarthwormRod(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        Entity owner = ((FishingHook)(Object)this).getOwner();
        if (!(owner instanceof Player player)) return;

        if (stack.is(ItemsRegistry.EARTHWORM_FISHING_ROD.get())) {
            // Replace the item in the correct hand
            if (player.getMainHandItem() == stack) {
                player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.FISHING_ROD));
            } else if (player.getOffhandItem() == stack) {
                player.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(Items.FISHING_ROD));
            }
        }
    }
}
