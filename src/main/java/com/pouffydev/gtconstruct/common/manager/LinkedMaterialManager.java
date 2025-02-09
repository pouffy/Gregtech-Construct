package com.pouffydev.gtconstruct.common.manager;

import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.common.crafting.conditions.ICondition;
import slimeknights.mantle.data.gson.ConditionSerializer;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.utils.Util;

import javax.annotation.Nullable;
import java.util.*;

@Log4j2
public class LinkedMaterialManager extends SimpleJsonResourceReloadListener {
    public static final String FOLDER = "tinkering/gtceu_material_links";
    public static final Gson GSON = (new GsonBuilder())
            .registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer())
            .registerTypeAdapter(ICondition.class, ConditionSerializer.INSTANCE)
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    private final Runnable onLoaded;

    protected Map<MaterialId, Material> materialLinks = Collections.emptyMap();
    @Setter
    private ICondition.IContext conditionContext = ICondition.IContext.EMPTY;

    public LinkedMaterialManager(Runnable onLoaded) {
        super(GSON, FOLDER);
        this.onLoaded = onLoaded;
    }

    @VisibleForTesting
    LinkedMaterialManager() {
        this(() -> {});
    }

    public Collection<MaterialId> getAllLinkedMaterials() {
        return new ArrayList<>(materialLinks.keySet());
    }

    public boolean isMaterialLinked(MaterialId materialId) {
        return materialLinks.containsKey(materialId);
    }

    public boolean isMaterialLinked(Material material) {
        return materialLinks.containsValue(material);
    }

    public boolean hasStat(MaterialId materialId, MaterialStatsId statsId) {
        return MaterialRegistry.getInstance().getMaterialStats(materialId, statsId).isPresent();
    }

    public boolean hasStat(Material material, MaterialStatsId statsId) {
        return MaterialRegistry.getInstance().getMaterialStats(getTinkerMaterial(material), statsId).isPresent();
    }


    public Material getGTMaterial(MaterialId materialId) {
        return materialLinks.get(materialId);
    }

    public MaterialId getTinkerMaterial(Material material) {
        for (Map.Entry<MaterialId, Material> entry : materialLinks.entrySet()) {
            if (entry.getValue().equals(material)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void updateMaterialLinksFromServer(Map<MaterialId, Material> materialLinks) {
        this.materialLinks = materialLinks;
        onUpdate();
    }

    private void onUpdate() {
        onLoaded.run();
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> splashList, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        long time = System.nanoTime();
        for (Map.Entry<ResourceLocation, JsonElement> entry : splashList.entrySet()) {
            ResourceLocation materialId = entry.getKey();
            JsonObject jsonObject = entry.getValue().getAsJsonObject();
            Material material = loadMaterial(materialId, jsonObject);
            MaterialId materialId1 = new MaterialId(entry.getKey().getNamespace(), entry.getKey().getPath());
            if (materialLinks.containsValue(material)) {
                log.warn("Duplicate material link for material {} in {}", material.getName(), materialId);
                continue;
            }
            if (material != null) {
                materialLinks.put(materialId1, material);
            }
        }
        onUpdate();
        log.debug("Loaded material links: {}", Util.toIndentedStringList(materialLinks.keySet()));
        long timeStep = System.nanoTime();
        log.debug("Loaded {} material links in {} ms", materialLinks.size(), (timeStep - time) / 1000000f);
    }

    @Nullable
    private Material loadMaterial(ResourceLocation materialId, JsonObject jsonObject) {
        try {
            MaterialLinkJson linkJson = GSON.fromJson(jsonObject, MaterialLinkJson.class);
            return GTMaterials.get(linkJson.getGregTechMaterial());
        } catch (Exception e) {
            throw new RuntimeException("Error linking material %s".formatted(materialId), e);
        }
    }
}
