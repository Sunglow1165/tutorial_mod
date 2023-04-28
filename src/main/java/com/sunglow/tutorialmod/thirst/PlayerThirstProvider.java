package com.sunglow.tutorialmod.thirst;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 玩家口渴的能力
 *
 * @Author xueyuntong
 * @Date 2023/4/25 17:37
 */
public class PlayerThirstProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    // 定义了一个能力PLAYER_THIRST
    public static Capability<PlayerThirst> PLAYER_THIRST = CapabilityManager.get(new CapabilityToken<PlayerThirst>() {
    });

    private PlayerThirst thirst = null;
    private final LazyOptional<PlayerThirst> optional = LazyOptional.of(this::createPlayerThirst);

    private PlayerThirst createPlayerThirst() {
        if (this.thirst == null) {
            this.thirst = new PlayerThirst();
        }
        return this.thirst;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_THIRST) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    /**
     * 将 Capability 中的数据序列化和反序列化到 NBT（Named Binary Tag）格式
     */
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerThirst().saveNBTData(nbt);
        return nbt;
    }

    /**
     * 延迟加载 PlayerThirst 实例
     */
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerThirst().loadNBTData(nbt);
    }
}
