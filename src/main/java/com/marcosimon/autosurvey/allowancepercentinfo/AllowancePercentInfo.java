package com.marcosimon.autosurvey.allowancepercentinfo;

import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "allowance_percent_info_id")
    private String allowancePercentInfoId;

    @MapsId
    @OneToOne(mappedBy = "allowancePercent")
    @JoinColumn(name = "allowance_percent_Info_Id")
    private MsfOrgInfo msfOrgInfo;

    @Column(name ="cost_of_living_allowance_percent")
    private Float colAllowancePercent;

    @Column(name ="transportation_allowance_percent")
    private Float transportationAllowancePercent;

    @Column(name ="housing_allowance_percent")
    private Float housingAllowancePercent;

    @Column(name ="other_allowance_percent")
    private Float otherAllowancePercent;

    @Column(name ="total_allowance_percent")
    private Float totalAllowancePercent;

    public AllowancePercentInfo(Float colAllowancePercent, Float transportationAllowancePercent, Float housingAllowancePercent, Float otherAllowancePercent, Float totalAllowancePercent) {
        this.colAllowancePercent = colAllowancePercent;
        this.transportationAllowancePercent = transportationAllowancePercent;
        this.housingAllowancePercent = housingAllowancePercent;
        this.otherAllowancePercent = otherAllowancePercent;
        this.totalAllowancePercent = totalAllowancePercent;
    }

    public AllowancePercentInfo(String allowancePercentInfoId, Float colAllowancePercent, Float transportationAllowancePercent, Float housingAllowancePercent, Float otherAllowancePercent, Float totalAllowancePercent) {
        this.allowancePercentInfoId = allowancePercentInfoId;
        this.colAllowancePercent = colAllowancePercent;
        this.transportationAllowancePercent = transportationAllowancePercent;
        this.housingAllowancePercent = housingAllowancePercent;
        this.otherAllowancePercent = otherAllowancePercent;
        this.totalAllowancePercent = totalAllowancePercent;
    }
}
