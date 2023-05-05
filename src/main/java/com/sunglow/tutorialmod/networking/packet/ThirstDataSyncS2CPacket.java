package com.sunglow.tutorialmod.networking.packet;

import com.sunglow.tutorialmod.client.ClientThirstData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * 口渴值数据包
 *
 * @Author xueyuntong
 * @Date 2023/4/25 15:15
 */
public class ThirstDataSyncS2CPacket {

    /**
     * 口渴值
     */
    private final int thirst;

    /**
     * 表示一个ByteBuf对象，这是一个可扩展的字节容器，用于在网络中传输数据
     *
     * @param thirst 口渴值
     */
    public ThirstDataSyncS2CPacket(int thirst) {
        this.thirst = thirst;
    }

    /**
     * 从网络数据流中读取数据初始化成员变量
     *
     * @param buf 表示一个ByteBuf对象，用于在网络中传输数据
     */
    public ThirstDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.thirst = buf.readInt();
    }

    /**
     * 将对象转换为ByteBuf类型的数据，便于在网络中传输。
     *
     * @param buf 表示一个ByteBuf对象，用于在网络中传输数据
     */
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(thirst);
    }

    /**
     * 处理接收到的同步数据包，将数据同步到客户端
     *
     * @param supplier 网络事件上下文
     * @return true成功处理
     */
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        // 将一个lambda表达式添加到在客户端主线程上执行的队列中
        context.enqueueWork(() -> {
            // 设置为客户端的玩家的口渴值
            ClientThirstData.setPlayerThirst(thirst);
        });
        return true;
    }
}
