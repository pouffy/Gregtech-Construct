package com.pouffydev.gtconstruct.datagen;

import com.google.gson.JsonObject;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.registry.GTCTools;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.library.data.AbstractToolItemModelProvider;

import java.io.IOException;

import static slimeknights.tconstruct.TConstruct.getResource;

public class GTCToolItemModelProv  extends AbstractToolItemModelProvider {
    public GTCToolItemModelProv(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, existingFileHelper, GTConstruct.MOD_ID);
    }

    @Override
    protected void addModels() throws IOException {
        JsonObject toolBlocking = readJson(getResource("base/tool_blocking"));

        tool(GTCTools.saw, toolBlocking, "head");
        tool(GTCTools.screwdriver, toolBlocking, "head");
        tool(GTCTools.file, toolBlocking, "head");
    }

    @Override
    public String getName() {
        return "GregTech Construct Tool Item Models";
    }
}
