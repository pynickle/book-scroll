package com.euphony.book_scroll.fabric.integration;

import com.euphony.book_scroll.BookScroll;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> BookScroll.config.makeScreen(screen);
    }
}
