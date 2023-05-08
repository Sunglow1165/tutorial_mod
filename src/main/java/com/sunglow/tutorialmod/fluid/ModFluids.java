package com.sunglow.tutorialmod.fluid;

import com.sunglow.tutorialmod.TutorialMod;
import com.sunglow.tutorialmod.item.ModItem;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 流体
 *
 * @Author xueyuntong
 * @Date 2023/5/4 14:46
 */
public class ModFluids {
    /**
     * 流体延迟注册器
     */
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TutorialMod.MOD_ID);

    /**
     * 肥皂水源
     */
    public static final RegistryObject<FlowingFluid> SOURCE_SOAP_WATER = FLUIDS.register("soap_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SOAP_WATER_FLUID_PROPERTIES));

    /**
     * 流动的肥皂水
     */
    public static final RegistryObject<FlowingFluid> FLOWING_SOAP_WATER = FLUIDS.register("flowing_soap_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SOAP_WATER_FLUID_PROPERTIES));

    /**
     * 设置了一些流体必要的属性
     */
    public static final ForgeFlowingFluid.Properties SOAP_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SOAP_WATER_FLUID_TYPE, SOURCE_SOAP_WATER, FLOWING_SOAP_WATER)
            // 流体的行为                                    流体的方块状态
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(com.sunglow.tutorialmod.block.ModBlock.SOAP_WATER_BLOCK)
            // 流体桶的物品，该桶可用于捡起或放置肥皂水流体
            .bucket(ModItem.SOAP_WATER_BUCKET);
}
