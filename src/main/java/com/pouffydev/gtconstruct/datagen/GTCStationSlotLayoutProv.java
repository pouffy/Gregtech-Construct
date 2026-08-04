package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.registry.GTCToolParts;
import com.pouffydev.gtconstruct.registry.GTCTools;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;

public class GTCStationSlotLayoutProv extends AbstractStationSlotLayoutProvider {
    public GTCStationSlotLayoutProv(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addLayouts() {
        defineModifiable(GTCTools.saw)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.sawBlade, 10, 20)
                .addInputItem(TinkerToolParts.toolHandle, 46, 56)
                .addInputItem(TinkerToolParts.toolBinding, 28, 38)
                .build();

        defineModifiable(GTCTools.screwdriver)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.screwdriverTip, 48, 26)
                .addInputItem(TinkerToolParts.toolBinding, 30, 44)
                .addInputItem(TinkerToolParts.toolHandle, 12, 62)
                .build();

        defineModifiable(GTCTools.file)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.fileHead, 48, 26)
                .addInputItem(TinkerToolParts.toolBinding, 30, 44)
                .addInputItem(TinkerToolParts.toolHandle, 12, 62)
                .build();

        defineModifiable(GTCTools.wirecutter)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.wirecutterClaws,   44, 29)
                .addInputItem(TinkerToolParts.toolBinding,  21, 52)
                .addInputItem(TinkerToolParts.toolHandle, 41, 49)
                .addInputItem(TinkerToolParts.toolHandle,   25, 20)
                .build();

        defineModifiable(GTCTools.wrench)
                .sortIndex(SORT_HARVEST)
                .addInputItem(TinkerToolParts.pickHead, 51, 34)
                .addInputItem(TinkerToolParts.adzeHead, 31, 22)
                .addInputItem(GTCToolParts.wrenchHandle, 22, 53)
                .build();

        defineModifiable(GTCTools.plunger)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.plungerHead, 48, 26)
                .addInputItem(TinkerToolParts.toolBinding, 30, 44)
                .addInputItem(TinkerToolParts.toolHandle, 12, 62)
                .build();

        defineModifiable(GTCTools.softMallet)
                .sortIndex(SORT_HARVEST)
                .addInputItem(GTCToolParts.softMalletHead, 48, 26)
                .addInputItem(TinkerToolParts.toolBinding, 30, 44)
                .addInputItem(TinkerToolParts.toolHandle, 12, 62)
                .build();

        defineModifiable(GTCTools.mortar)
                .sortIndex(SORT_HARVEST)
                .addInputItem(TinkerToolParts.adzeHead, 37, 33)
                .addInputItem(GTCToolParts.mortarBowl, 33, 53)
                .build();
    }

    @Override
    public String getName() {
        return "GregTech Construct Station Slot Layouts";
    }
}
