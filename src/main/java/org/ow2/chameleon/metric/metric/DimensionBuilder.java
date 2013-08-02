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
 * Created with IntelliJ IDEA.
 * User: clement
 * Date: 11/07/13
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public class DimensionBuilder {


    private Map<Dimension, Integer> products = new LinkedHashMap<Dimension, Integer>();

    public DimensionBuilder from(Dimension dimension) {
        products.put(dimension, 1);
        return this;
    }

    public DimensionBuilder times(Dimension dimension) {
        return pow(dimension, 1);
    }

    public DimensionBuilder divide(Dimension dimension) {
        if (products.containsKey(dimension)) {
            products.put(dimension, products.get(dimension) - 1);
        } else {
            products.put(dimension, -1);
        }
        return this;
    }

    public DimensionBuilder pow(Dimension dimension, int n) {
        if (products.containsKey(dimension)) {
            products.put(dimension, products.get(dimension) + n);
        } else {
            products.put(dimension, n);
        }
        return this;
    }

    public Dimension build() {
        products = DimensionProduct.consolidate(products);
        return new Dimension(products);
    }

}
