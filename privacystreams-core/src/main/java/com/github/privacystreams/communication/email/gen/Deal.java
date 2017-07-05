
package com.github.privacystreams.communication.email.gen;

import com.github.privacystreams.communication.email.Domain;
import com.github.privacystreams.communication.email.EmailInfoEntity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


/**
 * Deal
 * <p>
 *
 *
 */
public class Deal extends EmailInfoEntity {

    private Boolean isDeal;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Domain getDomain() {
        return Domain.DEAL;
    }

    /**
     *
     * @return
     *     The isDeal
     */
    public Boolean getIsDeal() {
        return isDeal;
    }

    /**
     *
     * @param isDeal
     *     The isDeal
     */
    public void setIsDeal(Boolean isDeal) {
        this.isDeal = isDeal;
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
        return new HashCodeBuilder().append(isDeal).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Deal) == false) {
            return false;
        }
        Deal rhs = ((Deal) other);
        return new EqualsBuilder().append(isDeal, rhs.isDeal).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
