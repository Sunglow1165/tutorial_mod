package com.sunglow.tutorialmod.networking;

import com.sunglow.tutorialmod.TutorialMod;
import com.sunglow.tutorialmod.networking.packet.DrinkWaterC2SPacket;
import com.sunglow.tutorialmod.networking.packet.ExampleC2SPacket;
import com.sunglow.tutorialmod.networking.packet.ThirstDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * 消息
 *
 * @Author xueyuntong
 * @Date 2023/4/25 15:05
 */
public class ModMessage {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    /**
     * 注册消息
     */
    public static void register() {
        // 创建消息通道
        SimpleChannel net = NetworkRegistry.ChannelBuilder //创建了一个通信频道的建造器
                .named(new ResourceLocation(TutorialMod.MOD_ID, "message")) //为该通信频道设置名称
                .networkProtocolVersion(() -> "1.0") //设置网络协议版本号
                .clientAcceptedVersions(s -> true) //指定客户端支持的通信协议版本范围
                .serverAcceptedVersions(s -> true) //指定服务器支持的通信协议版本范围
                .simpleChannel(); //创建并返回一个简单通信频道（SimpleChannel）的实例
        INSTANCE = net;

        // 定义消息
        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER) //创建消息构造器 消息类型,消息ID,消息方向
                .decoder(ExampleC2SPacket::new) //解码时所使用的方法
                .encoder(ExampleC2SPacket::toBytes) //编码时所使用的方法
                .consumerMainThread(ExampleC2SPacket::handle) //消息在接收后所执行的方法
                .add(); //将创建的消息构造器注册到SimpleChannel中，表示该消息可以发送和接收了

        net.messageBuilder(DrinkWaterC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DrinkWaterC2SPacket::new)
                .encoder(DrinkWaterC2SPacket::toBytes)
                .consumerMainThread(DrinkWaterC2SPacket::handle)
                .add();

        net.messageBuilder(ThirstDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ThirstDataSyncS2CPacket::new)
                .encoder(ThirstDataSyncS2CPacket::toBytes)
                .consumerMainThread(ThirstDataSyncS2CPacket::handle)
                .add();
    }

    /**
     * 发送消息到服务器
     *
     * @param message 发送的消息对象
     * @param <MSG>   网络消息泛型类型
     */
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    /**
     * 发送消息到玩家
     *
     * @param message 发送的消息对象
     * @param player  接收消息的玩家对象
     * @param <MSG>   网络消息泛型类型
     */
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
                       // 指定要发送到玩家player
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

}
