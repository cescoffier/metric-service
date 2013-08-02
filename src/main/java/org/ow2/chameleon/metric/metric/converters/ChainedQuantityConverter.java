/*
 * Copyright 2013 OW2 Chameleon
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.ow2.chameleon.metric.metric.converters;

import org.ow2.chameleon.metric.metric.Quantity;
import org.ow2.chameleon.metric.metric.Unit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a chain of conversion.
 */
public class ChainedQuantityConverter<Q extends Quantity<Q>> implements QuantityConverter<Q> {

    private final LinkedList<QuantityConverter<Q>> chain;

    public ChainedQuantityConverter(List<QuantityConverter<Q>> chain) {
        this.chain = new LinkedList<QuantityConverter<Q>>(chain);
    }

    public QuantityConverter<Q> inverse() {
        List<QuantityConverter<Q>> reverse = new LinkedList<QuantityConverter<Q>>(chain);
        Collections.reverse(reverse);
        return new ChainedQuantityConverter<Q>(reverse);
    }

    public Quantity<Q> convert(Quantity<Q> quantity) {
        // We need to handle the case where the chain is empty.
        if (chain.size() > 0  && ! quantity.unit().equals(from())) {
            throw new IllegalArgumentException("The quantity " + quantity.toString() + " cannot be converter to " +
                    to().toString() + " - incompatible units (expected " + from().toString() + ")");
        }
        // Compose all converters
        Quantity<Q> converted = quantity;
        for (QuantityConverter<Q> converter : chain) {
            converted = converter.convert(converted);
        }
        return converted;
    }

    public Unit<Q> from() {
        if (chain.isEmpty()) {
            return null;
        } else {
            return chain.getFirst().from();
        }
    }

    public Unit<Q> to() {
        if (chain.isEmpty()) {
            return null;
        } else {
            return chain.getLast().to();
        }
    }
}
