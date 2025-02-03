package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.GTConstruct;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class GTCMaterialIds {

    // Alloys
    public static final MaterialId sterlingSilver = id("sterling_silver"); // Ag x4, Cu x1
    public static final MaterialId blackBronze = id("black_bronze"); // Au x1, Ag x1, Cu x3
    public static final MaterialId bismuthBronze = id("bismuth_bronze"); // Bi x1, Zn x1, Cu x3
    public static final MaterialId cupronickel = id("cupronickel"); // Ni x1, Cu x1
    public static final MaterialId blackSteel = id("black_steel"); // AuAgCu3 x1, Ni x1, Fe x3
    public static final MaterialId blueSteel = id("blue_steel"); // CuAg4 x1, BiZnCu3 x1, Ni(AuAgCu3)Fe3 x 4, Fe x 2
    public static final MaterialId redSteel = id("red_steel"); // CuAu4 x1, ZnCu3 x1, Ni(AuAgCu3)Fe3 x 4, Fe x 2

    // Rubbers & Plastics (No Composition Comments becuz complex)
    public static final MaterialId siliconeRubber = id("silicone_rubber");
    public static final MaterialId styreneButadieneRubber = id("styrene_butadiene_rubber");
    public static final MaterialId polybenzimidazole = id("polybenzimidazole");
    public static final MaterialId polyethylene = id("polyethylene");
    public static final MaterialId polytetrafluoroethylene = id("polytetrafluoroethylene");
    public static final MaterialId rubber = id("rubber");

    // Metals
    public static final MaterialId bismuth = id("bismuth");

    private static MaterialId id(String name) {
        return new MaterialId(GTConstruct.MOD_ID, name);
    }
}
