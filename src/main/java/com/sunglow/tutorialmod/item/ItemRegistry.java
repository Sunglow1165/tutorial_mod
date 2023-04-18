package com.sunglow.tutorialmod.item;

import com.sunglow.tutorialmod.TutorialMod;
import com.sunglow.tutorialmod.common.ModTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 物品注册器
 *
 * @Author xueyuntong
 * @Date 2023/4/18 17:10
 */
public class ItemRegistry {

    // 延迟注册器
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    // 锆石
    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
            () -> new Item(new Item.Properties().tab(ModTab.MODE_TAB)));
}
