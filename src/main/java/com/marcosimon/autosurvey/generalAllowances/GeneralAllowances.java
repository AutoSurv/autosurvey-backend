package com.marcosimon.autosurvey.generalAllowances;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "General_Allowances")
public class GeneralAllowances {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "General_Allowance_Id")
    private String generalAllowanceId;

    @Column(name ="Org_Name")
    private String orgName;

    @Column(name ="Allowance_Type")
    private String allowanceType;

    @Column(name ="Value")
    private String value;

    @Column(name ="Pay_Type")
    private String payType;

    @JsonBackReference
    @ManyToOne
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JoinColumn(name = "Org_Id", nullable = false)
    private MsfOrgInfo msfOrgInfo;

}
