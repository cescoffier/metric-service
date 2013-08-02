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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is the builder of unit class.
 *
 * @author clement
 */
public class UnitBuilder<Q extends Quantity<Q>> {

    private Map<Unit<?>, Integer> products = new LinkedHashMap<Unit<?>, Integer>();
    private String name;
    private String symbol;

    public UnitBuilder<Q> from(Unit<?> unit) {
        products.put(unit, 1);
        return this;
    }

    public UnitBuilder<Q> symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public UnitBuilder<Q> name(String name) {
        this.name = name;
        return this;
    }

    public UnitBuilder<Q> times(Unit<?> unit) {
        return pow(unit, 1);
    }

    public UnitBuilder<Q> divide(Unit<?> unit) {
        if (products.containsKey(unit)) {
            products.put(unit, products.get(unit) - 1);
        } else {
            products.put(unit, -1);
        }
        return this;
    }

    public UnitBuilder<Q> pow(Unit<?> unit, int n) {
        if (products.containsKey(unit)) {
            products.put(unit, products.get(unit) + n);
        } else {
            products.put(unit, n);
        }
        return this;
    }

    public Unit<Q> build() {
        if (products.isEmpty()) {
            return new Unit<Q>(symbol, name);
        }
        products = UnitProduct.consolidate(products);
        return new DerivedUnit<Q>(symbol, name, products);
    }
}
