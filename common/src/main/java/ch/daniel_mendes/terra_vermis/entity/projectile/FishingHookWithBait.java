package ch.daniel_mendes.terra_vermis.entity.projectile;

import ch.daniel_mendes.terra_vermis.registry.EntityTypesRegistry;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class FishingHookWithBait extends FishingHook {

    private final int luck;
    private final int lureSpeed;

    private FishingHookWithBait(EntityType<? extends FishingHookWithBait> entityType, Level level, int luck, int lureSpeed) {
        super(entityType, level);
        this.luck = Math.max(0, luck);
        this.lureSpeed = Math.max(0, lureSpeed);
    }

    public FishingHookWithBait(EntityType<? extends FishingHookWithBait> entityType, Level level) {
        this(entityType, level, 0 ,0);
    }

    public FishingHookWithBait(@NotNull Player owner, Level level, int luck, int lureSpeed) {
        this(EntityTypesRegistry.FISHING_BOBBER_WITH_BAIT.get(), level, luck, lureSpeed);
        this.setOwner(owner);
        float f = owner.getXRot();
        float f1 = owner.getYRot();
        float f2 = Mth.cos(-f1 * (float) (Math.PI / 180.0) - (float) Math.PI);
        float f3 = Mth.sin(-f1 * (float) (Math.PI / 180.0) - (float) Math.PI);
        float f4 = -Mth.cos(-f * (float) (Math.PI / 180.0));
        float f5 = Mth.sin(-f * (float) (Math.PI / 180.0));
        double d0 = owner.getX() - f3 * 0.3;
        double d1 = owner.getEyeY();
        double d2 = owner.getZ() - f2 * 0.3;
        this.snapTo(d0, d1, d2, f1, f);
        Vec3 vec3 = new Vec3(-f3, Mth.clamp(-(f5 / f4), -5.0F, 5.0F), -f2);
        double d3 = vec3.length();
        vec3 = vec3.multiply(
                0.6 / d3 + this.random.triangle(0.5, 0.0103365), 0.6 / d3 + this.random.triangle(0.5, 0.0103365), 0.6 / d3 + this.random.triangle(0.5, 0.0103365)
        );
        this.setDeltaMovement(vec3);
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * 180.0F / (float)Math.PI));
        this.setXRot((float)(Mth.atan2(vec3.y, vec3.horizontalDistance()) * 180.0F / (float)Math.PI));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }
}
