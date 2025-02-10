package com.pouffydev.gtconstruct.common.material;

import com.google.common.base.Preconditions;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.api.GTConstructAPI;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.materials.definition.IMaterial;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class MaterialLinkRegistryManager implements IMaterialLinkRegistryManager {

    private static MaterialLinkRegistryManager INSTANCE;

    private final Object2ObjectMap<String, MaterialLinkRegistryImpl> registries = new Object2ObjectOpenHashMap<>();
    private final Int2ObjectMap<MaterialLinkRegistryImpl> networkIds = new Int2ObjectOpenHashMap<>();

    @Nullable
    private Collection<MaterialLink> registeredMaterialLinks;

    private final MaterialLinkRegistryImpl gregtechConstructRegistry = createInternalRegistry();

    private IMaterialLinkRegistryManager.Phase registrationPhase = IMaterialLinkRegistryManager.Phase.PRE;

    private MaterialLinkRegistryManager() {}

    public static MaterialLinkRegistryManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MaterialLinkRegistryManager();
        }
        return INSTANCE;
    }

    @Override
    public @NotNull MaterialLinkRegistry createRegistry(@NotNull String modid) {
        if (getPhase() != IMaterialLinkRegistryManager.Phase.PRE) {
            throw new IllegalStateException("Cannot create registries in phase " + getPhase());
        }

        Preconditions.checkArgument(!registries.containsKey(modid),
                "Material link registry already exists for modid %s", modid);
        MaterialLinkRegistryImpl registry = new MaterialLinkRegistryImpl(modid);
        registries.put(modid, registry);
        networkIds.put(registry.getNetworkId(), registry);
        return registry;
    }

    @Override
    public @NotNull MaterialLinkRegistry getRegistry(@NotNull String modid) {
        MaterialLinkRegistry registry = registries.get(modid);
        return registry != null ? registry : gregtechConstructRegistry;
    }

    @Override
    public @NotNull MaterialLinkRegistry getRegistry(int networkId) {
        MaterialLinkRegistry registry = networkIds.get(networkId);
        return registry != null ? registry : gregtechConstructRegistry;
    }

    @Override
    public @NotNull Collection<MaterialLinkRegistry> getRegistries() {
        if (getPhase() == IMaterialLinkRegistryManager.Phase.PRE) {
            throw new IllegalStateException("Cannot get all material link registries during phase " + getPhase());
        }
        return Collections.unmodifiableCollection(registries.values());
    }

    @Override
    public @NotNull Collection<MaterialLink> getRegisteredMaterialLinks() {
        if (registeredMaterialLinks == null || getPhase() != IMaterialLinkRegistryManager.Phase.FROZEN) {
            throw new IllegalStateException("Cannot retrieve all material links before registration");
        }
        return registeredMaterialLinks;
    }

    @Override
    public MaterialLink getMaterialLink(String name) {
        if (!name.isEmpty()) {
            String modid;
            String materialName;
            int index = name.indexOf(':');
            if (index >= 0) {
                modid = name.substring(0, index);
                materialName = name.substring(index + 1);
            } else {
                modid = GTConstruct.MOD_ID;
                materialName = name;
            }
            return getRegistry(modid).get(materialName);
        }
        return null;
    }

    @Override
    public MaterialLink getMaterialLink(Material material) {
        Collection<MaterialLink> links = GTConstructAPI.materialLinkManager.getRegisteredMaterialLinks();
        MaterialLink toReturn = null;
        for (MaterialLink link : links) {
            Material linkedGreg = link.getMaterialLinkInfo().getGregTechMaterial();
            if (material == linkedGreg) {
                toReturn = link;
            }
            if (toReturn != null) break;
        }
        return toReturn;
    }

    @Override
    public MaterialLink getMaterialLink(MaterialId materialId) {
        Collection<MaterialLink> links = GTConstructAPI.materialLinkManager.getRegisteredMaterialLinks();
        MaterialLink toReturn = null;
        for (MaterialLink link : links) {
            MaterialId linkedTinker = link.getMaterialLinkInfo().getTinkerMaterialId();
            if (materialId == linkedTinker) {
                toReturn = link;
            }
            if (toReturn != null) break;
        }
        return toReturn;
    }

    @Override
    public ResourceLocation getKey(MaterialLink materialLink) {
        return materialLink.getResourceLocation();
    }

    @Override
    public boolean isLinked(Material material) {
        boolean toReturn = false;
        Collection<MaterialLink> links = GTConstructAPI.materialLinkManager.getRegisteredMaterialLinks();
        for (MaterialLink link : links) {
            Material linkedGreg = link.getMaterialLinkInfo().getGregTechMaterial();
            toReturn = material == linkedGreg;
            if (toReturn) break;
        }
        return toReturn;
    }

    @Override
    public MaterialId getTinkerMaterial(Material material) {
        MaterialId toReturn = IMaterial.UNKNOWN_ID;
        Collection<MaterialLink> links = GTConstructAPI.materialLinkManager.getRegisteredMaterialLinks();
        for (MaterialLink link : links) {
            Material linkedGreg = link.getMaterialLinkInfo().getGregTechMaterial();
            if (material == linkedGreg) {
                toReturn = link.getMaterialLinkInfo().getTinkerMaterialId();
            }
            if (toReturn != IMaterial.UNKNOWN_ID) break;
        }
        return toReturn;
    }

    @Override
    public Material getGregMaterial(MaterialId materialId) {
        Material toReturn = GTMaterials.NULL;
        Collection<MaterialLink> links = GTConstructAPI.materialLinkManager.getRegisteredMaterialLinks();
        for (MaterialLink link : links) {
            MaterialId linkedTinker = link.getMaterialLinkInfo().getTinkerMaterialId();
            if (materialId == linkedTinker) {
                toReturn = link.getMaterialLinkInfo().getGregTechMaterial();
            }
            if (toReturn != GTMaterials.NULL) break;
        }
        return toReturn;
    }

    @Override
    public boolean hasStat(MaterialId materialId, MaterialStatsId statsId) {
        return getMaterialLink(materialId).getMaterialLinkInfo().getConnectedStats().contains(statsId);
    }

    @Override
    public @NotNull IMaterialLinkRegistryManager.Phase getPhase() {
        return registrationPhase;
    }

    public void unfreezeRegistries() {
        registries.values().forEach(MaterialLinkRegistryImpl::unfreeze);
        registrationPhase = IMaterialLinkRegistryManager.Phase.OPEN;
    }

    public void freezeRegistries() {
        Collection<MaterialLink> collection = new ArrayList<>();
        for (MaterialLinkRegistry registry : registries.values()) {
            collection.addAll(registry.getAllLinkedMaterials());
        }
        registeredMaterialLinks = Collections.unmodifiableCollection(collection);
        registries.values().forEach(MaterialLinkRegistryImpl::freeze);
        registrationPhase = IMaterialLinkRegistryManager.Phase.FROZEN;
    }

    @NotNull
    private MaterialLinkRegistryImpl createInternalRegistry() {
        MaterialLinkRegistryImpl registry = new MaterialLinkRegistryImpl(GTConstruct.MOD_ID);
        this.registries.put(GTConstruct.MOD_ID, registry);
        return registry;
    }
}
