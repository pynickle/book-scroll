package com.euphony.book_scroll.utils.mc;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;

public final class KeyUtils {
    private KeyUtils() {}

    public static boolean hasControlDown() {
        return InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), 341)
                || InputConstants.isKeyDown(Minecraft.getInstance().getWindow(), 345);
    }
}
