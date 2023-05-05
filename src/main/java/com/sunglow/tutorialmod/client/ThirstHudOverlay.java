package com.sunglow.tutorialmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sunglow.tutorialmod.TutorialMod;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

/**
 * 口渴值的GUI显示界面
 *
 * @Author xueyuntong
 * @Date 2023/4/26 11:19
 */
public class ThirstHudOverlay {
    private static final ResourceLocation FILLED_THIRST = new ResourceLocation(TutorialMod.MOD_ID,
            "textures/thirst/filled_thirst.png");
    private static final ResourceLocation EMPTY_THIRST = new ResourceLocation(TutorialMod.MOD_ID,
            "textures/thirst/empty_thirst.png");

    public static final IGuiOverlay HUD_THIRST = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_THIRST);
        for (int i = 0; i < 10; i++) {
            GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0, 0, 12, 12, 12, 12);
        }
        RenderSystem.setShaderTexture(0, FILLED_THIRST
        );
        for (int i = 0; i < 10; i++) {
            if (ClientThirstData.getPlayerThirst() > i) {
                GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0, 0, 12, 12, 12, 12);
            } else {
                break;
            }
        }
    });
}
