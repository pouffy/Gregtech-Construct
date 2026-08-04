package com.pouffydev.gtconstruct.common;

import com.gregtechceu.gtceu.api.capability.GTCapabilityHelper;
import com.gregtechceu.gtceu.api.capability.IMedicalConditionTracker;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.HazardProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.pouffydev.gtconstruct.GTConstruct;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import slimeknights.tconstruct.common.TinkerTags;

import static com.pouffydev.gtconstruct.util.GTCToolHelper.getValidHazardMaterial;

@Mod.EventBusSubscriber(modid = GTConstruct.MOD_ID)
public class ForgeCommonEvents {

    @SubscribeEvent
    public static void tickPlayerInventoryHazards(TickEvent.PlayerTickEvent event) {
        if (event.side != LogicalSide.CLIENT && event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            IMedicalConditionTracker tracker = GTCapabilityHelper.getMedicalConditionTracker(player);
            if (tracker != null) {
                if (ConfigHolder.INSTANCE.gameplay.hazardsEnabled) {
                    IItemHandler inventory = player.getCapability(ForgeCapabilities.ITEM_HANDLER, null).resolve().orElse(null);
                    if (inventory != null) {
                        tracker.tick();
                        for(int i = 0; i < inventory.getSlots(); ++i) {
                            ItemStack stack = inventory.getStackInSlot(i);
                            if (stack.is(TinkerTags.Items.TOOL_PARTS)) {
                                Material material = getValidHazardMaterial(stack);
                                if (!material.isNull() && material.hasProperty(PropertyKey.HAZARD)) {
                                    HazardProperty property = material.getProperty(PropertyKey.HAZARD);
                                    if (property.hazardTrigger.protectionType().isProtected(player)) {
                                        property.hazardTrigger.protectionType().damageEquipment(player, 1);
                                    } else {
                                        tracker.progressRelatedCondition(material);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
