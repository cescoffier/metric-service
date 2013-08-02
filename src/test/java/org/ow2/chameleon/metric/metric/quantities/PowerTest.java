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
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class PowerTest {

    @Test
    public void testPower() {
        Power Power1 = new Power(10);

        Power Power2 = new Power(10, Power.WATT);

        assertThat(Power1.getUnit().isCompatible(Power2.getUnit()));
        assertThat(Power1.getUnit().getDimension()).isEqualTo(Power2.getUnit().getDimension());

        Quantity<Power> result = Power1.add(Power2);
        assertThat(result.getNumber()).isEqualTo(20.0);
        assertThat(result.getUnit()).isEqualTo(Power.WATT);
    }
}
