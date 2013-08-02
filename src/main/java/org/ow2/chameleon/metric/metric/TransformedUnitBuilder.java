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

import org.ow2.chameleon.metric.metric.converters.*;

/**
 * This class is the builder of transformedUnit class.
 *
 * @author clement
 */
public class TransformedUnitBuilder<Q extends Quantity<Q>> {

    private String name;
    private String symbol;

    private Unit<Q> from;

    private ConversionFunction conversion = Identity.IDENTITY;
    private boolean registerConverter = false;

    public TransformedUnitBuilder(Unit<Q> from) {
        this.from = from;
    }

    public TransformedUnitBuilder() {

    }

    public TransformedUnitBuilder<Q> from(Unit from) {
        this.from = from;
        return this;
    }

    public TransformedUnitBuilder<Q> symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public TransformedUnitBuilder<Q> name(String name) {
        this.name = name;
        return this;
    }

    public Unit<Q> build() {
        if (symbol == null) {
            throw new IllegalArgumentException("An unit must have a symbol");
        }

        if (conversion == Identity.IDENTITY) {
            return new Unit<Q>(symbol, name);
        } else {
            // Transformed unit
            Unit<Q> unit =  new TransformedUnit<Q>(symbol, name, from, conversion);
            if (registerConverter) {
                ConverterRegistry.addConverter(new FunctionBasedConverter<Q>(unit, from, conversion));
            }
            return unit;
        }
    }

    public TransformedUnitBuilder<Q> add(Number constant) {
        conversion = new Compound(conversion, new Addition(constant));
        return this;
    }

    public TransformedUnitBuilder<Q> times(Number constant) {
        conversion = new Compound(conversion, new Multiplication(constant));
        return this;
    }

    public TransformedUnitBuilder<Q> registerConverter() {
        registerConverter = true;
        return this;
    }
}
