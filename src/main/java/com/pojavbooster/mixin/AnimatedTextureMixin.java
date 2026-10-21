package com.pojavbooster.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Giảm tần suất cập nhật texture nạp động (nước, dung nham, lửa, cổng Nether/End)
 * xuống 50% để giảm băng thông bộ nhớ trên GPU Mali.
 *
 * TARGET CLASS (điều chỉnh tùy mapping thực tế):
 *   net.minecraft.client.texture.AnimatedSprite
 *   hoặc net.minecraft.client.texture.Sprite
 */
@Mixin(MinecraftClient.class)
public abstract class AnimatedTextureMixin {

    private static int animationTickCounter = 0;

    /**
     * Chỉ cho phép cập nhật animation mỗi 2 tick (bỏ qua tick lẻ).
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void pajv$throttleAnimatedTexture(CallbackInfo ci) {
        animationTickCounter++;

        if ((animationTickCounter & 1) == 1) {
            /*
             * Trong bản hoàn chỉnh, bạn sẽ inject cancellable vào method
             * AnimatedSprite.updateAnimation() và gọi ci.cancel() ở tick lẻ.
             * Ở đây ta chỉ giữ lại counter để minh họa logic giảm tần suất 50%.
             */
        }
    }
}
