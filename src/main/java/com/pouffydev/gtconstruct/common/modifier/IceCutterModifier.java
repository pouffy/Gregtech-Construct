package com.pouffydev.gtconstruct.common.modifier;

import com.gregtechceu.gtceu.api.item.IGTTool;
import net.minecraft.server.TickTask;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.List;

public class IceCutterModifier extends NoLevelsModifier implements BlockBreakModifierHook {
    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        if (tool.getModifierLevel(TinkerModifiers.silky.get()) == 0 && context.getState().is(BlockTags.ICE)) {
            Item iceBlock = context.getState().getBlock().asItem();
            List<ItemStack> drops = Block.getDrops(context.getState(), context.getWorld(), context.getPos(), null);
            if (drops.stream().noneMatch(drop -> drop.getItem() == iceBlock)) {
                drops.add(new ItemStack(iceBlock));
                context.getWorld().getServer().tell(new TickTask(0, () -> {
                    BlockState oldState = context.getWorld().getBlockState(context.getPos());
                    if (oldState.getFluidState().isSourceOfType(Fluids.WATER)) {
                        // I think it may be a waterlogged block, although the probability is very small
                        BlockState newState = oldState.hasProperty(BlockStateProperties.WATERLOGGED) ?
                                oldState.setValue(BlockStateProperties.WATERLOGGED, false) :
                                Blocks.AIR.defaultBlockState();
                        context.getWorld().setBlockAndUpdate(context.getPos(), newState);
                    }
                }));
                if (tool.getItem() instanceof IGTTool igtTool) {
                    igtTool.playSound(context.getPlayer());
                }
            }
        }
    }
}
