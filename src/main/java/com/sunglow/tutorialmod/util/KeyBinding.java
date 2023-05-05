package com.sunglow.tutorialmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

/**
 * 快捷键按键绑定
 *
 * @Author xueyuntong
 * @Date 2023/4/25 14:17
 */
public class KeyBinding {
    /**
     * 定义键的类别
     */
    public static final String KEY_CATEGORY_TUTORIAL = "key.category.tutorialmod.tutorial";
    /**
     * 定义"喝水"键的名称
     */
    public static final String KEY_DRINK_WATER = "key.tutorialmod.drink_water";
    /**
     * 定义"喝水"键的按键映射①
     */
    public static final KeyMapping DRINKING_KEY = new KeyMapping(KEY_DRINK_WATER, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_TUTORIAL);
    /**
     * ①
     * KEY_DRINK_WATER作为键的名称，
     * KeyConflictContext.IN_GAME表示只在游戏中使用，
     * InputConstants.Type.KEYSYM表示使用键盘按键，
     * GLFW.GLFW_KEY_O表示将该键绑定到O键，
     * KEY_CATEGORY_TUTORIAL表示将该键放入教程模组的键类别中
     */
}
