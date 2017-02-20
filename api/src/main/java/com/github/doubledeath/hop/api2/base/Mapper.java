package com.github.doubledeath.hop.api2.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doubledeath on 2/20/17.
 */
@SuppressWarnings({
        "WeakerAccess",
        "unused"
})
public abstract class Mapper<F, T> {

    public F from(T to) {
        return to == null ? null : fromImpl(to);
    }

    public T to(F from) {
        return from == null ? null : toImpl(from);
    }

    public List<F> fromList(List<T> toList) {
        List<F> fromList = new ArrayList<>();

        if (toList == null) {
            return fromList;
        }

        toList.forEach(to -> fromList.add(fromImpl(to)));

        return fromList;
    }

    public List<T> toList(List<F> fromList) {
        List<T> toList = new ArrayList<>();

        if (fromList == null) {
            return toList;
        }

        fromList.forEach(from -> toList.add(toImpl(from)));

        return toList;
    }

    protected abstract F fromImpl(T from);

    protected abstract T toImpl(F to);

}
