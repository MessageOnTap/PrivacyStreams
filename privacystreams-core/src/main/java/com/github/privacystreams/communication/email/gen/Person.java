
package com.github.privacystreams.communication.email.gen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


/**
 * Person
 * <p>
 * 
 * 
 */
public class Person {

    private String email;
    private String familyName;
    private String faxNumber;
    private String givenName;
    /**
     * ContactPoint
     * <p>
     * 
     * 
     */
    private ContactPoint homeLocation;
    private String jobTitle;
    private String name;
    private String telephone;
    /**
     * ContactPoint
     * <p>
     * 
     * 
     */
    private ContactPoint workLocation;
    /**
     * Organization
     * <p>
     * 
     * 
     */
    private Organization worksFor;
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
     *     The familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * 
     * @param familyName
     *     The familyName
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * 
     * @return
     *     The faxNumber
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * 
     * @param faxNumber
     *     The faxNumber
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * 
     * @return
     *     The givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * 
     * @param givenName
     *     The givenName
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * ContactPoint
     * <p>
     * 
     * 
     * @return
     *     The homeLocation
     */
    public ContactPoint getHomeLocation() {
        return homeLocation;
    }

    /**
     * ContactPoint
     * <p>
     * 
     * 
     * @param homeLocation
     *     The homeLocation
     */
    public void setHomeLocation(ContactPoint homeLocation) {
        this.homeLocation = homeLocation;
    }

    /**
     * 
     * @return
     *     The jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * 
     * @param jobTitle
     *     The jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
     * ContactPoint
     * <p>
     * 
     * 
     * @return
     *     The workLocation
     */
    public ContactPoint getWorkLocation() {
        return workLocation;
    }

    /**
     * ContactPoint
     * <p>
     * 
     * 
     * @param workLocation
     *     The workLocation
     */
    public void setWorkLocation(ContactPoint workLocation) {
        this.workLocation = workLocation;
    }

    /**
     * Organization
     * <p>
     * 
     * 
     * @return
     *     The worksFor
     */
    public Organization getWorksFor() {
        return worksFor;
    }

    /**
     * Organization
     * <p>
     * 
     * 
     * @param worksFor
     *     The worksFor
     */
    public void setWorksFor(Organization worksFor) {
        this.worksFor = worksFor;
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
        return new HashCodeBuilder().append(email).append(familyName).append(faxNumber).append(givenName).append(homeLocation).append(jobTitle).append(name).append(telephone).append(workLocation).append(worksFor).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Person) == false) {
            return false;
        }
        Person rhs = ((Person) other);
        return new EqualsBuilder().append(email, rhs.email).append(familyName, rhs.familyName).append(faxNumber, rhs.faxNumber).append(givenName, rhs.givenName).append(homeLocation, rhs.homeLocation).append(jobTitle, rhs.jobTitle).append(name, rhs.name).append(telephone, rhs.telephone).append(workLocation, rhs.workLocation).append(worksFor, rhs.worksFor).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
