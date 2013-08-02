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
package org.ow2.chameleon.metric.metric.converters;

import org.ow2.chameleon.metric.metric.Quantity;
import org.ow2.chameleon.metric.metric.quantities.Length;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 *
 */
public class ConverterRegistryTest {


    @Test
    public void testConversionFromInchToMeter() {
        QuantityConverter<Length> converter = ConverterRegistry.findConverter(Length.INCH, Length.METER);
        assertThat(converter).isNotNull();
        Quantity<Length> converted = converter.convert(Quantity.valueOf(10, Length.INCH));
        assertThat(converted.unit()).isEqualTo(Length.METER);
        assertThat(converted.value()).isEqualTo(0.254);
    }

    @Test
    public void testConversionFromFootToCentimeter() {
        QuantityConverter<Length> converter = ConverterRegistry.findConverter(Length.FOOT, Length.CENTIMETER);
        assertThat(converter).isNotNull();
        Quantity<Length> converted = converter.convert(Quantity.valueOf(10, Length.FOOT));
        assertThat(converted.unit()).isEqualTo(Length.CENTIMETER);
        assertThat(converted.value()).isEqualTo(304.8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectIncorrectInput() {
        QuantityConverter<Length> converter = ConverterRegistry.findConverter(Length.FOOT, Length.CENTIMETER);
        assertThat(converter).isNotNull();
        // Illegal input
        converter.convert(Quantity.valueOf(10, Length.CENTIMETER));
    }
}
