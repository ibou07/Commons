/*
 * Copyright (c) 2017-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.commons.io.serializer;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link StringSerializer} for objects of type {@code T}.
 *
 * @param <T> the type of (de)serialized objects
 */
@ParametersAreNonnullByDefault
public abstract class AbstractStringSerializer<T> implements StringSerializer<T> {

    /**
     * The serializer to use before applying a {@link String} encoding or decoding.
     */
    @Nonnull
    private final Serializer<T> serializer;

    /**
     * Constructs a new {@code Serializer}.
     *
     * @param serializer the serializer to use before applying a {@link String} encoding or decoding
     */
    protected AbstractStringSerializer(@Nonnull Serializer<T> serializer) {
        checkNotNull(serializer, "serializer");

        this.serializer = serializer;
    }

    @Nonnull
    @Override
    public String serialize(T t) {
        return encode(serializer.convert(t));
    }

    @Nonnull
    @Override
    public T deserialize(String data) {
        return serializer.revert(decode(data));
    }

    /**
     * Encodes a {@code byte} array into its literal representation.
     *
     * @param data a byte array
     *
     * @return the literal representation of the byte array
     */
    @Nonnull
    protected abstract String encode(byte[] data);

    /**
     * Decodes a {@code String} into its binary representation.
     *
     * @param data a string
     *
     * @return the binary representation of the string
     */
    @Nonnull
    protected abstract byte[] decode(String data);
}
