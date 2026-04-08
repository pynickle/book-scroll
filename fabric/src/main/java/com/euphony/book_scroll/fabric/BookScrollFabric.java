package com.euphony.book_scroll.fabric;

import com.euphony.book_scroll.BookScroll;
import net.fabricmc.api.ModInitializer;

public final class BookScrollFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BookScroll.init();
    }
}
