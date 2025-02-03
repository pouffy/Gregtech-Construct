package com.pouffydev.gtconstruct.datagen.material;

import com.pouffydev.gtconstruct.registry.GTCMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import static slimeknights.tconstruct.library.materials.MaterialRegistry.*;

public class GTCMaterialTraitsProv extends AbstractMaterialTraitDataProvider {
    public GTCMaterialTraitsProv(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialTraits() {
        addTraits(GTCMaterialIds.sterlingSilver, MELEE_HARVEST, ModifierIds.smite, new ModifierId(TConstruct.MOD_ID, "dwarven"));
        addTraits(GTCMaterialIds.sterlingSilver, RANGED, TinkerModifiers.holy, TinkerModifiers.dwarven);
        addTraits(GTCMaterialIds.sterlingSilver, ARMOR, ModifierIds.consecrated, new ModifierId(TConstruct.MOD_ID, "dwarven"));

        addDefaultTraits(GTCMaterialIds.bismuthBronze, ModifierIds.maintained);
        addDefaultTraits(GTCMaterialIds.blackBronze, ModifierIds.maintained, new ModifierId(TConstruct.MOD_ID, "dwarven"));

        addDefaultTraits(GTCMaterialIds.bismuth, ModifierIds.maintained);
        addDefaultTraits(GTCMaterialIds.cupronickel, TinkerModifiers.conducting, TinkerModifiers.dwarven);

        addDefaultTraits(GTCMaterialIds.blackSteel, ModifierIds.ductile);
        addDefaultTraits(GTCMaterialIds.blueSteel, ModifierIds.ductile, ModifierIds.sharpness);
        addDefaultTraits(GTCMaterialIds.redSteel, ModifierIds.ductile, ModifierIds.haste);

        noTraits(GTCMaterialIds.siliconeRubber);
        noTraits(GTCMaterialIds.styreneButadieneRubber);
        noTraits(GTCMaterialIds.polybenzimidazole);
        noTraits(GTCMaterialIds.polyethylene);
        noTraits(GTCMaterialIds.polytetrachloroethylene);
        noTraits(GTCMaterialIds.rubber);
    }

    @Override
    public String getName() {
        return "GregTech Construct Material Traits";
    }
}
