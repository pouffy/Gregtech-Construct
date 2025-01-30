package com.pouffydev.gtconstruct.common.item;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.item.tool.IGTToolDefinition;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

public class ModifiableGTToolItem extends ModifiableItem implements IGTTool {
    protected final GTToolType toolType;
    private final IGTToolDefinition toolStats;

    public ModifiableGTToolItem(Properties properties, ToolDefinition toolDefinition, GTToolType type) {
        super(properties, toolDefinition);
        this.toolType = type;
        this.toolStats = type.toolDefinition;
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
        return toolType.electricTier > -1;
    }

    @Override
    public int getElectricTier() {
        return toolType.electricTier;
    }

    @Override
    public IGTToolDefinition getToolStats() {
        return toolStats;
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
