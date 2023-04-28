package com.sunglow.tutorialmod.thirst;

import net.minecraft.nbt.CompoundTag;

/**
 * 玩家口渴程度
 *
 * @Author xueyuntong
 * @Date 2023/4/25 17:23
 */
public class PlayerThirst {
    // 口渴程度
    private int thirst;
    // 最小口渴程度
    private final int MIN_THIRST = 0;
    // 最大口渴程度
    private final int MAX_THIRST = 10;

    // 获取当前玩家口渴程度
    public int getThirst() {
        return this.thirst;
    }

    // 增加口渴程度
    public void addThirst(int add) {
        this.thirst = Math.min(thirst + add, MAX_THIRST);
    }

    // 减少口渴程度
    public void subThirst(int sub) {
        this.thirst = Math.max(thirst - sub, MIN_THIRST);
    }

    // 从另一个 PlayerThirst 对象中复制口渴程度
    public void copyFrom(PlayerThirst source) {
        this.thirst = source.thirst;
    }

    // 将口渴程度保存到 NBT 数据中
    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("thirst", thirst);
    }

    // 将口渴程度保存到 NBT 数据中
    public void loadNBTData(CompoundTag nbt) {
        thirst = nbt.getInt("thirst");
    }
}
