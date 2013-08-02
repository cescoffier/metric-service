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

/**
 * This class represents a speed of data transmission in bit/s.
 * This class use the JEDEC memory standard (http://en.wikipedia.org/wiki/JEDEC_memory_standards).
 *
 * @author clement.escoffier@gmail.com
 */
public class DataRate extends Quantity<DataRate> {

    public static final Unit<DataRate> BIT_PER_SECOND = new Unit<DataRate>("b/s", "bit/second");

    public static final Unit<DataRate> KILOBIT_PER_SECOND = new TransformedUnitBuilder<DataRate>(BIT_PER_SECOND)
            .times(1024)
            .symbol("Kb/s")
            .registerConverter()
            .build();

    public static final Unit<DataRate> MEGABIT_PER_SECOND = new TransformedUnitBuilder<DataRate>(KILOBIT_PER_SECOND)
            .times(1024)
            .symbol("Mb/s")
            .registerConverter()
            .build();

    /**
     * @param number
     * @param unit
     */
    public DataRate(Number number, Unit<DataRate> unit) {
        super(number, unit);
    }

    public DataRate(Number number) {
        super(number, BIT_PER_SECOND);
    }
}
