package com.pouffydev.gtconstruct.util;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.HazardProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.api.item.tool.ToolHelper;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.api.GTConstructAPI;
import com.pouffydev.gtconstruct.common.item.ModifiableGTToolItem;
import com.pouffydev.gtconstruct.common.material.IMaterialLinkRegistryManager;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.SafeClientAccess;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.EntityInteractionModifierHook;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.helper.TooltipBuilder;
import slimeknights.tconstruct.library.tools.item.IModifiableDisplay;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.List;

import static slimeknights.tconstruct.library.tools.helper.TooltipUtil.*;

public class GTCToolHelper {

    public static void damageItem(@NotNull ItemStack stack, @Nullable LivingEntity user, int damage) {
        if (!(stack.getItem() instanceof ModifiableGTToolItem tool) && (stack.getItem() instanceof IGTTool)) {
            ToolHelper.damageItem(stack, user, damage);
        } else {
            if (!(user instanceof Player player) || !player.isCreative()) {
                ToolStack toolStack = ToolStack.from(stack);
                if (damage <= 0) {
                    return;
                }
                int newDurability = stack.getDamageValue() + damage;
                if (user instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.ITEM_DURABILITY_CHANGED.trigger(serverPlayer, stack, newDurability);
                }
                ToolDamageUtil.damage(toolStack, damage, user, stack);
                if (newDurability > stack.getMaxDamage()) {
                    if (user != null) {
                        ToolDamageUtil.breakTool(toolStack.createStack());
                    }
                }
            }
        }
    }

    public static void addInformation(IModifiableDisplay item, ItemStack stack, @javax.annotation.Nullable Level world, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        Player player = world == null ? null : SafeClientAccess.getPlayer();
        addInformation(item, stack, player, tooltip, tooltipKey, tooltipFlag);
    }

    public static void addInformation(IModifiableDisplay item, ItemStack stack, @javax.annotation.Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        ToolDefinition definition = item.getToolDefinition();
        if (isDisplay(stack)) {
            ToolStack tool = ToolStack.from(stack);
            addModifierNames(stack, tool, player, tooltip, tooltipFlag);
        } else if (!definition.isDataLoaded()) {
            tooltip.add(TConstruct.makeTranslation("tooltip", "missing_data").withStyle(ChatFormatting.GRAY));
        } else if (!ToolStack.isInitialized(stack)) {
            tooltip.add(TConstruct.makeTranslation("tooltip", "uninitialized").withStyle(ChatFormatting.GRAY));
            if (definition.hasMaterials()) {
                CompoundTag nbt = stack.getTag();
                if (nbt == null || !nbt.contains("tic_materials", 9)) {
                    tooltip.add(TConstruct.makeTranslation("tooltip", "random_materials").withStyle(ChatFormatting.GRAY));
                }
            }
        } else {
            switch (tooltipKey) {
                case SHIFT:
                    item.getStatInformation(ToolStack.from(stack), player, tooltip, tooltipKey, tooltipFlag);
                    break;
                case CONTROL:
                    if (definition.hasMaterials()) {
                        getComponents(item, stack, tooltip, tooltipFlag);
                        break;
                    }
                default:
                    ToolStack tool = ToolStack.from(stack);
                    getDefaultInfo(stack, tool, player, tooltip, tooltipFlag);
            }
        }
    }

    public static void getDefaultInfo(ItemStack stack, IToolStackView tool, @javax.annotation.Nullable Player player, List<Component> tooltips, TooltipFlag flag) {
        if (tool.getItem().canBeDepleted() && !isUnbreakable(tool) && tool.hasTag(TinkerTags.Items.DURABILITY)) {
            tooltips.add(TooltipBuilder.formatDurability(tool.getCurrentDurability(), tool.getStats().getInt(ToolStats.DURABILITY), true));
        }

        addModifierNames(stack, tool, player, tooltips, flag);
        tooltips.add(Component.empty());
        tooltips.add(TOOLTIP_HOLD_SHIFT);
        if (tool.getDefinition().hasMaterials()) {
            tooltips.add(TOOLTIP_HOLD_CTRL);
        }

    }

