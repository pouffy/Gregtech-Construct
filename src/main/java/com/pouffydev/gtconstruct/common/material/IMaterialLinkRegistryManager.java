package com.pouffydev.gtconstruct.common.material;

import com.gregtechceu.gtceu.api.data.chemical.material.IMaterialRegistryManager;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

import java.util.Collection;
import java.util.Optional;

public interface IMaterialLinkRegistryManager {

    @NotNull
    MaterialLinkRegistry createRegistry(@NotNull String modid);

    @NotNull
    MaterialLinkRegistry getRegistry(@NotNull String modid);

    @NotNull
    MaterialLinkRegistry getRegistry(int networkId);

    @NotNull
    Collection<MaterialLinkRegistry> getRegistries();

    @NotNull
    Collection<MaterialLink> getRegisteredMaterialLinks();

    MaterialLink getMaterialLink(String name);

    MaterialLink getMaterialLink(Material material);

    MaterialLink getMaterialLink(MaterialId materialId);

    ResourceLocation getKey(MaterialLink materialLink);

    boolean isLinked(Material material);

    MaterialId getTinkerMaterial(Material material);

    Material getGregMaterial(MaterialId materialId);

    boolean hasStat(MaterialId materialId, MaterialStatsId statsId);

    @NotNull
    Phase getPhase();

    default boolean canModifyMaterials() {
        return this.getPhase() != IMaterialLinkRegistryManager.Phase.FROZEN && this.getPhase() != IMaterialLinkRegistryManager.Phase.PRE;
    }

    default Codec<MaterialLink> codec() {
        return ResourceLocation.CODEC
                .flatXmap(
                        id -> Optional.ofNullable(this.getRegistry(id.getNamespace()).get(id.getPath()))
                                .map(DataResult::success)
                                .orElseGet(() -> DataResult
                                        .error(() -> "Unknown registry key in material link registry: " + id)),
                        obj -> DataResult.success(obj.getResourceLocation()));
    }

    enum Phase {
        /** Material Link Registration is not started */
        PRE,
        /** Material Link Registration is available */
        OPEN,
        /** Material Registration is unavailable and only Modification is available */
        CLOSED,
        /** Material Link Registration is unavailable */
        FROZEN
    }
}
