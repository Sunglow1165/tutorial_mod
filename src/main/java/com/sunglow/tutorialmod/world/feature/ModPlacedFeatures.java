package com.sunglow.tutorialmod.world.feature;

import com.sunglow.tutorialmod.TutorialMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

/**
 * 放置特性
 * 定义了应该在哪里以及如何生成
 *
 * @Author xueyuntong
 * @Date 2023/4/24 18:23
 */
public class ModPlacedFeatures {

    /**
     * 创建放置特性的延迟注册器
     */
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, TutorialMod.MOD_ID);

    /**
     * 注册主世界锆石的放置
     */
    public static final RegistryObject<PlacedFeature> ZIRCON_ORE_PLACED = PLACED_FEATURES.register("zircon_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ZIRCON_ORE.getHolder().get(), commonOrePlacement(7, // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    /**
     * 注册末地锆石的放置
     */
    public static final RegistryObject<PlacedFeature> END_ZIRCON_ORE_PLACED = PLACED_FEATURES.register("end_zircon_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.END_ZIRCON_ORE.getHolder().get(), commonOrePlacement(7, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    /**
     * 注册下界锆石的放置
     */
    public static final RegistryObject<PlacedFeature> NETHER_ZIRCON_ORE_PLACED = PLACED_FEATURES.register("nether_zircon_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_ZIRCON_ORE.getHolder().get(), commonOrePlacement(7, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    /**
     * 生成矿石布置列表
     *
     * @param distributionModifier 矿物的生成数量和分布规则
     * @param placementModifier    矿物的高度分布规则
     * @return 由四个 PlacementModifier 对象组成的列表
     */
    public static List<PlacementModifier> orePlacement(PlacementModifier distributionModifier, PlacementModifier placementModifier) {
        return List.of(distributionModifier, InSquarePlacement.spread(), placementModifier, BiomeFilter.biome());
    }

    /**
     * 控制生成矿石的数量
     *
     * @param num               矿物生成数量
     * @param placementModifier 矿物生成的位置修饰符
     * @return 分布列表
     */
    public static List<PlacementModifier> commonOrePlacement(int num, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(num), placementModifier);
    }

    /**
     * 控制生成矿物的稀有度
     *
     * @param rarity            矿物生成稀有度
     * @param placementModifier 矿物生成的位置修饰符
     * @return 分布列表
     */
    public static List<PlacementModifier> rareOrePlacement(int rarity, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(rarity), placementModifier);
    }
}
