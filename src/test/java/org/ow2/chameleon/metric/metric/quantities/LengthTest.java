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

import org.ow2.chameleon.metric.metric.units.SI;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: clement
 * Date: 12/07/13
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
public class LengthTest {


    @Test
    public void testLengthsUsingSameUnit() {
        Length l1 = new Length(10);
        Length l2 = new Length(10, SI.METER);
        Length l3 = new Length(10, Length.METER);

        assertThat(l1.value()).isEqualTo(10);
        assertThat(l2.value()).isEqualTo(10);
        assertThat(l3.value()).isEqualTo(10);

        assertThat(l1.getNumber()).isEqualTo(10);
        assertThat(l2.getNumber()).isEqualTo(10);
        assertThat(l3.getNumber()).isEqualTo(10);

        assertThat(l1.getNormalizedQuantity().getNumber()).isEqualTo(10);
        assertThat(l2.getNormalizedQuantity().getNumber()).isEqualTo(10);
        assertThat(l3.getNormalizedQuantity().getNumber()).isEqualTo(10);

        assertThat(l1.getNormalizedQuantity().getUnit()).isEqualTo(SI.METER);
        assertThat(l2.getNormalizedQuantity().getUnit()).isEqualTo(SI.METER);
        assertThat(l3.getNormalizedQuantity().getUnit()).isEqualTo(SI.METER);

        // Additions

        Length l12 = l1.add(l2);
        Length l21 = l2.add(l1);

        assertThat(l12.getNumber()).isEqualTo(20.0);
        assertThat(l21.getNumber()).isEqualTo(20.0);
        assertThat(l12.getUnit()).isEqualTo(SI.METER);
        assertThat(l21.getUnit()).isEqualTo(SI.METER);

        // Subtractions

        Length l_12 = l1.sub(l2);
        Length l_21 = l2.sub(l1);

        assertThat(l_12.getNumber()).isEqualTo(0.0);
        assertThat(l_21.getNumber()).isEqualTo(0.0);
        assertThat(l_12.getUnit()).isEqualTo(SI.METER);
        assertThat(l_21.getUnit()).isEqualTo(SI.METER);
    }

    @Test
    public void testLengthsFromDifferentUnits() {
        Length l1 = new Length(1000);
        Length l2 = new Length(1, Length.KILOMETER);

        assertThat(l2.getNumber()).isEqualTo(1);
        assertThat(l2.getNormalizedQuantity().getNumber()).isEqualTo(1000.0);

        Length l3 = l1.add(l2);
        assertThat(l3.getNumber()).isEqualTo(2000.0);
        assertThat(l3.getUnit()).isEqualTo(SI.METER);

        assertThat(l3.as(Length.KILOMETER).getNumber()).isEqualTo(2.0);
        assertThat(l1.as(Length.KILOMETER).getNumber()).isEqualTo(1.0);
    }

    @Test
    public void testConversionBetweenMetricLengths() {

        Length k = new Length(1, Length.KILOMETER);
        Length m = new Length(1, Length.METER);
        Length d = new Length(2, Length.DECIMETER);

        assertThat(k.as(Length.HECTOMETER).getNumber()).isEqualTo(10.0);
        assertThat(d.as(Length.CENTIMETER).getNumber()).isEqualTo(20.0);
        assertThat(d.as(Length.MILLIMETER).getNumber()).isEqualTo(200.0);

        assertThat(d.as(Length.KILOMETER).getNumber().doubleValue()).isEqualTo(0.0002);
        assertThat(m.as(Length.KILOMETER).getNumber().doubleValue()).isEqualTo(0.001);

    }

    @Test
    public void testConversionBetweenMetricAndImperialLengths() {

        Length i = new Length(1, Length.INCH);
        Length f = new Length(1, Length.FOOT);

        Length c = new Length(1, Length.CENTIMETER);

        assertThat(i.as(Length.CENTIMETER).getNumber()).isEqualTo(2.54);
        assertThat(f.as(Length.CENTIMETER).getNumber()).isEqualTo(2.54 * 12);

        assertThat(c.as(Length.INCH).getNumber()).isEqualTo(1/2.54);


    }
}
