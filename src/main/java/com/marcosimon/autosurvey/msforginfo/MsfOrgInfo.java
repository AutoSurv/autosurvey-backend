package com.marcosimon.autosurvey.msforginfo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.allowanceInKindInfo.AllowanceInKindInfo;
import com.marcosimon.autosurvey.allowanceinfo.AllowanceInfo;
import com.marcosimon.autosurvey.allowancepercentinfo.AllowancePercentInfo;
import com.marcosimon.autosurvey.contactinfo.ContactInfo;
import com.marcosimon.autosurvey.benchmarkinfo.BenchmarkInfo;
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
  @Column(name = "Org_Id")
  private Long orgId;

  @Column(name = "Data_Collection_Date")
  private String dataCollectionDate;

  @Column(name = "Org_Short_Name")
  private String orgShortName;

  @Column(name = "Org_Full_Name")
  private String orgFullName;

  @Column(name = "Org_Type")
  private String orgType;

  @Column(name = "Main_Office_Location")
  private String mainOfficeLocation;

  @Column(name = "Project_Location_1")
  private String projectLocation1;

  @Column(name = "Project_Location_2")
  private String projectLocation2;

  @Column(name = "Project_Location_3")
  private String projectLocation3;

  @Column(name = "Project_Location_4")
  private String projectLocation4;

  @Column(name = "Contact_First_Name")
  private String contactFirstName;

  @Column(name = "Contact_Last_Name")
  private String contactLastName;

  @Column(name = "Contact_Job_Title")
  private String contactJobTitle;

  @Column(name = "Contact_Phone_Country_Code")
  private Integer contactPhoneCountryCode;

  @Column(name = "Contact_Phone_Number")
  private Double contactPhoneNumber;

  @Column(name = "Contact_Email")
  private String contactEmail;

  @Column(name = "Org_Consent")
  private Boolean orgConsent;

  @Column(name = "Activities_Healthcare")
  private Boolean activitiesHealthcare;

  @Column(name = "Activities_Livelihood")
  private Boolean activitiesLivelihood;

  @Column(name = "Activities_Women_Youth")
  private Boolean activitiesWomenYouth;

  @Column(name = "Activities_Education")
  private Boolean activitiesEducation;

  @Column(name = "Activities_WASH")
  private Boolean activitiesWASH;

  @Column(name = "Activities_Refugees")
  private Boolean activitiesRefugees;

  @Column(name = "Activities_ER")
  private Boolean activitiesEr;

  @Column(name = "Activities_Protection")
  private Boolean activitiesProtection;

  @Column(name = "Activities_Advocacy_Rights")
  private Boolean activitiesAdvocacyRights;

  @Column(name = "Activities_Other")
  private String activitiesOther;

  @Column(name = "SS_Same_All_Locations")
  private Boolean salaryScaleSameAllLocations;

  @Column(name = "Allowances_Same_All_Locations")
  private Boolean allowancesSameAllLocations;

  @Column(name = "SS_currency_denominated")
  private String salaryScaleDenominated;

  @Column(name = "SS_currency_paid")
  private String salaryScalePaid;

  @Column(name = "Org_XR")
  private Float exchangeRate;

  @Column(name = "Org_XR_Date")
  private String exchangeRateDate;

  @Column(name = "Org_XR_Source")
  private String exchangeRateSource;

  @Column(name = "Org_XR_Source")
  private String exchangeRateSourceOther;

  @Column(name = "Contract_Type_1mo")
  private Boolean contractType1Month;

  @Column(name = "Contract_Type_3mo")
  private Boolean contractType3Month;

  @Column(name = "Contract_Type_6mo")
  private Boolean contractType6Month;

  @Column(name = "Contract_Type_1yr")
  private Boolean contractType1Year;

  @Column(name = "Contract_Type_2yr")
  private Boolean contractType2yr;

  @Column(name = "Contract_Type_3yr")
  private Boolean contractType3yr;

  @Column(name = "Contract_Type_Indefinite")
  private Boolean contractTypeIndefinite;

  @Column(name = "Contract_Type_Other")
  private Boolean contractTypeOther;

  @Column(name = "Remote_Work")
  private Boolean remoteWork;

  @Column(name = "Challenge_Remuneration")
  private Boolean challengeRemuneration;

  @Column(name = "Challenge_COL")
  private Boolean challengeCol;

  @Column(name = "Challenge_Benefit")
  private Boolean challengeBenefit;

  @Column(name = "Challenge_Recruitment_All")
  private Boolean challengeRecruitmentAll;

  @Column(name = "Challenge_Recruitment_Specialist")
  private Boolean challengeRecruitmentSpecialist;

  @Column(name = "Challenge_Recruitment_Mgmt")
  private Boolean challengeRecruitmentManagement;

  @Column(name = "Challenge_Retention")
  private Boolean challengeRetention;

  @Column(name = "Challenge_Travel_Policy")
  private Boolean challengeTravelPolicy;

  @Column(name = "Challenge_Rural_High_Risk")
  private Boolean challengeRuralHighRisk;

  @Column(name = "Challenge_Other")
  private Boolean challengeOther;

  @Column(name = "Challenge_Detail")
  private Boolean challengeTravelDetail;

  @Column(name = "Intl_Staff")
  private Boolean internationalStaff;

  @Column(name = "Intl_Staff_Num")
  private Integer internationalStaffNum;

  @Column(name = "Intl_Staff_Intl_Contract")
  private Integer internationalStaffInternationalContract;

  @Column(name = "Intl_Staff_Local_Pkg")
  private Integer internationalStaffLocalPackages;

  @Column(name = "Intl_Staff_Local_Contract")
  private String internationalStaffLocalContract;

  @Column(name = "Pay_Policy_Intl_Staff")
  private String internationalStaffPayPolicy;

  @Column(name = "Local_Staff")
  private Boolean localStaff;

  @Column(name = "Local_Staff_Full_Time_Num")
  private Integer localStaffFullTimeNum;

  @Column(name = "Local_Staff_Part_Time_Num")
  private Integer localStaffPartTimeNum;

  @Column(name = "CBA")
  private Boolean collectiveBargainingAgreement;

  @Column(name = "CBA_Name")
  private String cbaName;

  @Column(name = "interns")
  private Boolean interns;

  @Column(name = "Interns_Paid")
  private Integer internsPaid;

  @Column(name = "Interns_Unpaid")
  private Integer internsUnpaid;

  @Column(name = "Interns_Paid_Salary")
  private Double internsPaidSalary;

  @Column(name = "Interns_Currency")
  private String internsCurrency;

  @Column(name = "Volunteers")
  private Boolean volunteers;

  @Column(name = "Volunteers_Num")
  private Integer volunteersNum;

  @Column(name = "Incentives")
  private Boolean incentives;

  @Column(name = "Incentives_Currency")
  private String incentivesCurrency;

  @Column(name = "Incentives_Num")
  private Integer incentivesNum;

  @Column(name = "Incentives_MoH")
  private Integer incentiveMoH;

  @Column(name = "Incentives_MoH_Rate")
  private Float incentivesMoHRate;

  @Column(name = "Incentives_MoFA")
  private Integer incentiveMoFA;

  @Column(name = "Incentives_MoFA_Rate")
  private Float incentivesMoFARate;

  @Column(name = "Incentives_MoF")
  private Integer incentiveMoF;

  @Column(name = "Incentives_MoF_Rate")
  private Float incentivesMoFRate;

  @Column(name = "Incentives_Other_Govt")
  private Integer incentiveOtherGovt;

  @Column(name = "Incentives_Other_Govt_Rate")
  private Float incentivesOtherGovtRate;

  @Column(name = "Incentives_Other")
  private Integer incentiveOther;

  @Column(name = "Incentives_Other_Rate")
  private Float incentivesOtherRate;

  @Column(name = "Daily_Workers")
  private Boolean dailyWorkers;

  @Column(name = "Daily_Workers_Num")
  private Float dailyWorkersNum;



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

  @JsonIgnore
  @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
  @PrimaryKeyJoinColumn
  private AllowanceInKindInfo allowanceInKind;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "currency_info_id")
  private CurrencyInfo currencyInfo;

  @JsonBackReference
  @ManyToOne
  @OnDelete(action= OnDeleteAction.CASCADE)
  @JoinColumn(name = "country_info_id", nullable = false)
  private BenchmarkInfo benchmarkInfo;

  @JsonIgnore
  @OneToMany(mappedBy = "functionSalaryInfoId", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<FunctionSalaryInfo> functionSalaryInfoList;

}
