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

/**
 * This class represents Units of angles.
 *
 * In deed, even if angles have no dimensions. We define units to build more easily derived units.
 *
 * @author clement
 */
public class Angle {

    public static Unit STERADIAN = new Unit("sr", "steradian", Dimension.NONE);
}
