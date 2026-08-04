package com.pouffydev.gtconstruct.client;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.pouffydev.gtconstruct.GTConstruct;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.TinkerTags;

import static com.pouffydev.gtconstruct.util.GTCToolHelper.getMaterialEntry;
import static com.pouffydev.gtconstruct.util.GTCToolHelper.getValidHazardMaterial;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = GTConstruct.MOD_ID, value = {Dist.CLIENT})
public class ForgeClientEvents {

    @SubscribeEvent
    public static void onTooltipEvent(ItemTooltipEvent event) {
        if (event.getItemStack().is(TinkerTags.Items.TOOL_PARTS)) {
            MaterialEntry materialEntry = getMaterialEntry(event.getItemStack());
            if (!materialEntry.isEmpty()) {
                String formula = materialEntry.material().getChemicalFormula();
                if (formula != null && !formula.isEmpty()) {
                    event.getToolTip().add(1, Component.literal(formula).withStyle(ChatFormatting.YELLOW));
                }
            }

            Material material = getValidHazardMaterial(event.getItemStack());
            if (!material.isNull()) {
                GTUtil.appendHazardTooltips(material, event.getToolTip());
            }
        }
    }
}
