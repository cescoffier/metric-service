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
 *  This class offers methods to build derived units from SI units
 *
 *  @author clement
 */
public class DerivedUnit<Q extends Quantity<Q>> extends Unit<Q> {


    private final Map<Unit<?>, Integer> product;

    public DerivedUnit(String symbol, String name, Map<Unit<?>, Integer> product) {
        super(symbol, name, UnitProduct.getDimension(product));
        this.product = product;
    }

    public DerivedUnit(String symbol, Map<Unit<?>, Integer> product) {
        this(symbol, null, product);
    }

    public DerivedUnit(Map<Unit<?>, Integer> product) {
        this(UnitProduct.toString(product), null, product);
    }

    @Override
    public String toString() {
        if (getSymbol() != null) {
            return getSymbol();
        }
        return getProductAsString();
    }

    public String getProductAsString() {
        return UnitProduct.toString(product);
    }

    public Map<Unit<?>, Integer> getProduct() {
        return new LinkedHashMap<Unit<?>, Integer>(product);
    }


}
