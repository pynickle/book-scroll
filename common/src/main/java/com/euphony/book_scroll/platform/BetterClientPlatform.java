package com.euphony.book_scroll.platform;

import java.nio.file.Path;

public interface BetterClientPlatform {
    Path getConfigDirectory();

    boolean isModLoaded(String modId);

    PlatformType getPlatformType();
}
