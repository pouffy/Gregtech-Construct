package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.registry.GTCModifiers;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.modules.build.StatBoostModule;
import slimeknights.tconstruct.library.modifiers.modules.combat.ConditionalMeleeDamageModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.recipe.partbuilder.Pattern;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;

public class GTCModifierProv extends AbstractModifierProvider implements IConditionBuilder {
    public GTCModifierProv(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {
        buildModifier(GTCModifiers.iceCutter.getId()).levelDisplay(ModifierLevelDisplay.NO_LEVELS).build();

        buildModifier(GTCModifiers.blunt)
                .addModule(ConditionalMeleeDamageModule.builder().target(LivingEntityPredicate.simple((lE) -> lE.getArmorCoverPercentage() > 0.0F)).eachLevel(1.6f));
    }

    @Override
    public String getName() {
        return "GregTech Construct Modifiers";
    }

    /** Short helper to get a modifier ID */
    private static ModifierId id(String name) {
        return new ModifierId(GTConstruct.MOD_ID, name);
    }

    /** Short helper to get a modifier ID */
    private static Pattern pattern(String name) {
        return new Pattern(GTConstruct.MOD_ID, name);
    }
}
