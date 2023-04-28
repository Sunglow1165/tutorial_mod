package com.sunglow.tutorialmod.client;

/**
 * 客户端口渴值数据
 *
 * @Author xueyuntong
 * @Date 2023/4/26 11:26
 */
public class ClientThirstData {

    private static int playerThirst;

    public static int getPlayerThirst() {
        return playerThirst;
    }

    public static void setPlayerThirst(int playerThirst) {
        ClientThirstData.playerThirst = playerThirst;
    }
}
