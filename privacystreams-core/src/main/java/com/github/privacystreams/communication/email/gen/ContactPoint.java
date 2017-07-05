
package com.github.privacystreams.communication.email.gen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


/**
 * ContactPoint
 * <p>
 * 
 * 
 */
public class ContactPoint {

    private String email;
    private String telephone;
    private String contactType;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 
     * @param telephone
     *     The telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 
     * @return
     *     The contactType
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * 
     * @param contactType
     *     The contactType
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
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
        return new HashCodeBuilder().append(email).append(telephone).append(contactType).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ContactPoint) == false) {
            return false;
        }
        ContactPoint rhs = ((ContactPoint) other);
        return new EqualsBuilder().append(email, rhs.email).append(telephone, rhs.telephone).append(contactType, rhs.contactType).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
