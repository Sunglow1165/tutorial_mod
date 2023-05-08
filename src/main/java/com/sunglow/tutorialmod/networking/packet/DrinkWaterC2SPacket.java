package com.sunglow.tutorialmod.networking.packet;

import com.sunglow.tutorialmod.networking.ModMessage;
import com.sunglow.tutorialmod.thirst.PlayerThirstProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * 喝水消息包
 *
 * @Author xueyuntong
 * @Date 2023/4/25 15:15
 */
public class DrinkWaterC2SPacket {
    /**
     * 喝水操作
     */
    private static final String MESSAGE_DRINK_WATER = "message.tutorialmod.drink_water";
    /**
     * 未发现水源
     */
    private static final String MESSAGE_NO_WATER = "message.tutorialmod.no_water";

    public DrinkWaterC2SPacket() {
    }

    public DrinkWaterC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    /**
     * 处理收到的消息包
     *
     * @param supplier 网络事件上下文
     * @return true成功处理
     */
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        // 将上下文对象存储在一个局部变量中
        NetworkEvent.Context context = supplier.get();
        // 将任务添加到主线程队列中
        context.enqueueWork(() -> {
            // 我们在这里的服务器上!
            // 获取发出这个 Packet 的玩家
            ServerPlayer player = context.getSender();
            // 获取玩家所在的服务器世界
            ServerLevel level = player.getLevel();
            // 判断是否有水源
            if (hasWaterAroundThem(player, level, 2)) {
                // 通知玩家水已经喝完了
                player.sendSystemMessage(Component.translatable(MESSAGE_DRINK_WATER).withStyle(ChatFormatting.DARK_AQUA));
                // 播放饮水声
                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                        0.5F, level.random.nextFloat() * 0.1F + 0.9F);
                // 获取玩家的口渴值,如果获取到了则
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    // 提高玩家的口渴值
                    thirst.addThirst(1);
                    // 向玩家发送一条系统消息，消息内容为当前玩家的口渴度
                    player.sendSystemMessage(Component.literal("Current Thirst " + thirst.getThirst())
                            .withStyle(ChatFormatting.AQUA));
                    // 将该数据同步给客户端
                    ModMessage.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                });
            } else {
                // 通知玩家，周围没有水
                player.sendSystemMessage(Component.translatable(MESSAGE_NO_WATER).withStyle(ChatFormatting.RED));
                // 获取玩家的口渴值,如果获取到了则
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    // 输出当前的口渴程度
                    player.sendSystemMessage(Component.literal("Curren Thirst " + thirst.getThirst())
                            .withStyle(ChatFormatting.AQUA));
                    // 将该数据同步给客户端
                    ModMessage.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                });
            }
        });
        return true;
    }

    /**
     * 检查玩家周围是否有水源方块
     *
     * @param player 玩家
     * @param level  世界
     * @param size   范围,周围多少个方块范围内查找水源方块
     * @return true:有水源方块,false:没有
     */
    private boolean hasWaterAroundThem(ServerPlayer player, ServerLevel level, int size) {
        //            区域内的方块状态      玩家的包围盒          将其沿各个方向（x、y、z）扩大 size 个单位，然后返回新的包围盒
        return level.getBlockStates(player.getBoundingBox().inflate(size))
        //             过滤水方块                          转换为数组  返回是否为空
                .filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }
}
