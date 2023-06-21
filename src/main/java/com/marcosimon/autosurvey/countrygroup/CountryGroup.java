package com.marcosimon.autosurvey.countrygroup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
import com.marcosimon.autosurvey.organization.Organization;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countryGroup")
public class CountryGroup {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(
            name = "system-uuid", strategy = "uuid"
    )
    @Column(name="country_id")
    private String countryId;

    @Column(name="country")
    private String country;

    @OneToMany(mappedBy = "countryGroup")
    //@JoinColumn(name = "survey_id")
    private List<AutoSurvey> surveys;

    /*@ManyToOne
    @JoinColumn(name ="org_id")
    @JsonIgnore
    private Organization organization;*/

    @Column
    private String orgId;

    @Column
    private String orgName;


    public CountryGroup() {
    }

    public CountryGroup(String country, List<AutoSurvey> surveys, String orgId, String orgName) {
        this.country = country;
        this.surveys = surveys;
        this.orgId = orgId;
        this.orgName = orgName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public List<AutoSurvey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<AutoSurvey> surveys) {
        this.surveys = surveys;
    }

    /*public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }*/

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
