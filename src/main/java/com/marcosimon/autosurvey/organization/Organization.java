package com.marcosimon.autosurvey.organization;

import com.marcosimon.autosurvey.countrygroup.CountryGroup;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;


@Entity
@Table(name="organization")
public class Organization {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(
            name = "system-uuid", strategy = "uuid"
    )
    @Column(name="org_id")
    private String orgId;

    @Column(name = "org_name")
    private String orgName;

    @OneToMany(mappedBy = "organization")
    //@JoinColumn(name = "org_id")
    private List<CountryGroup> countries;

    public Organization() {
    }c

    public Organization(String orgName, List<CountryGroup> countries) {
        this.orgName = orgName;
        this.countries = countries;

    }

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

    public List<CountryGroup> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryGroup> countries) {
        this.countries = countries;
    }
}
