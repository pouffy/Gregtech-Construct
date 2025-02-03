package com.pouffydev.gtconstruct.common.modifier;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class BluntModifier extends Modifier implements MeleeDamageModifierHook {
    private static final Component BOOST = TConstruct.makeTranslation("modifier", "blunt.boost");
    private static final float PERCENT_PER_LEVEL = 0.15f; // 15% bonus when enemy wearing armor

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE);
    }

    @Override
    public int getPriority() {
        return 90;
    }

    public static float bonusScale(LivingEntity target) {
        if (target.getArmorCoverPercentage() > 0) {
            return 1 * target.getArmorCoverPercentage();
        }
        return 1;
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity target = context.getLivingTarget();
        if (target == null) {
            return damage;
        }
        float bonus = bonusScale(context.getLivingTarget()) * modifier.getEffectiveLevel() * PERCENT_PER_LEVEL;
        if (bonus > 0) {
            damage *= 1 + bonus;
        }
        return damage;
    }
}
