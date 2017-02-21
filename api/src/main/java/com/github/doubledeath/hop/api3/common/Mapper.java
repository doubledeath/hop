package com.github.doubledeath.hop.api3.common;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by doubledeath on 2/20/17.
 */
@SuppressWarnings({
        "WeakerAccess",
        "unused"
})
public abstract class Mapper<F, T> {

    @NotNull
    public F from(@NotNull T to) {
        return fromImpl(to);
    }

    @NotNull
    public T to(@NotNull F from) {
        return toImpl(from);
    }

    @NotNull
    public List<F> fromList(@NotNull List<T> toList) {
        return toList
                .stream()
                .map(this::fromImpl)
                .collect(Collectors.toList());
    }

    @NotNull
    public List<T> toList(@NotNull List<F> fromList) {
        return fromList
                .stream()
                .map(this::toImpl)
                .collect(Collectors.toList());
    }

    @NotNull
    protected abstract F fromImpl(@NotNull T from);

    @NotNull
    protected abstract T toImpl(@NotNull F to);

}
