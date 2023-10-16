package com.marcosimon.autosurvey.msforginfo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.allowanceinfo.AllowanceInfo;
import com.marcosimon.autosurvey.allowancepercentinfo.AllowancePercentInfo;
import com.marcosimon.autosurvey.contactinfo.ContactInfo;
import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.currencyinfo.CurrencyInfo;
import com.marcosimon.autosurvey.functionsalaryinfo.FunctionSalaryInfo;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "org_info")
public class MsfOrgInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "org_id")
  private Long orgId;

  @Column(name = "org_full_name")
  private String orgFullName;

  @Column(name = "org_name")
  private String orgName;

  @Column(name = "working_hours")
  private Integer workingHours;

  @Column(name = "thirteenth_salary ")
  private Integer thirteenthSalary;

  @Column(name = "currency_in_use")
  private String currencyInUse;

  @JsonIgnore
  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
  @PrimaryKeyJoinColumn
  private ContactInfo contact;

  @JsonIgnore
  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
  @PrimaryKeyJoinColumn
  private AllowanceInfo allowance;

  @JsonIgnore
  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
  @PrimaryKeyJoinColumn
  private AllowancePercentInfo allowancePercent;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "currency_info_id")
  private CurrencyInfo currencyInfo;

  @JsonBackReference
  @ManyToOne
  @OnDelete(action= OnDeleteAction.CASCADE)
  @JoinColumn(name = "country_info_id", nullable = false)
  private CountryInfo countryInfo;

  @JsonIgnore
  @OneToMany(mappedBy = "functionSalaryInfoId", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<FunctionSalaryInfo> functionSalaryInfoList;

  public MsfOrgInfo(String orgFullName, String orgName, Integer workingHours, Integer thirteenthSalary, String currencyInUse, CountryInfo countryInfo) {
    this.orgFullName = orgFullName;
    this.orgName = orgName;
    this.workingHours = workingHours;
    this.thirteenthSalary = thirteenthSalary;
    this.currencyInUse = currencyInUse;
    this.countryInfo = countryInfo;
  }
}
