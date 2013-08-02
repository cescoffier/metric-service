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

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Test the data units
 */
public class DataTest {

    @Test
    public void testBit() {
        Data data = new Data(10);
        Data data2 = new Data(10, Data.BIT);

        assertThat(data.getUnit().getSymbol()).isEqualTo("b");
        assertThat(data2.getUnit().getSymbol()).isEqualTo("b");
        assertThat(data.getUnit().getName()).isEqualTo("bit");
    }

    @Test
    public void testKbAndConversion() {
        Data data = new Data(2048);
        Data data2 = new Data(2, Data.KILOBIT);

        assertThat(data.getUnit().getSymbol()).isEqualTo("b");
        assertThat(data2.getUnit().getSymbol()).isEqualTo("Kb");

        assertThat(data.as(Data.KILOBIT).unit().getSymbol()).isEqualTo("Kb");
        assertThat(data.as(Data.KILOBIT).value()).isEqualTo(2d);

        assertThat(data2.as(Data.BIT).value()).isEqualTo(2048d);
    }

    @Test
    public void testMbAndConversion() {
        Data data = new Data(2048*1024);
        Data data2 = new Data(2048, Data.KILOBIT);
        Data data3 = new Data(2, Data.MEGABIT);

        assertThat(data.getUnit().getSymbol()).isEqualTo("b");
        assertThat(data2.getUnit().getSymbol()).isEqualTo("Kb");
        assertThat(data3.getUnit().getSymbol()).isEqualTo("Mb");

        assertThat(data.as(Data.MEGABIT).unit().getSymbol()).isEqualTo("Mb");
        assertThat(data.as(Data.MEGABIT).value()).isEqualTo(2d);

        assertThat(data2.as(Data.MEGABIT).unit().getSymbol()).isEqualTo("Mb");
        assertThat(data2.as(Data.MEGABIT).value()).isEqualTo(2d);

        assertThat(data3.as(Data.BIT).value()).isEqualTo(2048*1024d);
    }
}
