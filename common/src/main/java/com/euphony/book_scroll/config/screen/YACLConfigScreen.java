package com.euphony.book_scroll.config.screen;

import com.euphony.book_scroll.config.Config;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static com.euphony.book_scroll.BookScroll.config;
import static com.euphony.book_scroll.config.Config.DEFAULTS;

public final class YACLConfigScreen {
    private YACLConfigScreen() {}

    public static Screen generate(Screen parent) {
        Option<Integer> ctrlSpeedMultiplierOpt = Option.<Integer>createBuilder()
                .name(Component.translatable("yacl3.config.book_scroll:config.ctrlSpeedMultiplier"))
                .description(OptionDescription.of(Component.translatable(
                        "yacl3.config.book_scroll:config.ctrlSpeedMultiplier.desc")))
                .binding(
                        DEFAULTS.ctrlSpeedMultiplier,
                        () -> config.ctrlSpeedMultiplier,
                        newVal -> config.ctrlSpeedMultiplier = newVal)
                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(1, 10).step(1))
                .build();

        Option<Boolean> enablePageTurnSoundOpt = Option.<Boolean>createBuilder()
                .name(Component.translatable("yacl3.config.book_scroll:config.enablePageTurnSound"))
                .description(OptionDescription.of(Component.translatable(
                        "yacl3.config.book_scroll:config.enablePageTurnSound.desc")))
                .binding(
                        DEFAULTS.enablePageTurnSound,
                        () -> config.enablePageTurnSound,
                        newVal -> config.enablePageTurnSound = newVal)
                .controller(TickBoxControllerBuilder::create)
                .build();

        return YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("yacl3.config.book_scroll:config"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.translatable("yacl3.config.book_scroll:config.category.client"))
                        .group(OptionGroup.createBuilder()
                                .name(Component.translatable("yacl3.config.book_scroll:config.category.client.group.book_scroll"))
                                .options(List.of(ctrlSpeedMultiplierOpt, enablePageTurnSoundOpt))
                                .build())
                        .build())
                .save(Config::save)
                .build()
                .generateScreen(parent);
    }
}
