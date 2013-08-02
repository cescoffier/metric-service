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
package org.ow2.chameleon.metric.metric.quantities;

import org.ow2.chameleon.metric.metric.Quantity;
import org.ow2.chameleon.metric.metric.TransformedUnitBuilder;
import org.ow2.chameleon.metric.metric.Unit;
import org.ow2.chameleon.metric.metric.units.SI;

/**
 * This class represents the mass quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Mass extends Quantity<Mass> {

    public static final Unit<Mass> GRAM = SI.GRAM;

    public static final Unit<Mass> KILOGRAM = new TransformedUnitBuilder<Mass>(GRAM)
            .times(1000)
            .symbol("kg")
            .registerConverter()
            .build();

    public static final Unit<Mass> HECTOGRAM = new TransformedUnitBuilder<Mass>(GRAM)
            .times(100)
            .symbol("hg")
            .registerConverter()
            .build();

    public static final Unit<Mass> DECIGRAM = new TransformedUnitBuilder<Mass>(GRAM)
            .times(0.1)
            .symbol("dg")
            .registerConverter()
            .build();

    public static final Unit<Mass> CENTIGRAM = new TransformedUnitBuilder<Mass>(GRAM)
            .times(0.01)
            .symbol("cg")
            .registerConverter()
            .build();

    public static final Unit<Mass> MILLIGRAM = new TransformedUnitBuilder<Mass>(GRAM)
            .times(0.001)
            .symbol("mg")
            .registerConverter()
            .build();

    public static final Unit<Mass> MICROGRAM = new TransformedUnitBuilder<Mass>(GRAM)
            .times(0.000001)
            .symbol("µg")
            .registerConverter()
            .build();

    /**
     * @param number
     * @param unit
     */
    public Mass(Number number, Unit<Mass> unit) {
        super(Mass.class, number, unit);
    }

    public Mass(Quantity<Mass> q) {
        super(Mass.class, q.value(), q.unit());
    }

    public Mass(Number number) {
        super(Mass.class, number, GRAM);
    }

    public Mass add(Mass that) {
        Quantity<Mass> l = this.add((Quantity<Mass>) that);
        return new Mass(l);
    }

    public Mass sub(Mass that) {
        Mass s = new Mass(-1 * that.value().doubleValue(), that.unit());
        return this.add(s);
    }
}
