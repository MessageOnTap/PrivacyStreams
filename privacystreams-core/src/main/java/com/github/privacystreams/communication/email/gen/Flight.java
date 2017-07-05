
package com.github.privacystreams.communication.email.gen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Flight
 * <p>
 * 
 * 
 */
public class Flight {

    /**
     * Airport
     * <p>
     * 
     * 
     */
    private Airport arrivalAirport;
    private Date arrivalTime;
    /**
     * Airport
     * <p>
     * 
     * 
     */
    private Airport departureAirport;
    private Date departureTime;
    private String flightNumber;
    /**
     * Airline
     * <p>
     * 
     * 
     */
    private Airline seller;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Airport
     * <p>
     * 
     * 
     * @return
     *     The arrivalAirport
     */
    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * Airport
     * <p>
     * 
     * 
     * @param arrivalAirport
     *     The arrivalAirport
     */
    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    /**
     * 
     * @return
     *     The arrivalTime
     */
    public Date getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 
     * @param arrivalTime
     *     The arrivalTime
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Airport
     * <p>
     * 
     * 
     * @return
     *     The departureAirport
     */
    public Airport getDepartureAirport() {
        return departureAirport;
    }

    /**
     * Airport
     * <p>
     * 
     * 
     * @param departureAirport
     *     The departureAirport
     */
    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
     * 
     * @return
     *     The departureTime
     */
    public Date getDepartureTime() {
        return departureTime;
    }

    /**
     * 
     * @param departureTime
     *     The departureTime
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * 
     * @return
     *     The flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * 
     * @param flightNumber
     *     The flightNumber
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Airline
     * <p>
     * 
     * 
     * @return
     *     The seller
     */
    public Airline getSeller() {
        return seller;
    }

    /**
     * Airline
     * <p>
     * 
     * 
     * @param seller
     *     The seller
     */
    public void setSeller(Airline seller) {
        this.seller = seller;
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
        return new HashCodeBuilder().append(arrivalAirport).append(arrivalTime).append(departureAirport).append(departureTime).append(flightNumber).append(seller).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Flight) == false) {
            return false;
        }
        Flight rhs = ((Flight) other);
        return new EqualsBuilder().append(arrivalAirport, rhs.arrivalAirport).append(arrivalTime, rhs.arrivalTime).append(departureAirport, rhs.departureAirport).append(departureTime, rhs.departureTime).append(flightNumber, rhs.flightNumber).append(seller, rhs.seller).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
