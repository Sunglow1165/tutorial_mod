package com.sunglow.tutorialmod.event;

import com.sunglow.tutorialmod.TutorialMod;
import com.sunglow.tutorialmod.networking.ModMessage;
import com.sunglow.tutorialmod.networking.packet.ThirstDataSyncC2SPacket;
import com.sunglow.tutorialmod.registry.ItemRegistry;
import com.sunglow.tutorialmod.registry.VillagerRegistry;
import com.sunglow.tutorialmod.thirst.PlayerThirst;
import com.sunglow.tutorialmod.thirst.PlayerThirstProvider;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/**
 * 添加自定义村民交易项
 *
 * @Author xueyuntong
 * @Date 2023/4/24 13:44
 */
@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
public class ModEvent {
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

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(TutorialMod.MOD_ID, "properties"), new PlayerThirstProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerThirst.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                if (thirst.getThirst() > 0 && event.player.getRandom().nextFloat() < 0.005F) {
                    thirst.subThirst(1);
                    ModMessage.sendToPlayer(new ThirstDataSyncC2SPacket(thirst.getThirst()), ((ServerPlayer) event.player));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    ModMessage.sendToPlayer(new ThirstDataSyncC2SPacket(thirst.getThirst()), player);
                });
            }
        }
    }
}