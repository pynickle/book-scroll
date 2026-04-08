package com.euphony.book_scroll.mixin;

import com.euphony.book_scroll.utils.mc.KeyUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import static com.euphony.book_scroll.BookScroll.config;

@Mixin(BookViewScreen.class)
public abstract class BookViewScreenMixin extends Screen {
    @Shadow
    protected abstract void pageBack();

    @Shadow
    private int currentPage;

    @Shadow
    protected abstract int getNumPages();

    @Shadow
    protected abstract void pageForward();

    @Unique
    private double book_scroll$progress = 0;

    protected BookViewScreenMixin(Component component) {
        super(component);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        double scrollDelta = verticalAmount + horizontalAmount;
        double speedFactor = KeyUtils.hasControlDown() ? config.ctrlSpeedMultiplier : 1.0;
        book_scroll$progress += scrollDelta * speedFactor;

        boolean pageTurned = false;
        if (book_scroll$progress >= 1.0) {
            while (book_scroll$progress >= 1.0) {
                book_scroll$progress -= 1.0;
                this.pageBack();
                pageTurned = true;
            }
        } else if (book_scroll$progress < 0.0) {
            while (book_scroll$progress < 0.0) {
                book_scroll$progress += 1.0;
                if (this.currentPage < this.getNumPages() - 1) {
                    this.pageForward();
                }
                pageTurned = true;
            }
        }

        if (pageTurned && config.enablePageTurnSound) {
            this.minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BOOK_PAGE_TURN, 1.0F));
        }
        return true;
    }
}
