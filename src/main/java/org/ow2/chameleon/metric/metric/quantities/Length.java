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
 * This class represents the length quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Length extends Quantity<Length> {

    public static final Unit<Length> METER = SI.METER;

    public static final Unit<Length> KILOMETER = new TransformedUnitBuilder<Length>(METER)
            .times(1000)
            .symbol("km")
            .registerConverter()
            .build();

    public static final Unit<Length> HECTOMETER = new TransformedUnitBuilder<Length>(METER)
            .times(100)
            .symbol("hm")
            .registerConverter()
            .build();

    public static final Unit<Length> DECIMETER = new TransformedUnitBuilder<Length>(METER)
            .times(0.1)
            .symbol("dm")
            .registerConverter()
            .build();

    public static final Unit<Length> CENTIMETER = new TransformedUnitBuilder<Length>(METER)
            .times(0.01)
            .symbol("cm")
            .registerConverter()
            .build();

    public static final Unit<Length> MILLIMETER = new TransformedUnitBuilder<Length>(METER)
            .times(0.001)
            .symbol("mm")
            .registerConverter()
            .build();

    public static final Unit<Length> INCH = new TransformedUnitBuilder<Length>(CENTIMETER)
            .times(2.54)
            .symbol("in")
            .registerConverter()
            .build();

    public static final Unit<Length> FOOT = new TransformedUnitBuilder<Length>(INCH)
            .times(12)
            .symbol("ft")
            .registerConverter()
            .build();

    /**
     * @param number
     * @param unit
     */
    public Length(Number number, Unit<Length> unit) {
        super(Length.class, number, unit);
    }

    public Length(Quantity<Length> q) {
        super(Length.class, q.value(), q.unit());
    }

    public Length(Number number) {
        super(Length.class, number, METER);
    }

    public Length add(Length that) {
        Quantity<Length> l = this.add((Quantity<Length>) that);
        return new Length(l);
    }

    public Length sub(Length that) {
        Length s = new Length(-1 * that.value().doubleValue(), that.unit());
        return this.add(s);
    }
}
