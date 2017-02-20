package com.github.doubledeath.hop.api.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doubledeath on 2/20/17.
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseMapper<F, T> {

    public T from(F from) {
        return from == null ? fromNull() : fromImpl(from);
    }

    public F to(T to) {
        return to == null ? toNull() : toImpl(to);
    }

    public T fromNull() {
        return null;
    }

    public F toNull() {
        return null;
    }

    public List<T> fromList(List<F> fromList) {
        List<T> toList = new ArrayList<>();

        if (fromList == null) {
            return toList;
        }

        fromList.forEach(from -> toList.add(fromImpl(from)));

        return toList;
    }

    public List<F> toList(List<T> toList) {
        List<F> fromList = new ArrayList<>();

        if (toList == null) {
            return fromList;
        }

        toList.forEach(to -> fromList.add(toImpl(to)));

        return fromList;
    }

    protected abstract T fromImpl(F from);

    protected abstract F toImpl(T to);

}
