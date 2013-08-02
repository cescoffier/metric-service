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

/**
 * This class provides methods to make addition on a number
 */
public class Addition implements ConversionFunction {

    private final Number offset;

    public Addition(Number offset) {
        this.offset = offset;
    }

    public ConversionFunction inverse() {
        return new Addition(-1 * offset.doubleValue());
    }

    public Number apply(Number number) {
        return number.doubleValue() + offset.doubleValue();
    }
}
