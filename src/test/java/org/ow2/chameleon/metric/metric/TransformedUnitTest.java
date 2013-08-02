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

import org.ow2.chameleon.metric.metric.quantities.Temperature;
import org.ow2.chameleon.metric.metric.units.SI;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Check transformed units
 */
public class TransformedUnitTest {

    @Test
    public void testCelsius() {
        Unit celsius = new TransformedUnitBuilder<Temperature>(SI.KELVIN).add(-273.15).name("celsius").symbol("ºC")
                .build();
        assertThat(celsius.getDimension()).isEqualTo(Dimension.TEMPERATURE);
        assertThat(celsius.getSymbol()).isEqualTo("ºC");
        assertThat(celsius.getName()).isEqualTo("celsius");
    }

    @Test
    public void testFahrenheit() {
        Unit fahrenheit = new TransformedUnitBuilder<Temperature>(SI.KELVIN).times(9d / 5d).add(-459.67).name("fahrenheit")
                .symbol("ºF")
                .build();
        assertThat(fahrenheit.getDimension()).isEqualTo(Dimension.TEMPERATURE);
        assertThat(fahrenheit.getSymbol()).isEqualTo("ºF");
        assertThat(fahrenheit.getName()).isEqualTo("fahrenheit");
    }

    @Test
    public void testGrade() {
        Unit rad = new UnitBuilder().symbol("rad").name("radian").from(SI.METER).divide(SI.METER).build();
        assertThat(rad.getDimension()).isEqualTo(Dimension.NONE);

        Unit grad = new TransformedUnitBuilder(rad).symbol("gr").name("grade").times(Math.PI).times(2d).times
                (Math.pow(400d, -1)).build();

        assertThat(grad.getDimension()).isEqualTo(Dimension.NONE);
        assertThat(grad.getSymbol()).isEqualTo("gr");
    }
}
