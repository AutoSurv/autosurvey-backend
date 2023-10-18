package com.marcosimon.autosurvey.allowanceInKindInfo;

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
@Table(name = "allowance_InKind_info")
public class AllowanceInKindInfo {
    @Id
    @Column(name = "allowance_inKind_info_id")
    private Long allowanceInKindInfoId;

    @JsonIgnore
    @MapsId
    @OneToOne(mappedBy = "allowanceInKind")
    @JoinColumn(name = "allowance_inKind_info_id")
    private MsfOrgInfo msfOrgInfo;

    @Column(name ="transportation_allowance_inKind")
    private String transportationAllowanceInKind;

    @Column(name ="housing_allowance_inKind")
    private String housingAllowanceInKind;

    @Column(name ="cost_of_living_allowance_inKind")
    private String colAllowanceInKind;

    @Column(name ="communication_allowance_inKind")
    private String communicationAllowanceInKind;

    @Column(name ="food_allowance_inKind")
    private String foodAllowanceInKind;

    @Column(name ="holiday_allowance_inKind")
    private String holidayAllowanceInKind;

    @Column(name ="religious_allowance_inKind")
    private String religiousAllowanceInKind;

    @Column(name ="end_of_year_allowance_inKind")
    private String endOfYearAllowanceInKind;

    @Column(name ="medical_allowance_inKind")
    private String medicalAllowanceInKind;

    @Column(name ="family_allowance_inKind")
    private String familyAllowanceInKind;

    @Column(name ="education_allowance_inKind")
    private String educationAllowanceInKind;

    @Column(name ="hardship_allowance_inKind")
    private String hardshipAllowanceInKind;

    @Column(name ="danger_allowance_inKind")
    private String dangerAllowanceInKind;

    @Column(name ="location_allowance_inKind")
    private String locationAllowanceInKind;

    @Column(name ="other_allowance_inKind")
    private String otherAllowanceInKind;

    @Column(name ="total_allowance_inKind")
    private String totalAllowanceInKind;
}
