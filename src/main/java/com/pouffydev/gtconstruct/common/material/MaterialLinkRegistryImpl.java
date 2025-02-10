package com.pouffydev.gtconstruct.common.material;

import com.gregtechceu.gtceu.GTCEu;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public class MaterialLinkRegistryImpl extends MaterialLinkRegistry {

    private static int networkIdCounter;

    private final int networkId = networkIdCounter++;
    private final java.lang.String modid;

    private boolean isRegistryClosed = false;

    protected MaterialLinkRegistryImpl(@NotNull java.lang.String modid) {
        super(modid);
        this.modid = modid;
    }

    @Override
    public void register(MaterialLink materialLink) {
        this.register(materialLink.getName(), materialLink);
    }

    @Override
    public <T extends MaterialLink> T register(@NotNull java.lang.String key, @NotNull T value) {
        if (isRegistryClosed) {
            GTCEu.LOGGER.error(
                    "Materials cannot be linked in the PostMaterialLinkEvent (or after)! Must be added in the MaterialLinkEvent. Skipping link {}...",
                    key);
            return null;
        }
        super.register(key, value);
        return value;
    }

    @NotNull
    @Override
    public Collection<MaterialLink> getAllLinkedMaterials() {
        return Collections.unmodifiableCollection(this.registry.values());
    }

    @Override
    public int getNetworkId() {
        return this.networkId;
    }

    @NotNull
    @Override
    public java.lang.String getModid() {
        return this.modid;
    }

    public void closeRegistry() {
        this.isRegistryClosed = true;
    }
}
