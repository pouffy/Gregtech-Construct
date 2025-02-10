package com.pouffydev.gtconstruct.common.material;

import com.google.common.collect.ImmutableList;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
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

    private MaterialLink(@NotNull MaterialLinkInfo materialLinkInfo) {
        this.materialLinkInfo = materialLinkInfo;
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

    @Getter
    public static class MaterialLinkInfo {
        private final ResourceLocation resourceLocation;
        public MaterialId tinkerMaterialId = IMaterial.UNKNOWN_ID;
        public Material gregTechMaterial = GTMaterials.NULL;
        public Collection<MaterialStatsId> connectedStats;

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

        private List<MaterialStatsId> connectedStats = new ArrayList<>();
        private List<MaterialStatsIdWrapper> connectedStatsSupplier;

        public Builder(ResourceLocation resourceLocation) {
            super(resourceLocation);
            materialLinkInfo = new MaterialLink.MaterialLinkInfo(resourceLocation);
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
            connectedStats = Arrays.asList(statsIds);
            return this;
        }

        public Builder withStats(ImmutableList<MaterialStatsId> statsIds) {
            connectedStats = statsIds;
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

        public Builder kjs$withStats(MaterialStatsIdWrapper... materialStatsIdWrappers) {
            connectedStatsSupplier = Arrays.asList(materialStatsIdWrappers);
            return this;
        }

        public Builder kjs$withStats(ImmutableList<MaterialStatsIdWrapper> materialStatsIdWrappers) {
            connectedStatsSupplier = materialStatsIdWrappers;
            return this;
        }

        private boolean validateLink() {
            return materialLinkInfo.tinkerMaterialId != IMaterial.UNKNOWN_ID && materialLinkInfo.gregTechMaterial != GTMaterials.NULL;
        }

        @HideFromJS
        public MaterialLink buildAndRegister() {
            materialLinkInfo.connectedStats = connectedStats.isEmpty() && this.connectedStatsSupplier != null ? ImmutableList.copyOf(connectedStatsSupplier.stream().map(MaterialStatsIdWrapper::toMaterialStatsId).toArray(MaterialStatsId[]::new)) : ImmutableList.copyOf(connectedStats);
            if (!validateLink()) {
                throw new IllegalStateException("Material Link must have both Tinker and GregTech materials set!");
            }
            var matLink = new MaterialLink(materialLinkInfo);
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
