package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.registry.GTCRegistration;
import com.pouffydev.gtconstruct.registry.GTCSmeltery;
import com.pouffydev.gtconstruct.registry.GTCToolParts;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.data.model.MaterialModelBuilder;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.library.tools.part.MaterialItem;

public class GTCItemModelProv extends RegistrateItemModelProvider {
    private final ModelFile.UncheckedModelFile GENERATED = new ModelFile.UncheckedModelFile("item/generated");
    public GTCItemModelProv(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(GTCRegistration.REGISTRATE, output, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        part(GTCToolParts.fileHead);
        part(GTCToolParts.sawBlade);
        part(GTCToolParts.screwdriverTip);
        part(GTCToolParts.wirecutterClaws);
        part(GTCToolParts.wrenchHandle);
        part(GTCToolParts.plungerHead);

        cast(GTCSmeltery.fileHeadCast);
        cast(GTCSmeltery.sawBladeCast);
        cast(GTCSmeltery.screwdriverTipCast);
        cast(GTCSmeltery.wirecutterClawsCast);
        cast(GTCSmeltery.wrenchHandleCast);
    }

    @SuppressWarnings("deprecation") // no its not
    private ResourceLocation id(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem());
    }

    /** Generated item with a texture */
    private ItemModelBuilder generated(ResourceLocation item, ResourceLocation texture) {
        return getBuilder(item.toString()).parent(GENERATED).texture("layer0", texture);
    }

    /** Generated item with a texture */
    private ItemModelBuilder generated(ResourceLocation item, String texture) {
        return generated(item, new ResourceLocation(item.getNamespace(), texture));
    }

    /** Generated item with a texture */
    private ItemModelBuilder generated(ItemLike item, String texture) {
        return generated(id(item), texture);
    }

    /** Generated item with a texture */
    private ItemModelBuilder basicItem(ResourceLocation item, String texture) {
        return generated(item, "item/" + texture);
    }

    /** Generated item with a texture */
    private ItemModelBuilder basicItem(ItemLike item, String texture) {
        return basicItem(id(item), texture);
    }


    /* Parts */

    /** Creates a part model with the given texture */
    private MaterialModelBuilder<ItemModelBuilder> part(ResourceLocation part, String texture) {
        return withExistingParent(part.getPath(), "forge:item/default")
                .texture("texture", GTConstruct.id("item/tool/" + texture))
                .customLoader(MaterialModelBuilder::new);
    }

    /** Creates a part model in the parts folder */
    private MaterialModelBuilder<ItemModelBuilder> part(Item item, String texture) {
        return part(id(item), texture);
    }

    /** Creates a part model with the given texture */
    private MaterialModelBuilder<ItemModelBuilder> part(ItemObject<? extends MaterialItem> part, String texture) {
        return part(part.getId(), texture);
    }

    /** Creates a part model in the parts folder */
    private void part(ItemObject<? extends MaterialItem> part) {
        part(part, "parts/" + part.getId().getPath());
    }


    /** Creates models for the given cast object */
    private void cast(CastItemObject cast) {
        String name = cast.getName().getPath();
        basicItem(cast.getId(), "cast/" + name);
        basicItem(cast.getSand(), "sand_cast/" + name);
        basicItem(cast.getRedSand(), "red_sand_cast/" + name);
    }
}
