package com.sunglow.tutorialmod;

import com.mojang.logging.LogUtils;
import com.sunglow.tutorialmod.item.ItemRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.ITEMS.register(modEventBus);
    }
}
