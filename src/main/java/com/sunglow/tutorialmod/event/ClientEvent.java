package com.sunglow.tutorialmod.event;

import com.sunglow.tutorialmod.TutorialMod;
import com.sunglow.tutorialmod.client.ThirstHudOverlay;
import com.sunglow.tutorialmod.networking.ModMessage;
import com.sunglow.tutorialmod.networking.packet.DrinkWaterC2SPacket;
import com.sunglow.tutorialmod.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


/**
 * 客户端事件
 *
 * @Author xueyuntong
 * @Date 2023/4/25 14:32
 */
public class ClientEvent {
    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        /**
         * 测当玩家按下“喝水”键时，通过网络发送一个喝水的请求到服务器端
         *
         * @param event 键盘输入事件,玩家按下或释放按键时触发
         */
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.DRINKING_KEY.consumeClick()) {
                ModMessage.sendToServer(new DrinkWaterC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvent {
        /**
         * 按键注册事件监听器,将自定义的按键注册到Minecraft游戏中
         *
         * @param event 注册键映射事件,游戏启动时触发
         */
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DRINKING_KEY);
        }

        /**
         * GUI注册事件监听器，将一个自定义的HUD（heads-up display）层添加到游戏GUI的上方，用于显示玩家的口渴度
         *
         * @param event 注册Gui覆盖事件,在游戏界面绘制之前触发
         */
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("thirst", ThirstHudOverlay.HUD_THIRST);
        }
    }
}
