package com.sunglow.tutorialmod.event;

import com.sunglow.tutorialmod.TutorialMod;
import com.sunglow.tutorialmod.registry.ItemRegistry;
import com.sunglow.tutorialmod.registry.VillagerRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/**
 * 添加自定义村民交易项
 *
 * @Author xueyuntong
 * @Date 2023/4/24 13:44
 */
@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class VillagerEvent {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        //交易1
        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ItemRegistry.EIGHT_BALL_ITEM.get(), 1);
            int villagerLevel = 1;
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    stack, 10, 8, 0.02F));
        }
        //交易2
        if (event.getType() == VillagerRegistry.JUMP_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ItemRegistry.BLUEBERRY.get(), 15);
            int villagerLevel = 1;
            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack, 10, 8, 0.02F));
        }
    }
}
