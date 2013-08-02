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
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: jeremy
 * Date: 15/07/13
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class FrequencyTest {

    @Test
    public void testFrequency() {
        Frequency Frequency1 = new Frequency(10);

        Frequency Frequency2 = new Frequency(10, Frequency.HERTZ);

        assertThat(Frequency1.getUnit().isCompatible(Frequency2.getUnit()));
        assertThat(Frequency1.getUnit().getDimension()).isEqualTo(Frequency2.getUnit().getDimension());

        Quantity<Frequency> result = Frequency1.add(Frequency2);
        assertThat(result.getNumber()).isEqualTo(20.0);
        assertThat(result.getUnit()).isEqualTo(Frequency.HERTZ);
    }
}
