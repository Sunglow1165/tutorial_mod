package com.sunglow.tutorialmod.item;

import com.sunglow.tutorialmod.TutorialMod;
import com.sunglow.tutorialmod.block.ModBlock;
import com.sunglow.tutorialmod.fluid.ModFluids;
import com.sunglow.tutorialmod.item.EightBallItem;
import com.sunglow.tutorialmod.common.ModTab;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 物品注册器
 *
 * @Author xueyuntong
 * @Date 2023/4/18 17:10
 */
public class ModItem {

    /**
     * create()创建一个DeferredRegister延迟寄存器 绑定到ForgeRegistries中,Mod_ID用于区分模组
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    /**
     * register()向延迟寄存器中注册一个Item对象,
     * 当我们在其他地方需要使用这个物品时，只需要从ITEMS中获取对应的RegistryObject对象，然后调用get方法即可获得注册后的Item对象。
     * 这种机制能够确保我们在使用Item之前，Item已经被注册到游戏中，并且保证了线程安全。
     */
    // 锆石
    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
            () -> new Item(new Item.Properties().tab(ModTab.MODE_TAB)));
    // 粗锆石
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(ModTab.MODE_TAB)));
    // 八球
    public static final RegistryObject<Item> EIGHT_BALL_ITEM = ITEMS.register("eight_ball_item",
            () -> new EightBallItem(new Item.Properties().tab(ModTab.MODE_TAB)));
    // 蓝莓种子
    public static final RegistryObject<Item> BLUEBERRY_SEEDS = ITEMS.register("blueberry_seeds",
            () -> new ItemNameBlockItem(com.sunglow.tutorialmod.block.ModBlock.BLUEBERRY_CROP.get(), new Item.Properties().tab(ModTab.MODE_TAB)));
    // 蓝莓果实
    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
            () -> new Item(new Item.Properties().tab(ModTab.MODE_TAB).food(new FoodProperties.Builder().nutrition(2).saturationMod(2F).build())));
    // 肥皂水桶
    public static final RegistryObject<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER,
                    new Item.Properties().tab(ModTab.MODE_TAB).craftRemainder(Items.BUCKET).stacksTo(1)));

}