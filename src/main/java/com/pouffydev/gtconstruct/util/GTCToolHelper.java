package com.pouffydev.gtconstruct.util;

import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.api.item.tool.ToolHelper;
import com.pouffydev.gtconstruct.common.item.ModifiableGTToolItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class GTCToolHelper {

    public static void damageItem(@NotNull ItemStack stack, @Nullable LivingEntity user, int damage) {
        if (!(stack.getItem() instanceof ModifiableGTToolItem tool) && (stack.getItem() instanceof IGTTool)) {
            ToolHelper.damageItem(stack, user, damage);
        } else {
            if (!(user instanceof Player player) || !player.isCreative()) {
                ToolStack toolStack = ToolStack.from(stack);
                if (damage <= 0) {
                    return;
                }
                int newDurability = stack.getDamageValue() + damage;
                if (user instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.ITEM_DURABILITY_CHANGED.trigger(serverPlayer, stack, newDurability);
                }
                ToolDamageUtil.damage(toolStack, damage, user, stack);
                if (newDurability > stack.getMaxDamage()) {
                    if (user != null) {
                        ToolDamageUtil.breakTool(toolStack.createStack());
                    }
                }
            }
        }
    }
}
