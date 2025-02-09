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
                .addInputItem(GTCToolParts.screwdriverTip, 53, 22)
                .addInputItem(TinkerToolParts.toolBinding, 15, 60)
                .addInputItem(TinkerToolParts.toolHandle, 33, 42)
                .build();

        defineModifiable(GTCTools.file)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.fileHead, 53, 22)
                .addInputItem(TinkerToolParts.toolBinding, 15, 60)
                .addInputItem(TinkerToolParts.toolHandle, 33, 42)
                .build();

        defineModifiable(GTCTools.wirecutter)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.wirecutterClaws,   44, 29)
                .addInputItem(TinkerToolParts.toolBinding,  21, 52)
                .addInputItem(TinkerToolParts.toolHandle, 41, 49)
                .addInputItem(TinkerToolParts.toolHandle,   25, 20)
                .build();

        defineModifiable(GTCTools.plunger)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.plungerHead, 53, 22)
                .addInputItem(TinkerToolParts.toolBinding, 15, 60)
                .addInputItem(TinkerToolParts.toolHandle, 33, 42)
                .build();

        defineModifiable(GTCTools.softMallet)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.softMalletHead, 53, 22)
                .addInputItem(TinkerToolParts.toolBinding, 15, 60)
                .addInputItem(TinkerToolParts.toolHandle, 33, 42)
                .build();
    }

    @Override
    public String getName() {
        return "GregTech Construct Station Slot Layouts";
    }
}
