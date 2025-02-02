package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.registry.GTCToolParts;
import com.pouffydev.gtconstruct.registry.GTCTools;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class GTCStationSlotLayoutProv extends AbstractStationSlotLayoutProvider {
    public GTCStationSlotLayoutProv(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addLayouts() {
        defineModifiable(GTCTools.saw)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.sawBlade,    53, 22)
                .addInputItem(TinkerToolParts.toolHandle,  15, 60)
                .addInputItem(TinkerToolParts.toolBinding, 33, 42)
                .build();

        defineModifiable(GTCTools.screwdriver)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.screwdriverTip, 39, 35)
                .addInputItem(TinkerToolParts.toolHandle, 21, 53)
                .addInputItem(TinkerToolParts.toolBinding, 57, 53)
                .build();
    }

    @Override
    public String getName() {
        return "GregTech Construct Station Slot Layouts";
    }
}
