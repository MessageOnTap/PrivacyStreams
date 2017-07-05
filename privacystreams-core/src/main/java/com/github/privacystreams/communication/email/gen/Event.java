
package com.github.privacystreams.communication.email.gen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Event
 * <p>
 * 
 * 
 */
public class Event {

    private Date endDate;
    /**
     * Place
     * <p>
     * 
     * 
     */
    private Place location;
    private String name;
    private Date startDate;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 
     * @param endDate
     *     The endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Place
     * <p>
     * 
     * 
     * @return
     *     The location
     */
    public Place getLocation() {
        return location;
    }

    /**
     * Place
     * <p>
     * 
     * 
     * @param location
     *     The location
     */
    public void setLocation(Place location) {
        this.location = location;
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

    /**
     * 
     * @return
     *     The startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate
     *     The startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
        return new HashCodeBuilder().append(endDate).append(location).append(name).append(startDate).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Event) == false) {
            return false;
        }
        Event rhs = ((Event) other);
        return new EqualsBuilder().append(endDate, rhs.endDate).append(location, rhs.location).append(name, rhs.name).append(startDate, rhs.startDate).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
