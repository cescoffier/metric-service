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

/**
 * This class defines a unit. A unit is associate to a specific quantity.
 *
 * @author clement
 */
public class Unit<Q extends Quantity<Q>> {

    /**
     * Holds the symbol.
     */
    private final String symbol;
    /**
     * Holds the symbol.
     */
    private final String name;
    /**
     * Holds the dimension.
     * If not set {@link Dimension#NONE} is used.
     */
    private final Dimension dimension;

    public Unit(String symbol, String name, Dimension dimension) {
        this.symbol = symbol;
        this.name = name;
        this.dimension = dimension;
    }

    public Unit(String symbol, Dimension dimension) {
        this(symbol, null, dimension);
    }

    public Unit(String symbol, String name) {
        this(symbol, name, Dimension.NONE);
    }

    public Unit(String symbol) {
        this(symbol, null, Dimension.NONE);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }

    public Dimension getDimension() {
        return dimension;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public boolean isCompatible(Unit<?> that) {
        return this == that
                || getSymbol().equals(that.getSymbol())
                || this.dimension.equals(that.dimension)  && ! this.dimension.equals(Dimension.NONE);
    }


}
