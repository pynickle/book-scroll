package com.euphony.book_scroll.config;

import com.euphony.book_scroll.BookScroll;
import com.euphony.book_scroll.platform.Platform;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static com.euphony.book_scroll.BookScroll.LOGGER;
import static com.euphony.book_scroll.BookScroll.config;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path BASE_PATH = Platform.getConfigFolder().resolve(BookScroll.MOD_ID);
    private static final Path PATH = BASE_PATH.resolve("client.json");

    public static Config DEFAULTS = new Config();

    public int ctrlSpeedMultiplier = 5;
    public boolean enablePageTurnSound = true;

    public static Config create() {
        config = Platform.isModLoaded("yet_another_config_lib_v3") ? new YACLConfig() : DEFAULTS;
        load();
        return config;
    }

    public static void load() {
        if (Files.notExists(BASE_PATH)) {
            try {
                Files.createDirectories(BASE_PATH);
            } catch (Exception e) {
                LOGGER.error("Couldn't create config directory: ", e);
                return;
            }
        }

        if (Files.notExists(PATH)) {
            save();
            return;
        }

        try {
            config = GSON.fromJson(Files.readString(PATH), config.getClass());
        } catch (Exception e) {
            LOGGER.error("Couldn't load config file: ", e);
        }
    }

    public static void save() {
        try {
            Files.write(PATH, Collections.singleton(GSON.toJson(config)));
        } catch (Exception e) {
            LOGGER.error("Couldn't save config file: ", e);
        }
    }

    public Screen makeScreen(Screen parent) {
        Minecraft mc = Minecraft.getInstance();
        String link = "https://modrinth.com/mod/yacl";

        return new ConfirmScreen(
                clicked -> {
                    if (clicked) {
                        ConfirmLinkScreen.confirmLinkNow(parent, link);
                    } else {
                        mc.setScreen(parent);
                    }
                },
                Component.translatable("text.book_scroll.help.missing"),
                Component.translatable("text.book_scroll.desc.help.missing"),
                Component.translatable("gui.continue"),
                Component.translatable("gui.back"));
    }
}
