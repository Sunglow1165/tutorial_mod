package com.sunglow.tutorialmod.fluid;

import com.mojang.math.Vector3f;
import com.sunglow.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 流体类型
 *
 * @Author xueyuntong
 * @Date 2023/5/4 14:46
 */
public class ModFluidTypes {
    /**
     * 流体类型延迟注册器
     */
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TutorialMod.MOD_ID);

    /**
     * 静态的水流动画贴图
     */
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    /**
     * 流动的水流动画贴图
     */
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    /**
     * 肥皂水表面覆盖的贴图
     */
    public static final ResourceLocation SOAP_OVERLAY_RL = new ResourceLocation(TutorialMod.MOD_ID, "misc/in_soap_water");

    /**
     * 注册肥皂水类型
     */
    public static final RegistryObject<FluidType> SOAP_WATER_FLUID_TYPE = register("soap_water_fluid",
            // 流体类型的属性                光照等级        密度        粘性          声音
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));

    public static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, SOAP_OVERLAY_RL,
                0xA1E038D0, new Vector3f(224F / 255F, 56F / 255F, 208F / 255F), properties));
    }
}
