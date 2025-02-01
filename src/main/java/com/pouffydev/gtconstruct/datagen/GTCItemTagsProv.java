package com.pouffydev.gtconstruct.datagen;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.registry.GTCTools;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class GTCItemTagsProv extends ItemTagsProvider {


    public GTCItemTagsProv(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, GTConstruct.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(TagUtil.createItemTag("tools/saws", false))
                .add(GTCTools.saw.asItem());
        this.tag(TagUtil.createItemTag("tools/screwdrivers", false))
                .add(GTCTools.screwdriver.asItem());
    }
}
