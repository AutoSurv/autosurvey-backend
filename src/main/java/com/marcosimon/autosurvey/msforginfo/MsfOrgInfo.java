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

  @Column(name = "Working_hours_per_contract")
  private Float workingHoursPerContract;

  @Column(name = "Different_WH_per_Staff_Group")
  private Boolean differentWorkingHoursPerStaffGroup;

  @Column(name = "WH_Medical")
  private Float workingHoursMedical;

  @Column(name = "WH_Driver")
  private Float workingHoursDriver;

  @Column(name = "WH_Guard")
  private Float workingHoursGuard;

  @Column(name = "WH_Other")
  private Float workingHoursOther;

  @Column(name = "WH_Comments")
  private String workingHoursComments;

  @Column(name = "Installments")
  private Integer installments;

  @Column(name = "Installments_BS_Only")
  private String installmentBSOnly;

  @Column(name = "Installments_Comments")
  private String installmentComments;

  @Column(name = "Mobility_Policy")
  private Boolean mobilityPolicy;

  @Column(name = "Mobility_Policy_Currency")
  private String mobilityPolicyCurrency;

  @Column(name = "Mobility_Accomm_Wks")
  private Integer mobilityAccommodationWeeks;

  @Column(name = "Mobility_Accomm_Reimb")
  private Float mobilityAccommodationReimbursed;

  @Column(name = "Mobility_Transport_Direct")
  private String mobilityTransportDirect;

  @Column(name = "Mobility_Allowance")
  private Float mobilityAllowance;

  @Column(name = "Mobility_Lump_Sum")
  private Float mobilityLumpSum;

  @Column(name = "Mobility_Addl_Leave_Days")
  private Integer mobilityAdditionalLeaveDays;

  @Column(name = "Mobility_Home_Trips")
  private Integer mobilityHomeTrips;

  @Column(name = "Mobility_Other")
  private String mobilityOther;

  @Column(name = "Travel_Perdiem_Allowance")
  private Boolean travelPerdiemAllowance;

  @Column(name = "Travel_Transport_Type")
  private String travelTransportType;

  @Column(name = "Travel_Accomm_Type")
  private String travelAccommodationType;

  @Column(name = "Travel_Meals_Type")
  private String travelMealsType;

  @Column(name = "Travel_PerDiem_Currency")
  private String travelPerdiemCurrency;

  @Column(name = "Travel_Location_1")
  private String travelLocation1;

  @Column(name = "Travel_Overnight_Breakfast_1")
  private Float  travelOvernightBreakfast1;

  @Column(name = "Travel_Overnight_Lunch_1")
  private Float  travelOvernightLunch1;

  @Column(name = "Travel_Overnight_Dinner_1")
  private Float  travelOvernightDinner1;

  @Column(name = "Travel_Overnight_Incidentals_1")
  private Float  travelOvernightIncidentals1;

  @Column(name = "Travel_Overnight_Transport_1")
  private Float  travelOvernightTransport1;

  @Column(name = "Travel_Overnight_Hotel_1")
  private Float  travelOvernightHotel1;

  @Column(name = "Travel_Overnight_Total_1")
  private Float  travelOvernightTotal1;

  @Column(name = "Travel_Location_1")
  private String travelLocation2;

  @Column(name = "Travel_Overnight_Breakfast_2")
  private Float  travelOvernightBreakfast2;

  @Column(name = "Travel_Overnight_Lunch_2")
  private Float  travelOvernightLunch2;

  @Column(name = "Travel_Overnight_Dinner_2")
  private Float  travelOvernightDinner2;

  @Column(name = "Travel_Overnight_Incidentals_2")
  private Float  travelOvernightIncidentals2;

  @Column(name = "Travel_Overnight_Transport_2")
  private Float  travelOvernightTransport2;

  @Column(name = "Travel_Overnight_Hotel_2")
  private Float  travelOvernightHotel2;

  @Column(name = "Travel_Overnight_Total_2")
  private Float  travelOvernightTotal2;

  @Column(name = "Travel_Location_3")
  private String travelLocation3;

  @Column(name = "Travel_Overnight_Breakfast_3")
  private Float  travelOvernightBreakfast3;

  @Column(name = "Travel_Overnight_Lunch_3")
  private Float  travelOvernightLunch3;

  @Column(name = "Travel_Overnight_Dinner_3")
  private Float  travelOvernightDinner3;

  @Column(name = "Travel_Overnight_Incidentals_3")
  private Float  travelOvernightIncidentals3;

  @Column(name = "Travel_Overnight_Transport_3")
  private Float  travelOvernightTransport3;

  @Column(name = "Travel_Overnight_Hotel_3")
  private Float  travelOvernightHotel3;

  @Column(name = "Travel_Overnight_Total_3")
  private Float  travelOvernightTotal3;

  @Column(name = "Soc_Sec_Employer_Perc")
  private Float  socialSecurityEmployerPercent;

  @Column(name = "Soc_Sec_Employer_Amt")
  private Float  socialSecurityEmployerAmount;

  @Column(name = "Soc_Sec_Employer_Brackets")
  private Boolean  socialSecurityEmployerBrackets;

  @Column(name = "Soc_Sec_Employer_Exempt")
  private Boolean  socialSecurityEmployerExempt;

  @Column(name = "Soc_Sec_Employee_Perc")
  private Float  socialSecurityEmployeePercent;

  @Column(name = "Soc_Sec_Employee_Amt")
  private Float  socialSecurityEmployeeAmount;

  @Column(name = "Soc_Sec_Employee_Brackets")
  private Boolean  socialSecurityEmployeeBrackets;

  @Column(name = "Soc_Sec_Employee_Exempt")
  private Boolean  socialSecurityEmployeeExempt;

  @Column(name = "Soc_Sec_Other_Employer_Perc")
  private Float  socialSecurityOtherEmployerPercent;

  @Column(name = "Soc_Sec_Other_Employer_Amt")
  private Float  socialSecurityOtherEmployerAmount;

  @Column(name = "Soc_Sec_Other_Employer_Brackets")
  private Boolean  socialSecurityOtherEmployerBrackets;

  @Column(name = "Soc_Sec_Other_Employer_Exempt")
  private Boolean  socialSecurityOtherEmployerExempt;

  @Column(name = "Soc_Sec_Other_Employee_Perc")
  private Float  socialSecurityOtherEmployeePercent;

  @Column(name = "Soc_Sec_Other_Employee_Amt")
  private Float  socialSecurityOtherEmployeeAmount;

  @Column(name = "Soc_Sec_Other_Employee_Brackets")
  private Boolean  socialSecurityOtherEmployeeBrackets;

  @Column(name = "Soc_Sec_Other_Employee_Exempt")
  private Boolean  socialSecurityOtherEmployeeExempt;

  @Column(name = "Inc_Tax_Employer_Perc")
  private Float  incomeTaxEmployerPercent;

  @Column(name = "Inc_Tax_Employer_Amt")
  private Float  incomeTaxEmployerAmount;

  @Column(name = "Inc_Tax_Employer_Brackets")
  private Boolean  incomeTaxEmployerBrackets;

  @Column(name = "Inc_Tax_Employer_Exempt")
  private Boolean  incomeTaxEmployerExempt;

  @Column(name = "Inc_Tax_Employee_Perc")
  private Float  incomeTaxEmployeePercent;

  @Column(name = "Inc_Tax_Employee_Amt")
  private Float  incomeTaxEmployeeAmount;

  @Column(name = "Inc_Tax_Employee_Brackets")
  private Boolean  incomeTaxEmployeeBrackets;

  @Column(name = "Inc_Tax_Employee_Exempt")
  private Boolean  incomeTaxEmployeeExempt;

  @Column(name = "Non-Tax_Allowance_1")
  private String  nonTaxAllowance1;

  @Column(name = "Non-Tax_Allowance_2")
  private String  nonTaxAllowance2;

  @Column(name = "Non-Tax_Allowance_3")
  private String  nonTaxAllowance3;

  @Column(name = "Non-Tax_Allowance_4")
  private String  nonTaxAllowance4;

  @Column(name = "Non-Tax_Allowance_5")
  private String  nonTaxAllowance5;

  @Column(name = "Private_Pension")
  private Boolean  privatePension;

  @Column(name = "Private_Pension_Type")
  private String  privatePensionType;

  @Column(name = "Private_Pension_All_Staff")
  private String  privatePensionAllStaff;

  @Column(name = "Private_Pension_Perc_Employer")
  private Float  privatePensionPercentEmployer;

  @Column(name = "Private_Pension_Perc_Employee")
  private Float  privatePensionPercentEmployee;

  @Column(name = "Private_Pension_DIR")
  private Float  privatePensionDirector;

  @Column(name = "Private_Pension_MGR")
  private Float  privatePensionManager;

  @Column(name = "Private_Pension_SUP")
  private Float  privatePensionSupervisor;

  @Column(name = "Private_Pension_SKL")
  private Float  privatePensionSkilled;

  @Column(name = "Private_Pension_BSC")
  private Float  privatePensionBasicSkilled;

  @Column(name = "Life_Ins")
  private Boolean  lifeInsurance;

  @Column(name = "Life_Ins_Company")
  private String  lifeInsuranceCompany;

  @Column(name = "Life_Ins_Payout")
  private Float  lifeInsurancePayout;

  @Column(name = "EOC_Pmt_Normal_EOC")
  private Integer  endOfContractPayNormal;

  @Column(name = "EOC_Pmt_Redundancy")
  private Integer  endOfContractPayRedundancy;

  @Column(name = "EOC_Pmt_Normal_Retirement")
  private Integer  endOfContractPayRetirement;

  @Column(name = "EOC_Pmt_Normal_Dismissal")
  private Integer  endOfContractPayDismissal;

  @Column(name = "OT_Policy_Type")
  private String  overtimePolicyType;

  @Column(name = "OT_Monetary_Wkday")
  private Float  overtimeMonetaryWeekday;

  @Column(name = "OT_Monetary_Wkend")
  private Float  overtimeMonetaryWeekend;

  @Column(name = "OT_Monetary_PH")
  private Float  overtimeMonetaryPublicHoliday;

  @Column(name = "OT_Time_Comp_Wkday")
  private Float  overtimeTimeWeekday;

  @Column(name = "OT_Time_Comp_Wkend")
  private Float  overtimeTimeWeekend;

  @Column(name = "OT_Time_Comp_PH")
  private Float  overtimeTimePublicHoliday;

  @Column(name = "Salary_Bands")
  private Boolean  salaryBands;

  @Column(name = "Salary_Bands_Recruit_Lvl")
  private String  salaryBandsRecruitLevel;

  @Column(name = "Salary_Bands_Mvt_Systematic")
  private String  salaryBandsSystematic;

  @Column(name = "Seniority_Increase")
  private Boolean  seniorityIncrease;

  @Column(name = "Seniority_Increase_Amt")
  private Float  seniorityIncreaseAmount;

  @Column(name = "Seniority_Increase_Perc")
  private Float  seniorityIncreasePercent;

  @Column(name = "Seniority_Increase_Cap")
  private Float  seniorityIncreaseCap;

  @Column(name = "Perf_Increases")
  private Boolean  performanceIncrease;

  @Column(name = "Perf_Inc_Ind_Performance")
  private Boolean  performanceIncreaseIndividual;

  @Column(name = "Perf_Inc_Org_Performance")
  private Boolean  performanceIncreaseOrganisation;

  @Column(name = "Perf_Inc_Org_Funding")
  private Boolean  performanceIncreaseFunding;

  @Column(name = "Perf_Inc_Service_Length")
  private Boolean  performanceIncreaseServiceLength;

  @Column(name = "Perf_Inc_Job_Level")
  private Boolean  performanceIncreaseJobLevel;

  @Column(name = "Perf_Inc_Salary_Range_Position")
  private Boolean  performanceIncreaseRangePosition;

  @Column(name = "Perf_Inc_COLA")
  private Boolean  performanceIncreaseCola;

  @Column(name = "Perf_Inc_Other")
  private Boolean  performanceIncreaseOther;

  @Column(name = "Performance_Lower")
  private Float  performanceLower;

  @Column(name = "Performance_Mid")
  private Float  performanceMid;

  @Column(name = "Performance_Upper")
  private Float  performanceUpper;

  @Column(name = "Other_Bonus_Education")
  private Float  otherBonusEducation;

  @Column(name = "Other_Bonus_Funeral")
  private Float  otherBonusFuneral;

  @Column(name = "Other_Bonus_Wedding")
  private Float  otherBonusWedding;

  @Column(name = "Other_Bonus_Birthday")
  private Float  otherBonusBirthday;

  @Column(name = "Other_Bonus_Driving")
  private Float  otherBonusDriving;

  @Column(name = "Other_Bonus_Other")
  private Float  otherBonusOther;



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
