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
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */
public class AreaTest {

    @Test
    public void testArea() {
        Area area1 = new Area(10);

        Area area2 = new Area(10, Area.CUBIC_METER);

        assertThat(area1.getUnit().isCompatible(area2.getUnit()));
        assertThat(area1.getUnit().getDimension()).isEqualTo(area2.getUnit().getDimension());

        Quantity<Area> result = area1.add(area2);

        System.out.println(result.getUnit());
        assertThat(result.getNumber()).isEqualTo(20.0);
        assertThat(result.getUnit()).isEqualTo(Area.CUBIC_METER);
    }
}
