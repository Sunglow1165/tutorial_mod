package com.sunglow.tutorialmod.common;

import com.sunglow.tutorialmod.item.ModItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * 创造模式物品栏标签
 *
 * @Author xueyuntong
 * @Date 2023/4/18 17:14
 */
public class ModTab {
    public static final CreativeModeTab MODE_TAB = new CreativeModeTab("tutorialmod") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItem.ZIRCON.get());
        }
    };
}
