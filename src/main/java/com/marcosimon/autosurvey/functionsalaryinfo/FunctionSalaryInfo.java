package com.marcosimon.autosurvey.functionsalaryinfo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.functioninfo.FunctionInfo;
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
@Table(name = "function_salary_info")
public class FunctionSalaryInfo {
  @Id
  //@GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "Benchmark_Line_ID")
  private String benchmarkLineId;

  @Column(name = "Function_ID")
  private String orgFunctionId;

  @Column(name = "Function_Name")
  private String orgFunction;

  @Column(name = "Min_Basic_Salary")
  private Float minBasicSalary;

  @Column(name = "Max_Basic_Salary")
  private Float maxBasicSalary;

  @Column(name = "Funct_Allowance_Perc")
  private Float functionAllowancePercent;

  @Column(name = "Funct_Allowance_Amt")
  private Float functionAllowanceAmount;

  @Column(name = "Funct_Allowance_InKind")
  private String functionAllowanceInKind;

  @Column(name = "Funct_Allowance_Other")
  private String functionAllowanceOther;

  @Column(name = "Min_TGC_Unadjusted_WH")
  private Float minTGCUnadjustedWH;

  @Column(name = "Max_TGC_Unadjusted_WH")
  private Float maxTGCUnadjustedWH;

  @Column(name = "Min_TGC_Adjusted_WH")
  private Float minTGCAdjustedWH;

  @Column(name = "Max_TGC_Adjusted_WH")
  private Float maxTGCAdjustedWH;

  //@JsonBackReference
  @JsonIgnore
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "Org_Id", nullable = true)
  private MsfOrgInfo msfOrgInfo;

  @JsonIgnore
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "function_info_id")
  private FunctionInfo functionInfo;


}
