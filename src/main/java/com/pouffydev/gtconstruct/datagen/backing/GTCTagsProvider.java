package com.pouffydev.gtconstruct.datagen.backing;

import com.pouffydev.gtconstruct.GTConstruct;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;

import java.util.function.Function;
import java.util.stream.Stream;

public class GTCTagsProvider<T> {

    private RegistrateTagsProvider<T> provider;
    private Function<T, ResourceKey<T>> keyExtractor;

    public GTCTagsProvider(RegistrateTagsProvider<T> provider, Function<T, Holder.Reference<T>> refExtractor) {
        this.provider = provider;
        this.keyExtractor = refExtractor.andThen(Holder.Reference::key);
    }

    public GTCTagAppender<T> tag(TagKey<T> tag) {
        TagBuilder tagbuilder = getOrCreateRawBuilder(tag);
        return new GTCTagAppender<>(tagbuilder, keyExtractor, GTConstruct.MOD_ID);
    }

    public TagBuilder getOrCreateRawBuilder(TagKey<T> tag) {
        return provider.addTag(tag).getInternalBuilder();
    }

    public static class GTCTagAppender<T> extends TagsProvider.TagAppender<T> {

        private Function<T, ResourceKey<T>> keyExtractor;

        public GTCTagAppender(TagBuilder pBuilder, Function<T, ResourceKey<T>> pKeyExtractor, String modId) {
            super(pBuilder, modId);
            this.keyExtractor = pKeyExtractor;
        }

        public GTCTagAppender<T> add(T entry) {
            this.add(this.keyExtractor.apply(entry));
            return this;
        }

        @SafeVarargs
        public final GTCTagAppender<T> add(T... entries) {
            Stream.<T>of(entries)
                    .map(this.keyExtractor)
                    .forEach(this::add);
            return this;
        }

    }
}
