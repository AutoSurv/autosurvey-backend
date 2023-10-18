package com.marcosimon.autosurvey.allowancepercentinfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "allowance_percent_info")
public class AllowancePercentInfo {
    @Id
    @Column(name = "allowance_percent_info_id")
    private Long allowancePercentInfoId;

    @JsonIgnore
    @MapsId
    @OneToOne(mappedBy = "allowancePercent")
    @JoinColumn(name = "allowance_percent_Info_Id")
    private MsfOrgInfo msfOrgInfo;

    @Column(name ="transportation_allowance_percent")
    private Float transportationAllowancePercent;

    @Column(name ="housing_allowance_percent")
    private Float housingAllowancePercent;

    @Column(name ="cost_of_living_allowance_percent")
    private Float colAllowancePercent;

    @Column(name ="communication_allowance_percent")
    private Float communicationAllowancePercent;

    @Column(name ="food_allowance_percent")
    private Float foodAllowancePercent;

    @Column(name ="holiday_allowance_percent")
    private Float holidayAllowancePercent;

    @Column(name ="religious_allowance_percent")
    private Float religiousAllowancePercent;

    @Column(name ="end_of_year_allowance_percent")
    private Float endOfYearAllowancePercent;

    @Column(name ="medical_allowance_percent")
    private Float medicalAllowancePercent;

    @Column(name ="family_allowance_percent")
    private Float familyAllowancePercent;

    @Column(name ="education_allowance_percent")
    private Float educationAllowancePercent;

    @Column(name ="hardship_allowance_percent")
    private Float hardshipAllowancePercent;

    @Column(name ="danger_allowance_percent")
    private Float dangerAllowancePercent;

    @Column(name ="location_allowance_percent")
    private Float locationAllowancePercent;

    @Column(name ="other_allowance_percent")
    private Float otherAllowancePercent;

    @Column(name ="total_allowance_percent")
    private Float totalAllowancePercent;

}
