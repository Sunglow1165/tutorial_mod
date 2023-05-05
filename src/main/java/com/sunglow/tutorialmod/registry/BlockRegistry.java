package com.sunglow.tutorialmod.registry;

import com.sunglow.tutorialmod.TutorialMod;
import com.sunglow.tutorialmod.block.BlueberryCropBlock;
import com.sunglow.tutorialmod.block.JumpyBlock;
import com.sunglow.tutorialmod.block.ZirconLampBlock;
import com.sunglow.tutorialmod.common.ModTab;
import com.sunglow.tutorialmod.fluid.ModFluids;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * 方块注册器
 *
 * @Author xueyuntong
 * @Date 2023/4/18 18:32
 */
public class BlockRegistry {

    //方块延迟寄存器
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    //锆石块
    public static final RegistryObject<Block> ZIRCON_BLOCK = registerBlock("zircon_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6F).requiresCorrectToolForDrops()),
            ModTab.MODE_TAB);
    //锆石矿
    public static final RegistryObject<Block> ZIRCON_ORE = registerBlock("zircon_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6F).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModTab.MODE_TAB);
    //深板岩锆石矿
    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE = registerBlock("deepslate_zircon_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6F).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModTab.MODE_TAB);

    //下界锆石矿
    public static final RegistryObject<Block> NETHERRACK_ZIRCON_ORE = registerBlock("netherrack_zircon_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6F).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModTab.MODE_TAB);

    //末地锆石矿
    public static final RegistryObject<Block> ENDSTONE_ZIRCON_ORE = registerBlock("endstone_zircon_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6F).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModTab.MODE_TAB);

    //跳跃方块
    public static final RegistryObject<Block> JUMPY_BLOCK = registerBlock("jumpy_block",
            () -> new JumpyBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6F).requiresCorrectToolForDrops()), ModTab.MODE_TAB);

    //锆石灯
    public static final RegistryObject<Block> ZIRCON_LAMP = registerBlock("zircon_lamp",
            () -> new ZirconLampBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6F).requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(ZirconLampBlock.LIT) ? 15 : 0)), ModTab.MODE_TAB);

    //蓝莓作物
    public static final RegistryObject<Block> BLUEBERRY_CROP = BLOCKS.register("blueberry_crop",
            () -> new BlueberryCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    //肥皂水方块
    public static final RegistryObject<LiquidBlock> SOAP_WATER_BLOCK = BLOCKS.register("soap_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_SOAP_WATER, BlockBehaviour.Properties.copy(Blocks.WATER)));

    //注册方块
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    /**
     * {@link com.sunglow.tutorialmod.TutorialMod}
     * 注册方块对应的物品
     */
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
