
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
 * FlightReservation
 * <p>
 * 
 * 
 */
public class FlightReservation extends EmailInfoEntity {

    /**
     * Organization
     * <p>
     * 
     * 
     */
    private Organization broker;
    private List<ProgramMembership> programMembershipUsed = new ArrayList<ProgramMembership>();
    private List<Flight> reservationFor = new ArrayList<Flight>();
    private String reservationId;
    /**
     * ReservationStatus
     * <p>
     * 
     * 
     */
    private ReservationStatus reservationStatus;
    private List<Ticket> reservedTicket = new ArrayList<Ticket>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Domain getDomain() {
        return Domain.FLIGHT;
    }

    /**
     * Organization
     * <p>
     * 
     * 
     * @return
     *     The broker
     */
    public Organization getBroker() {
        return broker;
    }

    /**
     * Organization
     * <p>
     * 
     * 
     * @param broker
     *     The broker
     */
    public void setBroker(Organization broker) {
        this.broker = broker;
    }

    /**
     * 
     * @return
     *     The programMembershipUsed
     */
    public List<ProgramMembership> getProgramMembershipUsed() {
        return programMembershipUsed;
    }

    /**
     * 
     * @param programMembershipUsed
     *     The programMembershipUsed
     */
    public void setProgramMembershipUsed(List<ProgramMembership> programMembershipUsed) {
        this.programMembershipUsed = programMembershipUsed;
    }

    /**
     * 
     * @return
     *     The reservationFor
     */
    public List<Flight> getReservationFor() {
        return reservationFor;
    }

    /**
     * 
     * @param reservationFor
     *     The reservationFor
     */
    public void setReservationFor(List<Flight> reservationFor) {
        this.reservationFor = reservationFor;
    }

    /**
     * 
     * @return
     *     The reservationId
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * 
     * @param reservationId
     *     The reservationId
     */
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * ReservationStatus
     * <p>
     * 
     * 
     * @return
     *     The reservationStatus
     */
    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    /**
     * ReservationStatus
     * <p>
     * 
     * 
     * @param reservationStatus
     *     The reservationStatus
     */
    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
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
        return new HashCodeBuilder().append(broker).append(programMembershipUsed).append(reservationFor).append(reservationId).append(reservationStatus).append(reservedTicket).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FlightReservation) == false) {
            return false;
        }
        FlightReservation rhs = ((FlightReservation) other);
        return new EqualsBuilder().append(broker, rhs.broker).append(programMembershipUsed, rhs.programMembershipUsed).append(reservationFor, rhs.reservationFor).append(reservationId, rhs.reservationId).append(reservationStatus, rhs.reservationStatus).append(reservedTicket, rhs.reservedTicket).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
