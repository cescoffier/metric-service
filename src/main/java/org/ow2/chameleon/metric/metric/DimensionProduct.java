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
 * This class provides methods to build product of differents dimensions.
 *
 * @author clement
 */
public class DimensionProduct {


    public static String toString(Map<Dimension, Integer> product) {
        // Consolidate first.
        Map<Dimension, Integer> consolidated = consolidate(product);

        StringBuilder symbol = new StringBuilder();
        for (Map.Entry<Dimension, Integer> entry : consolidated.entrySet()) {
            boolean prependedSymbol = false;
            if (symbol.length() != 0) {
                if (entry.getValue() == -1) {
                    symbol.append("/");
                    prependedSymbol = true;
                } else {
                    symbol.append(".");
                }
            }
            symbol.append(entry.getKey().toString());
            if (! prependedSymbol && entry.getValue() != 1) {
                symbol.append("^").append(entry.getValue());
            }
        }
        return symbol.toString();
    }

    public static Map<Dimension, Integer> consolidate(Map<Dimension, Integer> product) {
        LinkedHashMap<Dimension, Integer> consolidatedProduct = new LinkedHashMap<Dimension, Integer>();

        for (Map.Entry<Dimension, Integer> entry : product.entrySet()) {
            final Map<Dimension, Integer> productDimensions = entry.getKey().getProductDimensions();
            if (productDimensions.size() == 0) {
                // Dimensionless !
                // No impact on anything.
            }

            if (productDimensions.size() == 1) {
                // Fundamental dimension.
                if (consolidatedProduct.containsKey(entry.getKey())) {
                    final int value = consolidatedProduct.get(entry.getKey()) + entry.getValue();
                    if (value == 0) {
                        consolidatedProduct.remove(entry.getKey());
                    } else {
                        consolidatedProduct.put(entry.getKey(),
                                value);
                    }
                } else if (entry.getValue() != 0) {
                    consolidatedProduct.put(entry.getKey(), entry.getValue());
                }
            } else {
                // Non fundamental dimension, must be exploded
                // First consolidate it.
                Map<Dimension, Integer> cons = consolidate(productDimensions);
                for (Map.Entry<Dimension, Integer> consolidatedEntry : cons.entrySet()) {
                    final Dimension dimension = consolidatedEntry.getKey();
                    if (consolidatedProduct.containsKey(dimension)) {
                        final int value = consolidatedProduct.get(dimension) + consolidatedEntry.getValue();
                        if (value == 0) {
                            consolidatedProduct.remove(dimension);
                        } else {
                            consolidatedProduct.put(dimension,
                                    value);
                        }
                    } else {
                        consolidatedProduct.put(dimension, consolidatedEntry.getValue());
                    }
                }
            }
        }

        return consolidatedProduct;
    }

}
