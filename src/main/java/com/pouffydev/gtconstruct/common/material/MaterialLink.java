package com.pouffydev.gtconstruct.common.material;

import com.google.common.collect.ImmutableList;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.registry.registrate.BuilderBase;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.api.GTConstructAPI;
import com.pouffydev.gtconstruct.integration.kubejs.helper.MaterialStatsIdWrapper;
import com.pouffydev.gtconstruct.integration.kubejs.helper.MaterialWrapper;
import com.pouffydev.gtconstruct.integration.kubejs.helper.TinkerMaterialIdWrapper;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.RemapPrefixForJS;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.materials.definition.IMaterial;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MaterialLink implements Comparable<MaterialLink> {
    @NotNull
    private final MaterialLinkInfo materialLinkInfo;

    @NotNull
    private final ConnectedMaterialStats stats;

    private MaterialLink(@NotNull MaterialLinkInfo materialLinkInfo, @NotNull ConnectedMaterialStats stats) {
        this.materialLinkInfo = materialLinkInfo;
        this.stats = stats;
    }

    protected void registerMaterialLink() {
        GTConstructAPI.materialLinkManager.getRegistry(getModid()).register(this);
    }

    public String getName() {
        return materialLinkInfo.getResourceLocation().getPath();
    }

    public String getModid() {
        return materialLinkInfo.getResourceLocation().getNamespace();
    }

    @NotNull
    public ResourceLocation getResourceLocation() {
        return materialLinkInfo.getResourceLocation();
    }

    @Override
    public int compareTo(@NotNull MaterialLink materialLink) {
        return toString().compareTo(materialLink.toString());
    }

    @Override
    public String toString() {
        return getModid() + ":" + getName();
    }

    public void addStats(ResourceLocation... stats) {
        if (!GTConstructAPI.materialLinkManager.canModifyMaterials()) throw new IllegalStateException("Cannot add stats to material link when registry is frozen!");
        this.stats.addStats(stats).verify(this);
    }

    public boolean hasStat(ResourceLocation stat) {
        return stats.hasStat(stat);
    }

    public boolean hasStats(ResourceLocation... stats) {
        return Arrays.stream(stats).allMatch(this::hasStat);
    }

    public boolean hasAnyOfStats(ResourceLocation... stats) {
        return Arrays.stream(stats).anyMatch(this::hasStat);
    }

    @Getter
    public static class MaterialLinkInfo {
        private final ResourceLocation resourceLocation;
        public MaterialId tinkerMaterialId = IMaterial.UNKNOWN_ID;
        public Material gregTechMaterial = GTMaterials.NULL;

        private MaterialLinkInfo(ResourceLocation resourceLocation) {
            this.resourceLocation = resourceLocation;
        }

        public MaterialLink.MaterialLinkInfo setTinkerMaterial(final ResourceLocation tinkerMaterialId) {
            this.tinkerMaterialId = new MaterialId(tinkerMaterialId);
            return this;
        }

        public MaterialLink.MaterialLinkInfo setGregTechMaterial(final Material gregTechMaterial) {
            this.gregTechMaterial = gregTechMaterial;
            return this;
        }
    }

    @RemapPrefixForJS("kjs$")
    public static class Builder extends BuilderBase<MaterialLink> {

        private final MaterialLink.MaterialLinkInfo materialLinkInfo;

        private final ConnectedMaterialStats stats;

        //private List<MaterialStatsId> connectedStats = new ArrayList<>();
        //private List<MaterialStatsIdWrapper> connectedStatsSupplier;

        public Builder(ResourceLocation resourceLocation) {
            super(resourceLocation);
            materialLinkInfo = new MaterialLink.MaterialLinkInfo(resourceLocation);
            stats = new ConnectedMaterialStats();
        }

        public Builder setTinkerMaterial(ResourceLocation tinkerMaterialId) {
            materialLinkInfo.setTinkerMaterial(tinkerMaterialId);
            return this;
        }

        public Builder setGregTechMaterial(Material gregTechMaterial) {
            materialLinkInfo.setGregTechMaterial(gregTechMaterial);
            return this;
        }

        public Builder withStats(MaterialStatsId... statsIds) {
            stats.addStats(statsIds);
            return this;
        }

        public Builder kjs$setTinkerMaterial(TinkerMaterialIdWrapper tinkerMaterialId) {
            materialLinkInfo.setTinkerMaterial(tinkerMaterialId.materialId());
            return this;
        }

        public Builder kjs$setGregTechMaterial(MaterialWrapper gregTechMaterial) {
            materialLinkInfo.setGregTechMaterial((gregTechMaterial.toMaterial()));
            return this;
        }

        public Builder kjs$withStats(ResourceLocation... statsRls) {
            stats.addStats(statsRls);
            return this;
        }

        public Builder kjs$withStats(ImmutableList<ResourceLocation> statsRls) {
            stats.addStats(statsRls);
            return this;
        }

        private boolean validateLink() {
            return materialLinkInfo.tinkerMaterialId != IMaterial.UNKNOWN_ID && materialLinkInfo.gregTechMaterial != GTMaterials.NULL;
        }

        @HideFromJS
        public MaterialLink buildAndRegister() {
            if (!validateLink()) {
                throw new IllegalStateException("Material Link must have both Tinker and GregTech materials set!");
            }
            var matLink = new MaterialLink(materialLinkInfo, stats);
            matLink.registerMaterialLink();
            return matLink;
        }

        @Override
        public MaterialLink register() {
            return value = buildAndRegister();
        }
    }

    @NotNull
    public MaterialLinkInfo getMaterialLinkInfo() {
        return this.materialLinkInfo;
    }
}
