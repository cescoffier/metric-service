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
 * This class provides methods to build product of units.
 *
 * @author clement
 */
public class UnitProduct {
    /**
     * Computes the dimension of the product of units.
     * If any of the products has no dimension, {@link Dimension#NONE} is returned.
     *
     * @param product the product of unit
     * @return the dimension
     */
    public static Dimension getDimension(Map<Unit<?>, Integer> product) {
        DimensionBuilder builder = new DimensionBuilder();
        for (Map.Entry<Unit<?>, Integer> entry : product.entrySet()) {
            if (! entry.getKey().getDimension().equals(Dimension.NONE)) {
                builder.pow(entry.getKey().getDimension(), entry.getValue());
            }
        }
        return builder.build();
    }

    public static String toString(Map<Unit<?>,Integer> product) {
        Map<Unit<?>, Integer> consolidated = consolidate(product);
        StringBuilder symbol = new StringBuilder();
        for (Map.Entry<Unit<?>, Integer> entry : consolidated.entrySet()) {
            if (symbol.length() != 0) {
                if (entry.getValue() == -1) {
                    symbol.append("/");
                } else {
                    symbol.append(".");
                }
            }
            symbol.append(entry.getKey().toString());
            if (entry.getValue() != -1 && entry.getValue() != 1) {
                symbol.append("^").append(entry.getValue());
            }
        }
        return symbol.toString();
    }

    public static Map<Unit<?>, Integer> consolidate(Map<Unit<?>, Integer> product) {
        LinkedHashMap<Unit<?>, Integer> consolidatedProduct = new LinkedHashMap<Unit<?>, Integer>();
        for (Map.Entry<Unit<?>, Integer> entry : product.entrySet()) {
            if (entry.getKey() instanceof DerivedUnit) {
                Map<Unit<?>, Integer> sub = ((DerivedUnit) entry.getKey()).getProduct();
                sub = consolidate(sub);
                for (Map.Entry<Unit<?>, Integer> consolidatedEntry : sub.entrySet()) {
                    if (consolidatedProduct.containsKey(consolidatedEntry.getKey())) {
                        final int value = consolidatedProduct.get(consolidatedEntry.getKey()) + consolidatedEntry.getValue();
                        if (value != 0) {
                            consolidatedProduct.put(consolidatedEntry.getKey(), value);
                        }
                    } else {
                        consolidatedProduct.put(consolidatedEntry.getKey(), consolidatedEntry.getValue());
                    }
                }

            } else {
                if (consolidatedProduct.containsKey(entry.getKey())) {
                   final int value = consolidatedProduct.get(entry.getKey()) + entry.getValue();
                    if (value != 0) {
                        consolidatedProduct.put(entry.getKey(), value);
                    }
                } else {
                    consolidatedProduct.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return consolidatedProduct;
    }
}
