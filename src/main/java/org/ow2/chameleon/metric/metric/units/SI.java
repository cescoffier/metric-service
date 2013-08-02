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
package org.ow2.chameleon.metric.metric.units;

import org.ow2.chameleon.metric.metric.Dimension;
import org.ow2.chameleon.metric.metric.Unit;
import org.ow2.chameleon.metric.metric.quantities.Length;
import org.ow2.chameleon.metric.metric.quantities.Temperature;

/**
 * This class represents definition of SI Units
 *
 * @author clement
 */
public class SI {

    public static Unit<Length> METER = new Unit<Length>("m", "meter", Dimension.LENGTH);

    public static Unit GRAM = new Unit("g", "gram", Dimension.MASS);

    public static Unit SECOND = new Unit("s", "second", Dimension.TIME);

    public static Unit AMPERE = new Unit("A", "ampere", Dimension.ELECTRIC_CURRENT);

    public static Unit<Temperature> KELVIN = new Unit<Temperature>("K", "kelvin", Dimension.TEMPERATURE);

    public static Unit MOL = new Unit("mol", "mol", Dimension.AMOUNT_OF_SUBSTANCE);

    public static Unit CANDELA = new Unit("cd", "candela", Dimension.LUMINOUS_INTENSITY);



}
