package com.sunglow.tutorialmod.painting;

import com.sunglow.tutorialmod.TutorialMod;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 画注册器
 *
 * @Author xueyuntong
 * @Date 2023/4/24 14:09
 */
public class ModPainting {
    //绘画变体延迟寄存器
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, TutorialMod.MOD_ID);

    public static final RegistryObject<PaintingVariant> PLANT = PAINTING_VARIANTS.register("plant",
            () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> WANDERER = PAINTING_VARIANTS.register("wanderer",
            () -> new PaintingVariant(16, 32));
    public static final RegistryObject<PaintingVariant> SUNSET = PAINTING_VARIANTS.register("sunset",
            () -> new PaintingVariant(32, 16));

}
