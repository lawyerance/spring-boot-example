package pers.lyks.example.util;

import java.util.function.Supplier;

/**
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
public final class SingletonSupplier<R> implements Supplier<R> {

    private Supplier<R> singleton;
    private R value;

    private SingletonSupplier(final Supplier<R> original) {
        this.singleton = () -> {
            synchronized (original) {
                if (value == null) {
                    value = original.get();
                    singleton = () -> value;
                }
                return value;
            }
        };
    }

    @Override
    public R get() {
        return value;
    }
}
