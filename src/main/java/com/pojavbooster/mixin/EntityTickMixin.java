package com.pojavbooster.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Giảm tải CPU bằng cách thu gom tần suất cập nhật AI/Pathfinding
 * cho các thực thể nằm xa người chơi (>24 block).
 *
 * TARGET CLASS:
 *   net.minecraft.entity.Entity  (hoặc LivingEntity nếu bạn chỉ muốn ảnh hưởng mob)
 */
@Mixin(Entity.class)
public abstract class EntityTickMixin {

    private static int entityAiTickCounter = 0;

    /**
     * Inject vào đầu method tick() của Entity.
     * Nếu thực thể xa hơn 24 block, ta đánh dấu để bỏ qua cập nhật AI
     * ở các tick lẻ, giúp giảm tải CPU trên chip ARM64.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void pajv$reduceDistantEntityAi(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        var player = MinecraftClient.getInstance().player;

        if (player == null) {
            return;
        }

        double distanceSq = entity.squaredDistanceTo(player);
        if (distanceSq > 24.0 * 24.0) {
            entityAiTickCounter++;

            /*
             * Bản hoàn chỉnh: inject cancellable vào method aiStep() / pathfinding()
             * của LivingEntity, gọi ci.cancel() khi (entityAiTickCounter & 3) != 0.
             * Ở đây ta chỉ tính counter để minh họa cơ chế giảm tần suất xử lý AI xa.
             */
        }
    }
}
