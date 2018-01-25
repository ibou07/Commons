/*
 * Copyright (c) 2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.commons.function;

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A specialized {@link Function} that converts an object of type {@code T} to another of type {@code R}.
 * <p>
 * The reverse operation <b>may</b> be strict <i>inverse</i>, meaning that {@code converter.revert(converter.convert(a)).equals(a)}
 * always {@code true}.
 *
 * @param <T> the type of the input to the converter
 * @param <R> the type of the result of the converter
 *
 * @see Function
 */
@ParametersAreNonnullByDefault
public interface Converter<T, R> extends Function<T, R> {

    /**
     * Creates a converter that always converts or reverses an object to itself.
     *
     * @param <T> the type of the converted instance and the result
     *
     * @return a new converter
     */
    @Nonnull
    static <T> Converter<T, T> identity() {
        return new Converter<T, T>() {
            @Override
            public T convert(T t) {
                return t;
            }

            @Override
            public T revert(T t) {
                return t;
            }
        };
    }

    /**
     * Creates a converter based on separate forward and backward functions.
     *
     * @param convertFunc the function used for {@link #convert(Object)}
     * @param revertFunc  the function used for {@link #revert(Object)}
     * @param <T>         the type of the input to the converter
     * @param <R>         the type of the result of the converter
     *
     * @return a new converter
     */
    @Nonnull
    static <T, R> Converter<T, R> from(Function<? super T, ? extends R> convertFunc, Function<? super R, ? extends T> revertFunc) {
        return new Converter<T, R>() {
            @Override
            public R convert(T t) {
                return convertFunc.apply(t);
            }

            @Override
            public T revert(R r) {
                return revertFunc.apply(r);
            }
        };
    }

    @Override
    default R apply(T t) {
        return convert(t);
    }

    /**
     * Returns a representation of {@code t} as an instance of type {@code R}.
     *
     * @param t the instance to convert
     *
     * @return the converted instance
     */
    R convert(T t);

    /**
     * Returns a representation of {@code r} as an instance of type {@code T}.
     *
     * @param r the instance to convert
     *
     * @return the converted instance
     */
    T revert(R r);

    /**
     * Returns the reversed converter of this converter.
     *
     * @return the reversed converter
     */
    @Nonnull
    default Converter<R, T> reverse() {
        return from(this::revert, this::convert);
    }
}
