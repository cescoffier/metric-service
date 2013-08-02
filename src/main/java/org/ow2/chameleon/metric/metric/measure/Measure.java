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
package org.ow2.chameleon.metric.metric.measure;

import org.ow2.chameleon.metric.metric.Quantity;

import java.util.Date;

/**
 * <p> This class represents the result of a measurement stated in a
 * known unit.</p>
 * <p/>
 * <p> There is no constraint upon the measurement value itself: scalars,
 * vectors, or even data sets are valid values as long as
 * an aggregate magnitude can be determined (see {@link org.ow2.chameleon.metric.metric.measure.Measurable}).</p>
 * <p/>
 * /!\ A measure is immutable /!\
 *
 * @author jeremy.savonet@gmail.com
 */
public class Measure {
    /**
     * |----------------------------------------------------------------------|
     * |  ID  |  QUANTITY   |   minDeviation  |   maxDeviation  |  TIMESTAMP  |
     * |----------------------------------------------------------------------|
     */

    private final String m_id;
    private final Quantity m_quantity;
    private final Object m_minDeviation;
    private final Object m_maxDeviation;
    private final Date m_timeStamp;

    public static class Builder {

        private String _id;
        private Quantity _quantity;
        private Object _minDeviation;
        private Object _maxDeviation;
        private Date _timeStamp;

        public Builder(String id) {
            this._id = id;
        }

        public Builder quantity(Quantity quantity) {
            this._quantity= quantity;
            return this;
        }

        public Builder minDeviation(Object minDeviation) {
            this._minDeviation = minDeviation;
            return this;
        }

        public Builder maxDeviation(Object maxDeviation) {
            this._maxDeviation = maxDeviation;
            return this;
        }

        public Builder timeStamp(Date timeStamp) {
            this._timeStamp = timeStamp;
            return this;
        }

        public Measure build() {
            return new Measure(this);
        }

    }

    private Measure(Builder builder) {
        this.m_id = builder._id;
        this.m_quantity = builder._quantity;
        this.m_minDeviation = builder._minDeviation;
        this.m_maxDeviation = builder._maxDeviation;
        this.m_timeStamp = builder._timeStamp;
    }

    public String id() {
        return m_id;
    }

    public Quantity quantity() {
        return m_quantity;
    }

    public Object minDeviation() {
        return m_minDeviation;
    }

    public Object maxDeviation() {
        return m_maxDeviation;
    }

    public Date timeStamp() {
        return m_timeStamp;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "m_id='" + m_id + '\'' +
                ", m_quantity=" + m_quantity +
                ", m_minDeviation=" + m_minDeviation +
                ", m_maxDeviation=" + m_maxDeviation +
                ", m_timeStamp=" + m_timeStamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measure measure = (Measure) o;

        if (!m_id.equals(measure.m_id)) return false;
        if (!m_maxDeviation.equals(measure.m_maxDeviation)) return false;
        if (!m_minDeviation.equals(measure.m_minDeviation)) return false;
        if (!m_quantity.equals(measure.m_quantity)) return false;
        if (!m_timeStamp.equals(measure.m_timeStamp)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = m_id.hashCode();
        result = 31 * result + m_quantity.hashCode();
        result = 31 * result + m_minDeviation.hashCode();
        result = 31 * result + m_maxDeviation.hashCode();
        result = 31 * result + m_timeStamp.hashCode();
        return result;
    }

 }
