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
 * This class represents an amount of data quantity in bit.
 * This class use the JEDEC memory standard (http://en.wikipedia.org/wiki/JEDEC_memory_standards).
 *
 * @author clement.escoffier@gmail.com
 */
public class Data extends Quantity<Data> {

    public static final Unit<Data> BIT = new Unit<Data>("b", "bit");

    public static final Unit<Data> KILOBIT = new TransformedUnitBuilder<Data>(BIT)
            .times(1024)
            .symbol("Kb")
            .registerConverter()
            .build();

    public static final Unit<Data> MEGABIT = new TransformedUnitBuilder<Data>(KILOBIT)
            .times(1024)
            .symbol("Mb")
            .registerConverter()
            .build();

    public static final Unit<Data> GIGABIT = new TransformedUnitBuilder<Data>(MEGABIT)
            .times(1024)
            .symbol("Gb")
            .registerConverter()
            .build();

    public static final Unit<Data> TERAGABIT = new TransformedUnitBuilder<Data>(GIGABIT)
            .times(1024)
            .symbol("Tb")
            .registerConverter()
            .build();

    /**
     * @param number
     * @param unit
     */
    public Data(Number number, Unit<Data> unit) {
        super(number, unit);
    }

    public Data(Number number) {
        super(number, BIT);
    }
}
