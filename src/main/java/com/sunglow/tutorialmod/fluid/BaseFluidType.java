package com.sunglow.tutorialmod.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * 自定义流体类型
 *
 * @Author xueyuntong
 * @Date 2023/5/4 14:46
 */
public class BaseFluidType extends FluidType {

    /**
     * 静态贴图
     */
    private final ResourceLocation stillTexture;
    /**
     * 流动贴图
     */
    private final ResourceLocation flowingTexture;
    /**
     * 覆盖贴图
     */
    private final ResourceLocation overlayTexture;
    /**
     * 流体的颜色
     */
    private final int tintColor;
    /**
     * 流体在雾化状态下的颜色
     */
    private final Vector3f fogColor;

    public BaseFluidType(final ResourceLocation stillTexture, final ResourceLocation flowingTexture, final ResourceLocation overlayTexture,
                         final int tintColor, final Vector3f fogColor, final Properties properties) {
        super(properties);
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.overlayTexture = overlayTexture;
        this.tintColor = tintColor;
        this.fogColor = fogColor;
    }

    public ResourceLocation getStillTexture() {
        return stillTexture;
    }

    public ResourceLocation getFlowingTexture() {
        return flowingTexture;
    }

    public ResourceLocation getOverlayTexture() {
        return overlayTexture;
    }

    public int getTintColor() {
        return tintColor;
    }

    public Vector3f getFogColor() {
        return fogColor;
    }

    /**
     * 初始化客户端（即游戏客户端），为流体的渲染提供信息
     *
     * @param consumer 消费者
     */
    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return stillTexture;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return flowingTexture;
            }

            @Override
            public @Nullable ResourceLocation getOverlayTexture() {
                return overlayTexture;
            }

            @Override
            public int getTintColor(FluidStack stack) {
                return tintColor;
            }

            /**
             * 修改流体在雾化状态下的颜色
             * @param camera 相机
             * @param partialTick 部分时刻
             * @param level 客户端级别
             * @param renderDistance 客户端级别
             * @return 流体的雾化颜色
             */
            @Override
            public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance,
                                                    float darkenWorldAmount, Vector3f fluidFogColor) {
                return fogColor;
            }

            /**
             *  修改雾的渲染效果
             * @param camera 相机
             * @param mode 雾的模式
             * @param shape 雾的形状
             */
            @Override
            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick,
                                        float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart(1F);
                RenderSystem.setShaderFogEnd(6F);
            }

        });
    }
}
