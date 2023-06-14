package com.marcosimon.autosurvey.organization;
import com.marcosimon.autosurvey.autosurvey.AutoSurvey;
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

    //@Column(name = "country")
    //private String country;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    private List<AutoSurvey> autoSurveys;

    public Organization() {
    }

    public Organization(String orgId, String orgName, List<AutoSurvey> autoSurveys) {
        this.orgId = orgId;
        this.orgName = orgName;
        //this.country = country;
        this.autoSurveys = autoSurveys;
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
    /*
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    */
    public List<AutoSurvey> getAutoSurveys() {
        return autoSurveys;
    }

    public void setAutoSurveys(List<AutoSurvey> autoSurveys) {
        this.autoSurveys = autoSurveys;
    }
}
