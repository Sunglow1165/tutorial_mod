package com.sunglow.tutorialmod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/**
 * 八球功能物品
 *
 * @Author xueyuntong
 * @Date 2023/4/20 11:25
 */
public class EightBallItem extends Item {

    public EightBallItem(Properties properties) {
        super(properties);
    }

    //右键使用物品
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            int randomNum = RandomSource.createNewThreadLocalInstance().nextInt(10);
            player.sendSystemMessage(Component.literal("你的号码是:" + randomNum));
            player.getCooldowns().addCooldown(this, 20);
        }
        return super.use(level, player, hand);
    }
}
