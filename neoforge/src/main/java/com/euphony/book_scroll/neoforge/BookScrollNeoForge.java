package com.euphony.book_scroll.neoforge;

import com.euphony.book_scroll.BookScroll;
import net.neoforged.fml.common.Mod;

@Mod(BookScroll.MOD_ID)
public final class BookScrollNeoForge {
    public BookScrollNeoForge() {
        BookScroll.init();
    }
}
