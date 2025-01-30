package com.pouffydev.gtconstruct.common.item;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.item.tool.IGTToolDefinition;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

public class ModifiableSawItem extends ModifiableItem implements IGTTool {
    protected final GTToolType toolType;

    public ModifiableSawItem(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);
        this.toolType = GTToolType.SAW;
    }

    @Override
    public GTToolType getToolType() {
        return toolType;
    }

    @Override
    public Material getMaterial() {
        return null;
    }

    @Override
    public boolean isElectric() {
        return false;
    }

    @Override
    public int getElectricTier() {
        return 0;
    }

    @Override
    public IGTToolDefinition getToolStats() {
        return null;
    }

    @Override
    public @Nullable SoundEntry getSound() {
        return toolType.soundEntry;
    }

    @Override
    public boolean playSoundOnBlockDestroy() {
        return toolType.playSoundOnBlockDestroy;
    }
}
