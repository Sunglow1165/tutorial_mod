package com.sunglow.tutorialmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * 踩在这个方块上会有条约提升的效果
 *
 * @Author xueyuntong
 * @Date 2023/4/20 15:38
 */
public class JumpyBlock extends Block {

    public JumpyBlock(Properties properties) {
        super(properties);
    }

    //右键使用方块
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        player.sendSystemMessage(Component.literal("触发右键事件"));
        return super.use(state, level, pos, player, hand, result);
    }

    //踩在方块上
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200));
        }
        super.stepOn(level, pos, state, entity);
    }
}
