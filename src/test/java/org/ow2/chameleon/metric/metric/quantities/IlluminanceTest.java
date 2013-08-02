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
import org.ow2.chameleon.metric.metric.UnitBuilder;
import org.ow2.chameleon.metric.metric.units.Angle;
import org.ow2.chameleon.metric.metric.units.SI;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Checks the illuminance quantity.
 */
public class IlluminanceTest {


    @Test
    public void testIlluminance() {
        Illuminance illuminance1 = new Illuminance(10);

        Illuminance illuminance2 = new Illuminance(10, Illuminance.LUX);

        assertThat(illuminance1.getUnit().isCompatible(illuminance2.getUnit()));
        assertThat(illuminance1.getUnit().getDimension()).isEqualTo(illuminance2.getUnit().getDimension());

        Quantity<Illuminance> result = illuminance1.add(illuminance2);
        assertThat(result.getNumber()).isEqualTo(20.0);
        assertThat(result.getUnit()).isEqualTo(Illuminance.LUX);
    }

    @Test
    public void testAddingIlluminanceFromDerivedUnit() {
        Illuminance illuminance1 = new Illuminance(10);

        Quantity<Illuminance> illuminance2 = new Quantity<Illuminance>(Illuminance.class,10,
                new UnitBuilder<Illuminance>()
                        .from(SI.CANDELA).times(Angle.STERADIAN).pow(SI.METER, -2)
                        .name("lux")
                        .symbol("lx").build());

        assertThat(illuminance1.getUnit().isCompatible(illuminance2.getUnit()));
        assertThat(illuminance1.getUnit().getDimension()).isEqualTo(illuminance2.getUnit().getDimension());

        Quantity<Illuminance> result = illuminance1.add(illuminance2);
        assertThat(result.getNumber()).isEqualTo(20.0);
        assertThat(result.getUnit()).isEqualTo(Illuminance.LUX);
    }

    @Test
    public void testName() {
        Illuminance illuminance = new Illuminance(10);
        assertThat(illuminance.getQuantityName()).isEqualTo("Illuminance");
    }
}
