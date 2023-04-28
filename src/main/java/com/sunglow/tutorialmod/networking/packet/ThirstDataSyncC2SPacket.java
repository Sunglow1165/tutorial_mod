package com.sunglow.tutorialmod.networking.packet;

import com.sunglow.tutorialmod.client.ClientThirstData;
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
public class ThirstDataSyncC2SPacket {

    private final int thirst;

    public ThirstDataSyncC2SPacket(int thirst) {
        this.thirst = thirst;
    }

    public ThirstDataSyncC2SPacket(FriendlyByteBuf buf) {
        this.thirst = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(thirst);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientThirstData.setPlayerThirst(thirst);
        });
        return true;
    }
}
