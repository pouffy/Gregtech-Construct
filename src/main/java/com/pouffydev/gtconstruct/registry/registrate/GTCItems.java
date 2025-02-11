package com.pouffydev.gtconstruct.registry.registrate;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.ItemMaterialInfo;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.registry.GTCSmeltery;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.gregtechceu.gtceu.common.data.GTCreativeModeTabs.ITEM;
import static com.gregtechceu.gtceu.common.data.GTItems.materialInfo;
import static com.pouffydev.gtconstruct.registry.GTCRegistration.REGISTRATE;

public class GTCItems {

    public static final ItemEntry<Item>[] SHAPE_EXTRUDERS = new ItemEntry[4];
    public static ItemEntry<Item> ShapeExtruderPlungerHead;
    public static ItemEntry<Item> ShapeExtruderSoftMalletHead;
    public static ItemEntry<Item> ShapeExtruderToolBinding;
    public static ItemEntry<Item> ShapeExtruderToughCollar;

    static {
        SHAPE_EXTRUDERS[0] = ShapeExtruderPlungerHead = REGISTRATE.item("plunger_head_extruder_mold", Item::new)
                .lang("Extruder Mold (Plunger Head)")
                .onRegister(materialInfo(new ItemMaterialInfo(new MaterialStack(GTMaterials.Steel, GTValues.M * 4))))
                .register();

        SHAPE_EXTRUDERS[1] = ShapeExtruderSoftMalletHead = REGISTRATE.item("soft_mallet_head_extruder_mold", Item::new)
                .lang("Extruder Mold (Soft Mallet Head)")
                .onRegister(materialInfo(new ItemMaterialInfo(new MaterialStack(GTMaterials.Steel, GTValues.M * 4))))
                .register();

        SHAPE_EXTRUDERS[2] = ShapeExtruderToolBinding = REGISTRATE.item("tool_binding_extruder_mold", Item::new)
                .lang("Extruder Mold (Tool Binding)")
                .onRegister(materialInfo(new ItemMaterialInfo(new MaterialStack(GTMaterials.Steel, GTValues.M * 4))))
                .register();

        SHAPE_EXTRUDERS[3] = ShapeExtruderToughCollar = REGISTRATE.item("tough_collar_extruder_mold", Item::new)
                .lang("Extruder Mold (Tough Collar)")
                .onRegister(materialInfo(new ItemMaterialInfo(new MaterialStack(GTMaterials.Steel, GTValues.M * 4))))
                .register();
    }







    public static void init() {}
}
