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


import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Check base unit.
 */
public class UnitTest {

    @Test
    public void testUnitCreation() {
        Unit unit = new Unit("Hz", new DimensionBuilder().pow(Dimension.TIME, -1).build());
        assertThat(unit.getSymbol()).isEqualTo("Hz");
        assertThat(unit.getDimension().toString()).isEqualTo("T^-1");
        assertThat(unit.getName()).isNull();

        Unit freq = new Unit("Hz", "frequency", new DimensionBuilder().pow(Dimension.TIME, -1).build());
        assertThat(freq.getSymbol()).isEqualTo("Hz");
        assertThat(freq.getDimension().toString()).isEqualTo("T^-1");
        assertThat(freq.getName()).isEqualTo("frequency");

        Unit angle = new Unit("º", "degree");

        assertThat(unit.isCompatible(freq)).isTrue();
        assertThat(freq.isCompatible(angle)).isFalse();
    }
}
