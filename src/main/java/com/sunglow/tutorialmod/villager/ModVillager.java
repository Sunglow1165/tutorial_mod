package com.sunglow.tutorialmod.villager;

import com.google.common.collect.ImmutableSet;
import com.sunglow.tutorialmod.TutorialMod;
import com.sunglow.tutorialmod.block.ModBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

/**
 * 村民注册器
 *
 * @Author xueyuntong
 * @Date 2023/4/24 10:35
 */
public class ModVillager {

    /**
     * 职业方块延迟寄存器
     */
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, TutorialMod.MOD_ID);
    /**
     * 村民职业延迟寄存器
     */
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, TutorialMod.MOD_ID);

    public static final RegistryObject<PoiType> JUMP_BLOCK_POI = POI_TYPES.register("jumpy_block_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlock.JUMPY_BLOCK.get().getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<VillagerProfession> JUMP_MASTER = VILLAGER_PROFESSION.register("jumpy_master",
            () -> new VillagerProfession("jumpy_master", x -> x.get() == JUMP_BLOCK_POI.get(),
                    x -> x.get() == JUMP_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));

    /**
     * 通过ObfuscationReflectionHelper找到PoiType类的registerBlockStates()方法,
     * 通过invoke()调用,因为是静态方法,所以调用者为null,传参为JUMP_BLOCK_POI.get()
     */
    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, JUMP_BLOCK_POI.get());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link com.sunglow.tutorialmod.TutorialMod}
     * 注册职业方块与村民职业
     */
    public static void VillagerRegister(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSION.register(eventBus);
    }
}
