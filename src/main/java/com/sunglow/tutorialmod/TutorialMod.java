package com.sunglow.tutorialmod;

import com.mojang.logging.LogUtils;
import com.sunglow.tutorialmod.fluid.ModFluidTypes;
import com.sunglow.tutorialmod.fluid.ModFluids;
import com.sunglow.tutorialmod.networking.ModMessage;
import com.sunglow.tutorialmod.item.ModItem;
import com.sunglow.tutorialmod.painting.ModPainting;
import com.sunglow.tutorialmod.villager.ModVillager;
import com.sunglow.tutorialmod.world.feature.ModConfiguredFeatures;
import com.sunglow.tutorialmod.world.feature.ModPlacedFeatures;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * 主类
 *
 * @Author xueyuntong
 * @Date 2023/4/18 17:08
 */
@Mod(TutorialMod.MOD_ID)
public class TutorialMod {
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String MOD_ID = "tutorialmod";

    public TutorialMod() {
        //获取mod事件总线实例
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //将ITEMS的DeferredRegister实例注册到mod事件总线上，以便在游戏加载时异步注册mod的Item。
        ModItem.ITEMS.register(modEventBus);
        com.sunglow.tutorialmod.block.ModBlock.BLOCKS.register(modEventBus);
        ModPainting.PAINTING_VARIANTS.register(modEventBus);
        ModVillager.VillagerRegister(modEventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        ModFluids.FLUIDS.register(modEventBus);
        ModFluidTypes.FLUID_TYPES.register(modEventBus);
    }

    private void commonSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ModVillager.registerPOIs();
            ModMessage.register();
        });
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(), RenderType.translucent());
        }
    }
}