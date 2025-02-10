package com.pouffydev.gtconstruct.common.material;

import com.gregtechceu.gtceu.api.registry.GTRegistry;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.pouffydev.gtconstruct.registry.GTCRegistration;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@Getter
public abstract class MaterialLinkRegistry extends GTRegistry.String<MaterialLink> {
    private final GTRegistrate registrate;

    public MaterialLinkRegistry(java.lang.String modId) {
        super(new ResourceLocation(modId, "material_link"));
        this.registrate = GTCRegistration.REGISTRATE;
    }

    public abstract void register(MaterialLink materialLink);

    @NotNull
    public abstract Collection<MaterialLink> getAllLinkedMaterials();

    public abstract int getNetworkId();

    @NotNull
    public abstract java.lang.String getModid();

}
