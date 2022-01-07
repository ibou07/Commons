/*
 * Copyright (c) 2022 Naomod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */
package org.atlanmod.testing.generator;
import org.atlanmod.testing.Generator;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 *
 * Generator class for values of the type Byte.
 *
 */
public class RandomByteGenerator implements Generator<Byte> {

    @Override
    /**
     * Generates random Byte values.
     *
     * @return a single Byte value
     */
    public Byte generate() {
        Random rd = new Random();
        byte[] arr = new byte[7];
        rd.nextBytes(arr);

        return arr[2];
    }

    @Override
    /**
     * Returns all of the variation of the Byte data type the current class is able to generate.
     *
     * @return an array of class types.
     */
    public Class<Byte>[] types() {
        Class[] types={Byte.class,byte.class};
        return types;
    }
}
