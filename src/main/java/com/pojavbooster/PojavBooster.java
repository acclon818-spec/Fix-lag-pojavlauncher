package com.pojavbooster;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PojavBooster - Fabric Mod tối ưu hiệu năng cho Minecraft 1.21.x
 * Chạy trên PojavLauncher & MJLauncher (Android ARM64, GPU Mali).
 */
public class PojavBooster implements ClientModInitializer {

    public static final String MOD_ID = "pojavbooster";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("[PojavBooster] Initializing FPS Booster for Minecraft 1.21.x on Android ARM64...");
        LOGGER.info("[PojavBooster] Compatible with PojavLauncher & MJLauncher");
        LOGGER.info("[PojavBooster] Mixins loaded: ParticleEngine, AnimatedTexture, EntityTick, RenderSystem");
    }
}
