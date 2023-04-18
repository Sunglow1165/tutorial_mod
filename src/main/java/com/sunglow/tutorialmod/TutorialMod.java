package com.sunglow.tutorialmod;

import com.mojang.logging.LogUtils;
import com.sunglow.tutorialmod.block.BlockRegistry;
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
        //获取mod事件总线实例
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //将ITEMS的DeferredRegister实例注册到mod事件总线上，以便在游戏加载时异步注册mod的Item。
        ItemRegistry.ITEMS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
    }
}
