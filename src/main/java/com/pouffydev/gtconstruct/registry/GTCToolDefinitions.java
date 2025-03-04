package com.pouffydev.gtconstruct.registry;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.tools.TinkerTools;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GTCToolDefinitions {
    public static final ToolDefinition SAW = ToolDefinition.create(GTCTools.saw);
    public static final ToolDefinition SCREWDRIVER = ToolDefinition.create(GTCTools.screwdriver);
    public static final ToolDefinition FILE = ToolDefinition.create(GTCTools.file);
    public static final ToolDefinition WIRECUTTER = ToolDefinition.create(GTCTools.wirecutter);

    public static final ToolDefinition PLUNGER = ToolDefinition.create(GTCTools.plunger);
    public static final ToolDefinition SOFT_MALLET = ToolDefinition.create(GTCTools.softMallet);
}
