package com.pouffydev.gtconstruct.datagen.material;

import com.pouffydev.gtconstruct.registry.GTCMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class GTCMaterialDataProv extends AbstractMaterialDataProvider {
    public GTCMaterialDataProv(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "GregTech Construct Materials";
    }

    @Override
    protected void addMaterials() {
        addMaterial(GTCMaterialIds.bismuth,             2, ORDER_COMPAT + ORDER_GENERAL, false);

        addMaterial(GTCMaterialIds.bismuthBronze,       3, ORDER_COMPAT + ORDER_SPECIAL, false);
        addMaterial(GTCMaterialIds.cupronickel,         3, ORDER_COMPAT + ORDER_SPECIAL, false);
        addMaterial(GTCMaterialIds.sterlingSilver,      3, ORDER_COMPAT + ORDER_SPECIAL, false);
        addMaterial(GTCMaterialIds.blackBronze,         3, ORDER_COMPAT + ORDER_SPECIAL, false);

        addMaterial(GTCMaterialIds.blackSteel,          4, ORDER_COMPAT + ORDER_SPECIAL, false);
        addMaterial(GTCMaterialIds.blueSteel,           4, ORDER_COMPAT + ORDER_SPECIAL, false);
        addMaterial(GTCMaterialIds.redSteel,            4, ORDER_COMPAT + ORDER_SPECIAL, false);
    }
}
