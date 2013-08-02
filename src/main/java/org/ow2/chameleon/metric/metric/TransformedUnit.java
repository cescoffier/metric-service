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
package org.ow2.chameleon.metric.metric;

import org.ow2.chameleon.metric.metric.converters.ConversionFunction;
import org.ow2.chameleon.metric.metric.converters.FunctionBasedConverter;
import org.ow2.chameleon.metric.metric.converters.QuantityConverter;

/**
 * This class allows to build transform units.
 *
 * @author clement
 */
public class TransformedUnit<Q extends Quantity<Q>> extends Unit<Q> {

    private final QuantityConverter<Q> converter;

    public TransformedUnit(String symbol, String name, Unit<Q> parent, ConversionFunction conversion) {
        this(symbol, name, parent, conversion, parent.getDimension());
    }

    public TransformedUnit(String symbol, String name, Unit<Q> parent, ConversionFunction conversion,
                           Dimension dimension) {
        super(symbol, name, dimension);
        this.converter = new FunctionBasedConverter<Q>(this, parent, conversion);
    }

    @Override
    public String toString() {
        return getSymbol();
    }

    public QuantityConverter<Q> getConverter() {
        return converter;
    }
}
