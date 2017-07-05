
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
 * Contact
 * <p>
 * 
 * 
 */
public class Contact extends EmailInfoEntity {

    private List<Person> contacts = new ArrayList<Person>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Domain getDomain() {
        return Domain.CONTACT;
    }

    /**
     * 
     * @return
     *     The contacts
     */
    public List<Person> getContacts() {
        return contacts;
    }

    /**
     * 
     * @param contacts
     *     The contacts
     */
    public void setContacts(List<Person> contacts) {
        this.contacts = contacts;
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
        return new HashCodeBuilder().append(contacts).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Contact) == false) {
            return false;
        }
        Contact rhs = ((Contact) other);
        return new EqualsBuilder().append(contacts, rhs.contacts).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
