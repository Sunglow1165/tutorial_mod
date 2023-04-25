package com.sunglow.tutorialmod.networking.packet;

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
 * @Author xueyuntong
 * @Date 2023/4/25 15:15
 */
public class DrinkWaterC2SPacket {
    private static final String MESSAGE_DRINK_WATER = "message.tutorial.drink_water";
    private static final String MESSAGE_NO_WATER = "message.tutorial.no_water";

    public DrinkWaterC2SPacket() {
    }

    public DrinkWaterC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // 我们在这里的服务器上!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            if (hasWaterAroundThem(player, level, 2)) {
                // 通知玩家水已经喝完了
                player.sendSystemMessage(Component.translatable(MESSAGE_DRINK_WATER).withStyle(ChatFormatting.DARK_AQUA));
                // 播放饮酒声
                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                        0.5F, level.random.nextFloat() * 0.1F + 0.9F);
                // 提高玩家的水位/饥渴度
                // 输出当前的口渴程度
            } else {
                // 通知玩家，周围没有水!
                player.sendSystemMessage(Component.translatable(MESSAGE_NO_WATER).withStyle(ChatFormatting.RED));
                // 输出当前的口渴程度
            }
        });
        return true;
    }

    private boolean hasWaterAroundThem(ServerPlayer player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }
}
