package com.pouffydev.gtconstruct.common.item;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.api.item.component.ElectricStats;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.item.tool.IGTToolDefinition;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.pouffydev.gtconstruct.util.GTCToolHelper;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;

import static com.gregtechceu.gtceu.api.item.tool.ToolHelper.*;
import static com.gregtechceu.gtceu.api.item.tool.ToolHelper.HARVEST_LEVEL_KEY;

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

    @Override
    public boolean hasCraftingRemainingItem() {
        return super.hasCraftingRemainingItem();
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack itemStack, UseOnContext context) {
        super.onItemUseFirst(itemStack, context);
        return definition$onItemUseFirst(itemStack, context);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        return definition$onItemUse(context);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        return definition$mineBlock(stack, level, state, pos, miningEntity) && super.mineBlock(stack, level, state, pos, miningEntity);
    }

    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return definition$hasCraftingRemainingItem(stack);
    }

    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return definition$getCraftingRemainingItem(itemStack);
    }

    public static void damageItemWhenCrafting(@NotNull ItemStack stack, @Nullable LivingEntity entity) {
        int damage = ((IGTTool) stack.getItem()).getToolStats().getToolDamagePerCraft(stack);
        if (stack.getTags().anyMatch(s -> s.location().getPath().startsWith("tool") ||
                s.location().getPath().startsWith("crafting_tool"))) {
            damage = 1;
        }
        ToolStack toolStack = ToolStack.from(stack);
        ToolDamageUtil.damage(toolStack, damage, entity, stack);
    }

    public ItemStack definition$getCraftingRemainingItem(ItemStack stack) {
        if (!definition$hasCraftingRemainingItem(stack)) {
            return ItemStack.EMPTY;
        }
        stack = stack.copy();
        Player player = ForgeHooks.getCraftingPlayer();
        damageItemWhenCrafting(stack, player);
        playCraftingSound(player, stack);
        if (stack.isEmpty()) { // Equal to listening to PlayerDestroyItemEvent
            return getToolStats().getBrokenStack();
        }
        return stack;
    }

    @OnlyIn(Dist.CLIENT)
    public void definition$appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, TooltipFlag flag) {
        if (!(stack.getItem() instanceof IGTTool tool)) return;

        ToolStack toolStack = ToolStack.from(stack);

        CompoundTag tagCompound = stack.getTag();
        if (tagCompound == null) return;

        IGTToolDefinition toolStats = tool.getToolStats();

        // electric info
        if (this.isElectric()) {

            tooltip.add(Component.translatable("metaitem.generic.electric_item.tooltip",
                    FormattingUtil.formatNumbers(getCharge(stack)),
                    FormattingUtil.formatNumbers(getMaxCharge(stack)),
                    GTValues.VNF[getElectricTier()]));
            ElectricStats.addCurrentChargeTooltip(tooltip, getCharge(stack), getMaxCharge(stack), getElectricTier());
        }

        if (!tagCompound.getBoolean(UNBREAKABLE_KEY)) {
            // Plus 1 to match vanilla behavior where tools can still be used once at zero durability. We want to not
            // show this
            int damageRemaining = toolStack.getStats().getInt(ToolStats.DURABILITY) - stack.getDamageValue() + 1;
            if (toolStats.isSuitableForCrafting(stack)) {
                tooltip.add(Component.translatable("item.gtceu.tool.tooltip.crafting_uses", FormattingUtil.formatNumbers(toolStack.getCurrentDurability() / Math.max(1, toolStats.getToolDamagePerCraft(stack)))));
            }
            tooltip.add(Component.translatable("item.gtceu.tool.tooltip.max_uses", FormattingUtil.formatNumbers(toolStack.getStats().getInt(ToolStats.DURABILITY))));
            tooltip.add(Component.translatable("item.gtceu.tool.tooltip.general_uses", FormattingUtil.formatNumbers(damageRemaining)));
        }

        String uniqueTooltip = "item.gtceu.tool." + BuiltInRegistries.ITEM.getKey(this.asItem()).getPath() + ".tooltip";
        if (I18n.exists(uniqueTooltip)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable(uniqueTooltip));
        }

        tooltip.add(Component.literal(""));

        // valid tools
        tooltip.add(Component.translatable("item.gtceu.tool.usable_as",
                getToolClassNames(stack).stream()
                        .filter(s -> I18n.exists("gtceu.tool.class." + s))
                        .map(s -> Component.translatable("gtceu.tool.class." + s))
                        .collect(Component::empty, FormattingUtil::combineComponents,
                                FormattingUtil::combineComponents)));

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        definition$appendHoverText(stack, level, tooltipComponents, isAdvanced);
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
