
package com.github.privacystreams.communication.email.gen;

import com.github.privacystreams.communication.email.Domain;
import com.github.privacystreams.communication.email.EmailInfoEntity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * EventReservation
 * <p>
 * 
 * 
 */
public class EventReservation extends EmailInfoEntity {

    /**
     * Organization
     * <p>
     * 
     * 
     */
    private Organization provider;
    /**
     * Event
     * <p>
     * 
     * 
     */
    private Event reservationFor;
    private List<Ticket> reservedTicket = new ArrayList<Ticket>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Domain getDomain() {
        return Domain.EVENT;
    }

    /**
     * Organization
     * <p>
     * 
     * 
     * @return
     *     The provider
     */
    public Organization getProvider() {
        return provider;
    }

    /**
     * Organization
     * <p>
     * 
     * 
     * @param provider
     *     The provider
     */
    public void setProvider(Organization provider) {
        this.provider = provider;
    }

    /**
     * Event
     * <p>
     * 
     * 
     * @return
     *     The reservationFor
     */
    public Event getReservationFor() {
        return reservationFor;
    }

    /**
     * Event
     * <p>
     * 
     * 
     * @param reservationFor
     *     The reservationFor
     */
    public void setReservationFor(Event reservationFor) {
        this.reservationFor = reservationFor;
    }

    /**
     * 
     * @return
     *     The reservedTicket
     */
    public List<Ticket> getReservedTicket() {
        return reservedTicket;
    }

    /**
     * 
     * @param reservedTicket
     *     The reservedTicket
     */
    public void setReservedTicket(List<Ticket> reservedTicket) {
        this.reservedTicket = reservedTicket;
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
        return new HashCodeBuilder().append(provider).append(reservationFor).append(reservedTicket).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EventReservation) == false) {
            return false;
        }
        EventReservation rhs = ((EventReservation) other);
        return new EqualsBuilder().append(provider, rhs.provider).append(reservationFor, rhs.reservationFor).append(reservedTicket, rhs.reservedTicket).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
