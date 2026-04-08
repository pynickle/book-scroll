package com.euphony.book_scroll.config;

import com.euphony.book_scroll.config.screen.YACLConfigScreen;
import net.minecraft.client.gui.screens.Screen;

public class YACLConfig extends Config {
    @Override
    public Screen makeScreen(Screen parent) {
        return YACLConfigScreen.generate(parent);
    }
}
