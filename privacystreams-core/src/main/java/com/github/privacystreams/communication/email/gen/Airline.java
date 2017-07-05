
package com.github.privacystreams.communication.email.gen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


/**
 * Airline
 * <p>
 * 
 * 
 */
public class Airline {

    private String iataCode;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The iataCode
     */
    public String getIataCode() {
        return iataCode;
    }

    /**
     * 
     * @param iataCode
     *     The iataCode
     */
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(iataCode).append(name).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Airline) == false) {
            return false;
        }
        Airline rhs = ((Airline) other);
        return new EqualsBuilder().append(iataCode, rhs.iataCode).append(name, rhs.name).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
