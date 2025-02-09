package com.pouffydev.gtconstruct.registry;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.ItemMaterialInfo;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.GTCModule;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.gregtechceu.gtceu.common.data.GTItems.materialInfo;
import static com.pouffydev.gtconstruct.registry.GTCRegistration.REGISTRATE;

public class GTCCommons extends GTCModule {

    public static ItemEntry<Item> ShapeExtruderPlungerHead = REGISTRATE.item("plunger_head_extruder_mold", Item::new)
            .lang("Extruder Mold (Plunger Head)")
            .onRegister(materialInfo(new ItemMaterialInfo(new MaterialStack(GTMaterials.Steel, GTValues.M * 4))))
            .register();

    public static ItemEntry<Item> ShapeExtruderSoftMalletHead = REGISTRATE.item("soft_mallet_head_extruder_mold", Item::new)
            .lang("Extruder Mold (Soft Mallet Head)")
            .onRegister(materialInfo(new ItemMaterialInfo(new MaterialStack(GTMaterials.Steel, GTValues.M * 4))))
            .register();

    public static ItemEntry<Item> ShapeExtruderToolBinding = REGISTRATE.item("tool_binding_extruder_mold", Item::new)
            .lang("Extruder Mold (Tool Binding)")
            .onRegister(materialInfo(new ItemMaterialInfo(new MaterialStack(GTMaterials.Steel, GTValues.M * 4))))
            .register();

    public static ItemEntry<Item> ShapeExtruderToughCollar = REGISTRATE.item("tough_collar_extruder_mold", Item::new)
            .lang("Extruder Mold (Tough Collar)")
            .onRegister(materialInfo(new ItemMaterialInfo(new MaterialStack(GTMaterials.Steel, GTValues.M * 4))))
            .register();

    public static void init() {

    }
}
