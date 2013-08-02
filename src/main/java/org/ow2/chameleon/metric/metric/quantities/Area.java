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
import org.ow2.chameleon.metric.metric.Unit;
import org.ow2.chameleon.metric.metric.UnitBuilder;
import org.ow2.chameleon.metric.metric.units.SI;

/**
 * This class represents the Area quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Area extends Quantity<Area> {

    public static final Unit<Area> CUBIC_METER = new UnitBuilder<Area>()
            .from(SI.METER).times(SI.METER).times(SI.METER)
            .name("volume")
            .symbol("m3")
            .build();

    /**
     * @param number
     * @param unit
     */
    public Area(Number number, Unit<Area> unit) {
        super(number, unit);
    }

    public Area(Number number) {
        super(number, CUBIC_METER);
    }
}

