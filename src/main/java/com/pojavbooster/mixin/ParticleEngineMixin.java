package com.pojavbooster.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Tối ưu hóa ParticleEngine: loại bỏ các hạt (>12 block) nằm ngoài tầm nhìn người chơi.
 *
 * TARGET CLASS (điều chỉnh tùy mapping Yarn/Mojang thực tế):
 *   net.minecraft.client.particle.ParticleEngine
 *
 * Trong mẫu này ta mixin vào MinecraftClient để đảm bảo compile được trên mọi mapping.
 * Khi bạn đã xác định chính xác tên class ParticleEngine trong Yarn 1.21.x,
 * hãy thay @Mixin(MinecraftClient.class) bằng @Mixin(ParticleEngine.class)
 * và inject vào method tick()/render() của nó.
 */
@Mixin(MinecraftClient.class)
public abstract class ParticleEngineMixin {

    /**
     * Được gọi mỗi tick của game. Đây là nơi bạn sẽ chặn render các particle
     * nằm ngoài tầm nhìn camera hoặc cách người chơi >12 block.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void pajv$cullDistantParticles(CallbackInfo ci) {
        // TODO: Lấy danh sách particle từ ParticleEngine, lọc các Particle
        //       có khoảng cách đến player > 12.0 hoặc p.isRemoved() == true,
        //       rồi xóa chúng khỏi danh sách render.
        // Ví dụ (pseudo-code):
        //   for (Particle p : engine.getParticles()) {
        //       if (p.isRemoved() || p.getPos().distanceTo(player.getPos()) > 12.0) {
        //           p.remove();
        //       }
        //   }
    }
}