    public static List<Component> getDefaultStats(IToolStackView tool, @javax.annotation.Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        TooltipBuilder builder = new TooltipBuilder(tool, tooltip);
        if (tool.getItem().canBeDepleted() && !isUnbreakable(tool) && tool.hasTag(TinkerTags.Items.DURABILITY)) {
            builder.addDurability();
        }
        boolean allowMelee = !tool.getVolatileData().getBoolean(EntityInteractionModifierHook.NO_MELEE);
        boolean meleePrimary = allowMelee && tool.hasTag(TinkerTags.Items.MELEE_PRIMARY);
        if (meleePrimary) {
            builder.addWithAttribute(ToolStats.ATTACK_DAMAGE, Attributes.ATTACK_DAMAGE);
            builder.add(ToolStats.ATTACK_SPEED);
        }

        if (tool.hasTag(TinkerTags.Items.RANGED)) {
            builder.add(ToolStats.DRAW_SPEED);
            builder.add(ToolStats.VELOCITY);
            if (tool.hasTag(TinkerTags.Items.LAUNCHERS)) {
                builder.add(ToolStats.PROJECTILE_DAMAGE);
            }

            builder.add(ToolStats.ACCURACY);
        }

        if (allowMelee && !meleePrimary && tool.hasTag(TinkerTags.Items.MELEE_WEAPON)) {
            builder.addWithAttribute(ToolStats.ATTACK_DAMAGE, Attributes.ATTACK_DAMAGE);
            builder.add(ToolStats.ATTACK_SPEED);
        }

        if (tool.hasTag(TinkerTags.Items.HARVEST)) {
            if (tool.hasTag(TinkerTags.Items.HARVEST_PRIMARY)) {
                builder.addTier();
            }

            builder.add(ToolStats.MINING_SPEED);
        }

        if (tool.hasTag(TinkerTags.Items.ARMOR)) {
            builder.addOptional(ToolStats.ARMOR);
            builder.addOptional(ToolStats.ARMOR_TOUGHNESS);
            builder.addOptional(ToolStats.KNOCKBACK_RESISTANCE, 10.0F);
        }

        if (tool.getModifierLevel(TinkerModifiers.blocking.getId()) > 0 || tool.getModifierLevel(TinkerModifiers.parrying.getId()) > 0) {
            builder.add(ToolStats.BLOCK_AMOUNT);
            builder.add(ToolStats.BLOCK_ANGLE);
        }

        builder.addAllFreeSlots();

        for(ModifierEntry entry : tool.getModifierList()) {
            entry.getHook(ModifierHooks.TOOLTIP).addTooltip(tool, entry, player, tooltip, key, flag);
        }

        return builder.getTooltips();
    }

    public static boolean isUnbreakable(IToolStackView toolStack) {
        return toolStack.isUnbreakable() || toolStack.getModifier(TinkerModifiers.unbreakable.getId()) != ModifierEntry.EMPTY;
    }

    public static Material getValidHazardMaterial(ItemStack part) {
        Material material = GTMaterials.NULL;
        Item var6 = part.getItem();
        IMaterialLinkRegistryManager registryManager = GTConstructAPI.materialLinkManager;
        if (var6 instanceof ToolPartItem partItem) {
            MaterialId materialId = partItem.getMaterial(part).getId();
            material = registryManager.getGregMaterial(materialId);
        }
        HazardProperty property = material.getProperty(PropertyKey.HAZARD);
        if (property == null) {
            return GTMaterials.NULL;
        } else {
            return !property.hazardTrigger.isAffected(TagPrefix.toolHeadScrewdriver) ? GTMaterials.NULL : material;
        }
    }

    public static MaterialEntry getMaterialEntry(ItemStack part) {
        Material material = GTMaterials.NULL;
        IMaterialLinkRegistryManager registryManager = GTConstructAPI.materialLinkManager;
        if (part.getItem() instanceof ToolPartItem partItem) {
            MaterialId materialId = partItem.getMaterial(part).getId();
            material = registryManager.getGregMaterial(materialId);
        }
        return new MaterialEntry(TagPrefix.toolHeadScrewdriver, material);
    }
}
