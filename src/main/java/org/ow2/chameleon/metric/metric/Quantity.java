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

import org.ow2.chameleon.metric.metric.converters.ConverterRegistry;
import org.ow2.chameleon.metric.metric.converters.QuantityConverter;

import java.lang.reflect.ParameterizedType;

/**
 * This class defines what is a quantity. It offers methods to construct a quantity and
 * methods to build make arithmetical operations on quantities.
 *
 * @author clement
 */
public class Quantity<Q extends Quantity<Q>> {

    /**
     * The Unknown quantity name.
     */
    public static final String UNKNOWN_NAME = "unknown";

    private final Number number;
    private final Unit<Q> unit;
    private final Class clazz;

    /**
     * @param number
     * @param unit
     */
    public Quantity(Number number, Unit<Q> unit) {
        this(null, number, unit);
    }

    public Quantity(Class<Q> clazz, Number number, Unit<Q> unit) {
        this.number = number;
        this.unit = unit;
        this.clazz = clazz;
    }

    /**
     * Returns the amount corresponding to the specified value and unit.
     *
     * @param value the value stated in the specified unit.
     * @param unit  the unit in which the value is stated.
     * @return the corresponding amount.
     */
    public static <Q extends Quantity<Q>> Quantity<Q> valueOf(Number value,
                                                       Unit<Q> unit) {
        return new Quantity<Q>(value, unit);
    }

    public Number value() {
        return getNumber();
    }

    public Number getNumber() {
        return number;
    }

    public Unit<Q> unit() {
        return getUnit();
    }

    public Unit<Q> getUnit() {
        return unit;
    }

    /**
     * Gets the quantity name.
     *
     * The result depends on how the quantity is built:
     * <ul>
     *     <li>if the `clazz` parameter is set, returns the simple name of this class</li>
     *     <li>if the `clazz` parameter is not set, infer it by reflection</li>
     *     <li>classes extending quantity mechanism that can't rely on the previous options must override this
     *     method.</li>
     * </ul>
     *
     * @return the quantity name, "unknown" if it can't be inferred.
     */
    public String getQuantityName() {
        if (this.clazz == null) {
            ParameterizedType sup = (ParameterizedType) this.getClass().getGenericSuperclass();
            if (sup.getActualTypeArguments().length == 0  || ! (sup.getActualTypeArguments()[0] instanceof Class)) {
                return UNKNOWN_NAME;
            }
            Class q = (Class) sup.getActualTypeArguments()[0];
            return q.getSimpleName();
        } else {
            return this.clazz.getSimpleName();
        }
    }

    public Quantity<Q> add(Quantity<Q> that) {
        if (this.getUnit().isCompatible(that.getUnit())) {
            // Units are compatible, but we still must use the normalized quantity.
            return valueOf(that.getNormalizedQuantity().getNumber().doubleValue() + this.getNormalizedQuantity().getNumber
                    ().doubleValue
                    (), unit);
        } else {
           throw new IncommensurableException("Cannot add " + this.toString() + " and " + that.toString() + " - " +
                   "units are incompatible");
        }
    }

    public Quantity<Q> times(Number number) {
        return valueOf(getNumber().doubleValue() * number.doubleValue(), getUnit());
    }

    public Quantity<Q> getNormalizedQuantity() {
        if (unit instanceof TransformedUnit) {
            TransformedUnit<Q> transformedUnit = (TransformedUnit<Q>) getUnit();
            Quantity<Q> converted = transformedUnit.getConverter().convert(this);
            return converted.getNormalizedQuantity();
        } else {
            return this;
        }
    }

    public String toString() {
        return value() + unit().toString();
    }

    public Quantity<Q> as(Unit<Q> unit) {
        // Retrieve the normalized quantity
        Quantity<Q> normalized = getNormalizedQuantity();
        QuantityConverter<Q> converter = ConverterRegistry.findConverter(normalized.unit, unit);

        if (converter == null) {
            throw new IllegalArgumentException("Cannot convert " + this + " to " + unit + " - no converter in the " +
                    "registry");
        } else {
            return converter.convert(normalized);
        }
    }

}
