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
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class AccelerationTest {

    @Test
    public void testAcceleration() {
        Acceleration acceleration1 = new Acceleration(10);

        Acceleration acceleration2 = new Acceleration(10, Acceleration.ACCELERATION);

        assertThat(acceleration1.getUnit().isCompatible(acceleration2.getUnit()));
        assertThat(acceleration1.getUnit().getDimension()).isEqualTo(acceleration2.getUnit().getDimension());

        Quantity<Acceleration> result = acceleration1.add(acceleration2);
        assertThat(result.getNumber()).isEqualTo(20.0);
        assertThat(result.getUnit()).isEqualTo(Acceleration.ACCELERATION);
    }
}
