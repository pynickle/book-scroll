package com.euphony.book_scroll;

import com.euphony.book_scroll.config.Config;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public final class BookScroll {
    public static final String MOD_ID = "book_scroll";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Config config = Config.create();

    public static void init() {}
}
