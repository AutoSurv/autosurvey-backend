package com.marcosimon.autosurvey.allowanceinfo;

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
@Table(name = "allowance_info")
public class AllowanceInfo {
    @Id
    @Column(name = "allowance_info_id")
    private Long allowanceInfoId;

    @JsonIgnore
    @MapsId
    @OneToOne(mappedBy = "allowance")
    @JoinColumn(name = "allowance_info_id")
    private MsfOrgInfo msfOrgInfo;

    @Column(name ="transportation_allowance")
    private Integer transportationAllowance;

    @Column(name ="housing_allowance")
    private Integer housingAllowance;

    @Column(name ="cost_of_living_allowance")
    private Integer colAllowance;

    @Column(name ="communication_allowance")
    private Integer communicationAllowance;

    @Column(name ="food_allowance")
    private Integer foodAllowance;

    @Column(name ="holiday_allowance")
    private Integer holidayAllowance;

    @Column(name ="religious_allowance")
    private Integer religiousAllowance;

    @Column(name ="end_of_year_allowance")
    private Integer endOfYearAllowance;

    @Column(name ="medical_allowance")
    private Integer medicalAllowance;

    @Column(name ="family_allowance")
    private Integer familyAllowance;

    @Column(name ="education_allowance")
    private Integer educationAllowance;

    @Column(name ="hardship_allowance")
    private Integer hardshipAllowance;

    @Column(name ="danger_allowance")
    private Integer dangerAllowance;

    @Column(name ="location_allowance")
    private Integer locationAllowance;

    @Column(name ="other_allowance")
    private Integer otherAllowance;

    @Column(name ="total_allowance")
    private Integer totalAllowance;


}
