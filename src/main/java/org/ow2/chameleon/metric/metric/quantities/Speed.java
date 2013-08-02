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
 * This class represents the speed quantity. It defines its Unit, symbol name
 * and methods to initialize the quantity.
 *
 * @author jeremy.savonet@gmail.com
 */
public class Speed extends Quantity<Speed> {

    public static final Unit<Speed> SPEED = new UnitBuilder<Speed>()
            .from(SI.METER).pow(SI.SECOND, -1)
            .name("Speed")
            .symbol("m/s")
            .build();

    /**
     * @param number
     * @param unit
     */
    public Speed(Number number, Unit<Speed> unit) {
        super(Speed.class, number, unit);
    }

    public Speed(Number number) {
        super(Speed.class, number, SPEED);
    }
}