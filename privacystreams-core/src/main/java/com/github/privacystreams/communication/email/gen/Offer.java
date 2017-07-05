
package com.github.privacystreams.communication.email.gen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


/**
 * Offer
 * <p>
 * 
 * 
 */
public class Offer {

    /**
     * QuantitativeValue
     * <p>
     * 
     * 
     */
    private QuantitativeValue eligibleQuantity;
    /**
     * Product
     * <p>
     * 
     * 
     */
    private Product itemOffered;
    private String price;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * QuantitativeValue
     * <p>
     * 
     * 
     * @return
     *     The eligibleQuantity
     */
    public QuantitativeValue getEligibleQuantity() {
        return eligibleQuantity;
    }

    /**
     * QuantitativeValue
     * <p>
     * 
     * 
     * @param eligibleQuantity
     *     The eligibleQuantity
     */
    public void setEligibleQuantity(QuantitativeValue eligibleQuantity) {
        this.eligibleQuantity = eligibleQuantity;
    }

    /**
     * Product
     * <p>
     * 
     * 
     * @return
     *     The itemOffered
     */
    public Product getItemOffered() {
        return itemOffered;
    }

    /**
     * Product
     * <p>
     * 
     * 
     * @param itemOffered
     *     The itemOffered
     */
    public void setItemOffered(Product itemOffered) {
        this.itemOffered = itemOffered;
    }

    /**
     * 
     * @return
     *     The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(String price) {
        this.price = price;
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
        return new HashCodeBuilder().append(eligibleQuantity).append(itemOffered).append(price).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Offer) == false) {
            return false;
        }
        Offer rhs = ((Offer) other);
        return new EqualsBuilder().append(eligibleQuantity, rhs.eligibleQuantity).append(itemOffered, rhs.itemOffered).append(price, rhs.price).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
