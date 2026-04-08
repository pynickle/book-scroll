package com.euphony.book_scroll.platform;

import java.nio.file.Path;

public final class Platform {
    private Platform() {}

    public static Path getConfigFolder() {
        return PlatformServices.getPlatform().getConfigDirectory();
    }

    public static boolean isModLoaded(String modId) {
        return PlatformServices.getPlatform().isModLoaded(modId);
    }
}
