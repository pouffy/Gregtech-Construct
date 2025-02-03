package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.registry.GTCModifiers;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.recipe.partbuilder.Pattern;

public class GTCModifierProv extends AbstractModifierProvider implements IConditionBuilder {
    public GTCModifierProv(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {
        buildModifier(GTCModifiers.iceCutter.getId()).levelDisplay(ModifierLevelDisplay.NO_LEVELS).build();

        buildModifier(GTCModifiers.blunt.getId());
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
