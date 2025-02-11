/*
 * Copyright (c) 2022 Naomod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */
package org.atlanmod.testing.generator;

import org.atlanmod.testing.Generator;
import java.util.Random;

/**
 *  * Generator class for String values composed of only numerical characters.
 *
 */
public class RandomStringOfIntGenerator implements Generator<String> {
    private Random random = new Random();

    @Override
    /**
     * Generates random String values composed of numerical characters.
     *
     * @return a single String value
     */
    public String generate() {
        int length= random.nextInt(10)+1;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomInt = random.nextInt(10) + 48;
            char generatedChar = (char)randomInt;
            sb.append(generatedChar);
        }
        return sb.toString();
    }

    @Override
    /**
     * Returns all of the variation of the String data type the current class is able to generate.
     *
     * @return an array of class types.
     */
    public Class[] types() {
        return new Class[]{String.class};
    }
}
