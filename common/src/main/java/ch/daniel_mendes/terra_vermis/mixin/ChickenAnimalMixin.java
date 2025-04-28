package ch.daniel_mendes.terra_vermis.mixin;

import ch.daniel_mendes.terra_vermis.entity.ai.goal.EatEarthwormBlockGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public abstract class ChickenAnimalMixin extends Animal {

    private int eatAnimationTick;
    private EatEarthwormBlockGoal eatEarthwormBlockGoal;

    protected ChickenAnimalMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void addEarthwormEatingGoal(CallbackInfo ci) {
        this.eatEarthwormBlockGoal = new EatEarthwormBlockGoal(this);
        this.goalSelector.addGoal(4, this.eatEarthwormBlockGoal);
    }

    //@Inject(method = "customServerAiStep", at = @At("HEAD"), remap = false)
    //private void onCustomServerAiStep(ServerLevel level, CallbackInfo ci) {
    //    if (this.eatEarthwormBlockGoal != null) {
    //        this.eatAnimationTick = this.eatEarthwormBlockGoal.getEatAnimationTick();
    //    }
    //}
}