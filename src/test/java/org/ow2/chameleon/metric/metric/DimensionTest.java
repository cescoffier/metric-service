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
 * Check Dimension.
 */
public class DimensionTest {


    @Test
    public void testFundamentalDimension() {
        assertThat(Dimension.AMOUNT_OF_SUBSTANCE.getProductDimensions()).hasSize(1);
        assertThat(Dimension.ELECTRIC_CURRENT.getProductDimensions()).hasSize(1);
        assertThat(Dimension.LENGTH.getProductDimensions()).hasSize(1);
        assertThat(Dimension.LUMINOUS_INTENSITY.getProductDimensions()).hasSize(1);
        assertThat(Dimension.MASS.getProductDimensions()).hasSize(1);
        assertThat(Dimension.TEMPERATURE.getProductDimensions()).hasSize(1);
        assertThat(Dimension.TIME.getProductDimensions()).hasSize(1);

        // Dimensionless
        assertThat(Dimension.NONE.getProductDimensions()).hasSize(0);
    }

    @Test
    public void testFundamentalDimensionToString() {
        assertThat(Dimension.AMOUNT_OF_SUBSTANCE.toString()).isEqualTo("N");
        assertThat(Dimension.ELECTRIC_CURRENT.toString()).isEqualTo("I");
        assertThat(Dimension.LENGTH.toString()).isEqualTo("L");
        assertThat(Dimension.LUMINOUS_INTENSITY.toString()).isEqualTo("J");
        assertThat(Dimension.MASS.toString()).isEqualTo("M");
        assertThat(Dimension.TEMPERATURE.toString()).isEqualTo("Θ");
        assertThat(Dimension.TIME.toString()).isEqualTo("T");

        // Dimensionless
        assertThat(Dimension.NONE.getProductDimensions()).hasSize(0);
    }

    @Test
    public void testNewFundamentalDimension() {
        Dimension stuff = new Dimension('S');
        assertThat(stuff.getProductDimensions()).hasSize(1);
        assertThat(stuff.toString()).isEqualTo("S");
    }

    @Test
    public void testNewProductBasedDimensions() {
        Dimension speed = new DimensionBuilder().from(Dimension.LENGTH).divide(Dimension.TIME).build();
        assertThat(speed.getProductDimensions()).hasSize(2);
        assertThat(speed.toString()).isEqualTo("L/T");

        Dimension acc = new DimensionBuilder().from(Dimension.LENGTH).pow(Dimension.TIME, -2).build();
        assertThat(acc.getProductDimensions()).hasSize(2);
        assertThat(acc.toString()).isEqualTo("L.T^-2");

        Dimension weird = new DimensionBuilder().from(Dimension.LENGTH).pow(Dimension.TIME,
                -2).pow(Dimension.AMOUNT_OF_SUBSTANCE, 3).build();
        assertThat(weird.getProductDimensions()).hasSize(3);
        assertThat(weird.toString()).isEqualTo("L.T^-2.N^3");

        Dimension backToFundamental = new DimensionBuilder().from(Dimension.LENGTH).pow(Dimension.TIME,
                -2).times(Dimension.TIME).times(Dimension.TIME).build();
        assertThat(backToFundamental.getProductDimensions()).hasSize(1);
        assertThat(backToFundamental.toString()).isEqualTo("L");

        Dimension backToFundamental2 = new DimensionBuilder().from(speed).times(Dimension.TIME).build();
        assertThat(backToFundamental2.getProductDimensions()).hasSize(1);
        assertThat(backToFundamental2.toString()).isEqualTo("L");
    }

}
