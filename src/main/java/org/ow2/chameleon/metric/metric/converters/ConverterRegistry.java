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
import org.ow2.chameleon.metric.metric.Unit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Converter registry.
 */
public class ConverterRegistry {

    private static final List<QuantityConverter<?>> registry = new ArrayList<QuantityConverter<?>>();
    private static final Object lock = new Object();

    public static <Q extends Quantity<Q>> QuantityConverter<Q> findConverter(Unit<Q> in, Unit<Q> out) {
        List<QuantityConverter<Q>> chain = traverse(in, out);
        if (chain == null) {
            return null;
        } else {
            return new ChainedQuantityConverter<Q>(chain);
        }

    }

    public static <Q extends Quantity<Q>> List<QuantityConverter<Q>> traverse(Unit<Q> from, Unit<Q> to) {

        LinkedList<Unit<Q>> queue = new LinkedList<Unit<Q>>();
        queue.add(from);
        List<Unit<Q>> visited = new ArrayList<Unit<Q>>();
        visited.add(from);
        Map<Unit<Q>, Unit<Q>> preds = new java.util.LinkedHashMap<Unit<Q>, Unit<Q>>();

        while (!queue.isEmpty()) {
            Unit<Q> unit = queue.removeFirst();

            if (unit.equals(to)) {
                return buildChainFromTraces(preds, from, to);
            }

            for (QuantityConverter<Q> converter : getConvertersFrom(unit)) {
                Unit<Q> t = converter.to();
                if (!visited.contains(t)) {
                    visited.add(t);
                    preds.put(t, unit);
                    queue.addLast(t);
                }
            }
        }

        return null;

    }

    private static <Q extends Quantity<Q>> List<QuantityConverter<Q>> buildChainFromTraces(Map<Unit<Q>, Unit<Q>> preds,
                                                                                           Unit<Q> from,
                                                                                           Unit<Q> to) {
        LinkedList<QuantityConverter<Q>> chain = new LinkedList<QuantityConverter<Q>>();

        Unit<Q> current = to;
        while (!current.equals(from)) {
            Unit<Q> pred = preds.get(current);
            if (pred == null) {
                throw new IllegalStateException("Cannot compute the conversion path from " + from + " to " + to + " -" +
                        " " + current + " has to predecessor in " + preds);
            }
            // Find the converter from pred to current
            QuantityConverter<Q> converter = getConvertersFromTo(pred, current);
            if (converter == null) {
                throw new IllegalStateException("Cannot compute the conversion path from " + from + " to " + to + " -" +
                        " there is no converter from " + pred + " to " + current);
            } else {
                chain.addFirst(converter);
            }

            // Iteration step
            current = pred;
        }

        return chain;


    }

    private static <Q extends Quantity<Q>> List<QuantityConverter<Q>> getConvertersFrom(Unit<Q> unit) {
        List<QuantityConverter<Q>> converters = new ArrayList<QuantityConverter<Q>>();
        synchronized (lock) {
            for (QuantityConverter converter : registry) {
                if (converter.from().equals(unit)) {
                    //noinspection unchecked
                    converters.add(converter);
                } else if (converter.to().equals(unit)) {
                    //noinspection unchecked
                    converters.add(converter.inverse());
                }
            }
        }
        return converters;
    }

    private static <Q extends Quantity<Q>> QuantityConverter<Q> getConvertersFromTo(Unit<Q> from, Unit<Q> to) {
        List<QuantityConverter<Q>> converters = getConvertersFrom(from);
        for (QuantityConverter<Q> converter : converters) {
            if (converter.to().equals(to)) {
                return converter;
            }
        }
        return null;
    }

    public static void addConverter(QuantityConverter<?> converter) {
        synchronized (lock) {
            if (!registry.contains(converter)) {
                registry.add(converter);
            }
        }
    }

    public static void removeConverter(QuantityConverter<?> converter) {
        synchronized (lock) {
            registry.remove(converter);
        }
    }

    public static List<QuantityConverter<?>> getConverters() {
        List<QuantityConverter<?>> converters = new ArrayList<QuantityConverter<?>>();
        synchronized (lock) {
            for (QuantityConverter converter : registry) {
                converters.add(converter);
                converters.add(converter.inverse());
            }
        }
        return converters;
    }
}
